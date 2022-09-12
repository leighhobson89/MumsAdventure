package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Spider extends Entity {

    public OBJ_Spider(GamePanel gp) {

        super(gp);

        name = "Spider";
        down1 = setup("/objects/spider", gp.tileSize, gp.tileSize);
        direction = "down";

    }
}
