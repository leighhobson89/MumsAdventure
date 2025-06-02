package object;

import entity.Entity;
import main.GamePanel;
import main.MissionStates;

import java.util.Objects;

public class OBJ_BlockOfWood extends Entity {

    final GamePanel gp;
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
        blockWoodState = 1; //for upstairs correct image
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
        dialogueText[3][0] = "You clean the blood off the Chopping Block with the\ncheap knock off Chinese Stain Remover!";
        dialogueText[4][0] = "A nice clean Chopping Block!";
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
        } else if (opened && gp.player.missionList.size() >= 7) {
            boolean stainRemoverUsed = false;
            for (int i = 0; i < gp.player.inventory.size(); i++) {
                if (Objects.equals(gp.player.inventory.get(i).name, "StainRemover")) {
                    gp.ui.addMessage("Stain Remover used to clean blood!");
                    gp.player.inventory.remove(i);
                    stainRemoverUsed = true;
                    gp.player.stainRemoverUsed = true;
                    break;
                }
            }
            if (stainRemoverUsed) {
                startDialogue(this, 3);
                down1 = image;
                gp.player.blockWoodState = 1;
            } else {
                if (gp.player.stainRemoverUsed) {
                    startDialogue(this, 4);
                } else {
                    startDialogue(this, 1);
                }
            }
            gp.keyH.enterPressed = false;
        }
    }
}
