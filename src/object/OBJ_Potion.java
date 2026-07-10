package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion extends Entity {
	GamePanel gp;
	public static final String objName = "Pozione";

	public OBJ_Potion(GamePanel gp) {
		super(gp);
		this.gp=gp;
		type = type_consumable;
		name = objName;
		down1 = setup("/objects/Potion", gp.tileSize, gp.tileSize);
		price=20;
		stackable=true;
	}
	
	public void use(Entity entity) {
		
		entity.life=gp.player.maxLife;
	}
}
