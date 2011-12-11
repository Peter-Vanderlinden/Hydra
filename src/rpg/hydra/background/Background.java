package rpg.hydra.background;

import java.util.ArrayList;

import rpg.hydra.HydraSurface;
import rpg.hydra.utility.Directions;
import rpg.hydra.utility.Drawable;
import android.graphics.Canvas;

// surfaceview = 792 x 592 in virtualbox

public class Background implements Drawable {
	
	private ArrayList<Tile> tiles;
	
	public Background(HydraSurface surface) {
		int tilesx = 46;
		int tilesy = 32;
		tiles = new ArrayList<Tile>();
		
		tiles.add(new WallTile(Directions.TOP_LEFT, 0, 0, surface));
		for (int i=1; i<tilesx; i++) {
			tiles.add(new WallTile(Directions.TOP, i, 0, surface));
		}
		tiles.add(new WallTile(Directions.TOP_RIGHT, tilesx, 0, surface));
		for (int j=1; j<tilesy; j++) {
			tiles.add(new WallTile(Directions.LEFT, 0, j, surface));
			for (int i=1; i<tilesx; i++) {
				tiles.add(new FloorTile(i,j,surface));
			}
			tiles.add(new WallTile(Directions.RIGHT, tilesx, j, surface));
		}
		tiles.add(new WallTile(Directions.BOTTOM_LEFT, 0, tilesy, surface));
		for (int i=1; i<tilesx; i++) {
			tiles.add(new WallTile(Directions.BOTTOM, i, tilesy, surface));
		}
		tiles.add(new WallTile(Directions.BOTTOM_RIGHT, tilesx, tilesy, surface));
	}
	
	public void draw(Canvas canvas) {
		for (Tile tile : tiles) {
			tile.draw(canvas);
		}
	}
	
	public Tile getTileAt(int x, int y) {
		Tile target = null;
		for (Tile tile : tiles) {
			if (tile.x == x && tile.y == y) {
				target = tile;
			}
		}
		return target;
	}

}
