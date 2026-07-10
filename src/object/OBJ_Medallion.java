package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Medallion extends Entity{
	GamePanel gp;
	public static final String objName="Medallion";
	public OBJ_Medallion(GamePanel gp) {
		super(gp);
		this.gp=gp;
		type=type_pickupOnly;
		name=objName;
		down1=setup("/objects/medallion",gp.tileSize,gp.tileSize);
		setDialogues();
	}
	
	public void setDialogues() {
		dialogues[0][0]="Hai ottenuto il medaglione!";
		dialogues[0][1]="Il potere e la saggezza degli antenati sono\nfinalmente tuoi!";
	}

	public void use(Entity entity) {
		gp.gameState=gp.cutsceneState;
		gp.csManager.sceneNum=gp.csManager.ending;
		gp.csManager.scenePhase=0;
	}
}