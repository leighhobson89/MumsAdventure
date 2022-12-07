package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_FishTankDrawer extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "FishTankDrawer";

    public OBJ_FishTankDrawer(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "FishTankDrawer";
        image = setup("/objects/fishTankDrawer", gp.tileSize, gp.tileSize);
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
        dialogueText[0][0] = "A lighter, must be one of Leigh's from years\nago, oh it still works, I'll hang on to it in case!";
        dialogueText[1][0] = "The cupboard is empty!";
    }

    public void interact() {
        if (!opened) {
            gp.playSFX(4);
            startDialogue(this, 0);
            opened = true;
            gp.eHandler.fishTankDrawer();
        } else {
            startDialogue(this, 1);
        }
        gp.keyH.enterPressed = false;
    }
}
