package npc;

import entity.Entity;
import main.GamePanel;

public class NPC_WiseMan extends Entity{
	GamePanel gp;
	public NPC_WiseMan(GamePanel gp) {
		super(gp);
		this.gp= gp;
		type=type_npc;

	    direction = "up";
		getImage();
		setDialogue();
		collisionOn = false;
	}
	public void getImage() {
		up1=setup("/npc/grayBottom", gp.tileSize, gp.tileSize);
		up2=up1;
		headUp= setup("/npc/wiseManHead", gp.tileSize, gp.tileSize);
	}
	
	public void setDialogue() {
		dialogues[0][0]="Ci sono spesso delle stanze che non si\nvedono facilmente.";
		dialogues[0][1]="Puoi usare delle bombe per aprire dei\npassaggi segreti.";
		dialogues[0][2]="Dovrai essere molto intelligente se vorrai\nrisolvere tutti i misteri nascosti di questo\nmondo.";
	}
	public void speak() {
		startDialogue(this,dialogueSet);
	}
}
