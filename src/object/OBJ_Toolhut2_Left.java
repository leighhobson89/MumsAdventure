package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Toolhut2_Left extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "Toolhut2_Left";

    public OBJ_Toolhut2_Left(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "Toolhut2_Left";
        image = setup("/objects/toolhut2_left", gp.tileSize, gp.tileSize);
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
