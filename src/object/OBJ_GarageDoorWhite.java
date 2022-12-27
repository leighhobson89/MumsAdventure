package object;

import entity.Entity;
import main.GamePanel;
import main.MissionStates;

public class OBJ_GarageDoorWhite extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "GarageDoorWhite";

    public OBJ_GarageDoorWhite(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = true;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "GarageDoorWhite";
        direction = "down";
        image = setup("/objects/backGateSideways", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/backGateOpenSideways", gp.tileSize, gp.tileSize);
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
        dialogueText[0][0] = "You need a key to open the door";
        dialogueText[1][0] = "I've shut that garage door behind you\nseeing as though you left it wide open!";
    }
    public void interact() {
        startDialogue(this, 0);
        gp.keyH.enterPressed = false;
    }

    public void update() {
        if (opened && (worldX/gp.tileSize) - (gp.player.worldX/gp.tileSize) > 15) {
            opened = false;
            down1 = image;
            collision = true;
            startDialogue(this, 1);
        }
    }
}
