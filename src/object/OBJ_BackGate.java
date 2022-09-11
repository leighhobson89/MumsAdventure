package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BackGate extends Entity {

    public OBJ_BackGate(GamePanel gp) {

        super(gp);

        name = "BackGate";
        direction = "down";
        down1 = setup("/objects/backGate");

        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
