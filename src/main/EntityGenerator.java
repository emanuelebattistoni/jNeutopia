package main;

import entity.Entity;
import object.OBJ_Armor;
import object.OBJ_Bomb;
import object.OBJ_BookOfRevival;
import object.OBJ_Cherry;
import object.OBJ_Chest;
import object.OBJ_Coin;
import object.OBJ_Crystalball;
import object.OBJ_Fire_Wand;
import object.OBJ_Fireball;
import object.OBJ_GoldCoin;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_Medallion;
import object.OBJ_Potion;
import object.OBJ_Sword;
import object.OBJ_Wing;

public class EntityGenerator {
	GamePanel gp;
	
	public EntityGenerator(GamePanel gp) {
		this.gp=gp;
		
	}
	
	public Entity getObject(String itemName) {
		Entity obj = null;
		switch(itemName) {
		case OBJ_Bomb.objName: obj = new OBJ_Bomb(gp);break;
		case OBJ_Fire_Wand.objName: obj = new OBJ_Fire_Wand(gp);break;
		case OBJ_Key.objName: obj = new OBJ_Key(gp);break;
		case OBJ_Potion.objName: obj = new OBJ_Potion(gp);break;
		case OBJ_Sword.objName: obj = new OBJ_Sword(gp);break;
		case OBJ_Armor.objName: obj = new OBJ_Armor(gp);break;
		case OBJ_BookOfRevival.objName: obj = new OBJ_BookOfRevival(gp);break;
		case OBJ_Crystalball.objName: obj = new OBJ_Crystalball(gp);break;
		case OBJ_Chest.objName:obj = new OBJ_Chest(gp);break;
		case OBJ_Cherry.objName: obj = new OBJ_Cherry(gp);break;
		case OBJ_Fireball.objName :obj = new OBJ_Fireball(gp);break;
		case OBJ_Heart.objName:obj = new OBJ_Heart(gp);break;
		case OBJ_Coin.objName:obj = new OBJ_Coin(gp);break;
		case OBJ_GoldCoin.objName:obj = new OBJ_GoldCoin(gp);break;
		case OBJ_Wing.objName:obj = new OBJ_Wing(gp);break;
		case OBJ_Medallion.objName:obj = new OBJ_Medallion(gp);break;
		}
		return obj;
	}
}
