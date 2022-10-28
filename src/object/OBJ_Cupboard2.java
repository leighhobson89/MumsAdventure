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
        down1 = setup("/objects/cupboard2", gp.tileSize, gp.tileSize);
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
        gp.ui.currentDialogue = "This cardigan is nice and warm\nI should put this on!";
        if (!opened) {
            gp.playSFX(4);
            opened = true;
            gp.eHandler.cupboardHall();
        } else {
            gp.gameState = gp.playState;
        }
        gp.keyH.enterPressed = false;
    }
}
