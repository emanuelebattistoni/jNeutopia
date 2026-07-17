package monster;

import java.util.Random;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Bomb;
import object.OBJ_Cherry;
import object.OBJ_Coin;
import object.OBJ_GoldCoin;
import object.OBJ_Wing;

public class MON_Wolf extends Entity {
    GamePanel gp;
    
    public MON_Wolf(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = 2;
        name = "Wolf";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 5;
        life = maxLife;
        attack=2;
        solidArea.x = 13;
        solidArea.y = 36;
        solidArea.width = 46;
        solidArea.height = 60;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
        loadDeathImage();
    }

    public void getImage() {
        up1 = setup("/monster/wolfUP1", gp.tileSize*2, gp.tileSize*2);
        up2 = setup("/monster/wolfUP2", gp.tileSize*2, gp.tileSize*2);
        down1 = setup("/monster/wolfD1", gp.tileSize*2, gp.tileSize*2);
        down2 = setup("/monster/wolfD2", gp.tileSize*2, gp.tileSize*2);
        left1 = setup("/monster/wolfL1", gp.tileSize+gp.tileSize/2, gp.tileSize*2);
        left2 = setup("/monster/wolfL3", gp.tileSize+gp.tileSize/2, gp.tileSize*2);
        right1 = setup("/monster/wolfR1", gp.tileSize+gp.tileSize/2, gp.tileSize*2);
        right2 = setup("/monster/wolfR3", gp.tileSize+gp.tileSize/2, gp.tileSize*2);
    }

    
    public void setAction() {
        SaveStartPosition();
        
        if (startSaved == true) {
            checkWanderDistance(maxDistance);
            
            if (returningHome == true) {
                moveTowards(startWorldX, startWorldY);
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
        if(i >=45 && i<55) {
            dropItem(new OBJ_Cherry(gp));
        }
        if(i >=55 && i<70) {
            dropItem(new OBJ_Bomb(gp));
        }
        if(i >=70 && i<75) {
            dropItem(new OBJ_Wing(gp));
        }
    }
}