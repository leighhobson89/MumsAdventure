package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BackGateOpenSideways extends Entity {

    public OBJ_BackGateOpenSideways(GamePanel gp) {

        super(gp);

        name = "BackGateOpenSideways";
        down1 = setup("/objects/backGateOpenSideways", gp.tileSize, gp.tileSize);
        direction = "down";
        //collision = true;

    }
}
