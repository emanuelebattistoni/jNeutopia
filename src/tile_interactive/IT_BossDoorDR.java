package tile_interactive;

import main.GamePanel;

public class IT_BossDoorDR extends InteractiveTile{
	GamePanel gp;
	public IT_BossDoorDR(GamePanel gp, int col, int row) {
		super(gp);
		this.gp= gp;
		this.name = "BossDoor";
		down1=setup("/tiles_interactive/bossDoorDR",gp.tileSize, gp.tileSize);
		this.worldX = gp.tileSize*col;
		this.worldY = gp.tileSize*row;
		solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 48;
        solidArea.height = 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
	}
	public boolean isCorrectItem() {
		for (int i = 0; i < gp.player.inventory.size(); i++) {
			if ( gp.player.inventory.get(i).name.equals("Key")) {
				return true;
			}
    }
    return false;
}

	public InteractiveTile getDestroyedForm() {
		InteractiveTile tile =new IT_DestroyedWallL(gp, worldX/gp.tileSize, worldY/gp.tileSize);
		return tile;
	}
}
