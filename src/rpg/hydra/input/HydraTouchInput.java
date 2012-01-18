package rpg.hydra.input;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Color;
import rpg.hydra.ObjectManager;
import rpg.hydra.QuestConsole;
import rpg.hydra.utility.Actions;
import rpg.hydra.utility.Command;
import rpg.hydra.utility.Directions;
import rpg.hydra.utility.Drawable;

public class HydraTouchInput implements Drawable {
	
	private ArrayList<Button> buttons;
	
	public HydraTouchInput() {
		buttons = new ArrayList<Button>();
		
		int fourWayButtonX = 100;
		int fourWayButtonY = 490;
		buttons.add(new Button(fourWayButtonX, fourWayButtonY+2*BUTTON_SIZE-BUTTON_SIZE/2, Color.DKGRAY, new Command(Actions.MOVE,Directions.LEFT),true));
		buttons.add(new Button(fourWayButtonX+BUTTON_SIZE*3, fourWayButtonY, Color.DKGRAY, new Command(Actions.MOVE,Directions.UP),true));
		buttons.add(new Button(fourWayButtonX+BUTTON_SIZE*3, fourWayButtonY+BUTTON_SIZE*3, Color.DKGRAY, new Command(Actions.MOVE,Directions.DOWN),true));
		buttons.add(new Button(fourWayButtonX+BUTTON_SIZE*6, fourWayButtonY+2*BUTTON_SIZE-BUTTON_SIZE/2, Color.DKGRAY, new Command(Actions.MOVE,Directions.RIGHT),true));
		
		QuestConsole console = ObjectManager.getObjectManager().getConsole();
		int x = console.getX() + console.getWidth();
		int y = console.getY() + console.getHeight();
		buttons.add(new Button(x-BUTTON_SIZE, console.getY(), Color.DKGRAY, new Command(Actions.CONSOLE,Directions.UP),false));
		buttons.add(new Button(x-BUTTON_SIZE, y-BUTTON_SIZE, Color.DKGRAY, new Command(Actions.CONSOLE,Directions.DOWN),false));
		
		buttons.add(new Button(600,520,Color.GREEN, new Command(Actions.ACTIVATE),true));
		buttons.add(new Button(670,520,Color.RED, new Command(Actions.FIRE),true));
		
	}

	public void draw(Canvas canvas) {
		for (Button button : buttons) {
			button.draw(canvas);
		}
	}

	public Command processTouch(int x, int y) {
		Command command = null;
		for (Button button : buttons) {
			if (button.inButton(x, y)) {
				command = button.getCommand();
			}
		}
		return command;
	}

}
