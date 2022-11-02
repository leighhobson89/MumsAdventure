package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Cupboard2 extends Entity {

    GamePanel gp;

    public OBJ_Cupboard2(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "Cupboard2";
        image = setup("/objects/cupboard2", gp.tileSize, gp.tileSize);
        down1 = image;
        direction = "down";
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }

    public void interact() {
        gp.gameState = gp.dialogueState;
        if (!opened) {
            gp.playSFX(4);
            gp.ui.currentDialogue = "This cardigan is nice and warm\nI should put this on!";
            opened = true;
            gp.eHandler.cupboardHall();
        } else {
            gp.ui.currentDialogue = "The cupboard is empty!";
        }
        gp.keyH.enterPressed = false;
    }
}
