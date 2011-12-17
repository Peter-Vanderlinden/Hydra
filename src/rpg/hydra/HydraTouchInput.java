package rpg.hydra;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Color;
import rpg.hydra.utility.Actions;
import rpg.hydra.utility.Directions;
import rpg.hydra.utility.Drawable;

public class HydraTouchInput implements Drawable {
	
	private ArrayList<Button> buttons;
	
	public HydraTouchInput() {
		buttons = new ArrayList<Button>();
		
		int fourWayButtonX = 50;
		int fourWayButtonY = 545;
		buttons.add(new Button(fourWayButtonX, fourWayButtonY+BUTTON_SIZE-BUTTON_SIZE/4, Color.DKGRAY, new Action(Actions.MOVE,Directions.LEFT)));
		buttons.add(new Button(fourWayButtonX+BUTTON_SIZE*3/2, fourWayButtonY, Color.DKGRAY, new Action(Actions.MOVE,Directions.TOP)));
		buttons.add(new Button(fourWayButtonX+BUTTON_SIZE*3/2, fourWayButtonY+BUTTON_SIZE*3/2, Color.DKGRAY, new Action(Actions.MOVE,Directions.BOTTOM)));
		buttons.add(new Button(fourWayButtonX+BUTTON_SIZE*3, fourWayButtonY+BUTTON_SIZE-BUTTON_SIZE/4, Color.DKGRAY, new Action(Actions.MOVE,Directions.RIGHT)));
		
		QuestConsole console = ObjectManager.getObjectManager().getConsole();
		int x = console.getX() + console.getWidth();
		int y = console.getY() + console.getHeight();
		buttons.add(new Button(x-BUTTON_SIZE, console.getY(), Color.DKGRAY, new Action(Actions.CONSOLE,Directions.TOP)));
		buttons.add(new Button(x-BUTTON_SIZE, y-BUTTON_SIZE, Color.DKGRAY, new Action(Actions.CONSOLE,Directions.BOTTOM)));
		
	}

	public void draw(Canvas canvas) {
		for (Button button : buttons) {
			button.draw(canvas);
		}
	}

	public Action processTouch(int x, int y) {
		Action action = null;
		for (Button button : buttons) {
			if (button.inButton(x, y)) {
				action = button.getAction();
			}
		}
		return action;
	}

}
