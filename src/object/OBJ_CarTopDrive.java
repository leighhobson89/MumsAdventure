package object;

import entity.Entity;
import main.GamePanel;
import main.MissionStates;

import java.util.Random;

public class OBJ_CarTopDrive extends Entity {

    final GamePanel gp;

    final public static String OBJ_NAME = "CarTopDrive";
    final static int IMAGE_SCALE_FACTOR = 2;
    public OBJ_CarTopDrive(GamePanel gp) {
        super(gp);

        this.gp = gp;

        isUpdateable = true;
        type = type_obstacle;
        name = OBJ_NAME;
        direction = "down";
        collision = true;
        isScaledUpObject = true;

        solidArea.x = 0;
        solidArea.y = 20;
        solidArea.width = gp.tileSize*IMAGE_SCALE_FACTOR;
        solidArea.height = gp.tileSize*IMAGE_SCALE_FACTOR - 20;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
        down1 = image;

        setDialogue();
    }

    public void setDialogue() {
        dialogueText[0][0] = "I love this car, I'd hate it if owt\nhappened to it!";
        dialogueText[1][0] = "My poor car, I'll wring his bloody neck\n next door!";
    }

    public void getImage() {
        image = setup("/objects/carTopDrive", gp.tileSize*IMAGE_SCALE_FACTOR, gp.tileSize*IMAGE_SCALE_FACTOR);
        image2 = setup("/objects/carTopDriveScratched", gp.tileSize*IMAGE_SCALE_FACTOR, gp.tileSize*IMAGE_SCALE_FACTOR);
    }

    public void interact() {
        if (gp.player.missionList.size() > MissionStates.MOVE_TRAMPOLINE_OFF_CAR) {
            startDialogue(this, 1);
        } else {
            if (gp.player.missionState == MissionStates.MOVE_TRAMPOLINE_OFF_CAR && gp.player.missionSubstate == 1) {
                startDialogue(this, 1);
            } else {
                startDialogue(this, 0);
            }
        }
        gp.keyH.enterPressed = false;
    }

    public void update() {
        if (gp.player.missionState == MissionStates.MOVE_TRAMPOLINE_OFF_CAR && gp.player.missionSubstate == 1) {
            down1 = image2;
        }
    }
}
