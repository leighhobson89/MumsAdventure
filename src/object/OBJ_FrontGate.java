package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_FrontGate extends Entity {

    public OBJ_FrontGate(GamePanel gp) {

        super(gp);

        name = "FrontGate";
        down1 = setup("/objects/frontGate");
        direction = "down";

        collision = true;

    }
}
