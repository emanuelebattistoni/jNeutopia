package monster;

import java.util.Random;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Cherry;
import object.OBJ_Coin;

public class MON_Serpent extends Entity {
	
	int startWorldX, startWorldY;
	boolean startSaved = false;
	int maxDistance = 5;
	boolean returningHome = false;
	GamePanel gp;
	
	public MON_Serpent(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = 2;
		name = "serpent";
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 2;
		life = maxLife;
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
		left1 = setup("/monster/serpent", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/serpent2", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/serpent", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/serpent2", gp.tileSize, gp.tileSize);
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
				if (worldX > startWorldX && worldY > startWorldY) direction = "upLeft";
				else if (worldX < startWorldX && worldY > startWorldY) direction = "upRight";
				else if (worldX > startWorldX && worldY < startWorldY) direction = "downLeft";
				else if (worldX < startWorldX && worldY < startWorldY) direction = "downRight";
				else if (worldX > startWorldX) direction = "left";
				else if (worldX < startWorldX) direction = "right";
				else if (worldY > startWorldY) direction = "up";
				else if (worldY < startWorldY) direction = "down";
			} else {
				actionLockCounter++;
				
				if(actionLockCounter == 120) {
					Random random = new Random();
					int i = random.nextInt(100) + 1;
					
					if(i <= 25) {
						direction = "upLeft";
					}
					else if(i > 25 && i <= 50) {
						direction = "upRight";
					}
					else if(i > 50 && i <= 75) {
						direction = "downLeft";
					}
					else if(i > 75 && i <= 100) {
						direction = "downRight";
					}
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