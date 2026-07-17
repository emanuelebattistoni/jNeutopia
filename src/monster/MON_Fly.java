package monster;

import main.GamePanel;
import object.OBJ_Bomb;
import object.OBJ_Cherry;
import object.OBJ_Coin;
import object.OBJ_GoldCoin;
import object.OBJ_Wing;

import java.util.Random;

import entity.Entity;

public class MON_Fly extends Entity {
    
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
    
    public MON_Fly(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = 2;
        name = "fly"; 
        defaultSpeed = 2; 
        speed = defaultSpeed;
        attack=1;
        maxLife = 3;
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
    	up1 = setup("/monster/fly1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/fly2", gp.tileSize, gp.tileSize);
        down1 = up1;
        down2 = up2;
        left1 = up1;
        left2 = up2;
        right1 = up1;
        right2 = up2;
    }
    

    
    public void setAction() {
        SaveStartPosition();
        if (startSaved == true) {
            checkWanderDistance(maxDistance);   
            if (returningHome == true) {
                moveTowards(startWorldX, startWorldY);
            } else {
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
        }
    }
    	
    
    
    public void damageReaction() {
        actionLockCounter = 0;
        step = 0;
        currentPattern = new Random().nextInt(semiCircles.length);
    }
    
    public void checkDrop() {
        int i = new Random().nextInt(100) + 1;
        if(i < 5) {
            dropItem(new OBJ_GoldCoin(gp));
        }
        if(i>=25 && i <45) {
        	dropItem(new OBJ_Coin(gp));
        }
        if(i >=45 && i<50) {
            dropItem(new OBJ_Cherry(gp));
        }
        if(i >=55 && i<60) {
            dropItem(new OBJ_Bomb(gp));
        }
        if(i >=65 && i<75) {
            dropItem(new OBJ_Wing(gp));
        }
    }
}