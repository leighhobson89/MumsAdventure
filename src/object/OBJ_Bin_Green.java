package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bin_Green extends Entity {

    public OBJ_Bin_Green(GamePanel gp) {

        super(gp);

        name = "Bin_Green";
        down1 = setup("/objects/bin_Green");
        direction = "down";

    }
}
