package rpg.hydra.items;

import rpg.hydra.ObjectManager;
import rpg.hydra.characters.Link;
import rpg.hydra.characters.Robot;
import rpg.hydra.utility.Collisionable;
import rpg.hydra.utility.Directions;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;

public class Stone extends Item {
	
	public static final int SPEED = 4;
	public static final int RADIUS = 3;
	
	private ShapeDrawable circle;
	private Directions direction;
	private int color;
	
	public Stone(int x, int y, int quadrantX, int quadrantY, Directions direction, int color) {
		super(null,x,y,quadrantX,quadrantY);
		this.direction = direction;
		this.color = color;
		circle = new ShapeDrawable(new OvalShape());
		circle.getPaint().setColor(color);
		circle.setBounds(x-RADIUS, y-RADIUS, x+RADIUS, y+RADIUS);
	}
	
	public void draw(Canvas canvas) {
		if (isVisible()) {
			circle.draw(canvas);
		}
	}
	
	public void update() {
		switch (direction) {
		case UP: y -= SPEED; break;
		case RIGHT: x += SPEED; break;
		case DOWN: y += SPEED; break;
		case LEFT: x -= SPEED; break;
		}
		circle.setBounds(x-RADIUS, y-RADIUS, x+RADIUS, y+RADIUS);
		int xToTilesX = (x-OFFSET_TILESVIEW_X)/TILE_SIZE;
		int yToTilesY = (y-OFFSET_TILESVIEW_Y)/TILE_SIZE;
		ObjectManager om = ObjectManager.getObjectManager();
		boolean deleted = false;
		if (xToTilesX < 0 || yToTilesY < 0 || xToTilesX >= TILES_IN_VIEW_X || yToTilesY >= TILES_IN_VIEW_Y) {
			om.getStones().remove(this);
			deleted = true;
		}
		if (!deleted) {
			for (Collisionable object : om.getCollisionable()) {
				if (object.collisionAt(xToTilesX, yToTilesY)) {
					if (object.getClass() == Link.class && color == Color.RED) {
						om.getLink().gotHit();
					}
					else if (object.getClass() == Robot.class && color == Color.DKGRAY) {
						om.getRobot().gotHit();
					}
					if (!(object.getClass() == Link.class && color == Color.DKGRAY)) {
						om.getStones().remove(this);
					}
				}
			}
		}
	}

}
