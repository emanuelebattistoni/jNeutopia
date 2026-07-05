package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Crystalball extends Entity{
	 public OBJ_Crystalball(GamePanel gp) {
	        super(gp);
	        name = "Crystalball";
	        type = type_inUse;
	        down1 = setup("/objects/crystalBall", gp.tileSize, gp.tileSize); 
	    }
}
