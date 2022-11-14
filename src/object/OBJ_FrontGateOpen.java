package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_FrontGateOpen extends Entity {

    public static final String OBJ_NAME = "FrontGateOpen";

    public OBJ_FrontGateOpen(GamePanel gp) {

        super(gp);

        isUpdateable = false;
        name = OBJ_NAME;
        displayName = "FrontGateOpen";
        down1 = setup("/objects/frontGateOpen", gp.tileSize, gp.tileSize);
        direction = "down";

    }
}
