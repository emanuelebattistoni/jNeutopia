package monster;

import java.util.Random;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Bomb;
import object.OBJ_Cherry;
import object.OBJ_Coin;
import object.OBJ_GoldCoin;
import object.OBJ_Wing;

public class MON_Scorpion extends Entity {

    GamePanel gp;
    
    public MON_Scorpion(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = 2;
        name = "scorpion";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 5;
        attack=2;
        life = maxLife;
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
        up1 = setup("/monster/scorpionUP1", gp.tileSize, gp.tileSize+gp.tileSize/2);
        up2 = setup("/monster/scorpionUP2", gp.tileSize, gp.tileSize+gp.tileSize/2);
        down1 = setup("/monster/scorpionD1", gp.tileSize, gp.tileSize+gp.tileSize/2);
        down2 = setup("/monster/scorpionD2", gp.tileSize, gp.tileSize+gp.tileSize/2);
        left1 = setup("/monster/scorpionL1", gp.tileSize*2, gp.tileSize);
        left2 = setup("/monster/scorpionL2", gp.tileSize*2, gp.tileSize);
        right1 = setup("/monster/scorpionR1", gp.tileSize*2, gp.tileSize);
        right2 = setup("/monster/scorpionR2", gp.tileSize*2, gp.tileSize);
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