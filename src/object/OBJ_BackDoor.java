package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BackDoor extends Entity {

    GamePanel gp;

    public static final String OBJ_NAME = "BackDoor";

    public OBJ_BackDoor(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "BackDoor";
        down1 = setup("/objects/backDoor", gp.tileSize, gp.tileSize);
        direction = "down";
        collision = true;

        solidArea.x = 33;
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
