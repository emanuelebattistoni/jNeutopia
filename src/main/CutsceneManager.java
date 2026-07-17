package main;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import object.OBJ_Medallion;

public class CutsceneManager {
	GamePanel gp;
	Graphics2D g2;
	public int sceneNum;
	public int scenePhase;
	int counter=0;
	float alpha=0;
	int y;
	
	public final int NA =0;
	public final int ending=1;
	
	public CutsceneManager(GamePanel gp) {
		this.gp = gp;
	}
	
	public void draw(Graphics2D g2) {
		this.g2=g2;
		gp.ui.g2 = g2;
		switch(sceneNum) {
		case ending : scene_ending();break;
		}
	}
	
	public void scene_ending() {
		if(scenePhase==0) {
			gp.ui.npc = new OBJ_Medallion(gp);
			scenePhase++;
		}
		if(scenePhase==1) {

			gp.ui.drawDialogueScreen();
		}
		if(scenePhase==2) {
			gp.stopMusic();
			gp.playSE(6); 
			scenePhase++;
			gp.playMusic(25);
			
		}
		if(scenePhase==3) {
			if(counterReached(150)==true) {
				scenePhase++;
			}
		}
		if(scenePhase==4) {
			alpha+=0.005;
			if(alpha>1f) {
				alpha=1F;
			}
			drawBlackBackground(alpha);
			if(alpha==1f) {
				alpha=0;
				scenePhase++;
			}
		}
		if(scenePhase==5) {
			drawBlackBackground(1f);
			alpha+=0.005;
			if(alpha>1f) {
				alpha=1F;
			}
			String text="Hai raggiunto il tuo obiettivo!\n"
					+ "Ora puoi finalmente tornare a casa\n"
					+ "con l'amore della principessa\n"
					+ " e il potere e la saggezza\n"
					+ "dei nostri antenati.";
			drawString(alpha,20F, 130, text, 70);
			if(counterReached(600)==true) {
				scenePhase++;
			}
		}
		if(scenePhase==6) {
			drawBlackBackground(1f);
			drawString(alpha,38F, gp.screenHeight/2-70, "JNeutopia\nby\nEmanuele Battistoni", 70);
		if(counterReached(600)==true) {
			sceneNum = NA;
			scenePhase=0;
			gp.restart();
		}
		}
	}
	
	public boolean counterReached(int target) {
		boolean counterReached= false;
		counter++;
		if(counter > target) {
			counterReached = true;
			counter=0;
		}
		return counterReached;
	}
	
	public void drawBlackBackground(float alpha) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
		g2.setColor(Color.black);
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
	}
	
	public void drawString(float alpha, float fontSize, int y, String text, int lineHeight) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
		g2.setColor(Color.white);
		g2.setFont(gp.ui.nesFont.deriveFont(fontSize));		
		for(String line : text.split("\n")) {
			int x = gp.ui.getXForCenteredText(line);
			g2.drawString(line, x, y);
			y+=lineHeight;
		}
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
	}
 
}
