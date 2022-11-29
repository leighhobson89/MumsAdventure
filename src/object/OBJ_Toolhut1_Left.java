package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Toolhut1_Left extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "Toolhut1_Left";

    public OBJ_Toolhut1_Left(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "Toolhut1_Left";
        image = setup("/objects/toolhut1_left", gp.tileSize, gp.tileSize);
        down1 = image;
        direction = "down";
        collision = true;
        collisionType = 0;
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
