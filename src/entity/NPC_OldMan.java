package entity;

import main.GamePanel;

public class NPC_OldMan extends Entity{
	
	public NPC_OldMan(GamePanel gp) {
		super(gp);
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
		dialogues[0]="I am a kind and gentle old man\nwho will help you to prepare\nfor your journey.";
		dialogues[1]="When you are close to a hidden\nmedallion, the compass will\nsound a signal to alert you.";
		dialogues[2]="Go now and search out your\ndestiny.";
	}
	public void speak() {
		super.speak();
	}
}
