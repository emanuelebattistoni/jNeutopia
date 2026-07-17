package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {
	GamePanel gp;
	public BufferedImage up1, up2, up3, up4, down1, down2, down3, down4, left1, left2, left3, left4, right1, right2, right3, right4, headUp, headDown, headLeft, headRight,
	downleft1, downleft2,downright1,downright2,upleft1,upleft2,upright1,upright2, dead, bodyGetItem;
	public BufferedImage attackUp,attackDown,attackLeft,attackRight,attackBodyUp,attackBodyDown,attackBodyLeft,attackBodyRight, attackUp2,attackDown2,attackLeft2, attackRight2;
	public BufferedImage image, image2, image3;
	public BufferedImage death1,death2,death3, death4;
    public BufferedImage inv1, inv2, inv3, inv4;
	public Rectangle solidArea = new Rectangle (0, 0, 48, 48);
	public Rectangle attackArea = new Rectangle(0,0,0,0);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collision = false;
	public String dialogues[][] = new String[20][20];
	public Entity attacker;

	//state
	public int worldX, worldY;
	public String direction = "down";
	public int spriteNum = 1;
	public int dialogueSet=0;
	public int dialogueIndex = 0;
	public boolean collisionOn = false;
	public boolean invincible = false;
	public boolean attacking = false;
	public boolean destructible = false; 
	public boolean alive = true;
	public boolean dying = false;
	public boolean knockBack = false;
	public int startWorldX, startWorldY;
	public boolean startSaved = false;
	public int maxDistance = 4;
	public boolean returningHome = false;
	public String knockBackDirection;
	public Entity loot;
	public boolean opened = false;
	public boolean hasSpoken=false;
	//counter
	public int spriteCounter = 0;
	public int actionLockCounter = 0;
	public int shotAvaibleCounter;
	public int invincibleCounter = 0;
	int dyingCounter = 0;
	public int knockBackCounter=0;
	//character attributes
	public int defaultSpeed;
	public int speed;
	public String name;
	public int maxLife;
	public int life;
	public int attack;

	//item attributes
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int maxInventorySize = 12;
	public int price;
	public int coin;
	public int type;
	public int bomb;
	public int value;
	public boolean stackable = false;
	public int amount = 1;
	//type
	public final int type_player=0;
	public final int type_npc=1;
	public final int type_monster=2;
	public final int type_consumable=3;
	public final int type_pickupOnly = 4;
	public final int type_obstacle = 5;
	public final int type_reusable = 6;
	public final int type_key = 7;
	public final int type_inUse = 8;

	public Projectile projectile;
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public void resetCounter() {
		spriteCounter = 0;
		actionLockCounter = 0;
		shotAvaibleCounter=0;
		invincibleCounter = 0;
		dyingCounter = 0;
		knockBackCounter=0;
	}
	
	public void setLoot(Entity loot) {}
	public void setAction() {}
	public void damageReaction() {}
	public void speak() {}
	public void startDialogue(Entity entity, int setNum) {
		gp.gameState = gp.dialogueState;
		gp.ui.npc = entity;
		dialogueSet = setNum;
	}
	public void interact() {}
	public void use(Entity entity) {}
	public void checkDrop() {}
	public void checkCollision() {
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		gp.cChecker.checkEntity(this, gp.iTile);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		if(this.type == type_monster && contactPlayer == true) {
			damagePlayer(attack);
		}
	}

	public void dropItem(Entity droppedItem) {
		for(int i =0; i<gp.obj[1].length;i++) {
			if(gp.obj[gp.currentMap][i] == null) {
				gp.obj[gp.currentMap][i] = droppedItem;
				gp.obj[gp.currentMap][i].worldX = worldX;
				gp.obj[gp.currentMap][i].worldY = worldY;
				break;
			}
		}
	}

	public void update() {
		if (dying == true) {
			dyingAnimation();
			return;
		}
		if(knockBack == true) {
			checkCollision();
			if(collisionOn == true) {
				knockBackCounter=0;
				knockBack=false;
				speed = defaultSpeed;
			}
			else{
				switch(knockBackDirection) {
				case "up":worldY -= speed; break;
				case "down":worldY += speed;break;
				case "left":worldX -= speed;break;
				case "right":worldX += speed;break;
				case "upLeft": worldX -= speed; worldY -= speed; break;
				case "upRight": worldX += speed; worldY -= speed; break;
				case "downLeft": worldX -= speed; worldY += speed; break;
				case "downRight": worldX += speed; worldY += speed; break;
				}
			}
			knockBackCounter++;
			if(knockBackCounter == 10) {
				knockBackCounter=0;
				knockBack=false;
				speed = defaultSpeed;
			}
		}
		else if(attacking == true) {
			attacking();
		}
		else {
			setAction();
			checkCollision();
			if(collisionOn == false) {
				switch(direction) {
				case "up":worldY -= speed; break;
				case "down":worldY += speed;break;
				case "left":worldX -= speed;break;
				case "right":worldX += speed;break;
				case "upLeft": worldX -= speed; worldY -= speed; break;
				case "upRight": worldX += speed; worldY -= speed; break;
				case "downLeft": worldX -= speed; worldY += speed; break;
				case "downRight": worldX += speed; worldY += speed; break;
				}
			}
			spriteCounter++;
			if(spriteCounter > 8) {
				if(spriteNum == 1) {
					spriteNum=2;
				}
				else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter=0;

			}
		}
		if(invincible == true ) {
			invincibleCounter++;
			if(invincibleCounter >40) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
		if(shotAvaibleCounter < 30) {
			shotAvaibleCounter++;
		}
	}

	public void checkAttackOrNot(int rate, int straight, int horizontal) {
	    boolean targetInRange = false;
	    int xDis = getXdistance(gp.player); 
	    int yDis = getYdistance(gp.player);

	    switch(direction) {
	    case "up":
	        if(gp.player.worldY < worldY && yDis < straight && xDis < horizontal) {
	            targetInRange = true;
	        }
	        break; 
	    case "down":
	        if(gp.player.worldY > worldY && yDis < straight && xDis < horizontal) {
	            targetInRange = true;
	        }
	        break;
	    case "left":
	        if(gp.player.worldX < worldX && xDis < straight && yDis < horizontal) {
	            targetInRange = true;
	        }
	        break;
	    case "right":
	        if(gp.player.worldX > worldX && xDis < straight && yDis < horizontal) {
	            targetInRange = true;
	        }
	        break; 
	    }
	    if(targetInRange == true) {
	        int i = new Random().nextInt(rate);
	        if(i == 0) {
	            attacking = true;
	            spriteNum = 1;
	            shotAvaibleCounter = 0; 
	        }
	    }
	}

	public int getXdistance(Entity target) {
		int xDistance = Math.abs(worldX - target.worldX);
		return xDistance;
	}
	
	public int getYdistance(Entity target) {
		int yDistance = Math.abs(worldY - target.worldY);
		return yDistance;
	}

	public void SaveStartPosition() {
		if (startSaved == false) {
			if (worldX != 0 && worldY != 0) {
				startWorldX = worldX;
				startWorldY = worldY;
				startSaved = true;
			}
		}
	}
	
	public void checkWanderDistance(int maxDist) {
		int xDistance = Math.abs(worldX - startWorldX) / gp.tileSize;
		int yDistance = Math.abs(worldY - startWorldY) / gp.tileSize;

		if (xDistance > maxDist || yDistance > maxDist) {
			returningHome = true;
		}
		if (returningHome == true && xDistance < 2 && yDistance < 2) {
			returningHome = false;
		}
	}
	
	public void moveTowards(int targetX, int targetY) {
	    actionLockCounter++;
	    if(actionLockCounter > 30) {
	        int xDistance = Math.abs(worldX - targetX);
	        int yDistance = Math.abs(worldY - targetY);
	        if (xDistance > yDistance) {
	            if (worldX > targetX) {
	                direction = "left";
	            } else {
	                direction = "right";
	            }
	        } else {
	            if (worldY > targetY) {
	                direction = "up";
	            } else {
	                direction = "down";
	            }
	        }
	        actionLockCounter = 0;
	    }
	}

	public void getRandomDirection() {
		actionLockCounter++;
		if(actionLockCounter == 120) {
			Random random = new Random();
			int i = random.nextInt(100) + 1;

			if (i <= 25) { direction = "up"; }
			else if (i > 25 && i <= 50) { direction = "down"; }
			else if (i > 50 && i <= 75) { direction = "left"; }
			else if (i > 75 && i <= 100) { direction = "right"; }

			actionLockCounter = 0;
		}
	}
	
	public void loadDeathImage() {
		death1 = setup("/monster/dMonster1", gp.tileSize, gp.tileSize);
		death2 = setup("/monster/dMonster2", gp.tileSize, gp.tileSize);
		death3 = setup("/monster/dMonster3", gp.tileSize, gp.tileSize);
		death4 = setup("/monster/dMonster4", gp.tileSize, gp.tileSize);
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
	        int solidAreaY = solidArea.y;

	        int attackStrikeWidth = attackArea.width;
	        int attackStrikeHeight = attackArea.height;
	        if (type == type_player) {
	            switch(direction) {
	                case "up": worldY -= 90; attackStrikeWidth = 36; attackStrikeHeight = 60; break;
	                case "down": worldY += 30; attackStrikeWidth = 36; attackStrikeHeight = 54; break;
	                case "left": worldX -= 68; worldY -= 14; attackStrikeWidth = 54; attackStrikeHeight = 36; break;
	                case "right": worldX += 26; worldY -= 14; attackStrikeWidth = 54; attackStrikeHeight = 36; break;
	            }
	        } else {
	            switch(direction) {
	                case "up": worldY -= attackArea.height; break;
	                case "down": worldY += gp.tileSize; break;
	                case "left": worldX -= attackArea.width; break;
	                case "right": worldX += gp.tileSize; break;
	            }
	        }

	        solidArea.width = attackStrikeWidth;
	        solidArea.height = attackStrikeHeight;

	        if(type == type_monster) {
	            if(gp.cChecker.checkPlayer(this) == true) {
	                damagePlayer(attack);
	            }
	        } else {
	            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
	            gp.player.damageMonster(monsterIndex, gp.player, attack); 
	        }

	        worldX = currentWorldX;
	        worldY = currentWorldY;
	        solidArea.width = solidAreaWidth;
	        solidArea.height = solidAreaHeight;
	        solidArea.y = solidAreaY; 
	    }
	    
	    if(spriteCounter > 25) {
	        spriteNum = 1;
	        spriteCounter = 0;
	        attacking = false;
	    }
	}

	public void damagePlayer(int attack) {
		if(gp.player.invincible == false) {
			gp.playSE(14);
			gp.player.life-=attack;
			gp.player.invincible = true;
			gp.player.invincibleCounter=0;
			switch(gp.player.direction) {
			case "up":gp.player.direction="down";break;
			case "down":gp.player.direction="up";break;
			case "left":gp.player.direction="right";break;
			case "right":gp.player.direction="left";break;
			case "upLeft": gp.player.direction="downRight"; break;
			case "upRight": gp.player.direction="downLeft"; break;
			case "downLeft": gp.player.direction="upRight"; break;
			case "downRight": gp.player.direction="upLeft"; break;
			}
			gp.player.knockBackDirection = gp.player.direction;
			gp.player.knockBack=true;
			gp.player.knockBackCounter = 0;
		}
	}
	public void knockBack(Entity target, Entity attacker) {
		this.attacker=attacker;
		target.knockBackDirection=attacker.direction;
		target.speed+=10;
		target.knockBack=true;
	}
	public void draw(Graphics2D g2) {
		BufferedImage body = null, head = null;
		int screenX = worldX - gp.cameraX;
		int screenY = worldY - gp.cameraY;
		int join = 14;
		int headX = screenX;    
		int headY = (screenY - gp.tileSize + join);
		int tempScreenX = screenX;
		int tempScreenY = screenY;
		if (screenX + gp.tileSize > 0 && screenX < gp.screenWidth &&
		    screenY + gp.tileSize > 0 && screenY < gp.screenHeight) {
			String currentDirection = direction;
			if (currentDirection == null) {
				currentDirection = "down"; 
			}
			switch (currentDirection) {
	        case "up":
	        	if(attacking == false) {
	        		body = (spriteNum==1 ? up1 : spriteNum==2 ? up2 : spriteNum==3 ? up3 : up4);
	        		head = headUp;
	        	} else {
	        		tempScreenY = screenY - up1.getHeight();
	        		body = (spriteNum == 1 ? attackUp : attackUp2);
	        		head = null; 
	        	}
	            break;
	        case "down":
	        	if(attacking == false) {
	        		body = (spriteNum==1 ? down1 : spriteNum==2 ? down2 : spriteNum==3 ? down3 : down4);
	        		head = headDown;
	        	} else {
	        		body = (spriteNum == 1 ? attackDown : attackDown2);
	        		head = null;
	        	}
	            break;
	        case "left":
	        case "upLeft":
	        case "downLeft":
	        	if(attacking == false) {
	        		body = (spriteNum==1 ? left1 : spriteNum==2 ? left2 : spriteNum==3 ? left3 : left4);
	        		head = headLeft;
	        	} else {
	        		tempScreenX = screenX - up1.getWidth(); 
	        		body = (spriteNum == 1 ? attackLeft : attackLeft2);
	        		head = null;
	        	}
	            break;
	        case "right":
	        case "upRight":
	        case "downRight":
	        	if(attacking == false) {
	        		body = (spriteNum==1 ? right1 : spriteNum==2 ? right2 : spriteNum==3 ? right3 : right4);
	        		head = headRight;
	        	} else {
	        		body = (spriteNum == 1 ? attackRight : attackRight2);
	        		head = null;
	        	}
	            break;
	    }
			if(invincible == true && dying == false) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4F));
			}
			if(dying == true) {
				if (spriteNum == 1) body = death1;
				if (spriteNum == 2) body = death2;
				if (spriteNum == 3) body = death3;
				if (spriteNum == 4) body = death4;
				head = null; 
			}
			
			if(body != null) {
				g2.drawImage(body, tempScreenX, tempScreenY, null); 
			}
		    if (head != null) {
		    	g2.drawImage(head, headX, headY, null);
		    }
		    
		    
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
		}
	}

	public void dyingAnimation() {
		dyingCounter++;
		int i = 5; 
		if(dyingCounter <= i) {
			spriteNum = 1;
		}
		else if(dyingCounter > i && dyingCounter <= i * 2) {
			spriteNum = 2;
		}
		else if(dyingCounter > i * 2 && dyingCounter <= i * 3) {
			spriteNum = 3;
		}
		else if(dyingCounter > i * 3 && dyingCounter <= i * 4) {
			spriteNum = 4;
		}
		if (dyingCounter > 20) {
			alive = false;
		}
	}
	
	public BufferedImage setup(String imagePath, int width, int height) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		try {
			image =  ImageIO.read(getClass().getResourceAsStream(imagePath+".png"));
			image = uTool.scaleImage(image,  width,  height);			
		} catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}


