package rpg.hydra.characters;

import rpg.hydra.ObjectManager;
import rpg.hydra.R;
import rpg.hydra.background.Background;
import rpg.hydra.utility.Drawable;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Link implements Drawable {
	
	private Bitmap bitmap;
	private int x;
	private int y;
	private Background background;
	
	public Link() {
		bitmap = BitmapFactory.decodeResource(ObjectManager.getObjectManager().getSurface().getResources(), R.drawable.link_front_1);
		background = ObjectManager.getObjectManager().getBackground();
		x = 5;
		y = 5;
	}
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap,OFFSET_TILESVIEW_X+x*TILE_SIZE,OFFSET_TILESVIEW_Y+y*TILE_SIZE,null);
	}
	
	public void moveUp() {
		if (y-1 < 0) {
			background.slideDown();
			y = TILES_IN_VIEW_Y-1;
		}
		else if (!background.collisionAt(x,y-1)) { 
			y--; 
		}
	}
		
	public void moveRight() {
		if (x+1 > TILES_IN_VIEW_X-1) {
			background.slideLeft();
			x = 0;
		}
		else if (!background.collisionAt(x+1,y)) { 
			x++; 
		}
	}
	
	public void moveDown() {
		if (y+1 > TILES_IN_VIEW_Y-1) {
			background.slideUp();
			y = 0;
		}
		else if (!background.collisionAt(x,y+1)) { 
			y++; 
		}
	}
	
	public void moveLeft() {
		if (x-1 < 0) {
			background.slideRight();
			x = TILES_IN_VIEW_X-1;
		}
		else if (!background.collisionAt(x-1,y)) { 
			x--; 
		}
	}

}
