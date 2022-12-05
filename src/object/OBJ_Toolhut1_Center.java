package object;

import entity.Entity;
import main.GamePanel;
import main.MissionStates;

public class OBJ_Toolhut1_Center extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "Toolhut1_Center";

    public OBJ_Toolhut1_Center(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = true;
        type = type_hut;
        name = OBJ_NAME;
        displayName = "Toolhut1_Center";
        image = setup("/objects/toolhut1_center", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/toolhut1_center_open", gp.tileSize, gp.tileSize);
        down1 = image;
        direction = "down";
        collision = true;
        goesTransparentWhenHit = false;
        goesTransparentWhenStoodOnToolHut = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDialogue();
    }

    public void setDialogue() {

    }

    public void interact() {

    }
}
