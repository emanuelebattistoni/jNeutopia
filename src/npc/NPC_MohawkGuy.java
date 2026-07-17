package npc;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Bomb;
import object.OBJ_Fire_Wand;
import object.OBJ_Potion;
import object.OBJ_Wing;

public class NPC_MohawkGuy extends Entity{
	GamePanel gp;
	public NPC_MohawkGuy(GamePanel gp) {
		super(gp);
		this.gp= gp;
		type=type_npc;
	    direction = "up";
		getImage();
		setDialogue();
		setItems();
		collisionOn = false;
	}
	public void getImage() {
		up1=setup("/npc/cowboyBottom", gp.tileSize, gp.tileSize);
		up2=up1;
		headUp= setup("/npc/mohawkMan", gp.tileSize, gp.tileSize);
	}
	
	public void setDialogue() {
		dialogues[0][0] = "Posso venderti dei buoni prezzi per\ndegli oggetti assai utili, pensaci\nbene..."; 
		dialogues[1][0] = "Non ho più niente da offrirti,\nmi dispiace.";
		dialogues[2][0] = "Grazie per aver visitato il bazar!";
		dialogues[3][0] = "Non hai monete a sufficienza\nper comprarlo!";
		dialogues[4][0] = "Non puoi portare più oggetti!";
	}
	
	public void setItems() {
		inventory.add(new OBJ_Potion(gp));
		inventory.add(new OBJ_Bomb(gp));
		inventory.add(new OBJ_Fire_Wand(gp));
		inventory.add(new OBJ_Wing(gp));
	}
	public void speak() {
		if (inventory.isEmpty()) {
			startDialogue(this,1);	

		} else {
			startDialogue(this,0);
			gp.gameState = gp.tradeState;
			gp.ui.npc = this;
		}
	}
}
