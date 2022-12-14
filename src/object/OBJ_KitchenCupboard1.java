package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_KitchenCupboard1 extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "KitchenCupboard1";

    public OBJ_KitchenCupboard1(GamePanel gp) {

        super(gp);
        this.gp = gp;

        npcCanWalkOnWhenFollowing = true;
        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "Kitchen Cupboard";
        image = setup("/objects/kitchenCupboard1", gp.tileSize, gp.tileSize);
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
        dialogueText[0][0] = "Oh there's that bloody key, I have been looking\nall over for that!";
        dialogueText[1][0] = "The cupboard is empty!";
    }

    public void interact() {
        if (!opened) {
            gp.playSFX(4);
            startDialogue(this, 0);
            opened = true;
            gp.eHandler.kitchenCupBoard();
        } else {
            startDialogue(this, 1);
        }
        gp.keyH.enterPressed = false;
    }
}
