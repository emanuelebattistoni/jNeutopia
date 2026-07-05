package monster;

import java.util.Random;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Cherry;
import object.OBJ_Coin;

public class MON_Wolf extends Entity {
    
    int startWorldX, startWorldY;
    boolean startSaved = false;
    int maxDistance = 5;
    boolean returningHome = false;
    
    GamePanel gp;
    
    public MON_Wolf(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = 2;
        name = "wolf";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 3;
        life = maxLife;
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
    
    public void loadDeathImage() {
        death1 = setup("/monster/dMonster1", gp.tileSize, gp.tileSize);
        death2 = setup("/monster/dMonster2", gp.tileSize, gp.tileSize);
        death3 = setup("/monster/dMonster3", gp.tileSize, gp.tileSize);
        death4 = setup("/monster/dMonster4", gp.tileSize, gp.tileSize);
    }
    
    public void setAction() {
        if(startSaved == false) {
            if(worldX != 0 && worldY != 0) {
                startWorldX = worldX;
                startWorldY = worldY;
                startSaved = true;
            }
        }
        
        if(startSaved == true) {
            int xDistance = Math.abs(worldX - startWorldX) / gp.tileSize;
            int yDistance = Math.abs(worldY - startWorldY) / gp.tileSize;
            
            if(xDistance > maxDistance || yDistance > maxDistance) {
                returningHome = true;
            }
            
            if(returningHome == true && xDistance < 2 && yDistance < 2) {
                returningHome = false;
            }
            
            if(returningHome == true) {
                if (worldX > startWorldX) direction = "left";
                else if (worldX < startWorldX) direction = "right";
                else if (worldY > startWorldY) direction = "up";
                else if (worldY < startWorldY) direction = "down";
                
            } else {
                actionLockCounter++;
                
                if(actionLockCounter == 120) {
                    Random random = new Random();
                    int i = random.nextInt(100) + 1;
                    
                    if (i <= 25) { direction = "up"; }
                    else if (i > 25 && i <= 50) { direction = "down"; }
                    else if (i > 50 && i <= 75) { direction = "left"; }
                    else if (i > 75 && i <= 100) { direction = "right"; }
                    
                    actionLockCounter = 0;
                }
            }
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;
    }
    
    public void checkDrop() {
        int i = new Random().nextInt(100) + 1;
        if(i < 25) {
            dropItem(new OBJ_Coin(gp));
        }
        if(i >= 75) {
            dropItem(new OBJ_Cherry(gp));
        }
    }
}