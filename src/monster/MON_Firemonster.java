package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Fireball;

public class MON_Firemonster extends Entity {
    GamePanel gp;
    int attackTimer = 0;
    
    public MON_Firemonster(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = 2;
        name = "firemonster";
        defaultSpeed = 0;
        speed = 0;
        maxLife = 10;
        life = maxLife;
        attack = 0; 
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
        up1 = setup("/monster/fireMonster", gp.tileSize, gp.tileSize);
        up2 = up1;
        down1 = up1;
        down2 = up1;
        left1 = up1;
        left2 = up1;
        right1 = up1;
        right2 = up1;
    }
    
    @Override
    public void update() {
        super.update();
        invincible = true;
    }
    
    @Override
    public void setAction() {
        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        if (xDistance > yDistance * 2) {
            if (worldX > gp.player.worldX) direction = "left";
            else direction = "right";
        } 
        else if (yDistance > xDistance * 2) {
            if (worldY > gp.player.worldY) direction = "up";
            else direction = "down";
        } 
        else {
            if (worldY > gp.player.worldY && worldX > gp.player.worldX) direction = "upLeft";
            else if (worldY > gp.player.worldY && worldX < gp.player.worldX) direction = "upRight";
            else if (worldY < gp.player.worldY && worldX > gp.player.worldX) direction = "downLeft";
            else if (worldY < gp.player.worldY && worldX < gp.player.worldX) direction = "downRight";
        }
        attackTimer++;
        if (attackTimer >= 120) {
            OBJ_Fireball fireball = new OBJ_Fireball(gp);            
            fireball.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(fireball);
            attackTimer = 0;
        }
    }

    @Override
    public void draw(java.awt.Graphics2D g2) {
        int screenX = worldX - gp.cameraX;
        int screenY = worldY - gp.cameraY;
        
        if (screenX + gp.tileSize > 0 && screenX < gp.screenWidth &&
            screenY + gp.tileSize > 0 && screenY < gp.screenHeight) {
            g2.drawImage(up1, screenX, screenY, null);
        }
    }
}