package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_TreeTopGarden_3 extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "TreeTopGarden_3";

    public OBJ_TreeTopGarden_3(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "TreeTopGarden_3";
        image = setup("/objects/treeTopGarden3", gp.tileSize, gp.tileSize);
        down1 = image;
        direction = "down";
        goesTransparentWhenHit = false;
        drawAbovePlayer = true;
    }
}
