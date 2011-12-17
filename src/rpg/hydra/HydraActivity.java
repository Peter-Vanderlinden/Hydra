package rpg.hydra;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class HydraActivity extends Activity {
	
	private static final String TAG = HydraActivity.class.getSimpleName();
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // requesting to turn the title OFF
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // making it full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // set our MainGameView as the View
    	try {
			//code that crashes
    		setContentView(new HydraSurface(this));
		}
		catch (Exception e) {
			// this is the line of code that sends a real error message to the log
			Log.e("ERROR", "ERROR IN CODE: " + e.toString());
	 
			// this is the line that prints out the location in
			// the code where the error occurred.
			e.printStackTrace();
		}      
        Log.d(TAG, "View added");
    }
    
    @Override
	protected void onDestroy() {
		Log.d(TAG, "Destroying...");
		super.onDestroy();
	}

	@Override
	protected void onStop() {
		Log.d(TAG, "Stopping...");
		super.onStop();
	}
	
//	try {
//		//code that crashes
//	}
//	catch (Exception e) {
//		// this is the line of code that sends a real error message to the log
//		Log.e("ERROR", "ERROR IN CODE: " + e.toString());
// 
//		// this is the line that prints out the location in
//		// the code where the error occurred.
//		e.printStackTrace();
//	}
	
}