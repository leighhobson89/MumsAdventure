package object;

import entity.Entity;
import main.GamePanel;
import main.MissionStates;

public class OBJ_Bookhut1_Left extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "Bookhut1_Left";

    public OBJ_Bookhut1_Left(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "Bookhut1_Left";
        image = setup("/objects/bookhut1_left", gp.tileSize, gp.tileSize);
        down1 = image;
        direction = "down";
        collision = true;
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
