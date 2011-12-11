package rpg.hydra;

import rpg.hydra.background.Background;
import rpg.hydra.characters.Link;
import rpg.hydra.utility.Actions;
import rpg.hydra.utility.Directions;
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
	//private Droid droid;
	private Background bg;
	private Link link;
	private HydraTouchInput touchinput;

	public HydraSurface(Context context) {
		super(context);
		// adding the callback (this) to the surface holder to intercept events
		getHolder().addCallback(this);

		// create droid and load bitmap
		//droid = new Droid(BitmapFactory.decodeResource(getResources(), R.drawable.droid_1), 50, 50);
		
		bg = new Background(this);
		link = new Link(this, bg);
		touchinput = new HydraTouchInput(link);
		
		// create the game loop thread
		thread = new HydraEngine(getHolder(), this);
		
		// make the GameView focusable so it can handle events
		setFocusable(true);
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
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
		try
		{
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				touchinput.processTouch((int)event.getX(), (int)event.getY());
			}
		}
		catch (Exception e)
		{
			// this is the line of code that sends a real error message to the log
			Log.e("ERROR", "ERROR IN CODE: " + e.toString());
	 
			// this is the line that prints out the location in
			// the code where the error occurred.
			e.printStackTrace();
		}
		
		return true;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.d(TAG, "in onKeyDown");
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			Log.d(TAG, "inside if(action_down)");
			if (keyCode == KeyEvent.KEYCODE_W) {
				Log.d(TAG, "inside if(keycode_w)");
				link.update(Actions.MOVE, Directions.TOP);
				Log.d(TAG, "Link going up");
			}
			else if (keyCode == KeyEvent.KEYCODE_S) {
				link.update(Actions.MOVE, Directions.BOTTOM);
			}
			else if (keyCode == KeyEvent.KEYCODE_A) {
				link.update(Actions.MOVE, Directions.LEFT);
			}
			else if (keyCode == KeyEvent.KEYCODE_D) {
				link.update(Actions.MOVE, Directions.RIGHT);
			}
			
		}
		return true;
	}

	public void render(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		bg.draw(canvas);
		touchinput.draw(canvas);
		//droid.draw(canvas);
		link.draw(canvas);
	}

	/**
	 * This is the game update method. It iterates through all the objects
	 * and calls their update method if they have one or calls specific
	 * engine's update method.
	 */
	public void update() {
//		// check collision with right wall if heading right
//		if (droid.getSpeed().getxDirection() == Speed.DIRECTION_RIGHT
//				&& droid.getX() + droid.getBitmap().getWidth() / 2 >= getWidth()) {
//			droid.getSpeed().toggleXDirection();
//		}
//		// check collision with left wall if heading left
//		if (droid.getSpeed().getxDirection() == Speed.DIRECTION_LEFT
//				&& droid.getX() - droid.getBitmap().getWidth() / 2 <= 0) {
//			droid.getSpeed().toggleXDirection();
//		}
//		// check collision with bottom wall if heading down
//		if (droid.getSpeed().getyDirection() == Speed.DIRECTION_DOWN
//				&& droid.getY() + droid.getBitmap().getHeight() / 2 >= getHeight()) {
//			droid.getSpeed().toggleYDirection();
//		}
//		// check collision with top wall if heading up
//		if (droid.getSpeed().getyDirection() == Speed.DIRECTION_UP
//				&& droid.getY() - droid.getBitmap().getHeight() / 2 <= 0) {
//			droid.getSpeed().toggleYDirection();
//		}
//		// Update the lone droid
//		droid.update();
	}

}
