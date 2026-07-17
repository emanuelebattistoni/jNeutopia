package monster;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Bomb;
import object.OBJ_Cherry;
import object.OBJ_Coin;
import object.OBJ_GoldCoin;
import object.OBJ_Wing;

public class MON_Slime extends Entity {
    GamePanel gp;
    public int invincibilityTimer = 0;
    
    public int skillActiveCounter = 0;
    public boolean skillActive = false;
    
    public MON_Slime(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = 2;
        name = "slime";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 3;
        life = maxLife;
        attack = 1;
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
        up1 = setup("/monster/slime", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/slime1", gp.tileSize, gp.tileSize);
        down1 = up1;
        down2 = up2;
        left1 = up1;
        left2 = up2;
        right1 = up1;
        right2 = up2;
        
        inv1 = setup("/monster/slimeInvincible", gp.tileSize, gp.tileSize);
        inv2 = setup("/monster/slimeInvincibleR", gp.tileSize, gp.tileSize);
        inv3 = setup("/monster/slimeInvincible2", gp.tileSize, gp.tileSize);
        inv4 = setup("/monster/slimeInvincibleR2", gp.tileSize, gp.tileSize);
    }
    
    @Override
    public void setAction() {
        if (skillActive == true) {
            speed = 0;
            invincible = true; 
            invincibleCounter = 0; 
            skillActiveCounter++;
            if (skillActiveCounter >= 60) {
                skillActive = false;
                skillActiveCounter = 0;
                invincibilityTimer = 0;
                speed = defaultSpeed;
                invincible = false;
            }
        } 
        else {
            speed = defaultSpeed;
            invincibilityTimer++;          
            if (invincibilityTimer >= 180) {
                skillActive = true;
                invincibilityTimer = 0;
            }
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
    }

    @Override
    public void damageReaction() {
        actionLockCounter = 0;
        invincibilityTimer = 0; 
    }
    
    @Override
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - gp.cameraX;
        int screenY = worldY - gp.cameraY;

        if (screenX + gp.tileSize > 0 && screenX < gp.screenWidth &&
            screenY + gp.tileSize > 0 && screenY < gp.screenHeight) {
            
            if (dying == true) {
                dyingAnimation(); 
                if (spriteNum == 1) image = death1;
                if (spriteNum == 2) image = death2;
                if (spriteNum == 3) image = death3;
                if (spriteNum == 4) image = death4;
            } 
            else if (skillActive == true) {
                if (skillActiveCounter <= 10) image = inv1;
                else if (skillActiveCounter <= 20) image = inv2;
                else if (skillActiveCounter <= 30) image = inv3;
                else if (skillActiveCounter <= 40) image = inv4;
                else if (skillActiveCounter <= 50) image = inv1;
                else image = inv2;
            } 
            else {
                switch (direction) {
                    case "up": image = (spriteNum == 1) ? up1 : up2; break;
                    case "down": image = (spriteNum == 1) ? down1 : down2; break;
                    case "left": image = (spriteNum == 1) ? left1 : left2; break;
                    case "right": image = (spriteNum == 1) ? right1 : right2; break;
                }
            }
            if (invincible == true && dying == false) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6F));
            }
            
            if (image != null) {
                g2.drawImage(image, screenX, screenY, null);
            }
            
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
        }
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