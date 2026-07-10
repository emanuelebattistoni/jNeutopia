package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Crystalball extends Entity{
	public static final String objName = "Sfera di Cristallo";

	 public OBJ_Crystalball(GamePanel gp) {
	        super(gp);
	        name = objName;
	        type = type_inUse;
	        down1 = setup("/objects/crystalBall", gp.tileSize, gp.tileSize); 
	    }
}
