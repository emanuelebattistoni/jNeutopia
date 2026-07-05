package npc;

import entity.Entity;
import main.GamePanel;

public class NPC_BeardMan extends Entity{
	GamePanel gp;
	public NPC_BeardMan(GamePanel gp) {
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
		dialogues[0]="Sono un premuroso e gentile anziano che ti\naiuterà a prepararti per il tuo viaggio.";
		dialogues[1]="Raccogli pure questi oggetti che ho riposto\nnelle casse, ti saranno utili.";
		dialogues[2]="Ora vai e cerca il tuo destino.";
	}
	public void speak() {
		super.speak();
	}
}
