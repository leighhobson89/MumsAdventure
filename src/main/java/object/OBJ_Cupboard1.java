package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Cupboard1 extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "Cupboard1";

    public OBJ_Cupboard1(GamePanel gp) {

        super(gp);
        this.gp = gp;

        npcCanWalkOnWhenFollowing = true;
        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "Cupboard1";
        down1 = setup("/objects/cupboard1", gp.tileSize, gp.tileSize);
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
        dialogueText[0][0] = "Ahh, my Lavender Crocs!";
        dialogueText[1][0] = "The cupboard is empty!";
    }

    public void interact() {
        if (!opened) {
            gp.playSFX(4);
            startDialogue(this, 0);
            opened = true;
            gp.eHandler.cupboardLounge();
        } else {
            startDialogue(this, 1);
        }
        gp.keyH.enterPressed = false;
    }
}
