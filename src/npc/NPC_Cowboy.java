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
		up2=setup("/npc/cowboyBottom", gp.tileSize, gp.tileSize);
		headUp= setup("/npc/cowboyHead", gp.tileSize, gp.tileSize);
	}
	
	public void setDialogue() {
		dialogues[0]="Se vuoi recuperare le tue forze, puoi\ntornare al santuario della gentile signora\nanziana.";
		dialogues[1]="Potrai anche salvare la partita e tornare\npiù tardi per finirla.";
		dialogues[2]="Quindi se hai bisogno di fermarti, chiedi\naiuto all'anziana signora dalla grande\nmemoria.";
	}
	public void speak() {
		super.speak();
	}
}
