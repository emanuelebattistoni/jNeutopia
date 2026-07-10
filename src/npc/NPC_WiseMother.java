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
		dialogueSet=-1;
	}
	
	public void getImage() {
		up1=setup("/npc/grayBottom", gp.tileSize, gp.tileSize);
		up2=up1;
		headUp= setup("/npc/motherHead", gp.tileSize, gp.tileSize);
	}
	
	public void setDialogue() {
		dialogues[0][0]="Oh! Tu devi essere Jazeta! Grazie al cielo\nsei arrivato. Una terribile tragedia ci ha\ncolpiti.";
		dialogues[0][1]="A tarda notte, il malvagio demone Dirth è\nvenuto da noi mentre dormivamo.";
		dialogues[0][2]="Come un fulmine a ciel sereno ha rapito\nla principessa Aurora, l'unica in grado di\nscatenare le forze del bene.";
		dialogues[0][3]="Dirth ha nascosto la principessa nella\npropria cripta.";
		dialogues[0][4]="Il potere e la saggezza dei nostri antenati\ne l'amore della principessa saranno la tua\nricompensa.";
		dialogues[0][5]="Come madre saggia ed anziana, possiedo il\npotere della guarigione. Torna quando ti\nsenti debole.";
		dialogues[0][6]="Vieni a parlare con me quando vuoi salvare\nla partita in corso!";
		dialogues[1][0]="Vai, non c'è tempo da perdere!";
		dialogues[1][1]="Come madre saggia ed anziana, possiedo il\npotere della guarigione. Torna quando ti\nsenti debole.";
		dialogues[1][2]="Vieni a parlare con me quando vuoi salvare\nla partita in corso!";

	}
	public void speak() {
		startDialogue(this,dialogueSet);
		dialogueSet++;
		gp.player.restoreLife();
		gp.saveLoad.save();
		if(dialogues[dialogueSet][0] == null) {
			dialogueSet=1;
		}
	}
}
