package object;

import entity.Entity;
import main.GamePanel;
import main.MissionStates;

public class OBJ_BathLeft extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "BathLeft";

    public OBJ_BathLeft(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "BathLeft";
        image = setup("/objects/bathLeft", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/bathLeft2", gp.tileSize, gp.tileSize);
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
        dialogueText[0][0] = "You start the shower";
        dialogueText[1][0] = "Oh no there's bloody water going everywhere!";
        dialogueText[1][1] = "I'm going to have to mop that up now before\nhe bloody finds it!";
        dialogueText[1][2] = "I think I put one in the kitchen pantry\nactually!";
        dialogueText[2][0] = "I'm not going near that again til he\nfixes it, everything's broke in this house\nI'm bloody sick of it I am!";
        dialogueText[3][0] = "The control seems to be jammed.\nCan't even have a bloody shower in this house!";
    }

    public void interact() {
        if (!opened && gp.player.missionState == MissionStates.MOP_UP_THE_SHOWER_WATER && !gp.player.showerAlreadyRan) {
            startDialogue(this, 0);
            opened = true;
            gp.eHandler.startShower();
        } else if (!opened && gp.player.showerAlreadyRan) {
            startDialogue(this, 2);
        } else if (!opened && gp.player.missionState < MissionStates.MOP_UP_THE_SHOWER_WATER) {
            startDialogue(this, 3);
        } else if (opened && ((gp.player.missionState >= MissionStates.MOP_UP_THE_SHOWER_WATER && gp.player.missionSubstate > 0) || gp.player.missionState == MissionStates.BETWEEN_MISSIONS)) {
            startDialogue(this, 2);
        }
        gp.keyH.enterPressed = false;
    }
}
