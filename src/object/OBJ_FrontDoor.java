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
        displayName = "FrontDoor";
        down1 = setup("/objects/frontDoor", gp.tileSize, gp.tileSize);
        direction = "down";
        collision = true;

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 15;
        solidArea.height = 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDialogue();

    }

    public void setDialogue() {
        dialogueText[0][0] = "You need a key to open the door";
    }

    public void interact() {
        startDialogue(this, 0);
        gp.keyH.enterPressed = false;
    }
}
