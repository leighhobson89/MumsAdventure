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
        dialogueText[0][0] = "You answer the phone";
    }

    public void interact() {
        if (gp.player.readyForNextPhoneMission) {
            gp.stopSFX(28);
            startDialogue(this, 0);
            gp.keyH.enterPressed = false;
            setNewMissionState(gp.player.readyForNextPhoneMission, gp.player.missionState, gp.player.missionToSet);
            gp.player.readyForNextPhoneMission = false;
        }
    }
}
