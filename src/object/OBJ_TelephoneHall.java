package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_TelephoneHall extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "TelephoneHall";

    public OBJ_TelephoneHall(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = type_obstacle;
        collision = true;
        name = OBJ_NAME;
        displayName = "TelephoneHall";
        down1 = setup("/objects/telephoneHall", gp.tileSize, gp.tileSize);
        direction = "down";
        setDialogue();

    }

    public void setDialogue() {
        //DEFAULT
        dialogueText[0][0] = "There's no one there.";
        dialogueText[1][0] = "Never mind that phone, get them weeds dug up!";
        dialogueText[2][0] = "O'right is dat Peter and Sharon's house?";
        dialogueText[2][1] = "Listen yeah, I'm up at da back gate init\n'n I got loads of stuff for sale init!";
        dialogueText[2][2] = "You can sell me stuff aswell.\nYou don't know anybody who's got an electric guitar\nfor sale d'yer?";
        dialogueText[2][3] = "My brudder wants one, i'll pay good money init!";
        dialogueText[2][4] = "Come 'n see me round 'da back init\nand i'll sort you out!";

        //
    }

    public void interact() {
        if (gp.player.readyForNextPhoneMission || gp.player.missionState == 1) {
            setNewMissionState(gp.player.readyForNextPhoneMission, gp.player.missionState, gp.player.missionToSet);
            startDialogue(this, gp.player.missionState);
            gp.keyH.enterPressed = false;
            gp.player.readyForNextPhoneMission = false;
            gp.stopSFX(28);
        } else {
            startDialogue(this, 0);
            gp.keyH.enterPressed = false;
        }
    }
}
