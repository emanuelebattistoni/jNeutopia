package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Cherry;
import object.OBJ_Coin;
import java.util.Random;

public class MON_RedBat extends Entity {
    
    GamePanel gp;

    String[][] semiCircles = {
        {"up", "upRight", "right", "downRight", "down"},  
        {"down", "downRight", "right", "upRight", "up"},  
        {"up", "upLeft", "left", "downLeft", "down"}, 
        {"down", "downLeft", "left", "upLeft", "up"},
        {"left", "upLeft", "up", "upRight", "right"},        
        {"right", "upRight", "up", "upLeft", "left"},          
        {"left", "downLeft", "down", "downRight", "right"},    
        {"right", "downRight", "down", "downLeft", "left"}      
    };
    
    int currentPattern = 0; 
    int step = 0;        
    
    public MON_RedBat(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = 2;
        name = "fly"; 
        defaultSpeed = 2; 
        speed = defaultSpeed;
        maxLife = 2;
        life = maxLife;
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        currentPattern = new Random().nextInt(semiCircles.length);
        direction = semiCircles[currentPattern][0]; 
        
        getImage();
        loadDeathImage();
    }

    public void getImage() {
    	up1 = setup("/monster/redBat", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/redBat2", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/redBat", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/redBat2", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/redBat", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/redBat2", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/redBat", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/redBat2", gp.tileSize, gp.tileSize);
    }
    
    public void loadDeathImage() {
        death1 = setup("/monster/dMonster1", gp.tileSize, gp.tileSize);
        death2 = setup("/monster/dMonster2", gp.tileSize, gp.tileSize);
        death3 = setup("/monster/dMonster3", gp.tileSize, gp.tileSize);
        death4 = setup("/monster/dMonster4", gp.tileSize, gp.tileSize);
    }
    
    public void setAction() {
        actionLockCounter++;
        
        if(actionLockCounter == 15) {
            
            direction = semiCircles[currentPattern][step];
            
            step++;
            
            if(step == semiCircles[currentPattern].length) {
                step = 0;

                Random random = new Random();
                currentPattern = random.nextInt(semiCircles.length);
            }
            
            actionLockCounter = 0;
        }
    }
    
    public void damageReaction() {
        actionLockCounter = 0;
        step = 0;
        currentPattern = new Random().nextInt(semiCircles.length);
    }
    
    public void checkDrop() {
        int i = new Random().nextInt(100) + 1;
        if(i < 25) {
            dropItem(new OBJ_Coin(gp));
        }
        if(i >= 75) {
            dropItem(new OBJ_Cherry(gp));
        }
    }
}