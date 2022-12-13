package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_OutdoorGateVertical extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "OutdoorGateVerticalClosed";

    public OBJ_OutdoorGateVertical(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "OutdoorGateVerticalClosed";
        direction = "down";
        image = setup("/objects/outdoorGateVerticalClosed", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/outdoorGateVerticalOpened", gp.tileSize, gp.tileSize);
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
        dialogueText[0][0] = "The gates's already open!";
    }
    public void interact() {
        if (!opened) {
            gp.playSFX(4);

            down1 = image2;
            collision = false;
            opened = true;
        }
        else {
            startDialogue(this, 0);
        }
        gp.keyH.enterPressed = false;
    }
}
