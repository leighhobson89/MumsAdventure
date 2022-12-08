package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bookhut2_Right extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "Bookhut2_Right";

    public OBJ_Bookhut2_Right(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_hut;
        name = OBJ_NAME;
        displayName = "Bookhut2_Right";
        image = setup("/objects/bookhut2_right", gp.tileSize, gp.tileSize);
        down1 = image;
        direction = "down";
        collision = true;
        collisionType = 0;
        goesTransparentWhenHit = false;
        goesTransparentWhenStoodOnBookHut = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
}
