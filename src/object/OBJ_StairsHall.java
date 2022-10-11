package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_StairsHall extends Entity {

    public OBJ_StairsHall(GamePanel gp) {

        super(gp);

        type = type_obstacle;
        name = "StairsHall";
        down1 = setup("/objects/stairsHall", gp.tileSize, gp.tileSize);
        direction = "down";

    }
}
