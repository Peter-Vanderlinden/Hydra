package rpg.hydra.items;

import rpg.hydra.ObjectManager;
import rpg.hydra.QuestConsole;
import rpg.hydra.R;
import rpg.hydra.utility.Activatable;
import rpg.hydra.utility.Directions;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;

public class Slingshot extends Item implements Activatable {
	
	private boolean equipped;
	
	public Slingshot() {
		super(BitmapFactory.decodeResource(ObjectManager.getObjectManager().getSurface().getResources(), R.drawable.slingshot_1),5,5,1,1);
		equipped = false;
	}
	
	public void activate() {
		QuestConsole console = ObjectManager.getObjectManager().getConsole();
		console.getMessages().add("You have found the slingshot of epicness.");
		console.getMessages().add("You feel it's power flowing through your body");
		console.getMessages().add("as you hold it in your hand.");
		console.getMessages().add("********************");
		console.showLast(4);
		equipped = true;
		ObjectManager.getObjectManager().getLink().setSlingshot(this);
	}
	
	public boolean collisionAt(int x, int y) {
		return super.collisionAt(x, y) && !equipped;
	}
	
	public void draw(Canvas canvas) {
		if (!equipped) {
			super.draw(canvas);
		}
	}
	
	public void fire(int x, int y, int quadrantX, int quadrantY, Directions direction) {
		ObjectManager.getObjectManager().getStones().add(new Stone(x, y, quadrantX, quadrantY, direction, Color.DKGRAY));
	}

}
