package rpg.hydra.background;

import rpg.hydra.HydraSurface;
import rpg.hydra.utility.Directions;
import rpg.hydra.utility.Drawable;
import android.graphics.Canvas;

// surfaceview = 792 x 592 in virtualbox

public class Background implements Drawable {
	
	public static int tilesxperview = 46;
	public static int tilesyperview = 32;
	public static int tilesviewsx = 2;
	public static int tilesviewsy = 2;
	
	private TilesView[][] tilesviews;
	private TilesView currentview;
	private int currentviewx;
	private int currentviewy;
	
	public Background(HydraSurface surface) {
		tilesviews = new TilesView[tilesviewsx][tilesviewsy];
		for (int y=0; y<tilesviewsy; y++) {
			for (int x=0; x<tilesviewsx; x++) {
				tilesviews[y][x] = createTilesView(y,x, surface);
			}
		}
		currentviewx = 0;
		currentviewy = 0;
		currentview = tilesviews[currentviewy][currentviewx];
	}
	
	public void draw(Canvas canvas) {
		currentview.draw(canvas);
	}
	
	public Tile getTileAt(int x, int y) {
		return currentview.getTiles()[y][x];
	}
	
	public void slideUp() {
		currentviewy += 1;
		currentview = tilesviews[currentviewy][currentviewx];
	}
	
	public void slideRight() {
		currentviewx -= 1;
		currentview = tilesviews[currentviewy][currentviewx];
	}
	
	public void slideDown() {
		currentviewy -= 1;
		currentview = tilesviews[currentviewy][currentviewx];
	}
	
	public void slideLeft() {
		currentviewx += 1;
		currentview = tilesviews[currentviewy][currentviewx];
	}
	
	/*  ______________________________
	 * |tilesview(0,0)	tilesview(0,1)|
	 * |tilesview(1,0)	tilesview(1,1)|
	 * |______________________________|
	 */
	
	private TilesView createTilesView(int y, int x, HydraSurface surface) {    // !!! x,y <=> [y,x]
		Tile[][] tiles = new Tile[tilesyperview][tilesxperview];
		
		if (y == 0 && x == 0) {
			tiles[0][0] = new WallTile(Directions.TOP_LEFT, 0, 0, surface);	
			for (int i=1; i<tilesxperview; i++) {
				tiles[0][i] = new WallTile(Directions.TOP, i, 0, surface);
			}
			for (int j=1; j<tilesyperview; j++) {
				tiles[j][0] = new WallTile(Directions.LEFT, 0, j, surface);
				for (int i=1; i<tilesxperview; i++) {
					tiles[j][i] = new FloorTile(i,j,surface);
				}
			}			
		}
		
		else if (y == 1 && x == 0) {
			for (int j=0; j<tilesyperview; j++) {
				tiles[j][0] = new WallTile(Directions.LEFT, 0, j, surface);
				for (int i=1; i<tilesxperview; i++) {
					tiles[j][i] = new FloorTile(i,j,surface);
				}
			}			
			tiles[tilesyperview-1][0] = new WallTile(Directions.BOTTOM_LEFT, 0, tilesyperview-1, surface);
			for (int i=1; i<tilesxperview; i++) {
				tiles[tilesyperview-1][i] = new WallTile(Directions.BOTTOM, i, tilesyperview-1, surface);
			}
		}
		
		else if (y == 0 && x == 1) {
			
			for (int i=0; i<tilesxperview-1; i++) {
				tiles[0][i] = new WallTile(Directions.TOP, i, 0, surface);
			}
			tiles[0][tilesxperview-1] = new WallTile(Directions.TOP_RIGHT, tilesxperview-1, 0, surface);
			
			for (int j=1; j<tilesyperview; j++) {
				for (int i=0; i<tilesxperview-1; i++) {
					tiles[j][i] = new FloorTile(i,j,surface);
				}
				tiles[j][tilesxperview-1] = new WallTile(Directions.RIGHT, tilesxperview-1, j, surface);
			}
		}
		
		else if (y == 1 && x == 1) {

			for (int j=0; j<tilesyperview-1; j++) {
				for (int i=0; i<tilesxperview-1; i++) {
					tiles[j][i] = new FloorTile(i,j,surface);
				}
				tiles[j][tilesxperview-1] = new WallTile(Directions.RIGHT, tilesxperview-1, j, surface);
			}
			
			for (int i=0; i<tilesxperview-1; i++) {
				tiles[tilesyperview-1][i] = new WallTile(Directions.BOTTOM, i, tilesyperview-1, surface);
			}
			tiles[tilesyperview-1][tilesxperview-1] = new WallTile(Directions.BOTTOM_RIGHT, tilesxperview-1, tilesyperview-1, surface);
		}
		return new TilesView(tiles,tilesxperview,tilesyperview);
	}

}
