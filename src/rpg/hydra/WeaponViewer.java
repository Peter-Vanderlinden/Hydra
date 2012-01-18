package rpg.hydra;

import rpg.hydra.items.Item;
import rpg.hydra.utility.Drawable;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;

public class WeaponViewer implements Drawable {
	
	private String label;
	private ShapeDrawable rectangle;
	private int x;
	private int y;
	private int width;
	private int height;
	private Paint paint;
	private Typeface type;
	private int textSize;
	
	public WeaponViewer() {
		x = 300;
		y = 500;
		width = 95;
		height = 80;
		rectangle = new ShapeDrawable(new RectShape());
		rectangle.getPaint().setColor(Color.BLUE);
		rectangle.setBounds(x, y, x+width, y+height);
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.YELLOW);
		type = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL);
		textSize = 14;
		paint.setTextSize(textSize);
		paint.setTypeface(type);
		label = "Weapon Slot";
	}

	public void draw(Canvas canvas) {
		rectangle.draw(canvas);
		canvas.drawText(label, x+5, y+5+textSize, paint);
		Item weapon = ObjectManager.getObjectManager().getLink().getSlingshot();
		if (weapon != null) {
			canvas.drawBitmap(weapon.getBitmap(),x+7+30,y+7+2*textSize,null);
		}
	}

}
