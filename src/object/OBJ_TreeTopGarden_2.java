package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_TreeTopGarden_2 extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "TreeTopGarden_2";

    public OBJ_TreeTopGarden_2(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "TreeTopGarden_2";
        image = setup("/objects/treeTopGarden2", gp.tileSize, gp.tileSize);
        down1 = image;
        direction = "down";
        goesTransparentWhenHit = false;
        drawAbovePlayer = true;
    }
}
