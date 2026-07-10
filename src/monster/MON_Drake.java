package monster;

import java.util.Random;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Cherry;
import object.OBJ_Coin;
import object.OBJ_Fireball;
import object.OBJ_Key; 

public class MON_Drake extends Entity {
    
    GamePanel gp;
    int attackTimer = 0; 
    
    public MON_Drake (GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        type = 2; 
        name = "Drake";
        defaultSpeed = 1;
        speed = defaultSpeed;
        //maxLife = 66;
        maxLife = 1;
        life = maxLife;
        attack=4;
        solidArea.width = 140;
        solidArea.height = 136; 
        solidArea.x = 2; 
        solidArea.y = 18; 
        
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        getImage();
        loadDeathImage();
    }

    public void getImage() {
        up1 = setup("/monster/dragonBossR1", gp.tileSize*3, gp.tileSize*3+10);
        up2 = setup("/monster/dragonBossL2", gp.tileSize*3, gp.tileSize*3+10);
        down1 = setup("/monster/dragonBossL1", gp.tileSize*3, gp.tileSize*3+10);
        down2 = setup("/monster/dragonBossR2", gp.tileSize*3, gp.tileSize*3+10);
        left1 = setup("/monster/dragonBossL1", gp.tileSize*3, gp.tileSize*3+10);
        left2 = setup("/monster/dragonBossR2", gp.tileSize*3, gp.tileSize*3+10);
        right1 = setup("/monster/dragonBossR1", gp.tileSize*3, gp.tileSize*3+10);
        right2 = setup("/monster/dragonBossL2", gp.tileSize*3, gp.tileSize*3+10);
    }

    
    public void setAction() {
        
        attackTimer++;
        
        if (attackTimer >= 180 && attackTimer <= 260) {
            
            speed = 0; 
            
            if (attackTimer == 240) {
                
                int xDistance = Math.abs(worldX - gp.player.worldX);
                int yDistance = Math.abs(worldY - gp.player.worldY);
                if (xDistance > yDistance) {
                    if (worldX > gp.player.worldX) direction = "left";
                    else direction = "right";
                } else {
                    if (worldY > gp.player.worldY) direction = "up";
                    else direction = "down";
                }
                
                int mouthX = worldX +gp.tileSize;
                int mouthY = worldY+10;

                int[] spreads = { -2, -1, 1, 2 };
                
                for (int spread : spreads) {
                    final int currentSpread = spread;
                    
                    OBJ_Fireball fireball = new OBJ_Fireball(gp) {
                        @Override
                        public void update() {
                            super.update();
                            if (alive) {
                               if (direction.equals("left") || direction.equals("right")) {
                                   worldY += currentSpread;
                               } else if (direction.equals("up") || direction.equals("down")) {
                                   worldX += currentSpread;
                               }
                            }
                        }
                    };
                    
                    fireball.set(mouthX, mouthY, direction, true, this);
                    gp.projectileList.add(fireball);
                }
            }
            
        } else {
            speed = defaultSpeed;
            
            actionLockCounter++;
            if(actionLockCounter >= 60) { 
                int xDistance = Math.abs(worldX - gp.player.worldX);
                int yDistance = Math.abs(worldY - gp.player.worldY);
                
                if (xDistance > yDistance) {
                    if (worldX > gp.player.worldX) direction = "left";
                    else direction = "right";
                } else {
                    if (worldY > gp.player.worldY) direction = "up";
                    else direction = "down";
                }
                actionLockCounter = 0;
            }
        }
        
        if (attackTimer > 260) {
            attackTimer = 0;
        }
    }

    public void damageReaction() {
        if (speed > 0) {
            if (worldX > gp.player.worldX) direction = "left";
            else if (worldX < gp.player.worldX) direction = "right";
            else if (worldY > gp.player.worldY) direction = "up";
            else if (worldY < gp.player.worldY) direction = "down";
        }
    }
    
    public void checkDrop() {
    	 dropItem(new OBJ_Key(gp));
    }
}