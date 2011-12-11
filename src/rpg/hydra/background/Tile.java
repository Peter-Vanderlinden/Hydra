package rpg.hydra.background;

import rpg.hydra.utility.Drawable;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public abstract class Tile implements Drawable {
	
	protected Bitmap bitmap;
	protected int x;
	protected int y;
	protected boolean walkable;
	
	public Tile(Bitmap bitmap, int x, int y, boolean walkable) {
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
		this.walkable = walkable;
	}
	
	public Tile(int x, int y, boolean walkable) {
		this.x = x;
		this.y = y;
		this.walkable = walkable;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isWalkable() {
		return walkable;
	}

	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap,x*TILESIZE,y*TILESIZE,null);
	}

}
