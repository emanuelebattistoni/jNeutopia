package npc;

import entity.Entity;
import main.GamePanel;

public class NPC_BeardMan2 extends Entity{
	GamePanel gp;
	public NPC_BeardMan2(GamePanel gp) {
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
		headUp= setup("/npc/BeardMan", gp.tileSize, gp.tileSize);
	}
	
	public void setDialogue() {
		dialogues[0]="I am a kind and gentle Beard man\nwho will help you to prepare\nfor your journey.";
		dialogues[1]="When you are close to a hidden\nmedallion, the compass will\nsound a signal to alert you.";
		dialogues[2]="Go now and search out your\ndestiny.";
	}
	public void speak() {
		super.speak();
	}
}