package rpg.hydra.background;

import rpg.hydra.HydraSurface;
import rpg.hydra.R;
import rpg.hydra.utility.Directions;

import android.graphics.BitmapFactory;

public class WallTile extends Tile {

	public WallTile(Directions dir, int x, int y, HydraSurface surface) {
		super(x,y,false);
		switch (dir) {
		case TOP_LEFT: setBitmap(BitmapFactory.decodeResource(surface.getResources(), R.drawable.house_top_left)); break;
		case TOP: setBitmap(BitmapFactory.decodeResource(surface.getResources(), R.drawable.house_top)); break;
		case TOP_RIGHT: setBitmap(BitmapFactory.decodeResource(surface.getResources(), R.drawable.house_top_right)); break;
		case RIGHT: setBitmap(BitmapFactory.decodeResource(surface.getResources(), R.drawable.house_right)); break;
		case BOTTOM_RIGHT: setBitmap(BitmapFactory.decodeResource(surface.getResources(), R.drawable.house_bottom_right)); break;
		case BOTTOM: setBitmap(BitmapFactory.decodeResource(surface.getResources(), R.drawable.house_bottom)); break;
		case BOTTOM_LEFT: setBitmap(BitmapFactory.decodeResource(surface.getResources(), R.drawable.house_bottom_left)); break;
		case LEFT: setBitmap(BitmapFactory.decodeResource(surface.getResources(), R.drawable.house_left)); break;
		}
	}

}
