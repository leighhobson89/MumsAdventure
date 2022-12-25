package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_TreeTopGarden_5 extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "TreeTopGarden_5";

    public OBJ_TreeTopGarden_5(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "TreeTopGarden_5";
        image = setup("/objects/treeTopGarden5", gp.tileSize, gp.tileSize);
        down1 = image;
        direction = "down";
        goesTransparentWhenHit = false;
        drawAbovePlayer = true;
    }
}
