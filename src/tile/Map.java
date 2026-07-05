package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Map extends TileManager{
	GamePanel gp;
	BufferedImage worldMap[];
	public Map(GamePanel gp) {
		super(gp);
		this.gp= gp;

	}

	public void createWorldMap() {
		worldMap = new BufferedImage[gp.maxMap];
		
		for(int i = 0; i < gp.maxMap; i++) {
			if(mapTileNum[i] == null) {
				continue;
			}
			int mapCols = mapTileNum[i].length;
			int mapRows = mapTileNum[i][0].length;

			int worldMapWidth = gp.tileSize * mapCols;
			int worldMapHeight = gp.tileSize * mapRows;
			
			worldMap[i] = new BufferedImage(worldMapWidth, worldMapHeight, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = (Graphics2D)worldMap[i].createGraphics();
			
			int col = 0;
			int row = 0;

			while(col < mapCols && row < mapRows) {
				int tileNum = mapTileNum[i][col][row];
				int x = gp.tileSize * col;
				int y = gp.tileSize * row;
				
				g2.drawImage(tile[tileNum].image, x, y, null);
				
				col++;
				if(col == mapCols) {
					col = 0;
					row++;
				}
			}
		}
	}
	public void drawMiniMap(Graphics2D g2) {
		int width = 200;
		int height = 200;
		int x = gp.screenWidth - width - 100;
		int y = gp.screenHeight/2;
		
		g2.drawImage(worldMap[gp.currentMap], x, y, width, height, null);

		int currentMapCols = mapTileNum[gp.currentMap].length;

		double scale = (double)(gp.tileSize * currentMapCols) / width;
		
		int playerX = (int)(x + gp.player.worldX / scale);
		int playerY = (int)(y + gp.player.worldY / scale);
		int playerSize = (int)(gp.tileSize / scale);

		g2.drawImage(gp.player.down1, playerX, playerY, playerSize, playerSize, null);
	}
}
