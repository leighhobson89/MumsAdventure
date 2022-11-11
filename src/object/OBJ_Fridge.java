package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Fridge extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "Fridge";

    public OBJ_Fridge(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "Fridge";
        down1 = setup("/objects/fridge", gp.tileSize, gp.tileSize);
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
        dialogueText[0][0] = "Ahh the chicken for the dogs is here!";
        dialogueText[1][0] = "The fridge is empty!";
        dialogueText[1][1] = "Peter, we'll have to go shopping!";
        dialogueText[2][0] = "The door is stuck, how strange!";
    }

    public void interact() {
        if (!opened && gp.player.missionState == 4) {
            gp.playSFX(4); //change to fridge sound
            startDialogue(this, 0);
            opened = true;
            gp.eHandler.fridgeOpen();
        } else if (!opened && gp.player.missionState < 4) {
            startDialogue(this, 2);
        } else {
            startDialogue(this, 1);
        }
        gp.keyH.enterPressed = false;
    }
}
