package object;
import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity {
	GamePanel gp;
	public static final String objName = "Chest";

	public OBJ_Chest(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = objName;
		image = setup("/objects/chestClosed", gp.tileSize, gp.tileSize);
		image2 = setup("/objects/chestOpened", gp.tileSize, gp.tileSize);
		down1 = image;
		type = type_obstacle;
		collision = true;
		solidArea.x = 4;
		solidArea.y = 16;
		solidArea.height = 32;
		solidArea.width = 48;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
	
	public void setLoot(Entity loot) {
		this.loot = loot;
		setDialogue();
	}
	
	public void setDialogue() {
		dialogues[0][0] = "Non puoi portare più oggetti!";
		dialogues[1][0] = "Il nostro eroe Jazeta ha ottenuto:\n" + loot.name + "!";
		dialogues[2][0] = "La cassa è vuota.";
	}
	
	public void interact() {
		if (opened == false) {
			if (loot.type == type_pickupOnly) {
				startDialogue(this, 1);			
				down1 = image2;
				gp.player.direction = "down";
				opened = true;
				gp.player.drawingGetItem = true;
				gp.player.itemObtained = loot;
				loot.use(gp.player);
			}
			else {
				if (gp.player.canObtainItem(loot) == false) {
					startDialogue(this, 0);}
				else {
					startDialogue(this, 1);			
					down1 = image2;
					gp.player.direction = "down";
					opened = true;
					gp.player.drawingGetItem = true;
					gp.player.itemObtained = loot;
				}
			}
		}
		else {
			startDialogue(this, 2);	
		}
		}
	}