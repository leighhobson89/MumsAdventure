package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_StairsHall extends Entity {

    public OBJ_StairsHall(GamePanel gp) {

        super(gp);

        name = "StairsHall";
        down1 = setup("/objects/stairsHall");
        direction = "down";

    }
}
