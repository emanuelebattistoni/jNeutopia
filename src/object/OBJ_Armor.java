package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Armor extends Entity{
	public static final String objName = "Armatura";
	public OBJ_Armor(GamePanel gp) {
			super(gp);
		 	name = objName;
		 	type = type_inUse;
		 	down1 = setup("/objects/Armor", gp.tileSize, gp.tileSize);
	}	
}
