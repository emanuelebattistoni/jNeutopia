package npc;

import entity.Entity;
import main.GamePanel;

public class NPC_WiseMother extends Entity{
	GamePanel gp;
	public NPC_WiseMother(GamePanel gp) {
		super(gp);
		this.gp=gp;
		type=type_npc;
	    direction = "up";  
		getImage();
		setDialogue();
		collisionOn = false;
	}
	
	public void getImage() {
		up1=setup("/npc/grayBottom", gp.tileSize, gp.tileSize);
		up2=setup("/npc/grayBottom", gp.tileSize, gp.tileSize);
		headUp= setup("/npc/motherHead", gp.tileSize, gp.tileSize);
	}
	
	public void setDialogue() {
		dialogues[0]="Oh! Tu devi essere Jazeta! Grazie al cielo\nsei arrivato. Una terribile tragedia ci ha\ncolpiti.";
		dialogues[1]="A tarda notte, il malvagio demone Dirth è\nvenuto da noi mentre dormivamo.";
		dialogues[2]="Come un fulmine a ciel sereno ha rapito\nla principessa Aurora, l'unica in grado di\nscatenare le forze del bene.";
		dialogues[3]="Dirth ha nascosto la principessa nella\npropria cripta.";
		dialogues[4]="Il potere e la saggezza dei nostri antenati\ne l'amore della principessa saranno la tua\nricompensa.";
		dialogues[5]="Come madre saggia ed anziana, possiedo il\npotere di guarire. Torna quando ti senti\ndebole.";

	}
	public void speak() {
		super.speak();
		gp.player.restoreLife();
	}
}
