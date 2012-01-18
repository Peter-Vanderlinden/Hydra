package rpg.hydra.input;

import rpg.hydra.utility.Command;
import rpg.hydra.utility.Drawable;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;

public class Button implements Drawable {
	
	private int x;
	private int y;
	private ShapeDrawable rectangle;
	private Command command;
	private boolean large;
	
	public Button(int x, int y, int color, Command command, boolean large) {
		this.x = x;
		this.y = y;
		this.command = command;
		this.large = large;
		rectangle = new ShapeDrawable(new RectShape());
		rectangle.getPaint().setColor(color);
		if (large) {
			rectangle.setBounds(x, y, x+2*BUTTON_SIZE, y+2*BUTTON_SIZE);
		}
		else {
			rectangle.setBounds(x, y, x+BUTTON_SIZE, y+BUTTON_SIZE);
		}
	}
	
	public boolean inButton(int x, int y) {
		boolean inside = (x > this.x && x < this.x+BUTTON_SIZE && y > this.y && y < this.y+BUTTON_SIZE);
		if (large) {
			inside = (x > this.x && x < this.x+2*BUTTON_SIZE && y > this.y && y < this.y+2*BUTTON_SIZE);
		}
		return inside;
	}
	
	public Command getCommand() {
		return command.getCopy();
	}

	public void draw(Canvas canvas) {
		rectangle.draw(canvas);
	}

}
