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
	public int mapTileNum[][][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		tile = new Tile[153];
		mapTileNum = new int [gp.maxMap] [][];
		
		getTileImage();
		loadMap("/maps/worldMap.txt", 0, 64, 33);
		loadMap("/maps/room1.txt", 1, 21,14);
		loadMap("/maps/room1.2.txt", 7, 21,14);
		loadMap("/maps/room2.txt", 2, 21,14);
		loadMap("/maps/room3.txt", 3, 22,14);
		loadMap("/maps/room3.2.txt", 8, 22,14);
		loadMap("/maps/room4.txt", 4, 21,15);
		loadMap("/maps/room5.txt", 5, 22,29);
		loadMap("/maps/dungeon1.txt", 6, gp.maxWorldCol, gp.maxWorldRow);
	}
	
	public void loadMap(String filePath, int map, int maxCol, int maxRow) {
	    try {
	    	mapTileNum[map] = new int[maxCol][maxRow];
	        InputStream is = getClass().getResourceAsStream(filePath);
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));
	        
	        int col = 0;
	        int row = 0;

	        while (col < maxCol && row < maxRow) {
	            String line = br.readLine();
	            while(col < maxCol) {
	            	String numbers [] = line.split(" ");
	            	int num = Integer.parseInt(numbers[col]);
	            	mapTileNum[map][col][row] = num;
	            	col++;
	            }
	            	
	            	if(col == maxCol) {
	            		col = 0;
	            		row++;
	            	}			
	            }
	        br.close();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}

	
	public void getTileImage() {
		//overworld
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
			//room
			setup(63,"floor",false);
			setup(64,"fireplaceTL",true);
			setup(65,"fireplaceTR",true);
			setup(66,"fireplaceDL",true);
			setup(67,"fireplaceDR",true);
			setup(68,"furTL",false);
			setup(69,"furTR",false);
			setup(70,"furDL",false);
			setup(71,"furDR",false);
			setup(72,"black",true);
			setup(73,"roomWallTL",true);
			setup(74,"roomWallL",true);
			setup(75,"roomWallL2",true);
			setup(76,"roomWallDL",true);
			setup(77,"roomWallT",true);
			setup(78,"roomWallT2",true);
			setup(79,"roomWallTR",true);
			setup(80,"roomWallR",true);
			setup(81,"roomWallR2",true);
			setup(82,"weaponL",true);
			setup(83,"weaponR",true);
			setup(84,"roomWallDR",true);
			setup(85,"roomWall",true);
			setup(86,"roomWall2",true);
			setup(87,"roomDown",true);
			setup(88,"roomWallSL",true);
			setup(89,"roomWallSR",true);
			setup(90,"pianoTL",true);
			setup(91,"pianoTR",true);
			setup(92,"pianoDL",true);
			setup(93,"pianoDR",true);
			setup(94,"flowerT",true);
			setup(95,"flowerD",true);
			setup(96,"bluStone",false);
			setup(97,"silverStone",false);
			setup(98,"greenStoneT",true);
			setup(99,"greenStoneD",true);
			setup(100,"roomWallBL",true);
			setup(101,"roomWallBR",true);
			setup(102,"eagleTL",true);
			setup(103,"eagleTR",true);
			setup(104,"eagleDL",true);
			setup(105,"eagleDR",true);
			setup(106,"roomStone",false);
			setup(107,"roomBrokenStone",false);
			setup(108,"roomWhiteStone",true);
			setup(109,"roomBrownStone",false);
			setup(110,"roomStatueT",true);
			setup(111,"roomStatueD",true);
			setup(112,"angelTL",true);
			setup(113,"angelTR",true);
			setup(114,"angelDL",true);
			setup(115,"angelDR",true);
			//dungeon
			setup(116,"greenFloor",false);
			setup(117,"poolT",true);
			setup(118,"poolD",true);
			setup(119,"cross",false);
			setup(120,"dungeonWallTL",true);
			setup(121,"dungeonWallT",true);
			setup(122,"dungeonWallT2",true);
			setup(123,"dungeonWallTR",true);
			setup(124,"dungeonWallL",true);
			setup(125,"dungeonWallL2",true);
			setup(126,"dungeonWall",true);
			setup(127,"dungeonWall2",true);
			setup(128,"dungeonWallR",true);
			setup(129,"dungeonWallR2",true);
			setup(130,"dungeonWallDL",true);
			setup(131,"dungeonWallDR",true);
			setup(132,"dungeonWallD",true);
			setup(133,"dungeonWallBL",true);
			setup(134,"dungeonWallBR",true);
			setup(135,"dungeonStare",false);
			setup(136,"dungeonRock",true);
			setup(137,"fireMonster",true);
			setup(138,"dungeonAngleL1",true);
			setup(139,"dungeonAngleL2",true);
			setup(140,"dungeonAngleR1",true);
			setup(141,"dungeonAngleR2",true);
			setup(142,"bossDoorTL",true);
			setup(143,"bossDoorTR",true);
			setup(144,"bossDoorDL",true);
			setup(145,"bossDoorDR",true);
			setup(146,"dungeonCarpet",false);
			setup(147,"dungeonStatueTop",true);
			setup(148,"dungeonStatue",true);
			setup(149,"dungeonStatueD",true);
			setup(150,"chainT",false);
			setup(151,"chainM",false);
			setup(152,"chainB",false);
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
		int currentMaxCol = mapTileNum[gp.currentMap].length;
	    int currentMaxRow = mapTileNum[gp.currentMap][0].length;
		while(worldCol < currentMaxCol && worldRow < currentMaxRow) {
			int tileNum = mapTileNum [gp.currentMap][worldCol][worldRow];
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
			if(worldCol == currentMaxCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
}
