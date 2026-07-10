package object;
import entity.Entity;
import main.GamePanel;
public class OBJ_Heart extends Entity{
	public static final String objName = "Heart";

	public OBJ_Heart(GamePanel gp) {
		super(gp);
		name=objName;
		image = setup("/objects/HeartFull", gp.tileSize, gp.tileSize);
		image2 = setup("/objects/HeartHalf", gp.tileSize, gp.tileSize);
		image3 = setup("/objects/Heart", gp.tileSize, gp.tileSize); 
	}
}