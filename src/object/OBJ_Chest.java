package object;
import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity{
	GamePanel gp;
	Entity loot;
	boolean opened = false;
	public OBJ_Chest(GamePanel gp, Entity loot) {
		super(gp);
		this.gp= gp;
		this.loot=loot;
		name = "Chest";
		image=setup("/objects/chestClosed", gp.tileSize, gp.tileSize);
		image2= setup("/objects/chestOpened", gp.tileSize, gp.tileSize);
		down1=image;
		type=type_obstacle;
		collision=true;
		solidArea.x=4;
		solidArea.y=16;
		solidArea.height=32;
		solidArea.width=48;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY= solidArea.y;
	}
	public void interact() {
		gp.gameState=gp.dialogueState;
		if(opened==false) {
			StringBuilder sb = new StringBuilder();
			if(gp.player.inventory.size() == gp.player.maxInventorySize) {
				sb.append("You cannot carry anymore!");
			}
			else {
				sb.append("Our hero Jazeta has obtained " + loot.name + "!");
				if(loot.type != type_pickupOnly) {
					gp.player.inventory.add(loot);
				}
				if (loot.name.equals("Bomb")) {
		            gp.player.bomb+=4;
		        }
				else {
					loot.use(gp.player);
				}
				down1=image2;
				gp.player.direction="down";
				opened=true;
				gp.player.drawingGetItem = true;
				gp.player.itemObtained = loot;
			}
			gp.ui.currentDialogue = sb.toString();
		}
		else {
			gp.ui.currentDialogue = "It's empty";
		}
	}
}
