package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, zetapressed;
	//DEBUG
	public boolean showDebugText = false;

	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	@Override
	public void keyTyped(KeyEvent e) {		
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();
		
		//title state
		if (gp.gameState == gp.titleState) {
			titleState(code);
		}
		//PlayState
		else if (gp.gameState == gp.playState){
			playState(code);
		}
		//inventory
		else if(gp.gameState == gp.inventoryState) {
			inventoryState(code); 
		}
		//DIALOGUE STATE
		else if (gp.gameState == gp.dialogueState) {
			dialogueState(code);
		}



		//DEBUG
		if(code == KeyEvent.VK_T) {
			if(showDebugText == false) {
				showDebugText = true;
			}else if (showDebugText == true) {
				showDebugText = false;
			}
		}
		if(code == KeyEvent.VK_R) {
			gp.tileM.loadMap("/maps/worldMap.txt");
		}
	}
	public void titleState(int code) {
		if(code == KeyEvent.VK_UP) {
			gp.ui.commandNum--;
			if(gp.ui.commandNum <0) {
				gp.ui.commandNum=1;
			}
			}
		if(code == KeyEvent.VK_DOWN) {
				downPressed = true;
				gp.ui.commandNum++;
				if(gp.ui.commandNum >1) {
					gp.ui.commandNum=0;
				}
		}
		if(code == KeyEvent.VK_ENTER) {
			if(gp.ui.commandNum==0) {
				gp.gameState=gp.playState;
				gp.playMusic(0);
			}
			if(gp.ui.commandNum==1) {
				//add later;
			}
		}
	}
	public void playState(int code) {
		if(code == KeyEvent.VK_UP) {
			upPressed = true;
		}
		if(code == KeyEvent.VK_DOWN) {
			downPressed = true;
		}
		if(code == KeyEvent.VK_LEFT) {
			leftPressed = true;
		}
		if(code == KeyEvent.VK_RIGHT) {
			rightPressed = true;
		}
		if(code == KeyEvent.VK_ENTER	) {
			gp.gameState = gp.inventoryState;
		}
		if(code == KeyEvent.VK_Z) {
				zetapressed = true;
		}
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
	}
	public void inventoryState(int code) {
		if(code == KeyEvent.VK_ENTER) {
			gp.gameState = gp.playState;
		}	
	}
	
	public void dialogueState(int code) {
		if(code == KeyEvent.VK_Z) {
			gp.gameState = gp.playState;
		}
	}



	@Override
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();

		if(code == KeyEvent.VK_UP) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_DOWN) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		}
	}

}
