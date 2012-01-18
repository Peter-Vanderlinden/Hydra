package rpg.hydra;

import rpg.hydra.characters.Link;
import rpg.hydra.items.Stone;
import rpg.hydra.utility.Actions;
import rpg.hydra.utility.Activatable;
import rpg.hydra.utility.Command;
import rpg.hydra.utility.Directions;
import rpg.hydra.utility.Drawable;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class HydraSurface extends SurfaceView implements SurfaceHolder.Callback {

	private static final String TAG = HydraSurface.class.getSimpleName();
	
	private HydraEngine thread;
	private ObjectManager objectmanager;
	private Command command;
	private HydraActivity activity;
	private boolean acceptsInput;
	private int counter;
	private long previousTime;

	public HydraSurface(Context context, HydraActivity activity) {
		super(context);
		// adding the callback (this) to the surface holder to intercept events
		getHolder().addCallback(this);
		
		// create and initialize objectmanager
		objectmanager = ObjectManager.getObjectManager();
		objectmanager.setSurface(this);
		objectmanager.initialize();
		
		// create the game loop thread
		thread = new HydraEngine(getHolder(), this);
		
		// make the GameView focusable so it can handle events
		setFocusable(true);
		
		this.activity = activity;
		acceptsInput = true;
		counter = 0;
		previousTime = 0;
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		
	}

	public void surfaceCreated(SurfaceHolder holder) {
		// at this point the surface is created and
		// we can safely start the game loop
		thread.setRunning(true);
		thread.start();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d(TAG, "Surface is being destroyed");
		// tell the thread to shut down and wait for it to finish
		// this is a clean shutdown
		boolean retry = true;
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
				// try again shutting down the thread
			}
		}
		Log.d(TAG, "Thread was shut down cleanly");
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			command = objectmanager.getTouchinput().processTouch((int)event.getX(), (int)event.getY());
		}
		return true;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			command = objectmanager.getKeyinput().processKeyEvent(keyCode);	
		}
		return true;
	}

	public void render(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		for (Drawable object : objectmanager.getDrawable()) {
			object.draw(canvas);
		}
		for (Stone stone : objectmanager.getStones()) {
			stone.draw(canvas);
		}
	}

	public void update() {
		if (command != null && acceptsInput) {
			// decode and execute command
			if (command.getAction() == Actions.MOVE) {
				Link link = objectmanager.getLink();
				if (command.getDirection() == Directions.UP) {
					link.moveUp();
				}
				else if (command.getDirection() == Directions.RIGHT) {
					link.moveRight();
				}
				else if (command.getDirection() == Directions.DOWN) {
					link.moveDown();
				}
				else if (command.getDirection() == Directions.LEFT) {
					link.moveLeft();
				}
			}
			else if (command.getAction() == Actions.CONSOLE) {
				QuestConsole console = objectmanager.getConsole();
				if (command.getDirection() == Directions.UP) {
					console.showPrevious();
				}
				else if (command.getDirection() == Directions.DOWN) {
					console.showNext();
				}
			}
			else if (command.getAction() == Actions.ACTIVATE) {
				Link link = objectmanager.getLink();
				for (Activatable object : objectmanager.getActivatable()) {
					
					boolean rightPositioning = link.getX() == object.getX() && link.getY() == object.getY()-1 && link.getFacingDirection() == Directions.DOWN;
					rightPositioning = rightPositioning || (link.getX() == object.getX()+1 && link.getY() == object.getY() && link.getFacingDirection() == Directions.LEFT);
					rightPositioning = rightPositioning || (link.getX() == object.getX() && link.getY() == object.getY()+1 && link.getFacingDirection() == Directions.UP);
					rightPositioning = rightPositioning || (link.getX() == object.getX()-1 && link.getY() == object.getY() && link.getFacingDirection() == Directions.RIGHT);

					if (object.isVisible() && rightPositioning) {
						object.activate();
					}
				}
			}
			else if (command.getAction() == Actions.FIRE) {
				long time = SystemClock.uptimeMillis();
				if (time - previousTime > 500) {
					objectmanager.getLink().fire();
					previousTime = time;
				}
			}
			command = null;
		}
		// do updates that don't depend on user input
		for (int i=0; i<objectmanager.getStones().size(); i++) {
			objectmanager.getStones().get(i).update();
		}
		objectmanager.getRobot().update();
		if (objectmanager.getRobot().getDead() || objectmanager.getLink().getDead()) {
			acceptsInput = false;
			counter++;
			if (counter >= 600) {
				thread.setRunning(false);
				activity.finish();
			}
		}
	}

}
