package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_GoldCoin extends Entity {
	GamePanel gp;
	public static final String objName = "Moneta d'oro";

	public OBJ_GoldCoin(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type=type_pickupOnly;
		name=objName;
		value=50;
		down1=setup("/objects/goldCoin", gp.tileSize, gp.tileSize);
	}
	
	public void use(Entity entity) {
		gp.player.coin+=value;
		gp.playSE(22);
	}
}
