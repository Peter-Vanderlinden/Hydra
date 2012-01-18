package rpg.hydra.input;

import android.view.KeyEvent;
import rpg.hydra.utility.Actions;
import rpg.hydra.utility.Command;
import rpg.hydra.utility.Directions;

public class HydraKeyInput {
	
	public Command processKeyEvent(int keyCode) {
		Command command = null;
		if (keyCode == KeyEvent.KEYCODE_W) {
			command = new Command(Actions.MOVE, Directions.UP);
		}
		else if (keyCode == KeyEvent.KEYCODE_S) {
			command = new Command(Actions.MOVE, Directions.DOWN);
		}
		else if (keyCode == KeyEvent.KEYCODE_A) {
			command = new Command(Actions.MOVE, Directions.LEFT);
		}
		else if (keyCode == KeyEvent.KEYCODE_D) {
			command = new Command(Actions.MOVE, Directions.RIGHT);
		}
		return command;
	}

}
