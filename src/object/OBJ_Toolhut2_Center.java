package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Toolhut2_Center extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "Toolhut2_Center";

    public OBJ_Toolhut2_Center(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_hut;
        name = OBJ_NAME;
        displayName = "Toolhut2_Center";
        image = setup("/objects/toolhut2_center", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/toolhut2_center_open", gp.tileSize, gp.tileSize);
        down1 = image;
        direction = "down";
        collision = false;
        goesTransparentWhenHit = false;
        goesTransparentWhenStoodOnToolHut = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
}
