package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_InsideDoor extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "InsideDoor";

    public OBJ_InsideDoor(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "InsideDoor";
        direction = "down";
        image = setup("/objects/insideDoor", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/insideDoorOpen", gp.tileSize, gp.tileSize);
        down1 = image;

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
        dialogueText[0][0] = "Door opened.";
        dialogueText[1][0] = "The door's already open!";
    }

    public void interact() {
        if (!opened) {
            gp.playSFX(4);
            down1 = image2;
            collision = false;
            opened = true;
            startDialogue(this, 0);
        }
        else {
            startDialogue(this, 1);
        }
        gp.keyH.enterPressed = false;
    }
}
