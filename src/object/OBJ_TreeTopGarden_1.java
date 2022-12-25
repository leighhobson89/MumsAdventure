package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_TreeTopGarden_1 extends Entity {

    final GamePanel gp;
    final static int IMAGE_SCALE_FACTOR = 2;
    public static final String OBJ_NAME = "TreeTopGarden_1";

    public OBJ_TreeTopGarden_1(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "TreeTopGarden_1";
        image = setup("/objects/treeTopGarden1", gp.tileSize*IMAGE_SCALE_FACTOR, gp.tileSize*IMAGE_SCALE_FACTOR);
        down1 = image;
        direction = "down";
        goesTransparentWhenHit = false;
        drawAbovePlayer = true;
        isScaledUpObject = true;
    }
}
