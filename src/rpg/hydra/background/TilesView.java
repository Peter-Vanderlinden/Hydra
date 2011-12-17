package rpg.hydra.background;

import android.graphics.Canvas;
import rpg.hydra.utility.Drawable;

public class TilesView implements Drawable {
	
	private Tile[][] tiles;
	private int tilesViewX;
	private int tilesViewY;
	
	public TilesView(Tile[][] tiles, int tilesViewY, int tilesViewX) {
		this.tiles = tiles;
		this.tilesViewY = tilesViewY;
		this.tilesViewX = tilesViewX;
	}

	public void draw(Canvas canvas) {
		for (int y=0; y<TILES_IN_VIEW_Y; y++) {
			for (int x=0; x<TILES_IN_VIEW_X; x++) {
				canvas.drawBitmap(tiles[y][x].getBitmap(),OFFSET_TILESVIEW_X+x*TILE_SIZE,OFFSET_TILESVIEW_Y+y*TILE_SIZE,null);
			}
		}
	}
	
	public boolean collisionAt(int x, int y) {
		return !tiles[y][x].walkable;
	}
	
	public Tile[][] getTiles() {
		return tiles;
	}

	public int getTilesViewY() {
		return tilesViewY;
	}

	public int getTilesViewX() {
		return tilesViewX;
	}

}
