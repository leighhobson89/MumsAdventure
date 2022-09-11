package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Tree1 extends Entity {

    public OBJ_Tree1(GamePanel gp) {

        super(gp);

        name = "Tree1";
        down1 = setup("/objects/tree1");
        direction = "down";

        collision = true;
    }
}
