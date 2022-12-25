package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_TreeTopGarden_6 extends Entity {

    final GamePanel gp;
    final static int IMAGE_SCALE_FACTOR = 2;
    public static final String OBJ_NAME = "TreeTopGarden_6";

    public OBJ_TreeTopGarden_6(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "TreeTopGarden_6";
        image = setup("/objects/treeTopGarden6", gp.tileSize*IMAGE_SCALE_FACTOR, gp.tileSize*IMAGE_SCALE_FACTOR);
        down1 = image;
        collision = true;
        direction = "down";
        goesTransparentWhenHit = false;
        drawAbovePlayer = true;
        isScaledUpObject = true;

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = gp.tileSize*IMAGE_SCALE_FACTOR;
        solidArea.height = gp.tileSize*IMAGE_SCALE_FACTOR;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
