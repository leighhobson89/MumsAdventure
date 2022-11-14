package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_FrontGate extends Entity {

    public static final String OBJ_NAME = "FrontGate";

    public OBJ_FrontGate(GamePanel gp) {

        super(gp);

        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "FrontGate";
        down1 = setup("/objects/frontGate", gp.tileSize, gp.tileSize);
        direction = "down";
        collision = true;

    }
}
