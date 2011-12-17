package rpg.hydra;

import rpg.hydra.characters.Link;
import rpg.hydra.utility.Actions;
import rpg.hydra.utility.Directions;
import rpg.hydra.utility.Drawable;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class HydraSurface extends SurfaceView implements SurfaceHolder.Callback {

	private static final String TAG = HydraSurface.class.getSimpleName();
	
	private HydraEngine thread;
	private ObjectManager objectmanager;
	private Action action;

	public HydraSurface(Context context) {
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
			action = objectmanager.getTouchinput().processTouch((int)event.getX(), (int)event.getY());
		}
		return true;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			action = objectmanager.getKeyinput().processKeyEvent(keyCode);	
		}
		return true;
	}

	public void render(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		for (Drawable object : objectmanager.getDrawable()) {
			object.draw(canvas);
		}
	}

	public void update() {
		if (action != null) {
			// decode and execute action
			if (action.getAction() == Actions.MOVE) {
				Link link = objectmanager.getLink();
				if (action.getDirection() == Directions.TOP) {
					link.moveUp();
				}
				else if (action.getDirection() == Directions.RIGHT) {
					link.moveRight();
				}
				else if (action.getDirection() == Directions.BOTTOM) {
					link.moveDown();
				}
				else if (action.getDirection() == Directions.LEFT) {
					link.moveLeft();
				}
			}
			if (action.getAction() == Actions.CONSOLE) {
				QuestConsole console = objectmanager.getConsole();
				if (action.getDirection() == Directions.TOP) {
					console.showPrevious();
				}
				else if (action.getDirection() == Directions.BOTTOM) {
					console.showNext();
				}
			}
			action = null;
		}
		// do updates that don't depend on user input
	}

}
