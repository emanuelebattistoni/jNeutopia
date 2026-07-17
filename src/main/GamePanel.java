package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import data.SaveLoad;
import entity.Entity;
import entity.Player;
import tile.Map;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	//SCREEN SETTINGS
	final int originalTileSize =16;	//16x16 tile
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; // 48 x 48 tile size
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 11;
	public final int maxMap = 10;
	public int currentMap=6;
	
	public final int screenWidth = tileSize * maxScreenCol;	//768 pixels
	public final int screenHeight = tileSize * maxScreenRow;	//528 pixels
	
	//WORLD SETTINGS 
	public final int maxWorldCol=80;
	public final int maxWorldRow=81;
	public final int worldWidth  = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	// Camera SETTING	
	public int cameraX = 0;
	public int cameraY = 0;


	int FPS = 60;
	// System
	TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	Sound music = new Sound();
	Sound se = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler(this);
	Map map = new Map(this);
	public SaveLoad saveLoad = new SaveLoad(this);
	public EntityGenerator eGenerator = new EntityGenerator(this);
	public CutsceneManager csManager = new CutsceneManager(this);
	Thread gameThread;
	
	//entity and object
	public Player player = new Player(this, keyH);
	public Entity obj[][] = new Entity[maxMap][20];
	public Entity npc[][] = new Entity[maxMap][8];
	public Entity monster[][]= new Entity[maxMap][70];
	public Entity iTile[][] = new Entity [maxMap][25];
	public ArrayList<Entity> projectileList = new ArrayList<>();
	ArrayList<Entity> entityList = new ArrayList<>();
	
	//GAME STATE
	public int gameState;
	public final int titleState=0;
	public final int playState = 1;
	public final int inventoryState = 2;
	public final int dialogueState = 3;
	public final int optionsState = 4;
	public final int gameOverState = 5;
	public final int transitionState = 6;
	public final int tradeState = 7;
	public final int cutsceneState = 8;
	public boolean bossBattleOn = false;
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		playMusic(0);
		aSetter.setObject();
		aSetter.setNpc();
		aSetter.setMonster();
		aSetter.setInteractivetile();
		map.createWorldMap();
		gameState = titleState;
	}
	
	public void retry() {
		player.setDefaultPosition();
		player.restoreLife();
		player.resetCounter();
		aSetter.setMonster();
		gameState=playState;
		stopMusic();
		playMusic(3);
	}
	
	public void restart() {
		
		aSetter.setObject();
		player.resetCounter();
		aSetter.setNpc();
		aSetter.setMonster();
		aSetter.setInteractivetile();		
		player.setDefaultValues();
		csManager.sceneNum = csManager.NA;
		csManager.scenePhase = 0;
		csManager.alpha = 0f;     
		csManager.counter = 0;
		csManager.y = 0;
		ui.commandNum = 0;
		ui.subState = 0;
		ui.charIndex = 0;
		ui.combinedText = "";
		ui.npc = null;
		ui.transitionCounter = 0;
		gameState = titleState;
		stopMusic();
		playMusic(0);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		//create the game loop
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >=1) {
			update();
			repaint();
			delta--;
			drawCount++;
			}
			if(timer >= 1000000000) {
				drawCount = 0;
				timer = 0;
			}
		}
	} 
	
	public void update() {
		if(gameState == playState) {
		player.update();
		for(int i = 0; i < npc[1].length; i++) {
			if(npc[currentMap][i] != null) {
				npc[currentMap][i].update();
			}
		}
		
		for(int i = 0; i < monster[1].length; i++) {
			if(monster[currentMap][i] != null) {
				if (monster[currentMap][i].alive == true) {
				monster[currentMap][i].update();
				}
				if (monster[currentMap][i].alive == false) {
					monster[currentMap][i].checkDrop();
					monster[currentMap][i]=null;
					}
			}
		}
		for(int i = 0; i < projectileList.size(); i++) {
			if(projectileList.get(i) != null) {
				if (projectileList.get(i).alive == true ) {
					projectileList.get(i).update();
				}
				if (projectileList.get(i).alive == false) {
					projectileList.remove(i);
					i--;
					}
				}
			}
		for(int i = 0; i < iTile[1].length ; i++) {
			if(iTile[currentMap][i] != null) {
				iTile[currentMap][i].update();
			}	
		}
		}
		
	}
	
	public void paintComponent(Graphics g) {

		int currentWorldWidth = tileM.mapTileNum[currentMap].length * tileSize;
		int currentWorldHeight = tileM.mapTileNum[currentMap][0].length * tileSize;

		cameraX = player.worldX - screenWidth / 2 + tileSize / 2;
		cameraY = player.worldY - screenHeight / 2 + tileSize / 2;

		if (currentWorldWidth < screenWidth) {

			cameraX = (currentWorldWidth - screenWidth) / 2;
		} else {

			if (cameraX < 0) cameraX = 0;
			if (cameraX > currentWorldWidth - screenWidth) cameraX = currentWorldWidth - screenWidth;
		}

		if (currentWorldHeight < screenHeight) {

			cameraY = (currentWorldHeight - screenHeight) / 2;
		} else {

			if (cameraY < 0) cameraY = 0;
			if (cameraY > currentWorldHeight - screenHeight) cameraY = currentWorldHeight - screenHeight;
		}

		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		//debug
		long drawStart =0;
		if(keyH.showDebugText == true) {
			drawStart=System.nanoTime();
		}
		
		//title screen
		if(gameState==titleState) {
			ui.draw(g2);
		}
		//others
		else {
			 //tile
			tileM.draw(g2);
			for(int i = 0; i < iTile[1].length ; i++) {
				if(iTile[currentMap][i] != null) {
					iTile[currentMap][i].draw(g2);
				}
			}
			//player
			entityList.add(player);
			//NPC
			for(int i = 0; i < npc[1].length ; i++) {
				if(npc[currentMap][i] != null) {
					entityList.add(npc[currentMap][i]);
				}	
			}
			//object
			for(int i = 0; i < obj[1].length ; i++) {
				if(obj[currentMap][i] != null) {
					entityList.add(obj[currentMap][i]);
				}	
			}	
			//monster
			for(int i = 0; i < monster[1].length ; i++) {
				if(monster[currentMap][i] != null) {
					entityList.add(monster[currentMap][i]);
				}	
			}
			for(int i = 0; i < projectileList.size() ; i++) {
				if(projectileList.get(i) != null) {
					entityList.add(projectileList.get(i));
				}	
			}
			for(int i = 0; i < iTile[1].length ; i++) {
				if(iTile[currentMap][i] != null) {
					iTile[currentMap][i].update();
				}	
			}
			//sort
				Collections.sort(entityList, new Comparator<Entity>(){
					@Override
					public int compare(Entity e1, Entity e2) {
						int result = Integer.compare(e1.worldY, e2.worldY);
						return result;
					}
				});
				
			//draw entities
			for(int i =0; i < entityList.size(); i++) {
				entityList.get(i).draw(g2);
			}
			
			//empty entities
			entityList.clear();
			
			//cutscene
			csManager.draw(g2);
			
			//UI
			ui.draw(g2);
		}	
		//DEBUG 
//		if(keyH.showDebugText == true) {
//			long drawEnd = System.nanoTime();
//			long passed = drawEnd - drawStart;
//			g2.setColor(Color.white);
//			g2.setFont(new Font("Arial", Font.PLAIN,20));
//			g2.setColor(Color.white);
//			int x=10;
//			int y = 400;	
//			int lineHeight = 20;
//			g2.drawString("Draw Time: "+ passed, 10 , 400); y+=lineHeight;
//			g2.drawString("WorldX"+player.worldX, x, y); y+=lineHeight;
//			g2.drawString("WorldY"+player.worldY, x, y); y+=lineHeight;
//			g2.drawString("Col"+(player.worldX +player.solidArea.x)/tileSize, x, y); y+=lineHeight;
//			g2.drawString("Row"+(player.worldY +player.solidArea.y)/tileSize, x, y); y+=lineHeight;
//		}
		
		
		g2.dispose();
	}
	public void playMusic(int i) {
		music.setFIle(i);
		music.play();
		music.loop();
	}
	public void stopMusic(){
		music.stop();
	}
	public void playSE(int i) {
		se.setFIle(i);
		se.play();
	}
}
