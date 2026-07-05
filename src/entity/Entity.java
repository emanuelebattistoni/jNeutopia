package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {
	GamePanel gp;
	public BufferedImage up1, up2, up3, up4, down1, down2, down3, down4, left1, left2, left3, left4, right1, right2, right3, right4, headUp, headDown, headLeft, headRight,
							downleft1, downleft2,downright1,downright2,upleft1,upleft2,upright1,upright2, dead, bodyGetItem;
	public BufferedImage attackUp,attackDown,attackLeft,attackRight,attackBodyUp,attackBodyDown,attackBodyLeft,attackBodyRight;
	public BufferedImage image, image2, image3;
	public BufferedImage death1,death2,death3, death4;
	public Rectangle solidArea = new Rectangle (0, 0, 48, 48);
	public Rectangle attackArea = new Rectangle(0,0,0,0);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collision = false;
	public String dialogues[] = new String[20];
	//state
	public int worldX, worldY;
	public String direction = "down";
	public int spriteNum = 1;
	public int dialogueIndex = 0;
	public boolean collisionOn = false;
	public boolean invincible = false;
	boolean attacking = false;
	public boolean destructible = false;
	public boolean alive = true;
	public boolean dying = false;
	public boolean knockBack = false;
	//counter
	public int spriteCounter = 0;
	public int actionLockCounter = 0;
	public int shotAvaibleCounter;
	int invincibleCounter = 0;
	int dyingCounter = 0;
	public int knockBackCounter=0;
	//character attributes
	public int defaultSpeed;
	public int speed;
	public String name;
	public int maxLife;
	public int life;
	
	//item attributes
	public int price;
	public int coin;
	public int type;
	public int bomb;
	public int attack;
	public int value;
	public final int type_player=0;
	public final int type_npc=1;
	public final int type_monster=2;
	public final int type_consumable=3;
	public final int type_pickupOnly = 4;
	public final int type_obstacle = 5;
	public final int type_reusable = 6;
	public final int type_key = 7;
	public final int type_inUse = 8;
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int maxInventorySize = 8;

	public Projectile projectile;
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setAction() {	
	}
	public void damageReaction() {
	}
	
	public void speak() {
		if (dialogues[dialogueIndex]== null) {
			dialogueIndex = 0;
		}
		gp.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex++;
	}
	public void interact() {
	}
	
	public void use(Entity entity) {}
	public void checkDrop() {}
	public void checkCollision() {
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
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
		if(knockBack == true) {
			checkCollision();
			if(collisionOn == true) {
				knockBackCounter=0;
				knockBack=false;
				speed = defaultSpeed;
			}
			else if(collisionOn == false) {
				switch(gp.player.direction) {
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
	
	public void damagePlayer(int attack) {
		if(gp.player.invincible == false) {
			//gp.playSE(6);
			gp.player.life--;
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

			gp.player.knockBack=true;
			gp.player.knockBackCounter = 0;
			}
		}
	
	public void draw(Graphics2D g2) {
		BufferedImage body = null, head = null;
		int screenX = worldX - gp.cameraX;
		int screenY = worldY - gp.cameraY;
		int join = 14;
		int headX = screenX;    
		int headY = (screenY - gp.tileSize + join);
		if (screenX + gp.tileSize > 0 && screenX < gp.screenWidth &&
		    screenY + gp.tileSize > 0 && screenY < gp.screenHeight) {
			
			switch (direction) {
	        case "up":
	            body = (spriteNum==1 ? up1 : spriteNum==2 ? up2 : spriteNum==3 ? up3 : up4);
	            head = headUp;
	            break;
	        case "down":
	            body = (spriteNum==1 ? down1 : spriteNum==2 ? down2 : spriteNum==3 ? down3 : down4);
	            head = headDown;
	            break;
	        case "left":
	        case "upLeft":
	        case "downLeft":
	            body = (spriteNum==1 ? left1 : spriteNum==2 ? left2 : spriteNum==3 ? left3 : left4);
	            head = headLeft;
	            break;
	        case "right":
	        case "upRight":
	        case "downRight":
	            body = (spriteNum==1 ? right1 : spriteNum==2 ? right2 : spriteNum==3 ? right3 : right4);
	            head = headRight;
	            break;
	    }
			if(invincible == true && dying == false) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4F));
			}
			if(dying == true) {
				dyingAnimation(); 
				if (spriteNum == 1) body = death1;
				if (spriteNum == 2) body = death2;
				if (spriteNum == 3) body = death3;
				if (spriteNum == 4) body = death4;
				
				head = null; 
			}
		    g2.drawImage(body, screenX, screenY, null);
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
