package rpg.hydra;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import rpg.hydra.utility.Drawable;

public class QuestConsole implements Drawable {
	
	private ArrayList<String> messages;
	private ShapeDrawable rectangle;
	private int x;
	private int y;
	private int width;
	private int height;
	private Paint paint;
	private Typeface type;
	private int textSize;
	private int windowStart;
	private int windowSize;
	
	public QuestConsole() {
		x = 20;
		y = 10;
		width = 760;
		height = 60;
		rectangle = new ShapeDrawable(new RectShape());
		rectangle.getPaint().setColor(Color.BLUE);
		rectangle.setBounds(x, y, x+width, y+height);
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.YELLOW);
		type = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL);
		textSize = 14;
		paint.setTextSize(textSize);
		paint.setTypeface(type);
		windowStart = 0;
		windowSize = 3;
		messages = new ArrayList<String>();
		
		messages.add("The last thing you remember was your ship getting caught in a storm.");
		messages.add("You fought it all you could but the ship was sinking nevertheless.");
		messages.add("Just as you thought you would never see another living soul,");
		messages.add("you heard a woman's voice in your head, calling out for help.");
		messages.add("Next thing you know, you woke up in this dark and cold dungeon.");
		messages.add("********************");
		
		textSize += 4;
	}

	public ArrayList<String> getMessages() {
		return messages;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void showPrevious() {
		if (windowStart > 0) {
			windowStart -= 1;
		}
	}
	
	public void showNext() {
		if (windowStart < messages.size()-windowSize) {
			windowStart += 1;
		}
	}
	
	public void showLast(int lines) {
		windowStart = messages.size()-lines > messages.size()-windowSize ? messages.size()-windowSize : messages.size()-lines;
	}

	public void draw(Canvas canvas) {
		rectangle.draw(canvas);
		int offset = 0;
		for (int i=windowStart; i<windowStart+windowSize; i++) {
			canvas.drawText(messages.get(i), x+5, y+15+(offset*textSize), paint);
			offset++;
		}
		if (windowStart != messages.size()-windowSize) {
			canvas.drawText("\\/", x+710, y+19+2*textSize, paint);
		}
		if (windowStart != 0) {
			canvas.drawText("/\\", x+710, y+15, paint);
		}
	}

}
