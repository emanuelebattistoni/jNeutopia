package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Bomb;
import object.OBJ_Cherry;
import object.OBJ_Coin;
import object.OBJ_GoldCoin;
import object.OBJ_Wing;

public class MON_Jellyfish extends Entity {
    
    
    GamePanel gp;
    
    public MON_Jellyfish(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = 2;
        name = "Jellyfish";
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
        attackArea.width = 48;
        attackArea.height = 48;
        getImage();
        getAttackImage();
        loadDeathImage();
    }

    public void getImage() {
        up1 = setup("/monster/jellyfish", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/jellyfish1", gp.tileSize, gp.tileSize);
        down1 = up1;
        down2 = up2;
        left1 = up1;
        left2 = up2;
        right1 = up1;
        right2 = up2;
    }
    public void getAttackImage() {
    	attackLeft=setup("/monster/jellyfishAttackLeft", gp.tileSize*2, gp.tileSize);
    	attackRight=setup("/monster/jellyfishAttackRight", gp.tileSize*2, gp.tileSize);
    	attackLeft2=setup("/monster/jellyfishAttackLeft2", gp.tileSize*2, gp.tileSize);
    	attackRight2=setup("/monster/jellyfishAttackRight2", gp.tileSize*2, gp.tileSize);	
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
        if (attacking == false) {
            int xDis = getXdistance(gp.player);
            int yDis = getYdistance(gp.player); 
            if (yDis < gp.tileSize && xDis <= gp.tileSize) {
                int i = new Random().nextInt(5); 
                if (i == 0) {
                    if (gp.player.worldX < worldX) {
                        direction = "left"; 
                    } else if (gp.player.worldX > worldX) {
                        direction = "right"; 
                    }
                    attacking = true;
                    spriteNum = 1;
                    shotAvaibleCounter = 0;
                }
            }
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;
    }
    
    @Override
    public void checkAttackOrNot(int rate, int straight, int horizontal) {
        if (direction.equals("up") || direction.equals("down")) {
            return; 
        }
        super.checkAttackOrNot(rate, straight, horizontal);
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