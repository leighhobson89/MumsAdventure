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
        image = setup("/lifebar/stress_full");
        image2 = setup("/lifebar/stress_half");
        image3 = setup("/lifebar/stress_none");
        image = uTool.scaleImage(image, (int) (gp.tileSize*0.7), gp.tileSize);
        image2 = uTool.scaleImage(image2, (int) (gp.tileSize*0.7), gp.tileSize);
        image3 = uTool.scaleImage(image3, (int) (gp.tileSize*0.7), gp.tileSize);
    }
}
