package tile_interactive;

import main.GamePanel;

public class IT_DestroyedWallR extends InteractiveTile{
	GamePanel gp;
	public IT_DestroyedWallR(GamePanel gp, int col, int row) {
		super(gp);
		this.gp=gp;
		down1=setup("/tiles_interactive/greenFloor",gp.tileSize, gp.tileSize);
		this.worldX = gp.tileSize*col;
		this.worldY = gp.tileSize*row;
		solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
	}
}
