package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Wing extends Entity {
	GamePanel gp;
	public static final String objName = "Ali del Ritorno";

	public OBJ_Wing(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type=type_consumable;
		name=objName;
		amount=1;
		value=1;
		stackable=true;
		down1=setup("/objects/wing", gp.tileSize, gp.tileSize);
		price=20;
	}
	
	public void use(Entity entity) {
		gp.eHandler.teleport(5,13,8);
	}
}
