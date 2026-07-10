package data;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Armor;
import object.OBJ_Bomb;
import object.OBJ_BookOfRevival;
import object.OBJ_Chest;
import object.OBJ_Crystalball;
import object.OBJ_Fire_Wand;
import object.OBJ_Key;
import object.OBJ_Potion;
import object.OBJ_Sword;

public class SaveLoad {
	GamePanel gp;
	public SaveLoad(GamePanel gp) {
		this.gp=gp;
	}
	
	public void save() {
		try {
			ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream(new File ("save.dat")));
			DataStorage ds = new DataStorage();
			//stats
			ds.maxLife=gp.player.maxLife;
			ds.life=gp.player.life;
			ds.coin=gp.player.coin;
			ds.bomb=gp.player.bomb;
			//inventory
			for (int i=0; i<gp.player.inventory.size();i++){
				ds.itemNames.add(gp.player.inventory.get(i).name);
				ds.itemAmounts.add(gp.player.inventory.get(i).amount);
			}
			//object
			ds.mapObjectNames= new String[gp.maxMap][gp.obj[1].length];
			ds.mapObjectWorldX= new int[gp.maxMap][gp.obj[1].length];
			ds.mapObjectWorldY= new int[gp.maxMap][gp.obj[1].length];
			ds.mapObjectLootNames= new String[gp.maxMap][gp.obj[1].length];
			ds.mapObjectOpened= new boolean[gp.maxMap][gp.obj[1].length];
			for(int mapNum=0; mapNum<gp.maxMap;mapNum++) {
				for (int i = 0;i < gp.obj[1].length; i++) {
					if(gp.obj[mapNum][i]== null) {
						ds.mapObjectNames[mapNum][i]="NA";
					}
					else {
						ds.mapObjectNames[mapNum][i]=gp.obj[mapNum][i].name;
						ds.mapObjectWorldX[mapNum][i]=gp.obj[mapNum][i].worldX;
						ds.mapObjectWorldY[mapNum][i]=gp.obj[mapNum][i].worldY;
						if(gp.obj[mapNum][i].loot!= null) {
							ds.mapObjectLootNames[mapNum][i]=gp.obj[mapNum][i].loot.name;
							ds.mapObjectOpened[mapNum][i]=gp.obj[mapNum][i].opened;
						}
					}
				}
			}
			oos.writeObject(ds);
			
		}
		catch(Exception e) {
			System.out.println("Save Exception");
		}
	}
	public void load() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File ("save.dat")));
			DataStorage ds = (DataStorage)ois.readObject();
			//stats
			gp.player.maxLife=ds.maxLife;
			gp.player.life=ds.life;
			gp.player.coin=ds.coin;
			gp.player.bomb=ds.bomb;
			gp.player.inventory.clear();
			//inventory
			for (int i=0; i<ds.itemNames.size();i++){
				gp.player.inventory.add(gp.eGenerator.getObject(ds.itemNames.get(i)));
				gp.player.inventory.get(i).amount=ds.itemAmounts.get(i);
			}
			//object
			for(int mapNum=0; mapNum < gp.maxMap ; mapNum++) {
				for (int i = 0;i < gp.obj[1].length; i++) {
					if(ds.mapObjectNames[mapNum][i].equals("NA")) {
						gp.obj[mapNum][i]= null;
					}
					else {
						gp.obj[mapNum][i] = gp.eGenerator.getObject(ds.mapObjectNames[mapNum][i]);;
						gp.obj[mapNum][i].worldX=ds.mapObjectWorldX[mapNum][i];
						gp.obj[mapNum][i].worldY=ds.mapObjectWorldY[mapNum][i];
						if(ds.mapObjectLootNames[mapNum][i]!= null) {
							gp.obj[mapNum][i].setLoot(gp.eGenerator.getObject(ds.mapObjectLootNames[mapNum][i]));
							gp.obj[mapNum][i].opened=ds.mapObjectOpened[mapNum][i];
							if(gp.obj[mapNum][i].opened==true) {
								gp.obj[mapNum][i].down1=gp.obj[mapNum][i].image2;
							}
						}
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();		}
	}
	
}
