package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bin_Blue extends Entity {

    public static final String OBJ_NAME = "Bin_Blue";

    public OBJ_Bin_Blue(GamePanel gp) {

        super(gp);

        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "Bin_Blue";
        down1 = setup("/objects/bin_Blue", gp.tileSize, gp.tileSize);
        direction = "down";

    }
}
