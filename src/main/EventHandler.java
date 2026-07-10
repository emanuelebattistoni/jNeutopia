package main;


public class EventHandler {
	GamePanel gp;
	EventRect eventRect[][][];
	int previousEventX, previousEventY;
	boolean canTouchEvent = true;
	boolean isTeleporting = false;
	int teleportCounter = 0;
	int targetMap, targetCol, targetRow;

		
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		eventRect= new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		int map=0;
		int col =0;
		int row =0;
		while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
			eventRect[map][col][row] = new EventRect();
			eventRect[map][col][row].x= 23;
			eventRect[map][col][row].y=23;
			eventRect[map][col][row].width=2;
			eventRect[map][col][row].height=2;
			eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
			eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
			col++;
			if (col == gp.maxWorldCol) {
				col=0;
				row++;
				if(row == gp.maxWorldRow) {
					row=0;
					map++;
				}
			}
		}
	}
	
	public void checkEvent() {
		if(isTeleporting == true) {
			teleportCounter++;
			
			if(teleportCounter > 30) { 
				executeTeleport(); 
				isTeleporting = false;
				teleportCounter = 0;
			}
			return; 
		}
		//check if the character is more than 1 tile away from last event
		int xDistance = Math.abs(gp.player.worldX-previousEventX);
		int yDistance = Math.abs(gp.player.worldY-previousEventY);
		int distance = Math.max(xDistance, yDistance);
		if(distance>gp.tileSize) {
			canTouchEvent = true;
		}
		if(canTouchEvent==true) {
			if(hit(0,41,26,"any")==true) {teleport(1,12, 11);}
			if(hit(1,12,12,"any")==true || (hit(1,13,12,"any")==true)) {teleport(0,41,27);}
			if(hit(0,40,18,"any")==true) {teleport(5,13,26);}
			if(hit(5,12,27,"any")==true || hit(5,13,27,"any")==true) {teleport(0,40,19);}
			if(hit(0,61,24,"any")==true) {teleport(7,12, 11);}
			if(hit(7,12,12,"any")==true || (hit(7,13,12,"any")==true)) {teleport(0,61,25);}
			if(hit(0,57,5,"any")==true) {teleport(2,12, 11);}
			if(hit(2,12,12,"any")==true || (hit(2,13,12,"any")==true)) {teleport(0,57,6);}
			if(hit(0,19,24,"any")==true) {teleport(3,12, 11);}
			if(hit(3,12,12,"any")==true || (hit(3,13,12,"any")==true)) {teleport(0,19,25);}	
			if(hit(0,20,16,"any")==true) {teleport(4,12, 11);}
			if(hit(4,12,12,"any")==true || (hit(4,13,12,"any")==true)) {teleport(0,20,17);}
			if(hit(0,8,5,"any")==true) {teleport(8,12, 11);}
			if(hit(8,12,12,"any")==true || (hit(8,13,12,"any")==true)) {teleport(0,8,6);}
			if(hit(0,25,6,"any")==true) {teleport(6,7,78);}
			if(hit(6,7,79,"any")==true || (hit(6,7,79,"any")==true)){
				{teleport(0,25,7);}
				gp.aSetter.setMonster();
			}
		}
	}
	
	public boolean hit(int map,int col, int row, String reqDirection) {
		boolean hit = false;
		if (map == gp.currentMap) {
	        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
	        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
	        eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
	        eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;
	        
	        if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone==false) {
	            if(gp.player.direction.equals(reqDirection) || reqDirection.contentEquals("any")) {
	                hit = true;
	                previousEventX = gp.player.worldX;
	                previousEventY = gp.player.worldY;
	            }
	        }
	        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
	        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
	        eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
	        eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
	    }
	    return hit;
	}
	
	public void damagePit(int gameState) {
		gp.gameState = gameState;
		gp.player.life--;
	}
	
	public void teleport(int map, int x, int y) {
	    targetMap = map;
	    targetCol = x;
	    targetRow = y;
	    
	    gp.gameState = gp.transitionState; 
	}
	public void executeTeleport() {
	    gp.currentMap = targetMap;
	    gp.player.worldX = gp.tileSize * targetCol;
	    gp.player.worldY = gp.tileSize * targetRow;
	    previousEventX = gp.player.worldX;
	    previousEventY = gp.player.worldY;
	    canTouchEvent = false;
	}
	
}