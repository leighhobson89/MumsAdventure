package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BackGateSideways extends Entity {

    public OBJ_BackGateSideways(GamePanel gp) {

        super(gp);

        name = "BackGateSideways";
        down1 = setup("/objects/backGateSideways");
        direction = "down";
        //collision = true;

    }
}
