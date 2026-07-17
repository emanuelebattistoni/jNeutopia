package npc;

import entity.Entity;
import main.GamePanel;

public class NPC_BeardMan extends Entity{
	GamePanel gp;
	public NPC_BeardMan(GamePanel gp) {
		super(gp);
		this.gp= gp;
		type=type_npc;
	    direction = "up";
		getImage();
		setDialogue();
		collisionOn = false;
	}
	public void getImage() {
		up1=setup("/npc/brownBottom", gp.tileSize, gp.tileSize);
		up2=up1;
		headUp= setup("/npc/beardMan", gp.tileSize, gp.tileSize);
	}
	
	public void setDialogue() {
		dialogues[0][0]="Sono un premuroso e gentile anziano che ti\naiuterà a prepararti per il tuo viaggio.";
		dialogues[0][1]="Raccogli pure questi oggetti che ho riposto\nnelle casse, ti saranno utili.";
		dialogues[0][2]="Ora vai e cerca il tuo destino.";
		dialogues[1][0]="Mi dispiace ma non posso più aiutarti.\nOra vai e cerca il tuo destino.";
	}
	public void speak() {
		if (hasSpoken==true && gp.obj[3][3].opened && gp.obj[3][4].opened) {
			dialogueSet = 1;
		}
		startDialogue(this,dialogueSet);
		hasSpoken = true;
	}
		
}
