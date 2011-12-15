package rpg.hydra;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import rpg.hydra.utility.Actions;
import rpg.hydra.utility.Directions;
import rpg.hydra.utility.Drawable;
import rpg.hydra.utility.Updateable;

public class QuestConsole implements Drawable, Updateable {
	
	private String message;
	private ShapeDrawable rectangle;
	private int x;
	private int y;
	private Paint paint;
	private Typeface type;
	
	public QuestConsole() {
		x = 500;
		y = 550;
		rectangle = new ShapeDrawable(new RectShape());
		rectangle.getPaint().setColor(Color.YELLOW);
		rectangle.setBounds(x, y, x+100, y+50);
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.CYAN);
		type = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL);
		paint.setTextSize(14);
		paint.setTypeface(type);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void update(Actions action, Directions dir) {
		
	}

	public void draw(Canvas canvas) {
		rectangle.draw(canvas);
		canvas.drawText(message, x, y, paint);
	}

}
