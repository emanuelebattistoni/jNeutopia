package npc;

import entity.Entity;
import main.GamePanel;

public class NPC_Princess extends Entity{
	GamePanel gp;
	public NPC_Princess(GamePanel gp) {
		super(gp);
		this.gp= gp;
		type=type_npc;
	    direction = "up";   // default
		getImage();
		setDialogue();
		collisionOn = false;
	}
	public void getImage() {
		up1=setup("/npc/princessBottom", gp.tileSize, gp.tileSize);
		up2=up1;
		headUp= setup("/npc/princessHead", gp.tileSize, gp.tileSize);
	}
	
	public void setDialogue() {
		dialogues[0][0]="Oh! Tu devi essere Jazeta!";
		dialogues[0][1]="Grazie per aver sconfitto Dirth e\nper avermi liberata da questa\ncripta.";
		dialogues[0][2]="Raccogli il medaglione dorato per\nottenere le ricompense che ti erano\nstate promessa dalla madre saggia!";
	}
	public void speak() {
		startDialogue(this,dialogueSet);
	}
}
