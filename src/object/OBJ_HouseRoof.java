package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_HouseRoof extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "HouseRoof";

    public OBJ_HouseRoof(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = true;
        type = type_hut;
        name = OBJ_NAME;
        displayName = "HouseRoof";
        image = setup("/objects/roofHouse", gp.tileSize * 15, gp.tileSize * 17);
        down1 = image;
        direction = "down";
        collision = true;
        collisionType = 0;
        goesTransparentWhenHit = false;
        isScaledUpObject = true;
        transparent = true;
        goesTransparentWhenInHouse = true;
    }
}
