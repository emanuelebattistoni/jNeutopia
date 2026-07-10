package monster;

import java.util.Random;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Bomb;
import object.OBJ_Cherry;
import object.OBJ_Coin;
import object.OBJ_GoldCoin;
import object.OBJ_Wing;

public class MON_Serpent extends Entity {
	GamePanel gp;
	
	public MON_Serpent(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = 2;
		name = "serpent";
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 3;
		life = maxLife;
		attack=2;
		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		getImage();
		loadDeathImage();
	}

	public void getImage() {
		up1= setup("/monster/serpent", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/serpent2", gp.tileSize, gp.tileSize);
		down1=up1;
		down2=up2;
		left1 = up1;
		left2 = up2;
		right1 = up1;
		right2 = up2;
	}

	public void setAction() {
		 SaveStartPosition();
		
		if(startSaved == true) {
			 checkWanderDistance(maxDistance);
			
			if(returningHome == true) {
				if (worldX > startWorldX && worldY > startWorldY) direction = "upLeft";
				else if (worldX < startWorldX && worldY > startWorldY) direction = "upRight";
				else if (worldX > startWorldX && worldY < startWorldY) direction = "downLeft";
				else if (worldX < startWorldX && worldY < startWorldY) direction = "downRight";
				else if (worldX > startWorldX) direction = "left";
				else if (worldX < startWorldX) direction = "right";
				else if (worldY > startWorldY) direction = "up";
				else if (worldY < startWorldY) direction = "down";
			} else {
				 getRandomDirection();
				}
			}
		}
	

	public void damageReaction() {
		actionLockCounter = 0;
	}
	
	public void checkDrop() {
        int i = new Random().nextInt(100) + 1;
        if(i < 5) {
            dropItem(new OBJ_GoldCoin(gp));
        }
        if(i>=25 && i <45) {
        	dropItem(new OBJ_Coin(gp));
        }
        if(i >=45 && i<50) {
            dropItem(new OBJ_Cherry(gp));
        }
        if(i >=55 && i<60) {
            dropItem(new OBJ_Bomb(gp));
        }
        if(i >=65 && i<75) {
            dropItem(new OBJ_Wing(gp));
        }
    }
}