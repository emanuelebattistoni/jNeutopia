package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, zetapressed, shotKeyPressed;
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
		else if(gp.gameState== gp.optionsState) {
			optionsState(code);
		}
		else if(gp.gameState== gp.tradeState) {
			tradeState(code);
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
			switch(gp.currentMap) {
			case 0:gp.tileM.loadMap("/maps/worldMap.txt",0,64, 33);break;
			case 1:gp.tileM.loadMap("/maps/room1.txt",1,21, 14);break;
			case 2:gp.tileM.loadMap("/maps/room2.txt",2,21, 14);break;
			case 3:gp.tileM.loadMap("/maps/room3.txt",3,22, 14);break;
			case 4:gp.tileM.loadMap("/maps/room4.txt",4,21, 15);break;
			case 5:gp.tileM.loadMap("/maps/room5.txt",5,22, 29);break;
			case 6:gp.tileM.loadMap("/maps/dungeon1.txt",6,gp.maxWorldCol, gp.maxWorldRow);break;
			}
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
				//gp.playMusic(0);
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
		if(code == KeyEvent.VK_X) {
			if (gp.player.currentItem != null) {
                gp.player.selectItem();
            }
		}
		if(code == KeyEvent.VK_ESCAPE) {
			gp.gameState=gp.optionsState;
		}
	}
	public void inventoryState(int code) {
		if(code == KeyEvent.VK_ENTER) {
			gp.gameState = gp.playState;
			//gp.playSE(8)
			}
			if(code == KeyEvent.VK_UP) {
				if(gp.ui.slotRow!=0) {
				gp.ui.slotRow--;
				//gp.playSE(8)
				}
			}
			if(code == KeyEvent.VK_DOWN) {
				if(gp.ui.slotRow!=1){
				gp.ui.slotRow++;
				//gp.playSE(8)
				}
			}
			if(code == KeyEvent.VK_LEFT) {
				if(gp.ui.slotCol!=0) {
				gp.ui.slotCol--;
				//gp.playSE(8)
				}
			}
			if(code == KeyEvent.VK_RIGHT) {
				if(gp.ui.slotCol!=3) {
				gp.ui.slotCol++;
				//gp.playSE(8)
				}
			}	
			if(code == KeyEvent.VK_ENTER) {
				int itemIndex=gp.ui.getItemIndexOnSlot();
				if (itemIndex < gp.player.inventory.size()) {
	                gp.player.currentItem = gp.player.inventory.get(itemIndex);
	             // gp.playSE(X);
				}
			}
	}
	
	public void dialogueState(int code) {
		if(code == KeyEvent.VK_Z) {
			gp.gameState = gp.playState;
			zetapressed = false;
			gp.player.drawingGetItem = false;
			gp.player.itemObtained = null;
		}
	}
	
	public void optionsState(int code) {
		if(code == KeyEvent.VK_ESCAPE) {
			gp.gameState=gp.playState;
			gp.ui.subState=0;
			gp.ui.commandNum=0;
		}
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		int maxCommandNum=0;
		switch(gp.ui.subState) {
		case 0: maxCommandNum=4;break;
		case 1:maxCommandNum=1;break;
		}
		if(code == KeyEvent.VK_UP) {
			gp.ui.commandNum--;
			if(gp.ui.commandNum<0) {
				gp.ui.commandNum= maxCommandNum;
			}
		}
		if(code == KeyEvent.VK_DOWN) {
			gp.ui.commandNum++;
			if(gp.ui.commandNum>maxCommandNum) {
				gp.ui.commandNum= 0;
			}
		}
		if(code == KeyEvent.VK_LEFT) {
			if(gp.ui.subState==0) {
				if(gp.ui.commandNum==0 && gp.music.volumeScale > 0) {
					gp.music.volumeScale--;
					gp.music.checkVolume();
					//gp.playSE();
				}
				if(gp.ui.commandNum==1 && gp.se.volumeScale > 0) {
					gp.se.volumeScale--;

					//gp.playSE();
				}
			}
		}
		if(code == KeyEvent.VK_RIGHT) {
			if(gp.ui.subState==0) {
				if(gp.ui.commandNum==0 && gp.music.volumeScale < 5) {
					gp.music.volumeScale++;
					gp.music.checkVolume();
					//gp.playSE();
				}
				if(gp.ui.subState==0) {
					if(gp.ui.commandNum==1 && gp.se.volumeScale < 5) {
						gp.se.volumeScale++;

						//gp.playSE();
					}
				}
			}
		}	
	}
	
	public void tradeState(int code) {
		if(code == KeyEvent.VK_Z) {
			zetapressed = true;
		}		
		if(gp.ui.subState == 0) {
			if(code == KeyEvent.VK_UP) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 1;
				}
			}
			if(code == KeyEvent.VK_DOWN) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 1) {
					gp.ui.commandNum = 0;
				}
			}
		}
		else if(gp.ui.subState == 1) { 
			if(code == KeyEvent.VK_UP) {
				if(gp.ui.slotRow > 0) gp.ui.slotRow--;
			}
			if(code == KeyEvent.VK_DOWN) {
				if(gp.ui.slotRow < 2) gp.ui.slotRow++;
			}
			if(code == KeyEvent.VK_LEFT) {
				if(gp.ui.slotCol > 0) gp.ui.slotCol--;
			}
			if(code == KeyEvent.VK_RIGHT) {
				if(gp.ui.slotCol < 3) gp.ui.slotCol++;
			}
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
		if(code == KeyEvent.VK_F) {
			shotKeyPressed=false;
		}
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = false;
		}
		if(code == KeyEvent.VK_Z) {
			zetapressed = false;
		}
	}

}
