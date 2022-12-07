package object;

import entity.Entity;
import main.GamePanel;
import main.MissionStates;

public class OBJ_WaspNest extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "WaspNest";

    public OBJ_WaspNest(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = true;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "WaspNest";
        image = setup("/objects/waspNest", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/waspNestOnFire", gp.tileSize, gp.tileSize);
        image3 = setup("/objects/waspNestOnFire2", gp.tileSize, gp.tileSize);
        image4 = setup("/objects/waspNestDestroyed", gp.tileSize, gp.tileSize);
        down1 = image;
        direction = "down";
        collision = true;

        stressLevel = 0;
        monsterMaxStress = 3;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDialogue();
    }

    public void update() {
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if (gp.player.missionState == MissionStates.GET_RID_OF_WASP_NEST && gp.player.missionSubstate == 3) {
            if (spriteCounter < 20) {
                down1 = image2;
            } else {
                down1 = image3;
            }
            if (spriteCounter > 40) {
                spriteCounter = 0;
            }
            spriteCounter++;
        } else {
            spriteCounter = 0;
        }
    }

    public void setDialogue() {
        dialogueText[0][0] = "Looks quiet at the moment but I need to\n get rid of it sharpish!";
        dialogueText[1][0] = "Aaaaghhhhh!";
        dialogueText[2][0] = "Burn it to the ground with the flaming aerosol,\nquick!";
        dialogueText[3][0] = "Quick! Get water, its burning the bloody house\ndown!!";
    }

    public void interact() {
        if (!opened && gp.player.missionSubstate < 1) {
            gp.aSetter.setMonster("WaspSwarm", gp.aSetter.monsterNumber, 17, 16, gp.currentMap, false);
            startDialogue(this, 1);
            gp.player.missionSubstate = 1;
            gp.playSFX(8);
            gp.keyH.enterPressed = false;
        } else if (!opened && gp.player.missionSubstate == 1) {
            startDialogue(this, 0);
            gp.keyH.enterPressed = false;
        } else if (gp.player.missionSubstate == 2) {
            startDialogue(this, 2);
            gp.keyH.enterPressed = false;
        } else if (gp.player.missionSubstate == 3) {
            startDialogue(this, 3);
            gp.keyH.enterPressed = false;
        }

    }
}
