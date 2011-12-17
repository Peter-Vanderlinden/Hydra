package rpg.hydra;

import rpg.hydra.utility.Actions;
import rpg.hydra.utility.Directions;

public class Action {
	
	private Actions action;
	private Directions direction;
	
	public Action(Actions action) {
		this.action = action;
	}
	
	public Action(Actions action, Directions direction) {
		this.action = action;
		this.direction = direction;
	}
	
	public Action getCopy() {
		return new Action(this.action,this.direction); 
	}

	public Actions getAction() {
		return action;
	}

	public Directions getDirection() {
		return direction;
	}

}
