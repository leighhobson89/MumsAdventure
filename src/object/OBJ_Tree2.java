package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Tree2 extends Entity {

    public OBJ_Tree2(GamePanel gp) {

        super(gp);

        name = "Tree2";
        down1 = setup("/objects/tree2");
        direction = "down";

        collision = true;
    }
}
