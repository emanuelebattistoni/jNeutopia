package npc;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Bomb;
import object.OBJ_Cherry;
import object.OBJ_Fire_Wand;
import object.OBJ_Fireball;
import object.OBJ_Potion;

public class NPC_MohawkGuy extends Entity{
	GamePanel gp;
	public NPC_MohawkGuy(GamePanel gp) {
		super(gp);
		this.gp= gp;
		type=type_npc;
	    direction = "up";   // default
		getImage();
		setDialogue();
		setItems();
		collisionOn = false;
	}
	public void getImage() {
		up1=setup("/npc/brownBottom", gp.tileSize, gp.tileSize);
		up2=setup("/npc/brownBottom", gp.tileSize, gp.tileSize);
		headUp= setup("/npc/mohawkMan", gp.tileSize, gp.tileSize);
	}
	
	public void setDialogue() {
		dialogues[0]="Posso venderti dei buoni prezzi per\ndegli oggetti assai utili, pensaci\nbene...";
	}
	
	public void setItems() {
		inventory.add(new OBJ_Potion(gp));
		inventory.add(new OBJ_Bomb(gp));
		inventory.add(new OBJ_Fire_Wand(gp));
	}
	public void speak() {
		super.speak();
		gp.gameState=gp.tradeState;
		gp.ui.npc = this;
		
	}
}
