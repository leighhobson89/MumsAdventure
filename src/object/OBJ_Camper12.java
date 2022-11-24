package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Camper12 extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "Camper12";

    public OBJ_Camper12(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "Camper12";
        image = setup("/objects/camper12", gp.tileSize, gp.tileSize);
        down1 = image;
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
        dialogueText[0][0] = "I wish he'd get this thing fixed!\nWe've been retired a few years now, we're never gonna\ngo nowhere, another bloody waste of money sat rotting\nthere!";
    }

    public void interact() {
        startDialogue(this, 0);
        gp.keyH.enterPressed = false;
    }
}
