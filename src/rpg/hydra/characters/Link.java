package rpg.hydra.characters;

import rpg.hydra.HydraSurface;
import rpg.hydra.R;
import rpg.hydra.background.Background;
import rpg.hydra.utility.Actions;
import rpg.hydra.utility.Directions;
import rpg.hydra.utility.Drawable;
import rpg.hydra.utility.Updateable;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Link implements Drawable,Updateable {
	
	private Bitmap bitmap;
	private int x;
	private int y;
	private Background background;
	
	public Link(HydraSurface surface, Background background) {
		bitmap = BitmapFactory.decodeResource(surface.getResources(), R.drawable.link_front_1);
		this.background = background;
		x = 5;
		y = 5;
	}
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap,x*TILESIZE,y*TILESIZE,null);
	}
	
	public void move(Directions dir) {  // y increases downwards !!!
		switch (dir) {
		case TOP:  moveUp(); break;
		case RIGHT:  moveRight(); break;
		case BOTTOM:  moveDown(); break;
		case LEFT:  moveLeft(); break;
		}
	}

	public void update(Actions action, Directions dir) {
		switch (action) {
		case MOVE: move(dir); break;
		}
	}
	
	public void moveUp() {
		if (y-1 < 0) {
			background.slideDown();
			y = Background.tilesyperview-1;
		}
		else if (background.getTileAt(x, y-1).isWalkable()) { 
			y--; 
		}
	}
		
	public void moveRight() {
		if (x+1 > Background.tilesxperview-1) {
			background.slideLeft();
			x = 0;
		}
		else if (background.getTileAt(x+1, y).isWalkable()) { 
			x++; 
		}
	}
	
	public void moveDown() {
		if (y+1 > Background.tilesyperview-1) {
			background.slideUp();
			y = 0;
		}
		else if (background.getTileAt(x, y+1).isWalkable()) { 
			y++; 
		}
	}
	
	public void moveLeft() {
		if (x-1 < 0) {
			background.slideRight();
			x = Background.tilesxperview-1;
		}
		else if (background.getTileAt(x-1, y).isWalkable()) { 
			x--; 
		}
	}

}
