package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity{
	GamePanel gp;
	public static final String objName = "Chiave";

	public OBJ_Key(GamePanel gp) {
		super(gp);
		type=type_key;
		name = objName;
		down1=setup("/objects/Key", gp.tileSize, gp.tileSize);
	}
}
