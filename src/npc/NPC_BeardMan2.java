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
		up2=up1;
		headUp= setup("/npc/BeardMan", gp.tileSize, gp.tileSize);
	}
	
	public void setDialogue() {
		dialogues[0][0]="Oh! Chi sei tu? Saresti così gentile da\nliberarmi da queste catene?";
		dialogues[0][1]="Mi prendevo cura io di questo labirinto\nprima che Dirth venisse qua e mi incatenasse.";
		dialogues[0][2]="Dovrai usare una bomba per passare\nattraverso il muro alla tua destra.";
		dialogues[0][3]="Prendi pure la bomba nascosta nelle\ncassa se non ne possiedi più!";
	}
	public void speak() {
		startDialogue(this,dialogueSet);
	}
}