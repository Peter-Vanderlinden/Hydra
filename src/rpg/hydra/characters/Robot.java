package rpg.hydra.characters;

import rpg.hydra.ObjectManager;
import rpg.hydra.QuestConsole;
import rpg.hydra.R;
import rpg.hydra.items.Stone;
import rpg.hydra.utility.Directions;
import android.graphics.BitmapFactory;
import android.graphics.Color;

public class Robot extends Npc {
	
	private boolean frenzy;
	private boolean dead;
	private int counter;
	private int life;
	
	public Robot() {
		super(BitmapFactory.decodeResource(ObjectManager.getObjectManager().getSurface().getResources(), R.drawable.robot_1),10,2,1,0);
		frenzy = false;
		counter = 0;
		dead = false;
		life = 100;
	}
	
	public boolean isVisible() {
		return super.isVisible() && !dead;
	}
	
	public void activate() {
		QuestConsole console = ObjectManager.getObjectManager().getConsole();
		console.getMessages().add("I kill you.");
		console.getMessages().add("********************");
		console.showLast(2);
	}
	
	public void update() {
		ObjectManager om = ObjectManager.getObjectManager();
		if (!dead && !om.getLink().getDead()) {
			if (!frenzy && om.getBackground().getCurrentQuadrantX() == getQuadrantX() && om.getBackground().getCurrentQuadrantY() == getQuadrantY()) {
				frenzy = true;
				QuestConsole console = om.getConsole();
				console.getMessages().add("\"I'm Android 2.5, an experimental branch of the OS with features that were tought");
				console.getMessages().add("to be too powerfull, and I'm here to end privacy as you know it.");
				console.getMessages().add("You can't defeat me, pathetic human, because I know everything about you.\"");
				console.getMessages().add("********************");
				console.showLast(4);
			}
			else if (frenzy) {
				if (counter == 100 && om.getBackground().getCurrentQuadrantX() == getQuadrantX() && om.getBackground().getCurrentQuadrantY() == getQuadrantY()) {
					x = om.getLink().getX();
					om.getStones().add(new Stone(OFFSET_TILESVIEW_X+x*TILE_SIZE+TILE_SIZE/2, OFFSET_TILESVIEW_Y+(y+1)*TILE_SIZE, quadrantX, quadrantY, Directions.DOWN, Color.RED));
					counter = 0;
				}
				counter++;
			}
		}
	}
	
	public void gotHit() {
		life -= 40;
		if (!dead && life < 0) {
			QuestConsole console = ObjectManager.getObjectManager().getConsole();
			console.getMessages().add("\"I will get you, human, I will ... beep ... errorrr ... going into emergency shutdown ...\" ");
			console.getMessages().add("You saved the world and it's privacy, but it's only a matter of time before new threads arise.");
			console.getMessages().add("Stay tuned for the follow up: EpicQuest II, The revenge of Android.");
			console.getMessages().add("********************");
			console.showLast(4);
			dead = true;
		}
	}
	
	public boolean getDead() {
		return dead;
	}

}
