package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_FrontGate extends Entity {

    public OBJ_FrontGate(GamePanel gp) {

        super(gp);

        type = type_obstacle;
        name = "FrontGate";
        displayName = "FrontGate";
        down1 = setup("/objects/frontGate", gp.tileSize, gp.tileSize);
        direction = "down";
        collision = true;

    }
}
