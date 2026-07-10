package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BookOfRevival extends Entity{
	public static final String objName = "Libro del Risveglio";
	
	public OBJ_BookOfRevival(GamePanel gp) {
			super(gp);
		 	name =objName;
		 	type = type_inUse;
		 	down1 = setup("/objects/BookOfRevival", gp.tileSize, gp.tileSize);
	}	
}
