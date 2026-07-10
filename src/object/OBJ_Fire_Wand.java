package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Fire_Wand extends Entity {
    GamePanel gp;
	public static final String objName = "Staffa del Fuoco";

    public OBJ_Fire_Wand(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = objName;
        type = type_reusable; 
        price = 50;
        down1 = setup("/objects/fire_wand", gp.tileSize, gp.tileSize); 
    }

    @Override
    public void use(Entity entity) {

        if (gp.player.shotAvaibleCounter >= 30) {

            OBJ_Fireball fireball = new OBJ_Fireball(gp);

            fireball.set(entity.worldX, entity.worldY, entity.direction, true, entity);
            
            gp.projectileList.add(fireball); 
            
            gp.player.shotAvaibleCounter = 0;
            
        }
    }
}