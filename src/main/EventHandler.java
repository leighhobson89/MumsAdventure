package main;

import entity.Entity;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class EventHandler {

    final GamePanel gp;
    final EventRect[][][] eventRect;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    int tempMap, tempCol, tempRow;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        int map = 0;
        int col = 0;
        int row = 0;
        while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if(col == gp.maxWorldCol) {
                col = 0;
                row++;

                if (row == gp.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }
        }
    }

    public void checkEvent() {

        //Check if the player character is more than 1 tile away from the last event
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize) {
            canTouchEvent = true;
        }

        if (canTouchEvent) {
            if (hit(0, 19, 10, "right", "")) {transitionUpDownStairs(1, 24, 10);}
            else if (hit(1, 24, 10, "left", "")) {transitionUpDownStairs(0, 19, 10);}
            else if (hit(0, 37, 8, "down", "up")) {
                flagInsideBookHut(false);
            } else if (hit(0, 37, 8, "up", "down")) {
                flagInsideBookHut(true);
            } else if (hit(0, 33, 8, "down", "up")) {
                flagInsideToolHut(false);
            } else if (hit(0, 33, 8, "up", "down")) {
                flagInsideToolHut(true);
            }
        }
    }

    public boolean hit(int map, int col, int row, String reqDirection, String exclude) {

        boolean hit = false;

        if (map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row*gp.tileSize + eventRect[map][col][row].y;

            if (gp.player.solidArea.intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone) {
                if (gp.player.direction.contentEquals(reqDirection) || (reqDirection.contentEquals("any") && !reqDirection.contentEquals(exclude))) {
                    hit = true;

                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }

        return hit;
    }

    public int spiderEvent(int col, int row, int gameState, int spiderCount, boolean randomizeLocation, boolean gardening) {
        boolean atBin = col == 30 && row == 8;
        if (atBin) {
            gp.playSFX(14);
        }
        if (spiderCount > 10) {
            if (gardening) {
                gp.gameState = gameState;
                gp.playSFX(8);
                gp.player.startDialogue(gp.player, 14);
                gp.aSetter.monsterNumber = gp.aSetter.setMonster("Spider", gp.aSetter.monsterNumber, col, row, gp.currentMap, randomizeLocation);
                gp.player.pillsConsumableNow = gp.player.stressLevel >= gp.player.STRESS_LEVEL_NEEDED_TO_CONSUME_PILLS;
                spiderCount++;
            }
        } else {
            gp.gameState = gameState;
            gp.playSFX(8);
            if (spiderCount == 1) {
                gp.player.startDialogue(gp.player, 12);
                gp.aSetter.monsterNumber = gp.aSetter.setMonster("Spider", gp.aSetter.monsterNumber, col, row, gp.currentMap, randomizeLocation); // trigger a spider when the dialogue is closed
            } else if (spiderCount > 1) {
                gp.player.setDialogue();
                gp.player.startDialogue(gp.player, 13);
                gp.aSetter.monsterNumber = gp.aSetter.setMonster("Spider", gp.aSetter.monsterNumber, col, row, gp.currentMap, randomizeLocation);
            }
            gp.player.pillsConsumableNow = gp.player.stressLevel >= gp.player.STRESS_LEVEL_NEEDED_TO_CONSUME_PILLS;
            //eventRect[col][row].eventDone = true; //for non recurring events only
            canTouchEvent = false;
            spiderCount++;
        }
        return spiderCount;
    }

    public void DestressEvent() {
        //if (gp.keyH.enterPressed && gp.player.stressLevel > 0) { // to access event with a key press only
            if (gp.player.stressLevel > 0 || gp.eManager.lighting.dayState != gp.eManager.lighting.day) {
                gp.player.attackCanceled = true;
                gp.playSFX(12);
                gp.player.startDialogue(gp.player, 10);
                gp.gameState = gp.sleepState;
                gp.player.stressLevel = 0;
                canTouchEvent = false;
            } else {
                gp.player.startDialogue(gp.player, 15);
            }
            gp.saveLoad.save();
        }
    //}

    public void teleportPills (int currentMap) {
        int[][] optionArray;
        gp.gameState = gp.dialogueState;
        gp.player.startDialogue(gp.player, 17);
        if (gp.player.doorUnlockedCount > 0) {
            optionArray = new int[][] {{0,46,15},{0,58,17},{0,12,10},{0,15,19},{0,17,11},{0,18,13},{0,20,11},{0,23,15},{0,24,15},{0,28,11},{1,22,11},{1,19,16},{1,29,12},{1,27,18}};
        } else {
            optionArray = new int[][] {{0,17,11},{0,18,13},{0,20,11},{0,23,15},{0,24,15},{0,28,11},{1,22,11},{1,19,16},{1,29,12},{1,27,18}};
        }
        Random random = new Random();
        int randomLocation = random.nextInt(optionArray.length);
        int map = optionArray[randomLocation][0];
        int randX = optionArray[randomLocation][1];
        int randY = optionArray[randomLocation][2];

        if (map != currentMap) {
            transitionUpDownStairs(map, randX, randY);
        } else {
            gp.player.worldX = gp.tileSize*randX;
            gp.player.worldY = gp.tileSize*randY;
        }
        gp.player.stressLevel = 0;
    }

    public void transitionUpDownStairs(int map, int col, int row) {

        tempMap = map;
        tempCol = col;
        tempRow = row;
        gp.gameState = gp.transitionState;

        if (gp.player.missionState == MissionStates.DRAG_COOKER_TO_BINS) { //reset movable object if change area, and it is not where it needs to be
            for (int i = 0; i < gp.npc[1].length; i++) {
                if (gp.npc[gp.currentMap][i] != null && (Objects.equals(gp.npc[gp.currentMap][i].name, "OldCooker"))) {
                    if (gp.npc[gp.currentMap][i].linkedEntity == null) {
                        gp.npc[gp.currentMap][i].worldX = 39 * gp.tileSize;
                        gp.npc[gp.currentMap][i].worldY = 8 * gp.tileSize;
                    }
                }
            }
        }
        setImageStates(tempMap);
        canTouchEvent = false;
        //add sound effect stairs
    }

    public void setImageStates(int tempMap) {
        for (int i = 0; i < gp.obj[1].length; i++) { //set correct image state for outdoor objects that appear on both maps, add more as required
            if (gp.obj[tempMap][i] != null) {
                if (Objects.equals(gp.obj[tempMap][i].name, "BackGate")) {
                    switch (gp.player.backGateState) {
                        case 1 -> gp.obj[tempMap][i].down1 = gp.obj[tempMap][i].image;
                        case 2 -> gp.obj[tempMap][i].down1 = gp.obj[tempMap][i].image2;
                    }
                } else if (Objects.equals(gp.obj[tempMap][i].name, "BlockOfWood")) {
                    switch (gp.player.blockWoodState) {
                        case 1 -> gp.obj[tempMap][i].down1 = gp.obj[tempMap][i].image;
                        case 2 -> gp.obj[tempMap][i].down1 = gp.obj[tempMap][i].image2;
                        case 3 -> gp.obj[tempMap][i].down1 = gp.obj[tempMap][i].image3;
                    }
                } else if (Objects.equals(gp.obj[tempMap][i].name, "Bookhut1_Center") || Objects.equals(gp.obj[tempMap][i].name, "Bookhut2_Center") ) {
                    switch (gp.player.bookHutState) {
                        case 0 -> gp.obj[tempMap][i].down1 = gp.obj[tempMap][i].image;
                        case 1 -> gp.obj[tempMap][i].down1 = gp.obj[tempMap][i].image2;
                    }
                } else if (Objects.equals(gp.obj[tempMap][i].name, "Toolhut1_Center") || Objects.equals(gp.obj[tempMap][i].name, "Toolhut2_Center") ) {
                    switch (gp.player.toolHutState) {
                        case 0 -> gp.obj[tempMap][i].down1 = gp.obj[tempMap][i].image;
                        case 1 -> gp.obj[tempMap][i].down1 = gp.obj[tempMap][i].image2;
                    }
                }  else if (Objects.equals(gp.obj[tempMap][i].name, "WaspNest")) {
                    switch (gp.player.waspNestState) {
                        case 0 -> gp.obj[tempMap][i].down1 = gp.obj[tempMap][i].image;
                        case 1 -> gp.obj[tempMap][i].down1 = gp.obj[tempMap][i].image3;
                        case 2 -> gp.obj[tempMap][i].down1 = gp.obj[tempMap][i].image4;
                    }
                }
            }
        }
        for (int i = 0; i < gp.player.inventory.size(); i++) { //for changing images in inventory
            if (Objects.equals(gp.player.inventory.get(i).name, "MagicQuizBook") || Objects.equals(gp.player.inventory.get(i).name, "FlammableSpray")) {
                gp.player.inventory.get(i).down1 = gp.player.inventory.get(i).image2;
            } else if (Objects.equals(gp.player.inventory.get(i).name, "WaspNest")) {
                gp.player.inventory.get(i).down1 = gp.player.inventory.get(i).image4;
            }
        }
    }

    public void cupboardHall() {
        gp.aSetter.setObjectAfterStart("Old Cardigan", gp.currentMap, 24, 11, false);
    }

    public void cupboardLounge() {
        gp.aSetter.setObjectAfterStart("Lavender Crocs", gp.currentMap, 18, 13, false);
    }

    public void kitchenCupBoard() {
        gp.aSetter.setObjectAfterStart("Spatula", gp.currentMap, 27, 15, false);
    }

    public void kitchenPantry() {
        gp.aSetter.setObjectAfterStart("Mop", gp.currentMap, 24, 13, false);
    }

    public void fishTankDrawer() { gp.aSetter.setObjectAfterStart("Lighter", gp.currentMap, 17, 17, false); }

    public void fridgeOpen() {
        gp.aSetter.setObjectAfterStart("Chicken", gp.currentMap, 26, 11, false);
    }

    public void lightPillsEvent() {

        gp.gameState = gp.dialogueState;
        gp.player.startDialogue(gp.player, 16);

        gp.player.countDownTimerForItemEffect("LightPills");
    }

    public void setUpNextPhoneCallWhenNotInAMissionAndNextMissionIsAPhoneMission(int missionState) {
        if (missionState == MissionStates.BETWEEN_MISSIONS && gp.player.weedCount < 1) {
            gp.player.missionEndingCounter++;
            if (gp.player.missionEndingCounter > gp.player.randomCounter) {
                gp.player.missionEndingCounter = 0;
                if (gp.player.nextMissionIsPhoneMission) {
                    gp.player.readyForNextPhoneMission = true;
                    gp.player.randomCounter = gp.player.setRandomCounter();
                    if (!gp.player.phoneRinging) {
                        gp.loopSFX(28); //make phone ring
                        gp.player.phoneRinging = true;
                    }
                }
            }
        }
    }

    @SuppressWarnings("SuspiciousListRemoveInLoop")
    public void removeMissionItemFromPlayerInventory(ArrayList<Entity> inventory, int missionState, int missionSubState) {
        for (int i = 0; i < inventory.size(); i++) {
            switch(missionState) {
                case MissionStates.CHOP_CHICKEN_FOR_DOGS:
                    if (Objects.equals(inventory.get(i).name, "Chicken")) {
                        inventory.remove(i);
                    }
                    break;
                case MissionStates.MAGIC_BOOK_QUIZ:
                    if (Objects.equals(inventory.get(i).name, "BookHutKey") && missionSubState == 0) {
                        inventory.remove(i);
                    }
                    break;
            }
        }
    }

    public void removeItemFromPlayerInventory(ArrayList<Entity> inventory, String itemToRemove) {
        for (int i = 0; i < inventory.size(); i++) {
            if (Objects.equals(inventory.get(i).name, itemToRemove)) {
                inventory.remove(i);
                break;
            }
        }
    }

    public void startShower() {
        for (int i = 0; i < gp.obj[1].length; i++) {
            if (gp.obj[gp.currentMap][i] != null && Objects.equals(gp.obj[gp.currentMap][i].name, "BathLeft")) {
                gp.obj[gp.currentMap][i].down1 = gp.obj[gp.currentMap][i].image2;
            }
            gp.player.showerCounterStart = true;
        }
    }

    public void stopShower() {
        for (int i = 0; i < gp.obj[1].length; i++) {
            if (gp.obj[gp.currentMap][i] != null && Objects.equals(gp.obj[gp.currentMap][i].name, "BathLeft")) {
                gp.obj[gp.currentMap][i].down1 = gp.obj[gp.currentMap][i].image;
                gp.obj[gp.currentMap][i].startDialogue(gp.obj[gp.currentMap][i], 1);
            }
        }
        gp.player.showerAlreadyRan = true;
        gp.player.waterTileCount = gp.aSetter.setInteractiveTilesAfterStart(MissionStates.MOP_UP_THE_SHOWER_WATER);
        gp.playSFX(33);
        gp.player.missionSubstate++;
    }

    public void flagInsideBookHut(boolean isInside) {
        if (isInside) {
            gp.player.insideBookShed = true;
        } else {
            gp.player.insideBookShed = false;
            for (int i = 0; i < gp.obj[1].length; i++) {
                if (gp.obj[gp.currentMap][i] != null && Objects.equals(gp.obj[gp.currentMap][i].name, "MagicQuizBook")) {
                    gp.player.changeOtherObjectImage("MagicQuizBook", 37, 6, 1);
                }
            }
        }
    }

    public void flagInsideToolHut(boolean isInside) {
        if (isInside) {
            gp.player.insideToolShed = true;
        } else {
            gp.player.insideToolShed = false;
            for (int i = 0; i < gp.obj[1].length; i++) {
                if (gp.obj[gp.currentMap][i] != null && Objects.equals(gp.obj[gp.currentMap][i].name, "FlammableSpray")) {
                    gp.player.changeOtherObjectImage("FlammableSpray", 33, 6, 1);
                }
            }
        }
    }
}
