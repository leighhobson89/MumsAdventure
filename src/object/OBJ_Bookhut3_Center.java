package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bookhut3_Center extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "Bookhut3_Center";

    public OBJ_Bookhut3_Center(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_hut;
        name = OBJ_NAME;
        displayName = "Bookhut3_Center";
        image = setup("/objects/bookhut3_center", gp.tileSize, gp.tileSize);
        down1 = image;
        direction = "down";
        collision = false;
        goesTransparentWhenHit = false;
        goesTransparentWhenStoodOnBookHut = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
}
