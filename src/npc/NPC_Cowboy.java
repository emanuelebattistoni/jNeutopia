package npc;

import entity.Entity;
import main.GamePanel;

public class NPC_Cowboy extends Entity{
	GamePanel gp;
	public NPC_Cowboy(GamePanel gp) {
		super(gp);
		this.gp= gp;
		type=type_npc;
	    direction = "up";   // default
		getImage();
		setDialogue();
		collisionOn = false;
	}
	public void getImage() {
		up1=setup("/npc/cowboyBottom", gp.tileSize, gp.tileSize);
		up2=up1;
		headUp= setup("/npc/cowboyHead", gp.tileSize, gp.tileSize);
	}
	
	public void setDialogue() {
		dialogues[0][0]="Se vuoi recuperare le tue forze, puoi\ntornare al santuario della gentile signora\nanziana.";
		dialogues[0][1]="Potrai anche salvare la partita e tornare\npiù tardi per finirla.";
		dialogues[0][2]="Usa le Ali del Ritorno per tornare più\nvelocemente dall'anziana signora.";
		dialogues[0][3]="Quindi se hai bisogno di fermarti, chiedi\naiuto all'anziana signora dalla grande\nmemoria.";
	}
	public void speak() {
		startDialogue(this,dialogueSet);
	}
}
