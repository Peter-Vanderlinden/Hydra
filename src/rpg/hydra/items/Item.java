package rpg.hydra.items;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import rpg.hydra.ObjectManager;
import rpg.hydra.background.Background;
import rpg.hydra.utility.Collisionable;
import rpg.hydra.utility.Drawable;

public abstract class Item implements Drawable,Collisionable {
	
	private Bitmap bitmap;
	protected int x;
	protected int y;
	private int quadrantX;
	private int quadrantY;
	
	public Item(Bitmap bitmap, int x, int y, int quadrantX, int quadrantY) {
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
		this.quadrantX = quadrantX;
		this.quadrantY = quadrantY;
	}
	
	public boolean isVisible() {
		Background bg = ObjectManager.getObjectManager().getBackground();
		boolean visible = false;
		if (bg.getCurrentQuadrantX() == quadrantX && bg.getCurrentQuadrantY() == quadrantY) {
			visible = true;
		}
		return visible;
	}

	public boolean collisionAt(int x, int y) {
		return this.x == x && this.y == y && isVisible();
	}

	public void draw(Canvas canvas) {
		if (isVisible()) {
			canvas.drawBitmap(bitmap,OFFSET_TILESVIEW_X+x*TILE_SIZE,OFFSET_TILESVIEW_Y+y*TILE_SIZE,null);
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getQuadrantX() {
		return quadrantX;
	}

	public int getQuadrantY() {
		return quadrantY;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

}
