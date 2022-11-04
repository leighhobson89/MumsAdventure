package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_TelephoneHall extends Entity {

    public static final String OBJ_NAME = "TelephoneHall";

    public OBJ_TelephoneHall(GamePanel gp) {

        super(gp);

        type = type_obstacle;
        collision = true;
        name = OBJ_NAME;
        displayName = "TelephoneHall";
        down1 = setup("/objects/telephoneHall", gp.tileSize, gp.tileSize);
        direction = "down";

    }
}
