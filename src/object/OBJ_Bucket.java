package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bucket extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "Bucket";

    public OBJ_Bucket(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_bucket;
        name = OBJ_NAME;
        displayName = "An Empty Bucket";
        description = "[" + name + "]\nAn Empty Bucket!";
        image = setup("/objects/bucketEmpty", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/bucketWater", gp.tileSize, gp.tileSize);
        down1 = image;
        direction = "down";
        collision = false;
        goesTransparentWhenHit = false;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
