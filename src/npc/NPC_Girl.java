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
		up2=up1;
		headUp= setup("/npc/girlHead", gp.tileSize, gp.tileSize);
	}
	
	public void setDialogue() {
		dialogues[0][0]="Quel farabutto di Dirth e le sue dozzine di\ndemoni malvagi proszperano nella sfera\nsotteranea.";
		dialogues[0][1]="Raccogli la chiave contenuta nella cassa per\npoter accedere al labirinto sotteraneo.";
		dialogues[0][2]="Ora vai verso ovest e trova l'accesso del\nlabirinto.";
		dialogues[1][0]="Mi dispiace ma non posso più aiutarti.\nOra vai verso ovest e trova l'accesso del\nlabirinto";
	}
	public void speak() {
		if (hasSpoken==true && gp.obj[2][5].opened) {
			dialogueSet = 1;
		}
		startDialogue(this,dialogueSet);
		hasSpoken = true;
	}
}
