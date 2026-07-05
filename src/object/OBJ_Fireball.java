package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Fireball extends Projectile {
	
	GamePanel gp;
	 
	public OBJ_Fireball(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Fireball";
		speed = 5;
		maxLife = 50;
		life = maxLife;
		attack = 1;
		alive = false;
		solidArea.x = 12;
		solidArea.y = 12;
		solidArea.width = 24;
		solidArea.height = 24;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	
	public void getImage() {
		up1 = setup("/projectile/fireBall1", gp.tileSize, gp.tileSize);
		up2 = setup("/projectile/fireBall2", gp.tileSize, gp.tileSize);
		down1 = up1;
		down2 = up2;
		left1 = up1;
		left2 = up2;
		right1 = up1;
		right2 = up2;
	}

	@Override
	public void update() {

		collisionOn = false;
		gp.cChecker.checkTile(this);
		
		if(collisionOn == true) {

			alive = false;
		} 
		else {
			super.update();
		}
	}
}