package rpg.hydra.characters;

import rpg.hydra.ObjectManager;
import rpg.hydra.QuestConsole;
import rpg.hydra.R;
import rpg.hydra.items.Slingshot;
import rpg.hydra.utility.Collisionable;
import rpg.hydra.utility.Directions;
import rpg.hydra.utility.Drawable;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;

public class Link implements Drawable,Collisionable {
	
	private Bitmap facingUp;
	private Bitmap facingRight;
	private Bitmap facingDown;
	private Bitmap facingLeft;
	private Directions facingDirection;
	private int x;
	private int y;
	private Slingshot slingshot;
	private int life;
	private boolean dead;
	private String label;
	private ShapeDrawable rectangle;
	private ShapeDrawable lifemeter;
	private Paint paint;
	private Typeface type;
	private int textSize;
	
	public Link() {
		facingUp = BitmapFactory.decodeResource(ObjectManager.getObjectManager().getSurface().getResources(), R.drawable.link_back_1);
		facingRight = BitmapFactory.decodeResource(ObjectManager.getObjectManager().getSurface().getResources(), R.drawable.link_side_1);
		facingDown = BitmapFactory.decodeResource(ObjectManager.getObjectManager().getSurface().getResources(), R.drawable.link_front_1);
		facingLeft = BitmapFactory.decodeResource(ObjectManager.getObjectManager().getSurface().getResources(), R.drawable.link_side_2);
		facingDirection = Directions.DOWN;
		x = 5;
		y = 5;
		life = 100;
		dead = false;
		rectangle = new ShapeDrawable(new RectShape());
		rectangle.getPaint().setColor(Color.BLUE);
		rectangle.setBounds(430, 500, 570, 580);
		lifemeter = new ShapeDrawable(new RectShape());
		lifemeter.getPaint().setColor(Color.RED);
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.YELLOW);
		type = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL);
		textSize = 14;
		paint.setTextSize(textSize);
		paint.setTypeface(type);
		label = "Life bar";
	}
	
	public void draw(Canvas canvas) {
		if (!dead) {
			switch (facingDirection) {
			case UP: canvas.drawBitmap(facingUp,OFFSET_TILESVIEW_X+x*TILE_SIZE,OFFSET_TILESVIEW_Y+y*TILE_SIZE,null); break;
			case RIGHT: canvas.drawBitmap(facingRight,OFFSET_TILESVIEW_X+x*TILE_SIZE,OFFSET_TILESVIEW_Y+y*TILE_SIZE,null); break;
			case DOWN: canvas.drawBitmap(facingDown,OFFSET_TILESVIEW_X+x*TILE_SIZE,OFFSET_TILESVIEW_Y+y*TILE_SIZE,null); break;
			case LEFT: canvas.drawBitmap(facingLeft,OFFSET_TILESVIEW_X+x*TILE_SIZE,OFFSET_TILESVIEW_Y+y*TILE_SIZE,null); break;
			}
		}
		rectangle.draw(canvas);
		lifemeter.setBounds(450, 540, 450+life, 560);
		lifemeter.draw(canvas);
		canvas.drawText(label, 470, 510+textSize, paint);
	}
	
	public void fire() {
		if (slingshot != null) {
			slingshot.fire(OFFSET_TILESVIEW_X+x*TILE_SIZE+TILE_SIZE/2,OFFSET_TILESVIEW_Y+y*TILE_SIZE+TILE_SIZE/2,
					ObjectManager.getObjectManager().getBackground().getCurrentQuadrantX(),
					ObjectManager.getObjectManager().getBackground().getCurrentQuadrantY(),
					facingDirection);
		}
	}
	
	private boolean detectCollisionAt(int x, int y) {
		boolean collision = false;
		for (Collisionable object : ObjectManager.getObjectManager().getCollisionable()) {
			if (object.collisionAt(x, y)) {
				collision = true;
			}
		}
		return collision;
	}
	
	public void moveUp() {
		if (y-1 < 0) {
			ObjectManager.getObjectManager().getBackground().slideDown();
			y = TILES_IN_VIEW_Y-1;
		}
		else if (!detectCollisionAt(x,y-1)) { 
			y--; 
		}
		facingDirection = Directions.UP;
	}
		
	public void moveRight() {
		if (x+1 > TILES_IN_VIEW_X-1) {
			ObjectManager.getObjectManager().getBackground().slideLeft();
			x = 0;
		}
		else if (!detectCollisionAt(x+1,y)) { 
			x++; 
		}
		facingDirection = Directions.RIGHT;
	}
	
	public void moveDown() {
		if (y+1 > TILES_IN_VIEW_Y-1) {
			ObjectManager.getObjectManager().getBackground().slideUp();
			y = 0;
		}
		else if (!detectCollisionAt(x,y+1)) { 
			y++; 
		}
		facingDirection = Directions.DOWN;
	}
	
	public void moveLeft() {
		if (x-1 < 0) {
			ObjectManager.getObjectManager().getBackground().slideRight();
			x = TILES_IN_VIEW_X-1;
		}
		else if (!detectCollisionAt(x-1,y)) { 
			x--; 
		}
		facingDirection = Directions.LEFT;
	}

	public Directions getFacingDirection() {
		return facingDirection;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Slingshot getSlingshot() {
		return slingshot;
	}

	public void setSlingshot(Slingshot slingshot) {
		this.slingshot = slingshot;
	}

	public boolean collisionAt(int x, int y) {
		return this.x == x && this.y == y;
	}
	
	public void gotHit() {
		life -= 15;
		if (!dead && life < 0) {
			QuestConsole console = ObjectManager.getObjectManager().getConsole();
			console.getMessages().add("\"Hahaha, I beated you, pathetic human, you are no match for me");
			console.getMessages().add("and my superior skills. You will wallow in defeat for all eternity ...\"");
			console.getMessages().add("********************");
			console.showLast(3);
			dead = true;
		}
	}
	
	public boolean getDead() {
		return dead;
	}

}
