package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BackGateSideways extends Entity {

    GamePanel gp;

    public OBJ_BackGateSideways(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = type_obstacle;

        name = "BackGateSideways";
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
            gp.player.spiderCount = gp.eHandler.spiderEvent(23, 13, gp.dialogueState, gp.player.spiderCount, true, false);
            System.out.println("SpiderCount after event set it: " + gp.player.spiderCount);
            gp.eHandler.kitchenCupBoard();
        }
        else {
            startDialogue(this, 0);
        }
        gp.keyH.enterPressed = false;
    }
}
