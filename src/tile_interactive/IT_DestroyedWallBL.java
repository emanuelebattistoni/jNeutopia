package tile_interactive;

import main.GamePanel;

public class IT_DestroyedWallBL extends InteractiveTile{
	GamePanel gp;
	public IT_DestroyedWallBL(GamePanel gp, int col, int row) {
		super(gp);
		this.gp=gp;
		down1=setup("/tiles_interactive/destroyedWallBL",gp.tileSize, gp.tileSize);
		this.worldX = gp.tileSize*col;
		this.worldY = gp.tileSize*row;
		solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 48;
        solidArea.height = 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    
	}
}
