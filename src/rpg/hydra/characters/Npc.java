package rpg.hydra.characters;

import rpg.hydra.ObjectManager;
import rpg.hydra.background.Background;
import rpg.hydra.utility.Activatable;
import rpg.hydra.utility.Collisionable;
import rpg.hydra.utility.Drawable;
import android.graphics.Bitmap;
import android.graphics.Canvas;

abstract public class Npc implements Drawable,Collisionable,Activatable {
	
	private Bitmap bitmap;
	protected int x;
	protected int y;
	protected int quadrantX;
	protected int quadrantY;
	
	public Npc(Bitmap bitmap, int x, int y, int quadrantX, int quadrantY) {
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

	public void draw(Canvas canvas) {
		if (isVisible()) {
			canvas.drawBitmap(bitmap,OFFSET_TILESVIEW_X+x*TILE_SIZE,OFFSET_TILESVIEW_Y+y*TILE_SIZE,null);
		}
	}

	public boolean collisionAt(int x, int y) {
		return this.x == x && this.y == y && isVisible();
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

}
