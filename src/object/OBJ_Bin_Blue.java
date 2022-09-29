package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bin_Blue extends Entity {

    public OBJ_Bin_Blue(GamePanel gp) {

        super(gp);

        name = "Bin_Blue";
        down1 = setup("/objects/bin_Blue", gp.tileSize, gp.tileSize);
        direction = "down";

    }
}
