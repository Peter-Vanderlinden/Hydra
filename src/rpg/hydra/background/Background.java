package rpg.hydra.background;

import rpg.hydra.HydraSurface;
import rpg.hydra.ObjectManager;
import rpg.hydra.R;
import rpg.hydra.utility.Collisionable;
import rpg.hydra.utility.Drawable;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

// surfaceview = 792 x 592 in virtualbox

public class Background implements Drawable,Collisionable {
	
	private Tile floor;
	private Tile floor2;
	private Tile wallTopLeft;
	private Tile wallTop;
	private Tile wallTopRight;
	private Tile wallRight;
	private Tile wallBottomRight;
	private Tile wallBottom;
	private Tile wallBottomLeft;
	private Tile wallLeft;
	
	private TilesView[][] tilesviews;
	private TilesView currentview;
	
	public Background() {
		initializeTiles();
		tilesviews = new TilesView[TILESVIEWS_Y][TILESVIEWS_X];
		for (int y=0; y<TILESVIEWS_Y; y++) {
			for (int x=0; x<TILESVIEWS_X; x++) {
				tilesviews[y][x] = createTilesView(y,x);
			}
		}
		currentview = tilesviews[0][0];
	}
	
	public void draw(Canvas canvas) {
		currentview.draw(canvas);
	}
	
	public boolean collisionAt(int x, int y) {
		return currentview.collisionAt(x, y);
	}
	
	public int getCurrentQuadrantX() {
		return currentview.getTilesViewX();
	}
	
	public int getCurrentQuadrantY() {
		return currentview.getTilesViewY();
	}
	
	// No need to test for matrix limitations if world-boundaries are all non-walkable tiles 
	public void slideUp() {
		currentview = tilesviews[currentview.getTilesViewY()+1][currentview.getTilesViewX()];
	}
	
	public void slideRight() {
		currentview = tilesviews[currentview.getTilesViewY()][currentview.getTilesViewX()-1];
	}
	
	public void slideDown() {
		currentview = tilesviews[currentview.getTilesViewY()-1][currentview.getTilesViewX()];
	}
	
	public void slideLeft() {
		currentview = tilesviews[currentview.getTilesViewY()][currentview.getTilesViewX()+1];
	}
	
	private void initializeTiles() {
		HydraSurface surface = ObjectManager.getObjectManager().getSurface();
		floor = new Tile(BitmapFactory.decodeResource(surface.getResources(), R.drawable.house_floor),true);
		floor2 = new Tile(BitmapFactory.decodeResource(surface.getResources(), R.drawable.house_floor_2),true);
		wallTopLeft = new Tile(BitmapFactory.decodeResource(surface.getResources(), R.drawable.house_top_left),false);
		wallTop = new Tile(BitmapFactory.decodeResource(surface.getResources(), R.drawable.house_top),false);
		wallTopRight = new Tile(BitmapFactory.decodeResource(surface.getResources(), R.drawable.house_top_right),false);
		wallRight = new Tile(BitmapFactory.decodeResource(surface.getResources(), R.drawable.house_right),false);
		wallBottomRight = new Tile(BitmapFactory.decodeResource(surface.getResources(), R.drawable.house_bottom_right),false);
		wallBottom = new Tile(BitmapFactory.decodeResource(surface.getResources(), R.drawable.house_bottom),false);
		wallBottomLeft = new Tile(BitmapFactory.decodeResource(surface.getResources(), R.drawable.house_bottom_left),false);
		wallLeft = new Tile(BitmapFactory.decodeResource(surface.getResources(), R.drawable.house_left),false);
	}
	
	/*  ______________________________
	 * |tilesview(0,0)	tilesview(0,1)|
	 * |tilesview(1,0)	tilesview(1,1)|
	 * |______________________________|
	 */
	
	private TilesView createTilesView(int y, int x) {    // !!! x,y <=> [y,x]
		Tile[][] tiles = new Tile[TILES_IN_VIEW_Y][TILES_IN_VIEW_X];
		TilesView tilesview = null;
		
		if (y == 0 && x == 0) {
			tiles[0][0] = wallTopLeft;
			for (int i=1; i<TILES_IN_VIEW_X-1; i++) {
				tiles[0][i] = wallTop;
			}
			tiles[0][TILES_IN_VIEW_X-1] = wallTopRight;
			for (int j=1; j<TILES_IN_VIEW_Y; j++) {
				tiles[j][0] = wallLeft;
				for (int i=1; i<TILES_IN_VIEW_X-1; i++) {
					tiles[j][i] = floor;
				}
				tiles[j][TILES_IN_VIEW_X-1] = wallRight;
			}
			tilesview = new TilesView(tiles,0,0);
		}
		
		else if (y == 1 && x == 0) {
			for (int j=0; j<TILES_IN_VIEW_Y; j++) {
				tiles[j][0] = wallLeft;
				for (int i=1; i<TILES_IN_VIEW_X; i++) {
					tiles[j][i] = floor;
				}
			}			
			tiles[0][TILES_IN_VIEW_X-1] = wallBottomLeft;
			tiles[TILES_IN_VIEW_Y-1][0] = wallBottomLeft;
			for (int i=1; i<TILES_IN_VIEW_X; i++) {
				tiles[TILES_IN_VIEW_Y-1][i] = wallBottom;
			}
			tilesview = new TilesView(tiles,1,0);
		}
		
		else if (y == 0 && x == 1) {
			tiles[0][0] = wallTopLeft;
			for (int i=1; i<TILES_IN_VIEW_X-1; i++) {
				tiles[0][i] = wallTop;
			}
			tiles[0][TILES_IN_VIEW_X-1] = wallTopRight;
			for (int j=1; j<TILES_IN_VIEW_Y; j++) {
				tiles[j][0] = wallLeft;
				for (int i=1; i<TILES_IN_VIEW_X-1; i++) {
					tiles[j][i] = floor2;
				}
				tiles[j][TILES_IN_VIEW_X-1] = wallRight;
			}
			tilesview = new TilesView(tiles,0,1);
		}
		
		else if (y == 1 && x == 1) {
			for (int j=0; j<TILES_IN_VIEW_Y-1; j++) {
				for (int i=0; i<TILES_IN_VIEW_X-1; i++) {
					tiles[j][i] = floor;
				}
				tiles[j][TILES_IN_VIEW_X-1] = wallRight;
			}
			tiles[0][0] = wallBottomRight;
			for (int i=0; i<TILES_IN_VIEW_X-1; i++) {
				tiles[TILES_IN_VIEW_Y-1][i] = wallBottom;
			}
			tiles[TILES_IN_VIEW_Y-1][TILES_IN_VIEW_X-1] = wallBottomRight;
			tilesview = new TilesView(tiles,1,1);
		}
		return tilesview;
	}

}
