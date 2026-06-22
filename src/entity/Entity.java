package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {
	
	GamePanel gp;
	
	public BufferedImage up1, up2, up3, up4, down1, down2, down3, down4, left1, left2, left3, left4, right1, right2, right3, right4, headUp, headDown, headLeft, headRight;
	public BufferedImage attackUp,attackDown,attackLeft,attackRight,attackBodyUp,attackBodyDown,attackBodyLeft,attackBodyRight;
	public BufferedImage image, image2, image3;
	public BufferedImage death1,death2,death3, death4;
	public Rectangle solidArea = new Rectangle (0, 0, 48, 48);
	public Rectangle attackArea = new Rectangle(0,0,0,0);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collision = false;
	String dialogues[] = new String[20];
	
	//state
	public int worldX, worldY;
	public String direction = "down";
	public int spriteNum = 1;
	int dialogueIndex = 0;
	public boolean collisionOn = false;
	public boolean invincible = false;
	boolean attacking = false;
	public boolean alive = true;
	public boolean dying = false;
	//counter
	public int spriteCounter = 0;
	public int actionLockCounter = 0;
	int invincibleCounter = 0;
	int dyingCounter = 0;
	
	//character attributes
	public int speed;
	public String name;
	public int type; //0 player, 1 NPC, 2 monster
	public int maxLife;
	public int life;
	public int coin;
	
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
	public void update() {
		setAction();
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.monster);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		if(this.type == 2 && contactPlayer == true) {
			if(gp.player.invincible == false) {
				//gp.playSE(6);
				gp.player.life--;
				gp.player.invincible = true;
				gp.player.invincibleCounter=0;
			}
		}
		if(collisionOn == false) {
			switch(direction) {
			case "up":worldY -= speed; break;
			case "down":worldY += speed;break;
			case "left":worldX -= speed;break;
			case "right":worldX += speed;break;
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
	            body = (spriteNum==1 ? left1 : spriteNum==2 ? left2 : spriteNum==3 ? left3 : left4);
	            head = headLeft;
	            break;
	        case "right":
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

		    g2.drawImage(body, screenX, screenY, gp.tileSize, gp.tileSize, null);
		    if (head != null) {
		    	g2.drawImage(head, headX, headY, null);
		    }
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
		}
	}	
	
	public void dyingAnimation() {
		dyingCounter++;
		int i = 10; 
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
		if (dyingCounter > 40) {
			dying = false;
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
