package tile_interactive;

import entity.Entity;
import main.GamePanel;

public class InteractiveTile extends Entity{
	GamePanel gp;

	public InteractiveTile(GamePanel gp) {
		super(gp);
		this.gp=gp;
	}
	public void update() {
		
	}
	public InteractiveTile getDestroyedForm() {
		InteractiveTile tile =null;
		return tile;
	}
}
