package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {

	GamePanel gp;
	public Tile [] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		tile = new Tile[65];
		mapTileNum = new int [gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/worldMap.txt");
	}
	
	public void loadMap(String filePath) {
	    try {
	        InputStream is = getClass().getResourceAsStream(filePath);
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));
	        
	        int col = 0;
	        int row = 0;

	        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
	            String line = br.readLine();
	            while(col < gp.maxWorldCol) {
	            	String numbers [] = line.split(" ");
	            	int num = Integer.parseInt(numbers[col]);
	            	mapTileNum[col][row] = num;
	            	col++;
	            }
	            	
	            	if(col == gp.maxWorldCol) {
	            		col = 0;
	            		row++;
	            	}			
	            }
	        br.close();
	    } catch (Exception e) {
	    	
	    }
	}

	
	public void getTileImage() {
			setup(0,"water",true);
			setup(1,"grass",false);
			setup(2,"grassT",false);
			setup(3,"grassTL",false);
			setup(4,"grassTR",false);
			setup(5,"grassL",false);
			setup(6,"grassR",false);
			setup(7,"grassB",false);
			setup(8,"grassBL",false);
			setup(9,"grassBR",false);
			setup(10,"rock",true);
			setup(11,"rockT",true);
			setup(12,"rockTL",true);
			setup(13,"rockTR",true);
			setup(14,"rockL",true);
			setup(15,"rockR",true);
			setup(16,"rockB",true);
			setup(17,"rockBL",true);
			setup(18,"rockBR",true);
			setup(19,"rockTW",true);
			setup(20,"wood",true);
			setup(21,"woodL",true);
			setup(22,"woodR",true);
			setup(23,"woodBG",true);
			setup(24,"woodBLG",true);
			setup(25,"woodBRG",true);
			setup(26,"woodBW",true);
			setup(27,"woodBLW",true);
			setup(28,"woodBRW",true);
			setup(29,"treeB",true);
			setup(30,"treeM",true);
			setup(31,"treeT",true);
			setup(32,"statueB",true);
			setup(33,"statueT",true);
			setup(34,"doorB",false);
			setup(35,"doorT",false);
			setup(36,"stair",false);	
			setup(37,"templeTL",true);	
			setup(38,"templeT",true);	
			setup(39,"templeTR",true);	
			setup(40,"templeML",true);	
			setup(41,"templeM",true);	
			setup(42,"templeMR",true);	
			setup(43,"templeL",true);	
			setup(44,"temple",false);	
			setup(45,"templeR",true);	
			setup(46,"templeBL",true);	
			setup(47,"templeB",false);	
			setup(48,"templeBR",true);	
			setup(49,"grassTLW",true);	
			setup(50,"grassTRW",true);	
			setup(51,"grassBLW",true);	
			setup(52,"grassBRW",true);	
			setup(53,"rockTRG",true);
			setup(54,"btempleTL",true);	
			setup(55,"btempleT",true);	
			setup(56,"btempleTR",true);		
			setup(57,"btempleL",true);	
			setup(58,"btemple",false);	
			setup(59,"btempleR",true);	
			setup(60,"btempleBL",true);	
			setup(61,"btempleB",false);	
			setup(62,"btempleBR",true);	

	}
	
	public void setup(int index, String imageName, boolean collision) {
		UtilityTool uTool = new UtilityTool();
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResource("/tiles/"+imageName+".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
		} catch(IOException e){
			e.printStackTrace();
		}
		
		
	}
	
	public void draw(Graphics2D g2) {

		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol<gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow];
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.cameraX;
			int screenY = worldY - gp.cameraY;

			// Draw only if it is in the screen
			if (screenX + gp.tileSize > 0 && screenX < gp.screenWidth &&
			    screenY + gp.tileSize > 0 && screenY < gp.screenHeight) {
			    g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}

			
			// here we draw only the tile near the player for improving the rendering efficiency
			if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
				worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			}
			
			worldCol++;
			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
}
