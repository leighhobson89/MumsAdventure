package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_WaspNest extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "WaspNest";

    public OBJ_WaspNest(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "WaspNest";
        image = setup("/objects/waspNest", gp.tileSize, gp.tileSize);
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
        dialogueText[0][0] = "Looks quiet at the moment but I need to\n get rid of it sharpish!";
        dialogueText[1][0] = "Aaaaghhhhh!";
        dialogueText[2][0] = "There it's burning!";
        dialogueText[2][1] = "Oooohh Jeeze...Bloody house is catching\nfire! Quick where's the bloody water??\nOh Goodddd!";
    }

    public void interact() {
        startDialogue(this, 0);
        gp.keyH.enterPressed = false;
    }
}
