package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Fireball extends Projectile {
	
	GamePanel gp;
	public static final String objName = "Fireball";

	public OBJ_Fireball(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = objName;
		speed = 5;
		maxLife = 40;
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
		death1=setup("/projectile/fireballD1", gp.tileSize, gp.tileSize);
		death2=setup("/projectile/fireballD2", gp.tileSize, gp.tileSize);
		death3=setup("/projectile/fireballD3", gp.tileSize, gp.tileSize);
	}

	@Override
	public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {

		super.set(worldX, worldY, direction, alive, user);
		if (user == gp.player) {
			this.attack = 1; 
		} else {
			this.attack = 4;
		}
	}

	public void update() {
		if (dying == true) {
			dyingAnimation();
			return; 
		}

		collisionOn = false;
		gp.cChecker.checkTile(this); 
		
		if(collisionOn == true) {
			dying = true; 
		} 
		else {
			super.update();
		}
	}

		
}
