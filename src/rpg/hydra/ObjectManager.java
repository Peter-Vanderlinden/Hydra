package rpg.hydra;

import java.util.ArrayList;

import rpg.hydra.background.Background;
import rpg.hydra.characters.Link;
import rpg.hydra.characters.Marlin;
import rpg.hydra.characters.Npc;
import rpg.hydra.characters.Robot;
import rpg.hydra.input.HydraKeyInput;
import rpg.hydra.input.HydraTouchInput;
import rpg.hydra.items.Item;
import rpg.hydra.items.Slingshot;
import rpg.hydra.items.Stone;
import rpg.hydra.utility.Activatable;
import rpg.hydra.utility.Collisionable;
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
	private ArrayList<Collisionable> collisionable;
	private ArrayList<Activatable> activatable;
	private ArrayList<Stone> stones;
	
	private HydraSurface surface;
	private QuestConsole console;
	private HydraTouchInput touchinput;
	private HydraKeyInput keyinput;
	private WeaponViewer weaponviewer;
	
	private Background background;
	private Link link;
	private Marlin marlin;
	private Slingshot slingshot;
	private Robot robot;
	
	private ObjectManager() {
		drawable = new ArrayList<Drawable>();
		collisionable = new ArrayList<Collisionable>();
		activatable = new ArrayList<Activatable>();
		stones = new ArrayList<Stone>();
	}
	
	public void initialize() { // watch out for the order of initialization !!!
		background = new Background();
		drawable.add(background);
		collisionable.add(background);
		console = new QuestConsole();
		drawable.add(console);
		touchinput = new HydraTouchInput();
		drawable.add(touchinput);
		keyinput = new HydraKeyInput();
		marlin = new Marlin();
		drawable.add(marlin);
		collisionable.add(marlin);
		activatable.add(marlin);
		slingshot = new Slingshot();
		drawable.add(slingshot);
		collisionable.add(slingshot);
		activatable.add(slingshot);
		robot = new Robot();
		drawable.add(robot);
		collisionable.add(robot);
		activatable.add(robot);
		link = new Link();
		drawable.add(link);
		collisionable.add(link);
		weaponviewer = new WeaponViewer();
		drawable.add(weaponviewer);
	}

	public ArrayList<Drawable> getDrawable() {
		return drawable;
	}
	
	public ArrayList<Collisionable> getCollisionable() {
		return collisionable;
	}
	
	public ArrayList<Activatable> getActivatable() {
		return activatable;
	}
	
	public ArrayList<Stone> getStones() {
		return stones;
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
	
	public Npc getMarlin() {
		return marlin;
	}
	
	public Item getSlingshot() {
		return slingshot;
	}

	public WeaponViewer getWeaponviewer() {
		return weaponviewer;
	}

	public Robot getRobot() {
		return robot;
	}

}
