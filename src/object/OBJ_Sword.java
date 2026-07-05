package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword extends Entity{
	public OBJ_Sword(GamePanel gp) {
			super(gp);
		 	name = "Sword";
		 	type = type_inUse;
		 	down1 = setup("/objects/sword", gp.tileSize, gp.tileSize);
	}	
}
