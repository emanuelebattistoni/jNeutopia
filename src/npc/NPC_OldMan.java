package npc;

import entity.Entity;
import main.GamePanel;

public class NPC_OldMan extends Entity{
	GamePanel gp;
	public NPC_OldMan(GamePanel gp) {
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
		headUp= setup("/npc/oldManHead", gp.tileSize, gp.tileSize);
	}
	
	public void setDialogue() {
		dialogues[0]="Prendi questo prezioso Libro del Risveglio.";
		dialogues[1]="Questo libro ti permetterà di poterti\nrisvegliare anche in caso di sconfitta\nin battaglia.";
		dialogues[2]="Una volta lasciata questa stanza, dirigiti\nverso il labirinto solo dopo aver ottenuto\n la chiave.";
		dialogues[3]="Fai attenzione ed usa tutti i tuoi poteri.";
	}
	public void speak() {
		super.speak();
	}
}
