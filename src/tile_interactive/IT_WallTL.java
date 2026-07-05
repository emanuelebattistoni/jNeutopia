package tile_interactive;

import main.GamePanel;

public class IT_WallTL extends InteractiveTile{
	GamePanel gp;
	public IT_WallTL(GamePanel gp, int col, int row) {
		super(gp);
		this.gp=gp;
		down1=setup("/tiles_interactive/dungeonWallR2",gp.tileSize, gp.tileSize);
		destructible=true;
		this.worldX = gp.tileSize*col;
		this.worldY = gp.tileSize*row;
		solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 48;
        solidArea.height = 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        collision=true;

	}
	public InteractiveTile getDestroyedForm() {
		InteractiveTile tile =new IT_DestroyedWallTL(gp, worldX/gp.tileSize, worldY/gp.tileSize);
		return tile;
	}
	
}
