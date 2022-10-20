package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BedMumDadTL extends Entity {

    GamePanel gp;

    public OBJ_BedMumDadTL(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "BedMumDadTL";
        image = setup("/objects/TLbedMumDadBedroom", gp.tileSize, gp.tileSize);
        down1 = image;
        direction = "down";

        collision = false;
    }
}
