package rpg.hydra;

import rpg.hydra.utility.Drawable;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;

public class Button implements Drawable {
	
	private int x;
	private int y;
	private ShapeDrawable rectangle;
	private Action action;
	
	public Button(int x, int y, int color, Action action) {
		this.x = x;
		this.y = y;
		this.action = action;
		rectangle = new ShapeDrawable(new RectShape());
		rectangle.getPaint().setColor(color);
		rectangle.setBounds(x, y, x+BUTTON_SIZE, y+BUTTON_SIZE);
	}
	
	public boolean inButton(int x, int y) {
		return (x > this.x && x < this.x+BUTTON_SIZE && y > this.y && y < this.y+BUTTON_SIZE);
	}
	
	public Action getAction() {
		return action.getCopy();
	}

	public void draw(Canvas canvas) {
		rectangle.draw(canvas);
	}

}
