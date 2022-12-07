package object;

import entity.Entity;
import main.GamePanel;
import main.MissionStates;

import java.util.Objects;

public class OBJ_Bucket extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "Bucket";

    public OBJ_Bucket(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_refillable_item;
        name = OBJ_NAME;
        displayName = "An Empty Bucket";
        description = "[" + name + "]\nAn Empty Bucket!";
        image = setup("/objects/bucketEmpty", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/bucketWater", gp.tileSize, gp.tileSize);
        down1 = image;
        direction = "down";
        collision = false;
        goesTransparentWhenHit = false;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDialogue();
    }

    public void setDialogue() {
        dialogueText[0][0] = "It's a bucket of water! Fancy that!";
        dialogueText[1][0] = "Get up to that Wasp Nest and put it out\nbefore it burns the bloody house down!";
        dialogueText[2][0] = "Phew! The fire is out, and the wasp nest\nis destroyed, thank God for that!";
        dialogueText[3][0] = "It's an empty bucket! Fancy that!";
    }

    public boolean use(Entity entity) {
        int waspNestIndex = 0;
        boolean nextToWaspNest = false;
        for (int i = 0; i < gp.obj[1].length; i++) {
            if (Objects.equals(gp.obj[gp.currentMap][i].name, "WaspNest")) {
                waspNestIndex = i;
            }
        }

        if (Math.abs(gp.player.worldX - gp.obj[gp.currentMap][waspNestIndex].worldX) <= 1 && Math.abs(gp.player.worldY - gp.obj[gp.currentMap][waspNestIndex].worldY) <= 1) { //if stood next to WaspNest
            nextToWaspNest = true;
        }

        if (gp.player.missionState == MissionStates.GET_RID_OF_WASP_NEST && gp.player.missionSubstate == 3) {
            if (nextToWaspNest) {
                startDialogue(this, 2);
                down1 = image;
                displayName = "An Empty Bucket";
                description = "[" + name + "]\nAn Empty Bucket!";
                gp.obj[gp.currentMap][waspNestIndex].down1 = gp.obj[gp.currentMap][waspNestIndex].image4;
                gp.misStat.endMissionTasks(MissionStates.GET_RID_OF_WASP_NEST, false);
                for (int i = 0; i < gp.player.inventory.size(); i++) {
                    if (Objects.equals(gp.player.inventory.get(i).name, "FlammableSprayWeapon")) {
                        gp.eHandler.removeItemFromPlayerInventory(gp.player.inventory, "FlammableSprayWeapon");
                        gp.player.currentWeapon = null;
                    }
                }
            } else {
                startDialogue(this, 1);
            }
        } else if (gp.player.bucketFull) {
            startDialogue(this, 0);
        } else { //bucket empty
            startDialogue(this, 3);
        }
        gp.keyH.enterPressed = false;
        return false;
    }
}
