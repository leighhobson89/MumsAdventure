package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BedMumDadBL extends Entity {

    GamePanel gp;

    public OBJ_BedMumDadBL(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "BedMumDadBL";
        image = setup("/objects/BLbedMumDadBedroom", gp.tileSize, gp.tileSize);
        down1 = image;
        direction = "down";

        collision = false;
    }
}
