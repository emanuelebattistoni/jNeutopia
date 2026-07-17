package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin extends Entity {
	GamePanel gp;
	public static final String objName = "Moneta";

	public OBJ_Coin(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type=type_pickupOnly;
		name=objName;
		value=10;
		down1=setup("/objects/coin", gp.tileSize, gp.tileSize);
		
	}
	
	public void use(Entity entity) {
		gp.player.coin+=value;
		gp.playSE(22);
	}
}
