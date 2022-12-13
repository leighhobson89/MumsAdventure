package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Music_Center extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "Music_Center";

    public OBJ_Music_Center(GamePanel gp) {

        super(gp);
        this.gp = gp;

        npcCanWalkOnWhenFollowing = true;
        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "Music_Center";
        down1 = setup("/objects/cupboard2HorLounge", gp.tileSize, gp.tileSize);
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
        dialogueText[0][0] = "Hey, don't touch my bloody music center!";
    }

    public void interact() {
        startDialogue(this, 0);
        gp.keyH.enterPressed = false;
    }
}
