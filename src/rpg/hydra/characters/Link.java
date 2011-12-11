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
		case TOP: if (background.getTileAt(x, y-1).isWalkable()) { y--; } break;
		case RIGHT: if (background.getTileAt(x+1, y).isWalkable()) { x++; } break;
		case BOTTOM: if (background.getTileAt(x, y+1).isWalkable()) { y++; } break;
		case LEFT: if (background.getTileAt(x-1, y).isWalkable()) { x--; } break;
		}
	}

	public void update(Actions action, Directions dir) {
		switch (action) {
		case MOVE: move(dir); break;
		}
	}

}
