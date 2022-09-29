package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_FrontDoor extends Entity {

    GamePanel gp;

    public OBJ_FrontDoor(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "FrontDoor";
        down1 = setup("/objects/frontDoor", gp.tileSize, gp.tileSize);
        direction = "down";
        collision = true;

    }

    public void interact() {
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You need a key to open the door";
        gp.keyH.enterPressed = false;
    }
}
