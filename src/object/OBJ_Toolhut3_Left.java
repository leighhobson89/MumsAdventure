package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Toolhut3_Left extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "Toolhut3_Left";

    public OBJ_Toolhut3_Left(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_hut;
        name = OBJ_NAME;
        displayName = "Toolhut3_Left";
        image = setup("/objects/toolhut3_left", gp.tileSize, gp.tileSize);
        down1 = image;
        direction = "down";
        collision = true;
        collisionType = 0;
        goesTransparentWhenHit = false;
        goesTransparentWhenStoodOnToolHut = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
}
