package object;

import entity.Entity;
import main.GamePanel;
import main.MissionStates;

public class OBJ_Bin_Green extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "Bin_Green";

    public OBJ_Bin_Green(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "Bin_Green";
        image = setup("/objects/bin_Green", gp.tileSize, gp.tileSize);
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
        dialogueText[0][0] = "No way I'm opening that bin again!";
        dialogueText[1][0] = "There, that's rid of that horrible thing.";
    }

    public void interact() {
        if (gp.player.missionState == MissionStates.CHUCK_WASP_NEST_IN_BIN) {
            gp.playSFX(14);
            startDialogue(this, 1);
            gp.keyH.enterPressed = false;
            gp.eHandler.removeItemFromPlayerInventory(gp.player.inventory, "WaspNest");
            gp.misStat.endMissionTasks(MissionStates.CHUCK_WASP_NEST_IN_BIN, false);
        } else {
            if (!opened) {
                gp.playSFX(14);

                gp.player.spiderCount = gp.eHandler.spiderEvent(27, 8, gp.dialogueState, gp.player.spiderCount, true, false);
                opened = true;
            } else {
                startDialogue(this, 0);
            }
            gp.keyH.enterPressed = false;
        }
    }
}
