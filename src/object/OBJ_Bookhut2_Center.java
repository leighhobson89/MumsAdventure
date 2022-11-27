package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bookhut2_Center extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "Bookhut2_Center";

    public OBJ_Bookhut2_Center(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "Bookhut2_Center";
        image = setup("/objects/bookhut2_center", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/bookhut2_center_open", gp.tileSize, gp.tileSize);
        down1 = image;
        direction = "down";
        collision = false;
        goesTransparentWhenHit = false;
        goesTransparentWhenStoodOn = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
}
