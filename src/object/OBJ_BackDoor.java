package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BackDoor extends Entity {

    GamePanel gp;

    public OBJ_BackDoor(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "BackDoor";
        down1 = setup("/objects/backDoor", gp.tileSize, gp.tileSize);
        direction = "down";
        collision = true;

        solidArea.x = 33;
        solidArea.y = 0;
        solidArea.width = 15;
        solidArea.height = 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }

    public void interact() {
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You need a key to open the door";
        gp.keyH.enterPressed = false;
    }
}
