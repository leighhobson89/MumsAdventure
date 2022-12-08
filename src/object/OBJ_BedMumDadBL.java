package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BedMumDadBL extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "BedMumDadBL";

    public OBJ_BedMumDadBL(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "BedMumDadBL";
        image = setup("/objects/BLbedMumDadBedroom", gp.tileSize, gp.tileSize);
        down1 = image;
        direction = "down";

        collision = false;
    }
}
