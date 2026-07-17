package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Cherry extends Entity {
	GamePanel gp;
	public static final String objName = "Cherry";

	public OBJ_Cherry(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type=type_pickupOnly;
		name=objName;
		value=1;
		down1=setup("/objects/cherry", gp.tileSize, gp.tileSize);
		price=5;
	}
	
	public void use(Entity entity) {
		if(gp.player.life != gp.player.maxLife){
		gp.player.life+=value;
		}
		gp.playSE(21);
	}
}
