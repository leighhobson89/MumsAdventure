package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Tutorial_Arrow_Right extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "Tutorial_Arrow_Right";

    public OBJ_Tutorial_Arrow_Right(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        name = OBJ_NAME;
        displayName = "Tutorial_Arrow_Right";
        image = setup("/objects/tutorial_arrowRight", gp.tileSize, gp.tileSize);
        down1 = image;
        direction = "down";
    }
}
