package rpg.hydra;

import java.util.ArrayList;

import rpg.hydra.background.Background;
import rpg.hydra.characters.Link;
import rpg.hydra.utility.Drawable;

public class ObjectManager {
	
	private static ObjectManager singleton;
	
	public static ObjectManager getObjectManager() {
		if (singleton == null) {
			singleton = new ObjectManager();
		}
		return singleton;
	}
	
	private ArrayList<Drawable> drawable;
	
	private HydraSurface surface;
	private QuestConsole console;
	private HydraTouchInput touchinput;
	private HydraKeyInput keyinput;
	
	private Background background;
	private Link link;
	
	private ObjectManager() {
		drawable = new ArrayList<Drawable>();
	}
	
	public void initialize() { // watch out for the order of initialization !!!
		background = new Background();
		drawable.add(background);
		link = new Link();
		drawable.add(link);
		console = new QuestConsole();
		drawable.add(console);
		touchinput = new HydraTouchInput();
		drawable.add(touchinput);
		keyinput = new HydraKeyInput();
	}

	public ArrayList<Drawable> getDrawable() {
		return drawable;
	}

	public HydraSurface getSurface() {
		return surface;
	}

	public void setSurface(HydraSurface surface) {
		this.surface = surface;
	}

	public HydraTouchInput getTouchinput() {
		return touchinput;
	}

	public QuestConsole getConsole() {
		return console;
	}

	public Background getBackground() {
		return background;
	}

	public Link getLink() {
		return link;
	}
	
	public HydraKeyInput getKeyinput() {
		return keyinput;
	}

}
