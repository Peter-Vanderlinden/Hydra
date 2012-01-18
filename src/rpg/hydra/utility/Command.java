package rpg.hydra.utility;


public class Command {
	
	private Actions action;
	private Directions direction;
	
	public Command(Actions action) {
		this.action = action;
	}
	
	public Command(Actions action, Directions direction) {
		this.action = action;
		this.direction = direction;
	}
	
	public Command getCopy() {
		return new Command(this.action,this.direction); 
	}

	public Actions getAction() {
		return action;
	}

	public Directions getDirection() {
		return direction;
	}

}
