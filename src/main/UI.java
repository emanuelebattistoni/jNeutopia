	package main;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entity.Entity;
import object.OBJ_Heart;
import object.OBJ_Sword;
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
    BufferedImage inventoryImage, inventoryCursor;
    BufferedImage heart_full, heart_half, heart_blank, swordImage;
    BufferedImage hudImage;
    public int slotCol=0;
    public int slotRow=0;
    int charIndex=0;
    String combinedText="";
    
    public int subState = 0;
	int transitionCounter = 0;
	public Entity npc;
    public UI(GamePanel gp) {
        this.gp = gp;
        loadFont();
       
        //create hud object
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank= heart.image3;
        Entity sword = new OBJ_Sword(gp);
        swordImage = sword.down1;
        UtilityTool uTool = new UtilityTool();
        try {
        	inventoryImage = ImageIO.read(getClass().getResourceAsStream("/inventory/Inventory.png"));
            inventoryCursor = ImageIO.read(getClass().getResourceAsStream("/inventory/cursor.png"));
            inventoryCursor = uTool.scaleImage(inventoryCursor, gp.tileSize + 16, gp.tileSize + 16);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            hudImage = ImageIO.read(getClass().getResourceAsStream("/hud/HudBanner.png")); 
            hudImage = uTool.scaleImage(hudImage, gp.screenWidth, gp.tileSize * 2);

        } catch (IOException e) {
            e.printStackTrace();
        }
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
    		drawTopHUD();
    	}
    	//Inventory State
    	if(gp.gameState == gp.inventoryState) {
    		drawInventory();
    	}
    	
    	//Dialogue State
    	if(gp.gameState == gp.dialogueState) {
    		drawTopHUD();
    		drawDialogueScreen();
    	}
    	//option state
    	if(gp.gameState == gp.optionsState) {
    		drawOptionsScreen();
    	}
    	if(gp.gameState == gp.gameOverState) {
    		drawTopHUD();
    	}
    	//transition
    	if(gp.gameState == gp.transitionState) {
            drawTransition(g2);
        }
    	if(gp.gameState == gp.tradeState) {
            drawTradeScreen();
        }
    }
    

    public void drawTopHUD() {
        if (hudImage != null) {
            g2.drawImage(hudImage, -1, 0, gp.screenWidth, gp.tileSize + gp.tileSize / 2 + gp.tileSize / 4, null);
        }

        Entity equippedWeapon = null;
        for (int i = 0; i < gp.player.inventory.size(); i++) {
            Entity item = gp.player.inventory.get(i);
            if (item.type == item.type_inUse && item.name != null && item.name.equals("Spada")) {
                equippedWeapon = item;
                break;
            }
        }

        int weaponX = gp.tileSize * 3 - gp.tileSize / 4;
        int weaponY = gp.tileSize - gp.tileSize / 2 - gp.tileSize / 5;
        if (equippedWeapon != null) {
            g2.drawImage(equippedWeapon.down1, weaponX, weaponY, gp.tileSize, gp.tileSize, null);
        } else {
            g2.drawImage(swordImage, weaponX, weaponY, gp.tileSize, gp.tileSize, null);
        }

        gp.player.currentItem = getHoveredItem();

        if (gp.player.currentItem != null) {
            if (gp.player.currentItem.type != gp.player.currentItem.type_inUse) {
                int itemX = gp.tileSize + gp.tileSize / 6; 
                int itemY = gp.tileSize - gp.tileSize / 2 - gp.tileSize / 5;   
                g2.drawImage(gp.player.currentItem.down1, itemX, itemY, gp.tileSize, gp.tileSize, null);
            }
        }

        g2.setColor(Color.WHITE);
        g2.setFont(nesFont.deriveFont(16F));
        
        int textY = gp.tileSize - 8; 
        int goldValueX = gp.tileSize * 7 + gp.tileSize / 4;
        g2.drawString(String.valueOf(gp.player.coin), goldValueX, textY);
        
        int bombValueX = gp.tileSize * 12;
        int bombIndex = gp.player.searchItemInInventory("Bomb");
        String bombAmountText = "0";
        
        if (bombIndex != 999 && bombIndex >= 0 && bombIndex < gp.player.inventory.size()) {
            bombAmountText = String.valueOf(gp.player.inventory.get(bombIndex).amount);
        }
        g2.drawString(bombAmountText, bombValueX, textY);

        int heartXStart = gp.tileSize * 6 + 22;
        int heartY = gp.tileSize - 8;
        int heartX = heartXStart;
        
        int maxHearts = (gp.player.maxLife + 1) / 2;
        for (int i = 0; i < maxHearts; i++) {
            g2.drawImage(heart_blank, heartX, heartY, gp.tileSize / 2, gp.tileSize / 2, null);
            heartX += gp.tileSize / 2 - 4;
        }
        
        heartX = heartXStart;
        int currentLife = 0;
        
        while (currentLife < gp.player.life) {
            if (currentLife + 1 == gp.player.life) {
                g2.drawImage(heart_half, heartX, heartY, gp.tileSize / 2, gp.tileSize / 2, null);
                currentLife++;
            } else {
                g2.drawImage(heart_full, heartX, heartY, gp.tileSize / 2, gp.tileSize / 2, null);
                currentLife += 2;
            }
            heartX += gp.tileSize / 2 - 4;
        }
    }

    public void drawTitleScreen() {
    	try {
    	    titleScreenImage = ImageIO.read(getClass().getResourceAsStream("/TitleScreen/TitleScreen.png"));
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
        	    selectImage = ImageIO.read(getClass().getResourceAsStream("/TitleScreen/SelectImage.png"));
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
        	    selectImage = ImageIO.read(getClass().getResourceAsStream("/TitleScreen/SelectImage.png"));
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
        g2.setFont(nesFont.deriveFont(16F));
		gp.ui.g2.setColor(Color.white);
        int x = 0;
        int y = gp.tileSize * 7;
        int width = gp.tileSize * 16;
        int height = gp.tileSize * 3;
        drawSubWindow(x, y, width, height);
        x += gp.tileSize;
        y += gp.tileSize;

        if (npc != null) {
            if (npc.dialogues[npc.dialogueSet][npc.dialogueIndex] != null) {
                currentDialogue = npc.dialogues[npc.dialogueSet][npc.dialogueIndex];
                char characters[] = npc.dialogues[npc.dialogueSet][npc.dialogueIndex].toCharArray();
                if(charIndex < characters.length) {
                	String s = String.valueOf(characters[charIndex]);
                	combinedText = combinedText+s;
                	currentDialogue=combinedText;
                	charIndex++;
                	gp.playSE(20);
                }
                if (charIndex == characters.length && gp.keyH.zetapressed == true ) {
                	charIndex=0;
                	combinedText="";
                    if (gp.gameState == gp.dialogueState || gp.gameState == gp.cutsceneState) {
                        npc.dialogueIndex++;
                        gp.keyH.zetapressed = false;
                    }
                }
            } 
            else {
                npc.dialogueIndex = 0;
                if (gp.gameState == gp.dialogueState) {
                    gp.gameState = gp.playState;
                    npc = null; 
                }
                else if (gp.gameState == gp.cutsceneState) {
                    gp.csManager.scenePhase++;
                    npc = null;
                }
            }
        }
        else {
            if (gp.keyH.zetapressed == true) {
                if (gp.gameState == gp.dialogueState) {
                    gp.gameState = gp.playState; 
                }
                gp.keyH.zetapressed = false;
            }
        }

        if (currentDialogue != null) {
            for (String line : currentDialogue.split("\n")) {
                g2.drawString(line, x, y);
                y += 30;
            }
        }
    }
    
    public void drawInventory() {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.drawImage(inventoryImage, 0, 0, gp.screenWidth, gp.screenHeight, null);
        
        final int slotXstart = gp.tileSize + gp.tileSize/4;
        final int slotYstart = gp.tileSize * 4;
        final int slotStep = gp.tileSize + (gp.tileSize / 3); 
        
        int slotX = slotXstart;
        int slotY = slotYstart;

        int selectCount = 0; 
        int inUseCount = 0; 

        int inUseStartX = gp.tileSize + gp.tileSize/3;
        int inUseStartY = gp.tileSize * 7 + gp.tileSize / 2;

        for (int i = 0; i < gp.player.inventory.size(); i++) {
            Entity item = gp.player.inventory.get(i);
            
            if (item.type == item.type_inUse) {

                int inUseX = inUseStartX + (inUseCount * (gp.tileSize + 16));
                g2.drawImage(item.down1, inUseX, inUseStartY, gp.tileSize, gp.tileSize, null);
                inUseCount++;
            } else {

                g2.drawImage(item.down1, slotX, slotY, null);
                slotX += slotStep; 
                selectCount++;
                
                if (selectCount % 4 == 0) {
                    slotX = slotXstart;
                    slotY += slotStep;
                }
            }
        }

        g2.drawImage(swordImage, gp.tileSize * 3 -gp.tileSize/4, gp.tileSize - gp.tileSize / 2-gp.tileSize/5, gp.tileSize, gp.tileSize, null);       
        g2.setColor(Color.WHITE);
        g2.setFont(nesFont.deriveFont(16F));
        g2.drawString(String.valueOf(gp.player.coin), gp.tileSize *7+ gp.tileSize/4, gp.tileSize-8);
        
        int x = gp.tileSize * 6 + 22;
        int y = gp.tileSize - 8;
        int i = 0;
        while (i < gp.player.maxLife / 2) {
            g2.drawImage(heart_blank, x, y, gp.tileSize / 2, gp.tileSize / 2, null);
            i++;
            x += gp.tileSize / 2 - 4;
        }

        int bombValueX=gp.tileSize *12;
        int textY = gp.tileSize-8; 

        g2.drawString(String.valueOf(gp.player.bomb), bombValueX, textY);
        x = gp.tileSize * 6 + 22;
        y = gp.tileSize - 8;
        i = 0;
        while (i < gp.player.life) {
            g2.drawImage(heart_half, x, y, gp.tileSize / 2, gp.tileSize / 2, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(heart_full, x, y, gp.tileSize / 2, gp.tileSize / 2, null);
                i++;
                x += gp.tileSize / 2 - 4;
            }
        }

        int cursorX = slotXstart + (slotStep * slotCol);
        int cursorY = slotYstart + (slotStep * slotRow);   
        int cursorOffset = 8; 
        g2.drawImage(inventoryCursor, cursorX - cursorOffset, cursorY - cursorOffset, null);
        
        Entity hoveredItem = getHoveredItem();
        if (hoveredItem != null) {
            int inUseX = gp.tileSize  + gp.tileSize/6;
            int inUseY =  gp.tileSize - gp.tileSize / 2-gp.tileSize/5;
            g2.drawImage(hoveredItem.down1, inUseX, inUseY, gp.tileSize, gp.tileSize, null);
        }

        boolean hasCrystalBall = false;
        for (int j = 0; j < gp.player.inventory.size(); j++) {

            if (gp.player.inventory.get(j).name != null && gp.player.inventory.get(j).name.equals("Sfera di Cristallo")) {
                hasCrystalBall = true;
                break;
            }
        }   

        if (hasCrystalBall == true) {
            gp.map.drawMiniMap(g2);
        }
    }
    
    
    public Entity getHoveredItem() {
        int targetGridIndex = slotCol + (slotRow * 4);
        int currentCount = 0;
        
        for (int i = 0; i < gp.player.inventory.size(); i++) {
            if (gp.player.inventory.get(i).type != gp.player.inventory.get(i).type_inUse) {
                if (currentCount == targetGridIndex) {
                    return gp.player.inventory.get(i);
                }
                currentCount++;
            }
        }
        return null;
    }

    public void drawTransition(Graphics2D g2) {
        transitionCounter++;
        float alpha = 0f;
        if(transitionCounter <= 50) {
            alpha = transitionCounter * 0.02f;
            if(alpha > 1f) alpha = 1f;
        }
        if(transitionCounter == 50) {
            gp.eHandler.executeTeleport();
            alpha = 1f;
        }
        if(transitionCounter > 50 && transitionCounter <= 100) {
            alpha = (100 - transitionCounter) * 0.02f;
            if(alpha < 0f) alpha = 0f; 
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        if(transitionCounter == 100) {
            transitionCounter = 0;
            gp.gameState = gp.playState;
        }
    }
    
    public void drawTradeScreen() {
    	switch(subState) {
    	case 0: trade_select();break;
    	case 1: trade_buy();break;

    	}
    	gp.keyH.enterPressed=false;
    }
    
    public void trade_select() {
    	drawDialogueScreen();
    	
    	int x= gp.screenWidth/2-gp.tileSize*3;
    	int y= gp.screenHeight/2-gp.tileSize*3;
    	int width=gp.tileSize*5;
    	int height=gp.tileSize*3;
    	drawSubWindow(x,y,width,height);
    	x+=gp.tileSize;
    	y+=gp.tileSize+gp.tileSize/4;
    	g2.drawString("Compra", x, y);
    	if(commandNum==0) {
    		g2.drawString(">", x-gp.tileSize/2, y);
    		if(gp.keyH.zetapressed==true) {
    			subState=1;
    			gp.keyH.zetapressed = false;
    		}
    	}
    	y+=gp.tileSize;
    	g2.drawString("Esci", x, y);
    	if(commandNum==1) {
        	g2.drawString(">", x-gp.tileSize/2, y);
        	if(gp.keyH.zetapressed==true) {
    			commandNum=0;
    			subState=0;
    			npc.startDialogue(npc, 2);
    			charIndex = 0;
    			combinedText = "";
    			
    			gp.keyH.zetapressed = false;
    		}
        }
    }
  
    public void trade_buy() {
        int frameX = gp.tileSize / 2;
        int frameY = gp.tileSize * 3;
        int frameWidth = gp.tileSize * 6;
        int frameHeight = gp.tileSize * 5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        
        int slotXStart = frameX + gp.tileSize / 2;
        int slotYStart = frameY + gp.tileSize / 2;
        int slotStep = gp.tileSize + (gp.tileSize / 3);
        int slotX = slotXStart;
        int slotY = slotYStart;
        
        for (int i = 0; i < npc.inventory.size(); i++) {
            g2.drawImage(npc.inventory.get(i).down1, slotX, slotY, null);
            slotX += slotStep;
            if ((i + 1) % 4 == 0) {
                slotX = slotXStart;
                slotY += slotStep;
            }
        }
        
        int cursorX = slotXStart + (slotStep * slotCol);
        int cursorY = slotYStart + (slotStep * slotRow);
        int cursorOffset = 8;
        g2.drawImage(inventoryCursor, cursorX - cursorOffset, cursorY - cursorOffset, null);
        
        int dFrameX = frameX + frameWidth + gp.tileSize / 2;
        int dFrameY = frameY;
        int dFrameWidth = gp.tileSize * 8;
        int dFrameHeight = gp.tileSize * 5;
        drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
        
        int textX = dFrameX + gp.tileSize / 2;
        int textY = dFrameY + gp.tileSize;
        g2.setFont(nesFont.deriveFont(16F));

        int itemIndex = getItemIndexOnSlot();
               
        if (itemIndex < npc.inventory.size()) {
            Entity item = npc.inventory.get(itemIndex);
            
            g2.setColor(Color.YELLOW);
            g2.drawString(item.name, textX, textY);
            textY += gp.tileSize;
            
            g2.setColor(Color.WHITE);
            g2.drawString("Costo: " + item.price + " Coin", textX, textY);
            textY += gp.tileSize;
            
            if (gp.keyH.zetapressed == true) {
                
                if(item.price > gp.player.coin) {
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    npc.startDialogue(npc, 3);
                    charIndex = 0;
                    combinedText = "";
                }
                else {
                    if(gp.player.canObtainItem(item) == true) {
                        gp.player.coin -= item.price;
                        gp.playSE(23);
                        npc.inventory.remove(itemIndex);
                        
                        subState = 0;
                        gp.gameState = gp.dialogueState;
                        npc.startDialogue(npc, 2);
                        charIndex = 0;
                        combinedText = "";
                    }
                    else {
                        subState = 0;
                        gp.gameState = gp.dialogueState;
                        npc.startDialogue(npc, 4);
                        charIndex = 0;
                        combinedText = "";
                    }
                }
                gp.keyH.zetapressed = false;
            }
        } 
        else {
            if (gp.keyH.zetapressed == true) {
                gp.keyH.zetapressed = false;
            }
        }
        
      
    }
  
    public void drawOptionsScreen() {
    	g2.setColor(Color.white);
    	g2.setFont(nesFont.deriveFont(32F));
    	int frameX= 0;
    	int frameY= 0;
    	int frameWidth= gp.screenWidth;
    	int frameHeight= gp.screenHeight;
    	drawSubWindow(frameX, frameY, frameWidth, frameHeight);
    	switch(subState) {
    	case 0: options_top( frameX,  frameY);break;
    	case 1:options_endGameConfirmation(frameX,  frameY); break;
    	case 2:options_control(frameX,  frameY); break;
    	}
    }
    
    public void options_top(int frameX, int frameY) {
    	int textX;
    	int textY;
    	String text="Opzioni";
    	textX=getXForCenteredText(text);
    	textY=frameY+gp.tileSize;
    	g2.drawString(text, textX, textY);
    	//MUSIC
    	textY+=gp.tileSize*2;
    	textX=frameX+gp.tileSize+gp.tileSize/2;
    	g2.drawString("Musica", textX,textY);
    	if(commandNum==0) {
    		g2.drawString(">", textX-32, textY);
    	}
    	//SE
    	textY+=gp.tileSize+gp.tileSize/2;
    	g2.drawString("SE", textX,textY);
    	if(commandNum==1) {
    		g2.drawString(">", textX-32, textY);
    	}
    	//CONTROL
    	textY+=gp.tileSize +gp.tileSize/2;
        g2.drawString("Controlli", textX,textY);
        if(commandNum==2) {
            g2.drawString(">", textX-32, textY);
            if(gp.keyH.enterPressed == true) {
            	subState =2;
            	commandNum=0;
            	gp.keyH.enterPressed = false;
            }
           
        }
    	//END GAME
    	textY+=gp.tileSize + gp.tileSize/2;
    	g2.drawString("Esci dal gioco", textX,textY);
    	if(commandNum==3) {
    		g2.drawString(">", textX-32, textY);
    		if(gp.keyH.enterPressed == true) {
            	subState =1;
            	commandNum=0;
            	gp.keyH.enterPressed = false;
            }
    	}
    	//Back
    	textY+=gp.tileSize*3;
    	g2.drawString("Indietro", textX,textY);
    	if(commandNum==4) {
    		g2.drawString(">", textX-32, textY);
    		if(gp.keyH.enterPressed == true) {
    			gp.gameState=gp.playState;
    			commandNum=0;
    		}
    	}
    	
    	//music volume
    	textX= frameX + gp.tileSize*8;
    	textY= frameY+gp.tileSize*2+21;
    	g2.setStroke(new BasicStroke(3));
    	g2.drawRect(textX, textY, 240, 24);
    	int volumeWidth=48*gp.music.volumeScale;
    	g2.fillRect(textX, textY, volumeWidth, 24);
    	
    			
    	
    	//se
    	textX= frameX + gp.tileSize*8;
    	textY+= gp.tileSize+17;
    	g2.drawRect(textX, textY, 240, 24);
    	volumeWidth=48*gp.se.volumeScale;
    	g2.fillRect(textX, textY, volumeWidth, 24);
    	
    }

    public void options_control(int frameX, int frameY) {
    	int textX;
    	int textY;
    	String text ="Controlli";
    	textX = getXForCenteredText(text);
    	textY=frameY+gp.tileSize;
    	textX=frameX+gp.tileSize+gp.tileSize/2;
    	g2.setFont(nesFont.deriveFont(20F));
    	g2.drawString("Movimento", textX,textY);
    	textY+=gp.tileSize+gp.tileSize/2;
    	g2.drawString("Conferma/Attacca", textX,textY);
    	textY+=gp.tileSize+gp.tileSize/2;
    	g2.drawString("Usa l'item", textX,textY);
    	textY+=gp.tileSize+gp.tileSize/2;
    	g2.drawString("Apre l'inventario", textX,textY);
    	textY+=gp.tileSize+gp.tileSize/2;
    	g2.drawString("Opzioni", textX,textY);
    	textY+=gp.tileSize*3;
        g2.drawString("Indietro", textX,textY);
        if(commandNum==0) {
            g2.drawString(">", textX-32, textY);
            if(gp.keyH.enterPressed==true) {
                subState=0;
                commandNum=2;
                gp.keyH.enterPressed = false; 
            }
        }

    	textX=frameX+gp.tileSize*10;
    	textY=frameY+gp.tileSize;
    	g2.drawString("→  ←  ↑  ↓ ", textX,textY);
    	textY+=gp.tileSize+gp.tileSize/2;
    	g2.drawString("Z", textX,textY);
    	textY+=gp.tileSize+gp.tileSize/2;
    	g2.drawString("X", textX,textY);
    	textY+=gp.tileSize+gp.tileSize/2;
    	g2.drawString("ENTER", textX,textY);
    	textY+=gp.tileSize+gp.tileSize/2;
    	g2.drawString("ESC", textX,textY);
    }
    
    public void options_endGameConfirmation(int frameX, int frameY){
    	
    	int textX = frameX +gp.tileSize*2-gp.tileSize/2;
    	int textY=frameY+gp.tileSize*2;
    	currentDialogue="Vuoi uscire dal gioco\ne tornare alla\nschermata\ndi inizio gioco?";
    	for(String line: currentDialogue.split("\n")){
    		g2.drawString(line, textX, textY);
    		textY+=40;
    	}
    	//yes
    	String text ="Si";
    	textX =getXForCenteredText(text);
    	textY+=gp.tileSize*2;
    	g2.drawString(text, textX, textY);
    	if(commandNum==0) {
    		g2.drawString(">", textX-32, textY);
    		if(gp.keyH.enterPressed == true) {
    			subState=0;
    			gp.restart();
    			gp.gameState=gp.titleState;
    		}
    	}
    	//NO
    	text ="No";
    	textX =getXForCenteredText(text);
    	textY+=gp.tileSize*2;
    	g2.drawString(text, textX, textY);
    	if(commandNum==1) {
    		g2.drawString(">", textX-gp.tileSize, textY);
    		if(gp.keyH.enterPressed == true) {
    			subState=0;
    			commandNum=3;
    			gp.keyH.enterPressed =false;
    		}
    	}
    }
    
    public int getItemIndexOnSlot() {
    	int itemIndex = slotCol +(slotRow*4);
    	return itemIndex;
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
