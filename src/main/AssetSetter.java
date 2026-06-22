package main;

import entity.NPC_OldMan;
import monster.MON_Slime;
import object.OBJ_Chest;
import object.OBJ_Door;

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject () {
		//nothing for now
	}
	
	public void setNpc() {
	gp.npc[0] = new NPC_OldMan(gp);
	gp.npc[0].worldX = gp.tileSize*40;
	gp.npc[0].worldY = gp.tileSize*20;
	}
	
	public void setMonster() {
		gp.monster[0]= new MON_Slime(gp);
		gp.monster[0].worldX=gp.tileSize*43;
		gp.monster[0].worldY=gp.tileSize*27;
		gp.monster[1]= new MON_Slime(gp);
		gp.monster[1].worldX=gp.tileSize*36;
		gp.monster[1].worldY=gp.tileSize*27;
	}
}
