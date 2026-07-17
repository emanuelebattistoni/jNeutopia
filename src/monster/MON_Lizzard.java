package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Bomb;
import object.OBJ_Cherry;
import object.OBJ_Coin;
import object.OBJ_GoldCoin;
import object.OBJ_Wing;

public class MON_Lizzard extends Entity {
    
    GamePanel gp;
    
    public MON_Lizzard(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = 2;
        name = "lizzard";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 5;
        attack=2;
        life = maxLife;
        solidArea.x = 8;          
        solidArea.y = 48;        
        solidArea.width = 32;    
        solidArea.height = 36;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
        loadDeathImage();
    }

    public void getImage() {
        up1 = setup("/monster/lizzardUP1", gp.tileSize, gp.tileSize*2);
        up2 = setup("/monster/lizzardUP2", gp.tileSize, gp.tileSize*2);
        down1 = setup("/monster/lizzardD1", gp.tileSize, gp.tileSize*2);
        down2 = setup("/monster/lizzardD2", gp.tileSize, gp.tileSize*2);
        left1 = setup("/monster/lizzardL1", gp.tileSize+gp.tileSize/2, gp.tileSize*2);
        left2 = setup("/monster/lizzardL2", gp.tileSize+gp.tileSize/2, gp.tileSize*2);
        right1 = setup("/monster/lizzardR1", gp.tileSize+gp.tileSize/2, gp.tileSize*2);
        right2 = setup("/monster/lizzardR2", gp.tileSize+gp.tileSize/2, gp.tileSize*2);
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