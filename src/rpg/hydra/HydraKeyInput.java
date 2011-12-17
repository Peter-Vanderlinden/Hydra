package rpg.hydra;

import android.view.KeyEvent;
import rpg.hydra.utility.Actions;
import rpg.hydra.utility.Directions;

public class HydraKeyInput {
	
	public Action processKeyEvent(int keyCode) {
		Action action = null;
		if (keyCode == KeyEvent.KEYCODE_W) {
			action = new Action(Actions.MOVE, Directions.TOP);
		}
		else if (keyCode == KeyEvent.KEYCODE_S) {
			action = new Action(Actions.MOVE, Directions.BOTTOM);
		}
		else if (keyCode == KeyEvent.KEYCODE_A) {
			action = new Action(Actions.MOVE, Directions.LEFT);
		}
		else if (keyCode == KeyEvent.KEYCODE_D) {
			action = new Action(Actions.MOVE, Directions.RIGHT);
		}
		return action;
	}

}
