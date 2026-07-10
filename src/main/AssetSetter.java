package main;

import monster.MON_Drake;
import monster.MON_Firemonster;
import monster.MON_Fly;
import monster.MON_Jellyfish;
import monster.MON_Lizzard;
import monster.MON_Pslime;
import monster.MON_RedBat;
import monster.MON_RedScorpion;
import monster.MON_Scorpion;
import monster.MON_Serpent;
import monster.MON_Skeleton;
import monster.MON_Slime;
import monster.MON_Wolf;
import npc.NPC_BeardMan;
import npc.NPC_BeardMan2;
import npc.NPC_Cowboy;
import npc.NPC_Girl;
import npc.NPC_MohawkGuy;
import npc.NPC_OldMan;
import npc.NPC_Princess;
import npc.NPC_WiseMan;
import npc.NPC_WiseMother;
import object.OBJ_Bomb;
import object.OBJ_BookOfRevival;
import object.OBJ_Chest;
import object.OBJ_Coin;
import object.OBJ_Crystalball;
import object.OBJ_GoldCoin;
import object.OBJ_Key;
import object.OBJ_Medallion;
import object.OBJ_Potion;
import tile_interactive.IT_BossDoorDL;
import tile_interactive.IT_BossDoorDR;
import tile_interactive.IT_BossDoorTL;
import tile_interactive.IT_BossDoorTR;
import tile_interactive.IT_Door;
import tile_interactive.IT_WallBL;
import tile_interactive.IT_WallBR;
import tile_interactive.IT_WallDL;
import tile_interactive.IT_WallDR;
import tile_interactive.IT_WallL;
import tile_interactive.IT_WallR;
import tile_interactive.IT_WallTL;
import tile_interactive.IT_WallTR;

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject () {
		int i =0;
		int mapNum=0;
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_Coin(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize*40;
		gp.obj[mapNum][i].worldY = gp.tileSize*6;
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_GoldCoin(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize*39;
		gp.obj[mapNum][i].worldY = gp.tileSize*6;
		mapNum=1;
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_BookOfRevival(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize*11;
		gp.obj[mapNum][i].worldY = gp.tileSize*6;
		i++;
		mapNum=3;
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_Potion(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize*9;
		gp.obj[mapNum][i].worldY = gp.tileSize*6;
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_Bomb(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize*11;
		gp.obj[mapNum][i].worldY = gp.tileSize*6;
		i++;
		mapNum=2;
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_Key(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize*10;
		gp.obj[mapNum][i].worldY = gp.tileSize*6;
		i++;
		mapNum=6;
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_Crystalball(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize*7;
		gp.obj[mapNum][i].worldY = gp.tileSize*40;
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_Bomb(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize*7;
		gp.obj[mapNum][i].worldY = gp.tileSize*33;
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_Key(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize*46;
		gp.obj[mapNum][i].worldY = gp.tileSize*32;
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_Bomb(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize*17;
		gp.obj[mapNum][i].worldY = gp.tileSize*18;
		i++;
		mapNum=6;
		gp.obj[mapNum][i] = new OBJ_Medallion(gp);;
		gp.obj[mapNum][i].worldX = gp.tileSize*57;
		gp.obj[mapNum][i].worldY = gp.tileSize*2;
		i++;
		
		
	}
	//chest
	
	
 
	public void setNpc() {
		int i = 0;
		int mapNum=5;
		gp.npc[mapNum][i]= new NPC_WiseMother(gp);
		gp.npc[mapNum][i].worldX=gp.tileSize*13;
		gp.npc[mapNum][i].worldY=gp.tileSize*6;
		i++;
		mapNum=1;
		gp.npc[mapNum][i]= new NPC_OldMan(gp);
		gp.npc[mapNum][i].worldX=gp.tileSize*13;
		gp.npc[mapNum][i].worldY=gp.tileSize*6;
		i++;
		mapNum=3;
		gp.npc[mapNum][i]= new NPC_BeardMan(gp);
		gp.npc[mapNum][i].worldX=gp.tileSize*13;
		gp.npc[mapNum][i].worldY=gp.tileSize*6;
		i++;
		mapNum=4;
		gp.npc[mapNum][i]= new NPC_Cowboy(gp);
		gp.npc[mapNum][i].worldX=gp.tileSize*12;
		gp.npc[mapNum][i].worldY=gp.tileSize*5;
		i++;
		mapNum=8;
		gp.npc[mapNum][i]= new NPC_MohawkGuy(gp);
		gp.npc[mapNum][i].worldX=gp.tileSize*13;
		gp.npc[mapNum][i].worldY=gp.tileSize*6;
		i++;
		mapNum=7;
		gp.npc[mapNum][i]= new NPC_WiseMan(gp);
		gp.npc[mapNum][i].worldX=gp.tileSize*13;
		gp.npc[mapNum][i].worldY=gp.tileSize*6;
		i++;
		mapNum=2;
		gp.npc[mapNum][i]= new NPC_Girl(gp);
		gp.npc[mapNum][i].worldX=gp.tileSize*13;
		gp.npc[mapNum][i].worldY=gp.tileSize*6;
		mapNum=6;
		gp.npc[mapNum][i]= new NPC_BeardMan2(gp);
		gp.npc[mapNum][i].worldX=gp.tileSize*24;
		gp.npc[mapNum][i].worldY=gp.tileSize*20;
		i++;
		gp.npc[mapNum][i]= new NPC_Princess(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*55;
		gp.npc[mapNum][i].worldY = gp.tileSize*3;
	}
	
	public void setMonster() {
		int i = 0;
		int mapNum=0;

		//MONSTER
		//1
		gp.monster[mapNum][i]= new MON_Slime(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*43;
		gp.monster[mapNum][i].worldY=gp.tileSize*27;
		i++;
		gp.monster[mapNum][i]= new MON_Slime(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*36;
		gp.monster[mapNum][i].worldY=gp.tileSize*27;
		i++;
		//2
		gp.monster[mapNum][i]= new MON_Slime(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*51;
		gp.monster[mapNum][i].worldY=gp.tileSize*29;
		i++;
		gp.monster[mapNum][i]= new MON_Slime(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*59;
		gp.monster[mapNum][i].worldY=gp.tileSize*28;
		i++;
		gp.monster[mapNum][i]= new MON_Slime(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*55;
		gp.monster[mapNum][i].worldY=gp.tileSize*26;
		i++;
		//3
		gp.monster[mapNum][i]= new MON_Slime(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*54;
		gp.monster[mapNum][i].worldY=gp.tileSize*19;
		i++;
		gp.monster[mapNum][i]= new MON_Slime(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*57;
		gp.monster[mapNum][i].worldY=gp.tileSize*19;
		i++;
		gp.monster[mapNum][i]= new MON_Slime(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*54;
		gp.monster[mapNum][i].worldY=gp.tileSize*14;
		i++;
		gp.monster[mapNum][i]= new MON_Slime(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*57;
		gp.monster[mapNum][i].worldY=gp.tileSize*14;
		i++;
		gp.monster[mapNum][i]= new MON_RedScorpion(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*52;
		gp.monster[mapNum][i].worldY=gp.tileSize*16;
		i++;
		gp.monster[mapNum][i]= new MON_RedScorpion(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*59;
		gp.monster[mapNum][i].worldY=gp.tileSize*14;
		i++;
		//4
		gp.monster[mapNum][i]= new MON_Fly(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*54;
		gp.monster[mapNum][i].worldY=gp.tileSize*3;
		i++;
		gp.monster[mapNum][i]= new MON_Fly(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*58;
		gp.monster[mapNum][i].worldY=gp.tileSize*6;
		i++;
		//5
		gp.monster[mapNum][i]= new MON_Slime(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*41;
		gp.monster[mapNum][i].worldY=gp.tileSize*8;
		i++;
		gp.monster[mapNum][i]= new MON_Slime(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*41;
		gp.monster[mapNum][i].worldY=gp.tileSize*4;
		i++;
		//6
		gp.monster[mapNum][i]= new MON_Slime(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*25;
		gp.monster[mapNum][i].worldY=gp.tileSize*29;
		i++;
		gp.monster[mapNum][i]= new MON_Slime(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*23;
		gp.monster[mapNum][i].worldY=gp.tileSize*26;
		i++;
		gp.monster[mapNum][i]= new MON_Slime(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*20;
		gp.monster[mapNum][i].worldY=gp.tileSize*26	;
		i++;
		//7
		gp.monster[mapNum][i]= new MON_Slime(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*23;
		gp.monster[mapNum][i].worldY=gp.tileSize*19;
		i++;
		gp.monster[mapNum][i]= new MON_Slime(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*20;
		gp.monster[mapNum][i].worldY=gp.tileSize*14;
		i++;
		gp.monster[mapNum][i]= new MON_Slime(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*26;
		gp.monster[mapNum][i].worldY=gp.tileSize*14;
		i++;
		//8
		gp.monster[mapNum][i]= new MON_Slime(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*10;
		gp.monster[mapNum][i].worldY=gp.tileSize*27;
		i++;
		//9
		gp.monster[mapNum][i]= new MON_Slime(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*12;
		gp.monster[mapNum][i].worldY=gp.tileSize*13;
		i++;
		gp.monster[mapNum][i]= new MON_Slime(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*10;
		gp.monster[mapNum][i].worldY=gp.tileSize*17;
		i++;
		//10
		gp.monster[mapNum][i]= new MON_Slime(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*12;
		gp.monster[mapNum][i].worldY=gp.tileSize*6;
		i++;
		gp.monster[mapNum][i]= new MON_RedScorpion(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*3;
		gp.monster[mapNum][i].worldY=gp.tileSize*7;
		i++;
		//11
		gp.monster[mapNum][i]= new MON_Lizzard(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*23;
		gp.monster[mapNum][i].worldY=gp.tileSize*5;
		//dungeon
		mapNum=6;
		//2
		gp.monster[mapNum][i]= new MON_Pslime(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*20;
		gp.monster[mapNum][i].worldY=gp.tileSize*62;
		i++;
		gp.monster[mapNum][i]= new MON_Pslime(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*27;
		gp.monster[mapNum][i].worldY=gp.tileSize*62;
		i++;
		gp.monster[mapNum][i]= new MON_Pslime(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*25;
		gp.monster[mapNum][i].worldY=gp.tileSize*66;
		i++;
		//1
		gp.monster[mapNum][i]= new MON_RedBat(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*24;
		gp.monster[mapNum][i].worldY=gp.tileSize*76;
		i++;
		//3
		gp.monster[mapNum][i]= new MON_RedBat(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*41;
		gp.monster[mapNum][i].worldY=gp.tileSize*53;
		i++;
		gp.monster[mapNum][i]= new MON_RedBat(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*43;
		gp.monster[mapNum][i].worldY=gp.tileSize*52;
		i++;
		gp.monster[mapNum][i]= new MON_RedBat(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*45;
		gp.monster[mapNum][i].worldY=gp.tileSize*57;
		i++;
		//4
		gp.monster[mapNum][i]= new MON_RedBat(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*6;
		gp.monster[mapNum][i].worldY=gp.tileSize*51;
		i++;
		gp.monster[mapNum][i]= new MON_RedBat(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*7;
		gp.monster[mapNum][i].worldY=gp.tileSize*52;
		i++;
		gp.monster[mapNum][i]= new MON_RedBat(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*6;
		gp.monster[mapNum][i].worldY=gp.tileSize*54;
		i++;
		gp.monster[mapNum][i]= new MON_RedBat(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*6;
		gp.monster[mapNum][i].worldY=gp.tileSize*56;
		i++;
		gp.monster[mapNum][i]= new MON_RedBat(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*9;
		gp.monster[mapNum][i].worldY=gp.tileSize*54;
		i++;
		gp.monster[mapNum][i]= new MON_RedBat(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*13;
		gp.monster[mapNum][i].worldY=gp.tileSize*57;
		i++;
		//5
		gp.monster[mapNum][i]= new MON_Skeleton(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*23;
		gp.monster[mapNum][i].worldY=gp.tileSize*30;
		i++;
		//6
		gp.monster[mapNum][i]= new MON_Wolf(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*8;
		gp.monster[mapNum][i].worldY=gp.tileSize*32;
		i++;
		gp.monster[mapNum][i]= new MON_Wolf(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*12;
		gp.monster[mapNum][i].worldY=gp.tileSize*29;
		i++;
		gp.monster[mapNum][i]= new MON_Wolf(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*5;
		gp.monster[mapNum][i].worldY=gp.tileSize*35;
		i++;
		//7
		gp.monster[mapNum][i]= new MON_Serpent(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*37;
		gp.monster[mapNum][i].worldY=gp.tileSize*31;
		i++;
		gp.monster[mapNum][i]= new MON_Serpent(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*37;
		gp.monster[mapNum][i].worldY=gp.tileSize*35;
		i++;
		gp.monster[mapNum][i]= new MON_Serpent(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*42;
		gp.monster[mapNum][i].worldY=gp.tileSize*31;
		i++;
		gp.monster[mapNum][i]= new MON_Serpent(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*42;
		gp.monster[mapNum][i].worldY=gp.tileSize*34;
		i++;
		//8
		gp.monster[mapNum][i]= new MON_Scorpion(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*36;
		gp.monster[mapNum][i].worldY=gp.tileSize*18;
		i++;
		gp.monster[mapNum][i]= new MON_Scorpion(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*38;
		gp.monster[mapNum][i].worldY=gp.tileSize*23;
		i++;
		gp.monster[mapNum][i]= new MON_Scorpion(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*40;
		gp.monster[mapNum][i].worldY=gp.tileSize*18;
		i++;
		gp.monster[mapNum][i]= new MON_Scorpion(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*42;
		gp.monster[mapNum][i].worldY=gp.tileSize*23;
		i++;
		gp.monster[mapNum][i]= new MON_Scorpion(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*44;
		gp.monster[mapNum][i].worldY=gp.tileSize*18;
		i++;
		gp.monster[mapNum][i]= new MON_Scorpion(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*46;
		gp.monster[mapNum][i].worldY=gp.tileSize*23;
		i++;
		//9
		gp.monster[mapNum][i]= new MON_Skeleton(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*53;
		gp.monster[mapNum][i].worldY=gp.tileSize*19;
		i++;
		gp.monster[mapNum][i]= new MON_Skeleton(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*53;
		gp.monster[mapNum][i].worldY=gp.tileSize*24;
		i++;
		gp.monster[mapNum][i]= new MON_Skeleton(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*58;
		gp.monster[mapNum][i].worldY=gp.tileSize*19;
		i++;
		gp.monster[mapNum][i]= new MON_Skeleton(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*58;
		gp.monster[mapNum][i].worldY=gp.tileSize*24;
		i++;
		gp.monster[mapNum][i]= new MON_Jellyfish(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*14;
		gp.monster[mapNum][i].worldY=gp.tileSize*40;
		i++;
		gp.monster[mapNum][i]= new MON_Jellyfish(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*13;
		gp.monster[mapNum][i].worldY=gp.tileSize*45;
		i++;
		gp.monster[mapNum][i]= new MON_Jellyfish(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*2;
		gp.monster[mapNum][i].worldY=gp.tileSize*45;
		i++;
		gp.monster[mapNum][i]= new MON_Jellyfish(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*2;
		gp.monster[mapNum][i].worldY=gp.tileSize*41;
		i++;
		gp.monster[mapNum][i]= new MON_Jellyfish(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*19;
		gp.monster[mapNum][i].worldY=gp.tileSize*32;
		i++;
		gp.monster[mapNum][i]= new MON_Jellyfish(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*27;
		gp.monster[mapNum][i].worldY=gp.tileSize*32;
		i++;
		gp.monster[mapNum][i]= new MON_Firemonster(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*23;
		gp.monster[mapNum][i].worldY=gp.tileSize*54;
		i++;
		
		
		//boss
		gp.monster[mapNum][i]= new MON_Drake(gp);
		gp.monster[mapNum][i].worldX=gp.tileSize*55;
		gp.monster[mapNum][i].worldY=gp.tileSize*7;
		i++;
		
	}	
	public void setInteractivetile() {
		int mapNum=6;
		int i =0;
		//1
		gp.iTile[mapNum][i]= new IT_WallTL(gp, 15,41);
		i++;
		gp.iTile[mapNum][i]= new IT_WallTR(gp, 16,41);
		i++;
		gp.iTile[mapNum][i]= new IT_WallDL(gp, 15,42);
		i++;
		gp.iTile[mapNum][i]= new IT_WallDR(gp, 16,42);
		i++;
		gp.iTile[mapNum][i]= new IT_WallL(gp, 15,43);
		i++;
		gp.iTile[mapNum][i]= new IT_WallR(gp, 16,43);
		i++;
		gp.iTile[mapNum][i]= new IT_WallBL(gp, 15,44);
		i++;
		gp.iTile[mapNum][i]= new IT_WallBR(gp, 16,44);
		i++;
		//2
		gp.iTile[mapNum][i]= new IT_WallTL(gp, 31,19);
		i++;
		gp.iTile[mapNum][i]= new IT_WallTR(gp, 32,19);
		i++;
		gp.iTile[mapNum][i]= new IT_WallDL(gp, 31,20);
		i++;
		gp.iTile[mapNum][i]= new IT_WallDR(gp, 32,20);
		i++;
		gp.iTile[mapNum][i]= new IT_WallL(gp, 31,21);
		i++;
		gp.iTile[mapNum][i]= new IT_WallR(gp, 32,21);
		i++;
		gp.iTile[mapNum][i]= new IT_WallBL(gp, 31,22);
		i++;
		gp.iTile[mapNum][i]= new IT_WallBR(gp, 32,22);
		i++;
		gp.iTile[mapNum][i]= new IT_BossDoorDL(gp, 55,17);
		i++;
		gp.iTile[mapNum][i]= new IT_BossDoorDR(gp, 56,17);
		i++;
		gp.iTile[mapNum][i]= new IT_BossDoorTL(gp, 55,16);
		i++;
		gp.iTile[mapNum][i]= new IT_BossDoorTR(gp, 56,16);
		i++;
		gp.iTile[mapNum][i]= new IT_BossDoorDL(gp, 55,6);
		i++;
		gp.iTile[mapNum][i]= new IT_BossDoorDR(gp, 56,6);
		i++;
		gp.iTile[mapNum][i]= new IT_BossDoorTL(gp, 55,5);
		i++;
		gp.iTile[mapNum][i]= new IT_BossDoorTR(gp, 56,5);
		i++;
		mapNum=0;
		gp.iTile[mapNum][i]= new IT_Door(gp, 25,6);
		i++;
		
	}
}
