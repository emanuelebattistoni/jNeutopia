package object;

import entity.Entity;
import entity.Projectile;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import main.GamePanel;
import tile_interactive.InteractiveTile;

public class OBJ_Bomb extends Projectile {
	GamePanel gp;
	public static final String objName = "Bomb";
	
	public OBJ_Bomb(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_consumable;
		name = objName;
		speed = 0; 
		maxLife = 60; 
		life = maxLife;
		attack = 2; 
		alive = false;
		price=10;
		stackable=true;
		getImage();
	}
	
	public void getImage() {
		up2 = setup("/projectile/bomb1", gp.tileSize, gp.tileSize);
		up1 = setup("/projectile/bomb2", gp.tileSize, gp.tileSize);
		
		down1 = up1;
		down2 = up2;
		left1 = up1;
		left2 = up2;
		right1 = up1;
		right2 = up2;

		death1 = setup("/projectile/bombD1", gp.tileSize , gp.tileSize);
		death2 = setup("/projectile/bombD2", gp.tileSize, gp.tileSize);
		death3 = setup("/projectile/bombD3", gp.tileSize, gp.tileSize);
		death4 = setup("/projectile/bombD3", gp.tileSize, gp.tileSize);
	}
	
	@Override
	public void use(Entity entity) {
		OBJ_Bomb newBomb = new OBJ_Bomb(gp);
	    newBomb.set(entity.worldX, entity.worldY, entity.direction, true, entity);
	    gp.projectileList.add(newBomb);	}

	public void update() {
	
		if(dying == true) {
			dyingAnimation();
			return;
		}
		
		life--; 
		
		spriteCounter++;
		if(spriteCounter > 12) {
			if(spriteNum == 1) spriteNum = 2;
			else if(spriteNum == 2) spriteNum = 1;
			spriteCounter = 0;
		}

		if(life <= 0) {
			explode();      
			gp.playSE(19); 
			dying = true;   
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		
		if(dying == true) {
			
			
			BufferedImage image = null;
			if (spriteNum == 1) image = death1;
			if (spriteNum == 2) image = death2;
			if (spriteNum == 3) image = death3;
			if (spriteNum == 4) image = death4;
			
			if (image != null) {
				int screenX = worldX - gp.cameraX;
				int screenY = worldY - gp.cameraY;
				int t = gp.tileSize;
				
				if (spriteNum == 1 || spriteNum == 2) {
					g2.drawImage(image, screenX, screenY, null);
				}
				else if (spriteNum == 3 || spriteNum == 4) {
					g2.drawImage(image, screenX - t, screenY - t, null); 
					g2.drawImage(image, screenX,     screenY - t, null); 
					g2.drawImage(image, screenX + t, screenY - t, null); 
					
					g2.drawImage(image, screenX - t, screenY,     null); 
					g2.drawImage(image, screenX,     screenY,     null);
					g2.drawImage(image, screenX + t, screenY,     null); 
					
					g2.drawImage(image, screenX - t, screenY + t, null); 
					g2.drawImage(image, screenX,     screenY + t, null); 
					g2.drawImage(image, screenX + t, screenY + t, null); 
				}
			}
		} 
		else {
			super.draw(g2);
		}
	}
	
	public void explode() {
		int explosionX = worldX - gp.tileSize;
		int explosionY = worldY - gp.tileSize;
		int explosionWidth = gp.tileSize * 3;
		int explosionHeight = gp.tileSize * 3;
		
		Rectangle explosionArea = new Rectangle(explosionX, explosionY, explosionWidth, explosionHeight);
		
		for (int i = 0; i < gp.monster[gp.currentMap].length; i++) {
			Entity target = gp.monster[gp.currentMap][i];
			if (target != null && target.dying == false) {
				Rectangle targetArea = new Rectangle(
					target.worldX + target.solidArea.x,
					target.worldY + target.solidArea.y,
					target.solidArea.width,
					target.solidArea.height
				);
				if (explosionArea.intersects(targetArea)) {
					damageMonster(target);
				}
			}
		}
		for (int i = 0; i < gp.iTile[gp.currentMap].length; i++) {
			Entity targetTile = gp.iTile[gp.currentMap][i];
			if (targetTile != null && targetTile.destructible == true) {
				Rectangle tileArea = new Rectangle(
					targetTile.worldX + targetTile.solidArea.x,
					targetTile.worldY + targetTile.solidArea.y,
					targetTile.solidArea.width,
					targetTile.solidArea.height
				);
				if (explosionArea.intersects(tileArea)) {
					InteractiveTile interactiveTile = (InteractiveTile) targetTile;
					gp.iTile[gp.currentMap][i] = interactiveTile.getDestroyedForm();
				}
			}
		}
	}
	
	private void damageMonster(Entity monster) {
		if (monster.invincible == false) {
			monster.life -= attack;
			monster.invincible = true;
			monster.damageReaction(); 
			if (monster.life <= 0) {
				monster.dying = true;
			}
		}
	}
}