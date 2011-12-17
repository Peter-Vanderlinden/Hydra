package rpg.hydra.background;

import android.graphics.Bitmap;

public class Tile {
	
	protected Bitmap bitmap;
	protected boolean walkable;
	
	public Tile(Bitmap bitmap, boolean walkable) {
		this.bitmap = bitmap;
		this.walkable = walkable;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public boolean isWalkable() {
		return walkable;
	}

}
