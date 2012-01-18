package rpg.hydra.characters;

import rpg.hydra.ObjectManager;
import rpg.hydra.QuestConsole;
import rpg.hydra.R;
import android.graphics.BitmapFactory;

public class Marlin extends Npc {
	
	public Marlin() {
		super(BitmapFactory.decodeResource(ObjectManager.getObjectManager().getSurface().getResources(), R.drawable.npc_1),1,10,0,1);
	}
	
	public void activate() {
		QuestConsole console = ObjectManager.getObjectManager().getConsole();
		console.getMessages().add("\"I'm so glad you're here. I'm being kept prisoner in this dungeon by an evil robot.");
		console.getMessages().add("After months of trying I finally broke through the magical barrier and teleported");
		console.getMessages().add("you here because you are the only one who can save me.");
		console.getMessages().add("My magical powers have no effect on this robot but I know you are destined to");
		console.getMessages().add("defeat him. How, you ask? Well, I AM Marlin, the great royal mage, that is");
		console.getMessages().add("I was at least until Google and it's technology took over.");
		console.getMessages().add("You must hurry now, and try to find yourself a weapon ...\"");
		console.getMessages().add("********************");
		console.showLast(8);
	}

}
