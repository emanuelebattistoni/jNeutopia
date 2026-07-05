package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import object.OBJ_Armor;
import object.OBJ_Bomb;
import object.OBJ_Fireball;
import object.OBJ_Key;
import object.OBJ_Sword;
import tile_interactive.IT_Door;

public class Player extends Entity{
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	public Entity currentItem;
	public boolean drawingGetItem = false;
	public Entity itemObtained;
	public boolean isDying=false;
	public boolean isDead = false;
	int deathSpinCounter = 0;
	int deathSpinCount = 0;
	int deadTimer = 0;
	
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
		attackArea.height =44;

		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
		getPlayerImage();
	}

	public void setDefaultValues() {
		//player position in the map
		worldX = gp.tileSize * 13;
		worldY = gp.tileSize * 8;
//		worldX = gp.tileSize * 18;
//		worldY = gp.tileSize * 44;
		defaultSpeed=4;
		speed = defaultSpeed;
		direction = "up";
		//player status
		maxLife = 10;
		life = maxLife;	
		coin = 500;
		bomb=0;
		isDead = false;
		isDying = false;
		deathSpinCount = 0;
		deathSpinCounter = 0;
		invincible = false;
		deadTimer = 0;
		inventory.add(new OBJ_Key(gp));
		inventory.add(new OBJ_Sword(gp));
		inventory.add(new OBJ_Armor(gp));
	}
	
	public void setDefaultPosition() {
		gp.currentMap=5;
		worldX = gp.tileSize * 13;
		worldY = gp.tileSize * 8;

		direction = "up";
	}
	
	public void restoreLife() {
		life=maxLife;
		invincible=false;
		isDead = false;
		isDying = false;
		deathSpinCount = 0;
		deathSpinCounter = 0;
		deadTimer = 0;
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
		headUp = setup("/player/HeadUp", gp.tileSize, gp.tileSize);
		headDown  = setup("/player/HeadDown", gp.tileSize, gp.tileSize);
		headLeft  = setup("/player/HeadLeft", gp.tileSize, gp.tileSize);
		headRight = setup("/player/HeadRight", gp.tileSize, gp.tileSize);
		dead=setup("/player/deadJazeta", gp.tileSize+gp.tileSize/2, gp.tileSize+gp.tileSize/2);
		bodyGetItem = setup("/player/jazetaItem", gp.tileSize, gp.tileSize+gp.tileSize/2);
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
		if(isDying == true) {
	        dyingAnimation();
	        return;
	    }
		if(isDead == true) {
			deadTimer++;
			if(deadTimer > 60) { 
				gp.gameState = gp.gameOverState; 
				knockBack=false;
				speed=defaultSpeed;
				gp.retry();
			}
			
			return;
		}
		
		if(knockBack == true) {
			collisionOn = false;
			gp.cChecker.checkTile(this);
			gp.cChecker.checkObject(this, true);
			gp.cChecker.checkEntity(this, gp.npc);

			if(collisionOn == true) {
				knockBackCounter = 0;
				knockBack = false;
				speed = defaultSpeed;
			}
			else {
				switch(direction) {
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
				}
			}

			knockBackCounter++;
			if(knockBackCounter >= 15) { 
				knockBackCounter = 0;
				knockBack = false;
				speed = defaultSpeed;
			}
		}
		
		else if(attacking == true) {
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
			
			int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
			//DEBUG
			if(iTileIndex != 999) {
				System.out.println("Collisione rilevata con iTile indice: " + iTileIndex);
			}
			checkDoorCollision(iTileIndex);
			//check event
			gp.eHandler.checkEvent();

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
		if(shotAvaibleCounter < 30) {
			shotAvaibleCounter++;
		}
		if (life <= 0 && isDying == false && isDead == false) {
	        isDying = true; 
	    }
		updateBombCount();
	}

	public void checkDoorCollision(int i) {
		if(i != 999) {
			if(gp.iTile[gp.currentMap][i].name != null && gp.iTile[gp.currentMap][i].name.equals("Door")) {	
				boolean hasKey = false;
				int keyIndex = -1;

				for(int j = 0; j < inventory.size(); j++) {
					if(inventory.get(j) != null && inventory.get(j).name != null && inventory.get(j).name.equals("Key")) {
						hasKey = true;
						keyIndex = j;
						break; 
					}
				}

				if(hasKey == true) {
					inventory.remove(keyIndex);
					if(currentItem != null && currentItem.name != null && currentItem.name.equals("Key")) {
	                    currentItem = null;
	                }
					gp.gameState=gp.dialogueState;
					gp.ui.currentDialogue="Hai aperto la porta del dungeon!";
					gp.ui.drawDialogueScreen();
					gp.iTile[gp.currentMap][i] = null; 

				} 
				else {
					gp.gameState=gp.dialogueState;
					gp.ui.currentDialogue="Devi trovare prima la chiave!";
					gp.ui.drawDialogueScreen();
				}
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
			case "up": 
				worldY -= gp.tileSize;
				solidArea.y = 0;      
				break;
			case "down": worldY += attackArea.height; break;
			case "left": worldX -= attackArea.width; break;
			case "right": worldX += attackArea.width; break;
			}
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;

			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			damageMonster(monsterIndex,1); 
			
			
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
			solidArea.y = solidAreaDefaultY;
		}
		
		if(spriteCounter > 25) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}
	
	public void dyingAnimation() {
	    deathSpinCounter++;
	    
	    if(deathSpinCounter > 5) {
	    	
	        switch(direction) {
	            case "up": direction = "right"; break;
	            case "right": direction = "down"; break;
	            case "down": direction = "left"; break;
	            case "left": 
	                direction = "up"; 
	                deathSpinCount++;
	                break;
	        }
	        deathSpinCounter = 0;
	    }
	    if(deathSpinCount >= 4) {
	        isDying = false; 
	        isDead = true;   
	        deathSpinCount = 0; 
	    }
	}
	
	public void pickUpObject(int i) {
		if(i != 999) {
			//pickup item
			if(gp.obj[gp.currentMap][i].type==type_pickupOnly) {
				gp.obj[gp.currentMap][i].use(this);
				gp.obj[gp.currentMap][i]=null;
			}
			//obstacle
			else if(gp.obj[gp.currentMap][i].type==type_obstacle) {
				if(keyH.zetapressed == true) {
					gp.obj[gp.currentMap][i].interact();
					attacking = false;
				}
			}
			//inventory item
			else {
			String text=""; 
			if(inventory.size() != maxInventorySize) {
				inventory.add(gp.obj[gp.currentMap][i]);
				//gp.playSE(1);
			}
			else {
				text="You cannot carry any more!";
			}
			gp.ui.addMessage(text);
			gp.obj[gp.currentMap][i]=null;
			}
		}
	}
	public void interactNPC(int i) {
		if(gp.keyH.zetapressed == true){
			if(i != 999) {
				gp.gameState = gp.dialogueState;
				gp.npc[gp.currentMap][i].speak();
			}
			else{
				//gp.playSE(7);
				attacking = true;
				
			}	
		}
	}
	public void contactMonster(int i) {
		if(i !=999) {
			if(invincible == false && gp.monster[gp.currentMap][i].dying == false) {
				//gp.playSE(6);
				life--;
				invincible = true;
				switch(direction) {
                case "up": direction = "down"; break;
                case "down": direction = "up"; break;
                case "left": direction = "right"; break;
                case "right": direction = "left"; break;
            }

            knockBack = true;
            knockBackCounter = 0;
            speed += 4;
        }
			}
		}
	

	public void damageMonster(int i, int attack) {
		if(i != 999) {
			if(gp.monster[gp.currentMap][i].invincible == false) {
				//gp.playSE(5);
				knockBack(gp.monster[gp.currentMap][i]);
				gp.monster[gp.currentMap][i].life -= attack; 
				gp.monster[gp.currentMap][i].invincible = true;
				gp.monster[gp.currentMap][i].damageReaction();
				if(gp.monster[gp.currentMap][i].life <= 0) {
					gp.monster[gp.currentMap][i].dying = true ;
				}
			}
		}
	}
	
	public void knockBack(Entity entity) {
		entity.direction=direction;
		entity.speed+=10;
		entity.knockBack=true;
		
	}

	public void selectItem() {
		int itemIndex = gp.ui.getItemIndexOnSlot();
		if(itemIndex<inventory.size()) {
			Entity selectedItem = inventory.get(itemIndex);
			if(selectedItem.type == type_consumable) {
				selectedItem.use(this);
				inventory.remove(itemIndex);
				currentItem = null;
			}
			else if(selectedItem.type == type_reusable) {
				selectedItem.use(this);
			}
		}
	}
	public void updateBombCount() {
	    int currentBombs = 0;
	    for (Entity item : inventory) {
	        if (item != null && item.name != null && item.name.equals("Bomb")) {
	            currentBombs++;
	        }
	    }
	    bomb = currentBombs;
	}
	
	public void draw(Graphics2D g2) {

		int screenX = worldX - gp.cameraX;
		int screenY = worldY - gp.cameraY;

		if(isDead == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
			g2.drawImage(dead, screenX, screenY, null);
			return; 
		}
		
		BufferedImage body = null, head = null;
		
		if(drawingGetItem == true) {
			body = bodyGetItem;
			head = headDown;
		}
		else{ 
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
		}
		
		screenX = worldX - gp.cameraX;
		screenY = worldY - gp.cameraY;
		
		int jumpOffset = 0;
		if(knockBack == true) {

			jumpOffset = (int) (Math.sin((double) knockBackCounter / 15.0 * Math.PI) * 48); 
		}		
		screenY = screenY - jumpOffset;
		
		int join = 14;
		if(drawingGetItem) {
			join+=20;
			}
		int headBob = 0;
		if(attacking == false) {
			int[] bobSeq = {0, 1, 0, 1};          
			headBob = bobSeq[spriteNum - 1];
		}
		
		int headX = screenX;    
		int headY = (screenY - gp.tileSize + join) - headBob;
		
		if(invincible == true && isDying==false) {
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
		if(drawingGetItem == true) {
			g2.drawImage(itemObtained.down1, screenX, screenY - gp.tileSize-8, null);
			
		}
	}
}
