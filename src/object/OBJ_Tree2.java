package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Tree2 extends Entity {

    public OBJ_Tree2(GamePanel gp) {

        super(gp);

        name = "Tree2";
        down1 = setup("/objects/tree2", gp.tileSize, gp.tileSize);
        direction = "down";

        collision = true;
    }
}
