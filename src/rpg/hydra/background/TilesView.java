package rpg.hydra.background;

import android.graphics.Canvas;
import rpg.hydra.utility.Drawable;

public class TilesView implements Drawable {
	
	private Tile[][] tiles;
	private int tilesx;
	private int tilesy;
	
	public TilesView(Tile[][] tiles, int tilesx, int tilesy) {
		this.tiles = tiles;
		this.tilesx = tilesx;
		this.tilesy = tilesy;
	}

	public void draw(Canvas canvas) {
		for (int y=0; y<tilesy; y++) {
			for (int x=0; x<tilesx; x++) {
				tiles[y][x].draw(canvas);
			}
		}
	}
	public Tile[][] getTiles() {
		return tiles;
	}

}
