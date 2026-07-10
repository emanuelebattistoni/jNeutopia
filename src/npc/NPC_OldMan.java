package npc;

import entity.Entity;
import main.GamePanel;

public class NPC_OldMan extends Entity{
	GamePanel gp;
	public NPC_OldMan(GamePanel gp) {
		super(gp);
		this.gp= gp;
		type=type_npc;
	    direction = "up";
		getImage();
		setDialogue();
		collisionOn = false;
		dialogueSet=0;

	}
	public void getImage() {
		up1=setup("/npc/brownBottom", gp.tileSize, gp.tileSize);
		up2=up1;
		headUp= setup("/npc/oldManHead", gp.tileSize, gp.tileSize);
	}
	
	public void setDialogue() {
		dialogues[0][0]="Prendi questo prezioso Libro del Risveglio.";
		dialogues[0][1]="Questo libro permette di poterti\nrisvegliare anche in caso di sconfitta\nin battaglia.";
		dialogues[0][2]="Una volta lasciata questa stanza, dirigiti\nverso il labirinto solo dopo aver ottenuto\nla chiave che si trova ad est.";
		dialogues[0][3]="Fai attenzione ed usa tutti i tuoi poteri.";
		dialogues[1][0] ="Mi dispiace ma non posso più aiutarti.";
	}
	public void speak() {
		if (hasSpoken==true && gp.obj[1][2].opened) {
			dialogueSet = 1;
		}
		startDialogue(this,dialogueSet);
		hasSpoken = true;
	}
}
