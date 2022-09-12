package object;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

public class OBJ_Heart extends Entity {

    public OBJ_Heart(GamePanel gp) {
        super(gp);
        UtilityTool uTool = new UtilityTool();

        name = "Heart";
        direction = "down";
        image = setup("/lifeBar/stress_full", gp.tileSize, gp.tileSize);
        image2 = setup("/lifeBar/stress_half", gp.tileSize, gp.tileSize);
        image3 = setup("/lifeBar/stress_none", gp.tileSize, gp.tileSize);
        image = uTool.scaleImage(image, (int) (gp.tileSize*0.7), gp.tileSize);
        image2 = uTool.scaleImage(image2, (int) (gp.tileSize*0.7), gp.tileSize);
        image3 = uTool.scaleImage(image3, (int) (gp.tileSize*0.7), gp.tileSize);
    }
}
