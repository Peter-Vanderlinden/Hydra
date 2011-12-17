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
		x = 150;
		y = 540;
		width = 600;
		height = 50;
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
		windowSize = 2;
		messages = new ArrayList<String>();
		
		messages.add("You are about to embark on a epic quest to retrieve the weapon");
		messages.add("of awesomeness and to save the kingdom from the evil Android robot.");
		messages.add("However, first you should try to find out more about this place.");
		messages.add("It is said that a guardian dwells these dungeons, forever searching");
		messages.add("and waiting for the champion that he is to help.");
		messages.add("Go now, ranger, and may the power of the gods be with you...");
		messages.add(" ");
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
	}

}
