package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_KitchenPantry extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "KitchenPantry";

    public OBJ_KitchenPantry(GamePanel gp) {

        super(gp);
        this.gp = gp;

        npcCanWalkOnWhenFollowing = true;
        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "KitchenPantry";
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
        dialogueText[0][0] = "The cupboard's already open!";
    }
    public void interact() {
        if (!opened) {
            gp.playSFX(4);

            down1 = image2;
            collision = false;
            opened = true;
            gp.player.spiderCount = gp.eHandler.spiderEvent(23, 13, gp.dialogueState, gp.player.spiderCount, true, false); //23,13
            gp.eHandler.kitchenPantry();
        }
        else {
            startDialogue(this, 0);
        }
        gp.keyH.enterPressed = false;
    }
}
