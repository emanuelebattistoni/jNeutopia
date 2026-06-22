package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import object.OBJ_Heart;
import entity.Entity;
public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font nesFont;
    public boolean messageOn = false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum=0;
    BufferedImage titleScreenImage;
    BufferedImage selectImage;
    BufferedImage heart_full, heart_half, heart_blank;
    public UI(GamePanel gp) {
        this.gp = gp;
        loadFont();
       
        //create hud object
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank= heart.image3;

    }

    private void loadFont() {
        try {
            InputStream is = getClass().getResourceAsStream("/fonts/PressStart2P-Regular.ttf");
            nesFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(20f); 
        } catch (Exception e) {
            e.printStackTrace();
            nesFont = new Font("Monospaced", Font.PLAIN, 16); // fallback
        }
    }

    public void addMessage(String text) {
    	message.add(text);
    	messageCounter.add(0);
    }
    public void draw(Graphics2D g2) {
    	this.g2 = g2;
    	g2.setFont(nesFont);
    	g2.setColor(Color.white);
    	
    	//TITLE STATE
    	if(gp.gameState == gp.titleState) {
    		drawTitleScreen();
    	}
    	//PLAYSTATE
    	if(gp.gameState == gp.playState) {
    		drawPlayerLife();
    		drawMessage();
    	}
    	//Inventory State
    	if(gp.gameState == gp.inventoryState) {
    		drawInventory();
    	}
    	
    	//Dialogue State
    	if(gp.gameState == gp.dialogueState) {
    		drawDialogueScreen();
    		drawPlayerLife();

    	}
    }
    
  
    public void drawPlayerLife() {
    	int x =gp.tileSize/2;
    	int y = gp.tileSize/2;
    	int i = 0;
    	//draw max life
    	while(i<gp.player.maxLife/2) {
    		g2.drawImage(heart_blank, x, y, null);
    		i++;
    		x+=gp.tileSize-8;
    	}
    	//draw current life
    	x =gp.tileSize/2;
    	y = gp.tileSize/2;
    	i = 0;
    	while(i<gp.player.life) {
    		g2.drawImage(heart_half, x, y, null);
    		i++;
    		if(i< gp.player.life) {
    			g2.drawImage(heart_full, x, y, null);
    			i++;
    			x+=gp.tileSize-8;
    		}
    	}
    	
    }
    public void drawMessage() {
    	int messageX = gp.screenWidth/2;
    	int messageY = gp.screenHeight/2;
    	g2.setFont(g2.getFont().deriveFont(32F));
    	for(int i =0; i<message.size(); i++) {
    		if(message.get(i)!= null) {
    			g2.setColor(Color.black);
    			g2.drawString(message.get(i), messageX+2, messageY+2);
    			g2.setColor(Color.white);
    			g2.drawString(message.get(i), messageX, messageY);
    			int counter = messageCounter.get(i)+1;
    			messageCounter.set(i, counter);
    			messageY+=50;
    			if(messageCounter.get(i)>180) {
    				message.remove(i);
    				messageCounter.remove(i);
    			}
    		}
    	}
    }
    public void drawTitleScreen() {
    	try {
    	    titleScreenImage = ImageIO.read(new File("res/TitleScreen/TitleScreen.png"));
    	} catch (IOException e) {
    	    e.printStackTrace();
    	    g2.setColor(new Color(70, 120, 180));
    	g2.fillRect(0,  0, gp.screenWidth, gp.screenHeight);
    	}
    	//title screen
    	g2.drawImage(titleScreenImage, 0, 0, gp.screenWidth, gp.screenHeight, null);
    	
    	//title name
    	g2.setFont(g2.getFont().deriveFont(Font.BOLD,72F));
    	String text ="jNEUTOPIA";
    	int x = getXForCenteredText(text) +gp.tileSize;
    	int y = gp.tileSize *3 -6;
    	//shadow
    	g2.setColor(Color.white);
    	g2.drawString(text, x+4, y+4);
    	//Color
    	g2.setColor(Color.red);
    	g2.drawString(text, x,y);
    	
    	//menu
    	g2.setFont(g2.getFont().deriveFont(Font.BOLD,20F));
    	text="PUSH RUN!";
    	x = getXForCenteredText(text) + 16;
    	y += gp.tileSize;
    	g2.setColor(Color.black);
    	g2.drawString(text, x+2, y+2);
    	g2.setColor(Color.white);
    	g2.drawString(text, x,y);
    	
    	
    	text="START";
    	x = getXForCenteredText(text) +8;
    	y += gp.tileSize -4;
    	g2.drawString(text, x,y);
    	g2.setColor(Color.black);
        g2.drawString(text, x+2, y+2);
        g2.setColor(Color.white);
        g2.drawString(text, x,y);
        if (commandNum == 0) {
        	try {
        	    selectImage = ImageIO.read(new File("res/TitleScreen/SelectImage.png"));
        	    g2.drawImage(selectImage, x-36, y-24,gp.tileSize/2, gp.tileSize/2, null);
        	} catch (IOException e) {
        	    e.printStackTrace();
        	    g2.drawString("", x-gp.tileSize, y);
        	}
        }
    	
    	text="CONTINUE";
    	x = getXForCenteredText(text)+40;
    	y += gp.tileSize -4;
    	g2.setColor(Color.black);
    	g2.drawString(text, x+2, y+2);
    	g2.setColor(Color.white);
    	g2.drawString(text, x,y);
    	if (commandNum == 1) {
        	try {
        	    selectImage = ImageIO.read(new File("res/TitleScreen/SelectImage.png"));
        	    g2.drawImage(selectImage, x-36, y-24,gp.tileSize/2, gp.tileSize/2, null);
        	} catch (IOException e) {
        	    e.printStackTrace();
        	    g2.drawString("", x-gp.tileSize, y);
        	}
        }
    	
    	text="FROM NEC";
    	x = getXForCenteredText(text)+4;
    	y += gp.tileSize+4;
    	g2.setColor(Color.black);
    	g2.drawString(text, x+2, y+2);
    	g2.setColor(Color.white);
    	g2.drawString(text, x,y);
    	
    	text="© 1990 HUDSON SOFT";
    	x = getXForCenteredText(text)+ 12;
    	y += gp.tileSize -4;
    	g2.setColor(Color.black);
    	g2.drawString(text, x+2, y+2);
    	g2.setColor(Color.white);
    	g2.drawString(text, x,y);
    }
    public void drawDialogueScreen() {
    	//Window
    	int x = 0;
    	int y = gp.tileSize*7;
    	int width = gp.tileSize*16;
    	int height = gp.tileSize*3;
    	drawSubWindow(x, y, width, height);
    	x+=gp.tileSize;
    	y+=gp.tileSize;
    	for(String line : currentDialogue.split("\n")) {
    		g2.drawString(line, x, y);
    		y+=30;
    	}
    	
    }
    
    public void drawInventory() {
    }
    
    public void drawSubWindow(int x, int y, int width, int height) {
    	Color c = new Color(0, 0, 0);
    	g2.setColor(c);
    	int arcWidth = 35;
    	int arcHeight = 35;
    	g2.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
    	
    	c = new Color(255, 255, 255);
    	g2.setColor(c);
    	g2.setStroke(new BasicStroke(5));
    	g2.drawRoundRect(x+5, y+5, width-10, height-10, arcWidth-10, arcHeight-10);
    	
    }
    
    public int getXForCenteredText(String text) {
    	int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    	int x = gp.screenWidth/2 - length/2;
    	return x;
    }
}
