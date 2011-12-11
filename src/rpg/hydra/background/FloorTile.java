package rpg.hydra.background;

import rpg.hydra.HydraSurface;
import rpg.hydra.R;
import android.graphics.BitmapFactory;

public class FloorTile extends Tile {

	public FloorTile(int x, int y, HydraSurface surface) {
		super(BitmapFactory.decodeResource(surface.getResources(), R.drawable.house_floor), x, y, true);
	}

}
