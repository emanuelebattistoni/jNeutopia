package object;

import entity.Entity;
import entity.Projectile;
import java.awt.Rectangle;
import main.GamePanel;
import tile_interactive.InteractiveTile;

public class OBJ_Bomb extends Projectile {
	GamePanel gp;
	
	public OBJ_Bomb(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_consumable;
		name = "Bomb";
		speed = 0; 
		maxLife = 60; 
		life = maxLife;
		attack = 2; 
		alive = false;
		price=10;
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
	}
	
	@Override
	public void use(Entity entity) {
	    this.set(entity.worldX, entity.worldY, entity.direction, true, entity);
	    
	    gp.projectileList.add(this);
		
		// gp.playSE(X); (suono di innesco)
	}


	@Override
	public void update() {
		life--; 
		
		spriteCounter++;
		if(spriteCounter > 12) {
			if(spriteNum == 1) spriteNum = 2;
			else if(spriteNum == 2) spriteNum = 1;
			spriteCounter = 0;
		}

		if(life <= 0) {
			explode();
			alive = false;
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
		
		// aggiungere un suono o generare particelle visive
		// gp.playSE(X); 
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