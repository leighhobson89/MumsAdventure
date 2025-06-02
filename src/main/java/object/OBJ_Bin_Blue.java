package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bin_Blue extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "Bin_Blue";

    public OBJ_Bin_Blue(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "Bin_Blue";
        image = setup("/objects/bin_Blue", gp.tileSize, gp.tileSize);
        down1 = image;
        direction = "down";
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
}
