package rpg.hydra.utility;

import android.graphics.Canvas;

public interface Drawable {
	
	public static final int TILE_SIZE = 16;
	public static final int BUTTON_SIZE = 20;
	
	public static final int TILES_IN_VIEW_X = 41;
	public static final int TILES_IN_VIEW_Y = 24;
	
	public static final int TILESVIEWS_X = 2;
	public static final int TILESVIEWS_Y = 2;
	
	public static final int OFFSET_TILESVIEW_X = 70;
	public static final int OFFSET_TILESVIEW_Y = 90;
	
	public void draw(Canvas canvas);

}
