package object;

import entity.Entity;
import main.GamePanel;
import main.MissionStates;

public class OBJ_BlockOfWood extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "BlockOfWood";

    public OBJ_BlockOfWood(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = true;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "Chopping Block";
        image = setup("/objects/blockOfWood", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/blockOfWoodChicken", gp.tileSize, gp.tileSize);
        image3 = setup("/objects/blockOfWoodBlood", gp.tileSize, gp.tileSize);
        down1 = image;
        gp.player.blockWoodState = 1; //for upstairs correct image
        direction = "down";
        collision = false;
        goesTransparentWhenHit = false;

        stressLevel = 0;
        monsterMaxStress = 5;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDialogue();
    }

    public void setDialogue() {
        dialogueText[0][0] = "I'll get this chopped up nicely for Phoebe and Pip!\n(Whack it with your axe a few times!)";
        dialogueText[1][0] = "A chopping block covered in dried blood!";
        dialogueText[2][0] = "I need some chicken to chop!";
    }

    public void interact() {
        if (!opened && gp.player.missionState == MissionStates.CHOP_CHICKEN_FOR_DOGS && checkIfPlayerHasMissionItem(gp.player.inventory, MissionStates.CHOP_CHICKEN_FOR_DOGS, gp.player.missionSubstate)) {
            gp.playSFX(4); //change to chicken slapping sound
            gp.eHandler.removeMissionItemFromPlayerInventory(gp.player.inventory, MissionStates.CHOP_CHICKEN_FOR_DOGS, gp.player.missionSubstate);
            startDialogue(this, 0);
            down1 = image2; //set chicken on wood image
            gp.player.blockWoodState = 2; //for upstairs correct image
            opened = true;
            gp.keyH.enterPressed = false;
        } else if (!opened && !checkIfPlayerHasMissionItem(gp.player.inventory, MissionStates.CHOP_CHICKEN_FOR_DOGS, gp.player.missionSubstate) && gp.player.missionState == MissionStates.CHOP_CHICKEN_FOR_DOGS) {
            startDialogue(this, 2);
            gp.keyH.enterPressed = false;
        } else if (!opened && gp.player.missionState < MissionStates.CHOP_CHICKEN_FOR_DOGS) {
            startDialogue(this, 1);
            gp.keyH.enterPressed = false;
        } else if (opened && gp.player.missionState > MissionStates.CHOP_CHICKEN_FOR_DOGS || opened && gp.player.missionState == MissionStates.BETWEEN_MISSIONS) {
            startDialogue(this, 0);
            gp.keyH.enterPressed = false;
        }
    }
}
