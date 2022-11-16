package object;

import entity.Entity;
import main.GamePanel;
import main.MissionStates;

public class OBJ_TelephoneHall extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "TelephoneHall";

    public OBJ_TelephoneHall(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
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

        //MISSION TEXTS
        //SELL_DADS_ELECTRIC_GUITAR_TO_MERCHANT
        dialogueText[2][0] = "O'right is dat Peter and Sharon's house?";
        dialogueText[2][1] = "Listen yeah, I'm up at da back gate init\n'n I got loads of stuff for sale init!";
        dialogueText[2][2] = "You can sell me stuff aswell.\nYou don't know anybody who's got an electric guitar\nfor sale d'yer?";
        dialogueText[2][3] = "My brudder wants one, i'll pay good money init!";
        dialogueText[2][4] = "Come 'n see me round 'da back init\nand i'll sort you out!";
        //HELP ANDREA OUT
        dialogueText[3][0] = "Hiya Sharon, its Andrea!";
        dialogueText[3][1] = "Listen, could you do me a favour and lend me\n40 quid?";
        dialogueText[3][2] = "I need some fags and petrol and Andy has spent\nup on our fourth holiday to Turkey this year.";
        dialogueText[3][3] = "Oh and I've got that coat you wanted off Amanda";
        dialogueText[3][4] = "Come to the front gate, I'm here";
        //CHOP CHICKEN FOR DOGS
        dialogueText[4][0] = "Hee Hee, I thought I'd make you run to the phone!";
        dialogueText[4][1] = "Can you do me a favour?";
        dialogueText[4][2] = "These dogs are pestering me for their chicken.";
        dialogueText[4][3] = "I got some yesterday up Colne and\n it's in the fridge.";
        dialogueText[4][4] = "Can you get it out, and go and chop it up\non the chopping block outside?";
        dialogueText[4][5] = "Then throw it in the garden for them\nthey'll love it!";


    }

    public void interact() {
        gp.stopSFX();
        gp.player.phoneRinging = false;
        if (gp.player.readyForNextPhoneMission || gp.player.missionState == MissionStates.WEEDING_MISSION) {
            if (gp.player.nextMissionIsPhoneMission) {
                setNewMissionState(gp.player.readyForNextPhoneMission, gp.player.missionState, gp.player.missionToSet);
                startDialogue(this, gp.player.missionState);
            }
            gp.keyH.enterPressed = false;
            gp.player.readyForNextPhoneMission = false;
        } else {
            startDialogue(this, 0);
            gp.keyH.enterPressed = false;
        }
    }
}
