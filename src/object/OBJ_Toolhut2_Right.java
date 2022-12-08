package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Toolhut2_Right extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "Toolhut2_Right";

    public OBJ_Toolhut2_Right(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_hut;
        name = OBJ_NAME;
        displayName = "Toolhut2_Right";
        image = setup("/objects/toolhut2_right", gp.tileSize, gp.tileSize);
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
