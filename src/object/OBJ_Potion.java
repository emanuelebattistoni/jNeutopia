package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion extends Entity {
	GamePanel gp;

	public OBJ_Potion(GamePanel gp) {
		super(gp);
		this.gp=gp;
		type = type_consumable;
		name = "Potion";
		down1 = setup("/objects/Potion", gp.tileSize, gp.tileSize);
		price=20;
	}
	
	public void use(Entity entity) {
		
		entity.life=gp.player.maxLife;
	}
}
