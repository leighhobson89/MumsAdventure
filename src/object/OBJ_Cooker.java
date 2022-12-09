package object;

import entity.Entity;
import main.GamePanel;
import main.MissionStates;

public class OBJ_Cooker extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "Cooker";

    public OBJ_Cooker(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "Cooker";
        image = setup("/objects/cooker", gp.tileSize, gp.tileSize);
        down1 = image;
        direction = "down";
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDialogue();
    }

    public void setDialogue() {
        dialogueText[0][0] = "";
    }

    public void interact() {

    }
}
