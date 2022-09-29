package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_InsideDoorSideways extends Entity {

    GamePanel gp;
    boolean opened = false;

    public OBJ_InsideDoorSideways(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = type_obstacle;

        name = "InsideDoorSideways";
        direction = "down";
        image = setup("/objects/insideDoorSideways", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/insideDoorOpenSideways", gp.tileSize, gp.tileSize);
        down1 = image;

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

            down1 = image2;
            collision = false;
            opened = true;
            gp.ui.currentDialogue = "Door opened.";
        }
        else {
            gp.ui.currentDialogue = "The door's already open!";
        }
        gp.keyH.enterPressed = false;
    }
}
