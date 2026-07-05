package npc;

import entity.Entity;
import main.GamePanel;

public class NPC_Girl extends Entity{
	GamePanel gp;
	public NPC_Girl(GamePanel gp) {
		super(gp);
		this.gp= gp;
		type=type_npc;
	    direction = "up";   // default
		getImage();
		setDialogue();
		collisionOn = false;
	}
	public void getImage() {
		up1=setup("/npc/brownBottom", gp.tileSize, gp.tileSize);
		up2=setup("/npc/brownBottom", gp.tileSize, gp.tileSize);
		headUp= setup("/npc/girlHead", gp.tileSize, gp.tileSize);
	}
	
	public void setDialogue() {
		dialogues[0]="Quel farabutto di Dirth e le sue dozzine di\ndemoni malvagi propsperano nella sfera\nsotteranea.";
		dialogues[1]="Raccogli la chiave contenuta nella cassa per\npoter accedere al labirinto sotteraneo.";
		dialogues[2]="Ora vai verso ovest e trova l'accesso del\nlabirinto.";
	}
	public void speak() {
		super.speak();
	}
}
