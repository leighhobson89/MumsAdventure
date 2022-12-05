package object;

import entity.Entity;
import main.GamePanel;
import main.MissionStates;

public class OBJ_Bookhut1_Center extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "Bookhut1_Center";

    public OBJ_Bookhut1_Center(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = true;
        type = type_hut;
        name = OBJ_NAME;
        displayName = "Bookhut1_Center";
        image = setup("/objects/bookhut1_center", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/bookhut1_center_open", gp.tileSize, gp.tileSize);
        down1 = image;
        direction = "down";
        collision = true;
        goesTransparentWhenHit = false;
        goesTransparentWhenStoodOnBookHut = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDialogue();
    }

    public void setDialogue() {
        dialogueText[0][0] = "You unlock the book hut.";
        dialogueText[1][0] = "All my old books are in here...ROTTING!\nWhy can't he just sort this bloody house out?";
        dialogueText[2][0] = "I need the book hut key, now where did I\nleave it?";
        dialogueText[3][0] = "It's already unlocked!";
    }

    public void interact() {
        if (!opened && gp.player.missionState == MissionStates.MAGIC_BOOK_QUIZ && checkIfPlayerHasMissionItem(gp.player.inventory, MissionStates.MAGIC_BOOK_QUIZ, gp.player.missionSubstate)) {
            gp.playSFX(3);
            gp.eHandler.removeMissionItemFromPlayerInventory(gp.player.inventory, MissionStates.MAGIC_BOOK_QUIZ, gp.player.missionSubstate);
            startDialogue(this, 0);
            changeOtherObjectImage("Bookhut2_Center", 37, 7, 2);
            down1 = image2; //set open book hut Image
            gp.player.bookHutState = 1;
            gp.player.missionSubstate = 1;
            collision = false;
            opened = true;
            gp.keyH.enterPressed = false;
        } else if (!opened && !checkIfPlayerHasMissionItem(gp.player.inventory, MissionStates.MAGIC_BOOK_QUIZ, gp.player.missionSubstate) && gp.player.missionState == MissionStates.MAGIC_BOOK_QUIZ) {
            startDialogue(this, 2);
            gp.keyH.enterPressed = false;
        } else if (!opened && gp.player.missionState < MissionStates.MAGIC_BOOK_QUIZ) {
            startDialogue(this, 1);
            gp.keyH.enterPressed = false;
        } else if (opened) {
            startDialogue(this, 3);
            gp.keyH.enterPressed = false;
        }
    }
}
