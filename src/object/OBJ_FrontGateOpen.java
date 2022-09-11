package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_FrontGateOpen extends Entity {

    public OBJ_FrontGateOpen(GamePanel gp) {

        super(gp);

        name = "FrontGateOpen";
        down1 = setup("/objects/frontGateOpen");
        direction = "down";

    }
}
