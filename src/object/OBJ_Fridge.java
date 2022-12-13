package object;

import entity.Entity;
import main.GamePanel;
import main.MissionStates;

public class OBJ_Fridge extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "Fridge";

    public OBJ_Fridge(GamePanel gp) {

        super(gp);
        this.gp = gp;

        npcCanWalkOnWhenFollowing = true;
        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "Fridge";
        image = setup("/objects/fridge", gp.tileSize, gp.tileSize);
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
        dialogueText[0][0] = "Ahh the chicken for the dogs is here!";
        dialogueText[1][0] = "The fridge is empty!";
        dialogueText[1][1] = "Peter, we'll have to go shopping!";
        dialogueText[2][0] = "The door is stuck, how strange!";
    }

    public void interact() {
        if (!opened && gp.player.missionState == MissionStates.CHOP_CHICKEN_FOR_DOGS) {
            gp.playSFX(4); //change to fridge sound
            startDialogue(this, 0);
            opened = true;
            gp.eHandler.fridgeOpen();
        } else if (!opened && gp.player.missionState < MissionStates.CHOP_CHICKEN_FOR_DOGS) {
            startDialogue(this, 2);
        } else {
            startDialogue(this, 1);
        }
        gp.keyH.enterPressed = false;
    }
}
