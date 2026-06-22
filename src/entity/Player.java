package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity{

	KeyHandler keyH;

	public final int screenX;
	public final int screenY;

	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.keyH = keyH;
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		solidArea = new Rectangle();
		solidArea.x=16;
		solidArea.y=10;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width=18;
		solidArea.height=32;
		attackArea.width = 36;
		attackArea.height =36;

		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
	}

	public void setDefaultValues() {
		//player position in the map
		worldX = gp.tileSize * 40;
		worldY = gp.tileSize * 19;
		speed = 5;
		direction = "up";
		//player status
		maxLife = 10;
		life = maxLife;
		coin = 0;
				
	}

	public void getPlayerImage() {
		up1 = setup("/player/JazetaUp1", gp.tileSize, gp.tileSize);
		up2 = setup("/player/JazetaUp2", gp.tileSize, gp.tileSize);
		up3 = setup("/player/JazetaUp3", gp.tileSize, gp.tileSize);
		up4 = setup("/player/JazetaUp4", gp.tileSize, gp.tileSize);
		down1 = setup("/player/JazetaDown1", gp.tileSize, gp.tileSize);
		down2 = setup("/player/JazetaDown2", gp.tileSize, gp.tileSize);
		down3 = setup("/player/JazetaDown3", gp.tileSize, gp.tileSize);
		down4 = setup("/player/JazetaDown4", gp.tileSize, gp.tileSize);
		left1 = setup("/player/JazetaLeft1", gp.tileSize, gp.tileSize);
		left2 = setup("/player/JazetaLeft2", gp.tileSize, gp.tileSize);
		left3 = setup("/player/JazetaLeft3", gp.tileSize, gp.tileSize);
		left4 = setup("/player/JazetaLeft4", gp.tileSize, gp.tileSize);
		right1 = setup("/player/JazetaRight1", gp.tileSize, gp.tileSize);
		right2 = setup("/player/JazetaRight2", gp.tileSize, gp.tileSize);
		right3 = setup("/player/JazetaRight3", gp.tileSize, gp.tileSize);
		right4 = setup("/player/JazetaRight4", gp.tileSize, gp.tileSize);
		headUp    = setup("/player/HeadUp", gp.tileSize, gp.tileSize);
		headDown  = setup("/player/HeadDown", gp.tileSize, gp.tileSize);
		headLeft  = setup("/player/HeadLeft", gp.tileSize, gp.tileSize);
		headRight = setup("/player/HeadRight", gp.tileSize, gp.tileSize);
	}
	
	public void getPlayerAttackImage() {
		attackUp= setup("/player/uAttack", gp.tileSize - gp.tileSize/4, gp.tileSize +gp.tileSize/4);
		attackDown= setup("/player/dAttack", gp.tileSize - gp.tileSize/4, gp.tileSize + gp.tileSize/8);
		attackLeft= setup("/player/lAttack", gp.tileSize + gp.tileSize/8, gp.tileSize - gp.tileSize/4);
		attackRight= setup("/player/rAttack", gp.tileSize + gp.tileSize/8, gp.tileSize - gp.tileSize/4);
		attackBodyUp = setup("/player/JazetaAttackUp", gp.tileSize, gp.tileSize);
		attackBodyDown = setup("/player/JazetaAttackDown", gp.tileSize, gp.tileSize);
		attackBodyLeft = setup("/player/JazetaAttackLeft", gp.tileSize, gp.tileSize);
		attackBodyRight = setup("/player/JazetaAttackRight", gp.tileSize, gp.tileSize);
	}


	public void update() {
		if(attacking == true) {
			attacking();
		}
		else if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true ||keyH.rightPressed == true || keyH.zetapressed == true) {
			if(keyH.upPressed == true) {
				direction = "up";

			} else if(keyH.downPressed == true) {
				direction = "down";

			} else if(keyH.leftPressed == true) {
				direction = "left";

			} else if(keyH.rightPressed == true) {
				direction = "right";

			}
			// check tile collision
			collisionOn = false;
			gp.cChecker.checkTile(this);

			//CHECK NPC COLLISION
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			//check monster collision
			int monsterIndex = gp.cChecker.checkEntity(this,gp.monster);
			contactMonster(monsterIndex);
			
			//check event
			gp.eHandler.checkEvent();
			gp.keyH.zetapressed = false;

			// check object collision
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			// if collision is false player can move
			if(collisionOn == false && keyH.zetapressed == false) {
				switch(direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
			keyH.zetapressed = false;

			spriteCounter++;
			if(spriteCounter > 6) {

				if(spriteNum == 1) {
					spriteNum=2;
				}
				else if (spriteNum == 2) {
					spriteNum = 3;
				}
				else if (spriteNum == 3) {
					spriteNum = 4;
				}
				else if (spriteNum == 4) {
					spriteNum = 1;
				}
				spriteCounter = 0;

			}

		}
		if(invincible == true ) {
			invincibleCounter++;
			if(invincibleCounter >60) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
	}

	public void attacking() {
		spriteCounter++;
		
		if(spriteCounter <= 5) {
			spriteNum = 1;
		}
		
		if(spriteCounter > 5 && spriteCounter <= 25) {
			spriteNum = 2;
			
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			
			switch(direction) {
			case "up": worldY -= attackArea.height; break;
			case "down": worldY += attackArea.height; break;
			case "left": worldX -= attackArea.width; break;
			case "right": worldX += attackArea.width; break;
			}
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;

			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			damageMonster(monsterIndex); 
			
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
		}
		
		if(spriteCounter > 25) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}
	
	public void pickUpObject(int i) {
		if(i != 999) {

		}
	}
	public void interactNPC(int i) {
		if(gp.keyH.zetapressed == true){
			if(i != 999) {
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
			else{
				//gp.playSE(7);
				attacking = true;
				
			}	
		}
	}
	public void contactMonster(int i) {
		if(i !=999) {
			if(invincible == false) {
				//gp.playSE(6);
				life--;
				invincible = true;
			}
		}
	}

	public void damageMonster(int i) {
		if(i != 999) {
			if(gp.monster[i].invincible == false) {
				//gp.playSE(5);
				gp.monster[i].life -= 1; 
				gp.monster[i].invincible = true;
				gp.monster[i].damageReaction();
				if(gp.monster[i].life <= 0) {
					gp.monster[i].dying = true ;
				}
			}
		}
	}

	public void draw(Graphics2D g2) {

		BufferedImage body = null, head = null;
		

		switch (direction) {
		case "up":
			if(attacking == true && spriteNum == 2) body = attackBodyUp;
			else body = (spriteNum==1?up1 : spriteNum==2?up2 : spriteNum==3?up3 : up4);
			head = headUp;
			break;
		case "down":
			if(attacking == true && spriteNum == 2) body = attackBodyDown;
			else body = (spriteNum==1?down1 : spriteNum==2?down2 : spriteNum==3?down3 : down4);
			head = headDown;
			break;
		case "left":
			if(attacking == true && spriteNum == 2) body = attackBodyLeft;
			else body = (spriteNum==1?left1 : spriteNum==2?left2 : spriteNum==3?left3 : left4);
			head = headLeft;
			break;
		case "right":
			if(attacking == true && spriteNum == 2) body = attackBodyRight;
			else body = (spriteNum==1?right1 : spriteNum==2?right2 : spriteNum==3?right3 : right4);
			head = headRight;
			break;
		}
		
		int screenX = worldX - gp.cameraX;
		int screenY = worldY - gp.cameraY;
		int join = 14;
		
		int headBob = 0;
		if(attacking == false) {
			int[] bobSeq = {0, 1, 0, 1};          
			headBob = bobSeq[spriteNum - 1];
		}
		
		int headX = screenX;    
		int headY = (screenY - gp.tileSize + join) - headBob;
		
		if(invincible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3F));
		}
		
		g2.drawImage(body, screenX, screenY, null);
		
		if(attacking == true) {
			BufferedImage weaponImage = null;
			int weaponX = screenX;
			int weaponY = screenY; 
			
			switch(direction) {
			case "up":
				weaponImage = attackUp;
				weaponX = screenX;
				weaponY = screenY - 2*gp.tileSize +16;
				break;
			case "down":
				weaponImage = attackDown;
				weaponX = screenX;
				weaponY = screenY  + gp.tileSize -8;
				break;
			case "left":
				weaponImage = attackLeft;
				weaponX = screenX-gp.tileSize -4;
				weaponY = screenY-4; 
				break;
			case "right":
				weaponImage = attackRight;
				weaponX = screenX + gp.tileSize-6;
				weaponY = screenY-4; 
				break;
			}

			if(spriteNum == 2 && weaponImage != null) {
				g2.drawImage(weaponImage, weaponX, weaponY, null);
			}
		}
		
		g2.drawImage(head, headX, headY, null);
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
	}
}
