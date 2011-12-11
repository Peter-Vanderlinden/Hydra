package rpg.hydra;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import rpg.hydra.characters.Link;
import rpg.hydra.utility.Actions;
import rpg.hydra.utility.Directions;
import rpg.hydra.utility.Drawable;

public class HydraTouchInput implements Drawable {
	
	private Link link;
	private ShapeDrawable[] rectangles;
	
	public HydraTouchInput(Link link) {
		this.link = link;
		rectangles = new ShapeDrawable[4];
		for (int i=0; i<4; i++) {
			rectangles[i] = new ShapeDrawable(new RectShape());
			rectangles[i].getPaint().setColor(Color.GRAY);
		}
		rectangles[0].setBounds(50, 560, 70, 580);
		rectangles[1].setBounds(80, 545, 100, 565);
		rectangles[2].setBounds(80, 575, 100, 595);
		rectangles[3].setBounds(110, 560, 130, 580);
	}

	public void draw(Canvas canvas) {
		for (ShapeDrawable rect : rectangles) {
			rect.draw(canvas);
		}
	}

	public void processTouch(int x, int y) {
		if (x >= 50 && x <= 70 && y >= 560 && y <= 580) {
			link.update(Actions.MOVE, Directions.LEFT);
		}
		else if (x >= 80 && x <= 100 && y >= 545 && y <= 565) {
			link.update(Actions.MOVE, Directions.TOP);
		}
		else if (x >= 80 && x <= 100 && y >= 575 && y <= 595) {
			link.update(Actions.MOVE, Directions.BOTTOM);
		}
		else if (x >= 110 && x <= 130 && y >= 560 && y <= 580) {
			link.update(Actions.MOVE, Directions.RIGHT);
		}
	}

}
