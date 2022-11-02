package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Cupboard3 extends Entity {

    GamePanel gp;

    public OBJ_Cupboard3(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "Cupboard3";
        down1 = setup("/objects/cupboard2HorLounge", gp.tileSize, gp.tileSize);
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
        dialogueText[0][0] = "Hey, don't touch my bloody music center!";
    }

    public void interact() {
        startDialogue(this, 0);
        gp.keyH.enterPressed = false;
    }
}
