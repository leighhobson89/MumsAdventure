package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_TelephoneHall extends Entity {

    public OBJ_TelephoneHall(GamePanel gp) {

        super(gp);

        name = "TelephoneHall";
        down1 = setup("/objects/telephoneHall", gp.tileSize, gp.tileSize);
        direction = "down";

        collectable = false;
        isOpenable = false;

    }
}
