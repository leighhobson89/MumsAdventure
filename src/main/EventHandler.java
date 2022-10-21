package main;

import java.util.Random;

public class EventHandler {

    GamePanel gp;
    EventRect[][][] eventRect;
    UtilityTool uTool = new UtilityTool();

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
            if (hit(0, 19, 10, "right")) {transitionUpDownStairs(1, 24, 10);}
            else if (hit(1, 24, 10, "left")) {transitionUpDownStairs(0, 19, 10);}
        }
    }

    public boolean hit(int map, int col, int row, String reqDirection) {

        boolean hit = false;

        if (map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row*gp.tileSize + eventRect[map][col][row].y;

            if (gp.player.solidArea.intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone) {
                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
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
        boolean atBin = false;
        if (col == 30 && row == 8) {
            atBin = true;
        }
        if (atBin) {
            gp.playSFX(14);
        }
        if (spiderCount > 10) {
            if (gardening) {
                gp.gameState = gameState;
                gp.playSFX(8);
                gp.ui.currentDialogue = "So many bloody spiders today, Peter will you\nsort this bloody garden out?";
                gp.aSetter.monsterNumber = gp.aSetter.setMonster("Spider", gp.aSetter.monsterNumber, col, row, randomizeLocation);
                gp.player.pillsConsumableNow = gp.player.stressLevel >= gp.player.STRESS_LEVEL_NEEDED_TO_CONSUME_PILLS;
                spiderCount++;
                System.out.println("Spider: " + spiderCount);
            }
        } else {
            gp.gameState = gameState;
            gp.playSFX(8);
            if (spiderCount == 1) {
                gp.ui.currentDialogue = "Aaagh a bloody big spider!";
                gp.aSetter.monsterNumber = gp.aSetter.setMonster("Spider", gp.aSetter.monsterNumber, col, row, randomizeLocation); // trigger a spider when the dialogue is closed
            } else if (spiderCount > 1) {
                gp.ui.currentDialogue = "Aaagh another bloody spider!\nThat's " + uTool.parseNumberString(spiderCount) + " in one day, sick of it!";
                gp.aSetter.monsterNumber = gp.aSetter.setMonster("Spider", gp.aSetter.monsterNumber, col, row, randomizeLocation);
            }
            gp.player.pillsConsumableNow = gp.player.stressLevel >= gp.player.STRESS_LEVEL_NEEDED_TO_CONSUME_PILLS;
            //eventRect[col][row].eventDone = true; //for non recurring events only
            canTouchEvent = false;
            spiderCount++;
            System.out.println("Spider: " + spiderCount);
        }
        return spiderCount;
    }

    public void DestressEvent() {
        //if (gp.keyH.enterPressed && gp.player.stressLevel > 0) { // to access event with a key press only
            if (gp.player.stressLevel > 0 || gp.eManager.lighting.dayState != gp.eManager.lighting.day) {
                gp.player.attackCanceled = true;
                gp.playSFX(12);
                gp.gameState = gp.sleepState;
                gp.player.stressLevel = 0;
                canTouchEvent = false;
            } else {
                gp.ui.currentDialogue = "I'm fine, I don't need to\nrelax at the moment!";
            }
        }
    //}

    public void teleportPills (int currentMap) {
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "Bloody pills, I can't think straight!\nWhat am I doing up here??\nThe stress has gone at least!";
        int[][] optionArray = {{0,17,11},{0,18,13},{0,20,11},{0,23,15},{0,24,15},{0,28,11}}; //add some upstairs locations too
        Random random = new Random();
        int randomLocation = random.nextInt(optionArray.length);
        int map = optionArray[randomLocation][0];
        int randX = optionArray[randomLocation][1];
        int randY = optionArray[randomLocation][2];

        if (map != currentMap) {
            transitionUpDownStairs(map, 19, 17); //if required to teleport to another floor then handle that here
        }

        gp.player.worldX = gp.tileSize*randX;
        gp.player.worldY = gp.tileSize*randY;

        gp.playSFX(2);
        gp.player.countDownTimerForItemEffect(gp.player.PILLS_COUNT_DOWN_VALUE, "Pills");
        gp.player.stressLevel = 0;
    }

    public void transitionUpDownStairs(int map, int col, int row) {
        gp.gameState = gp.transitionState;
        tempMap = map;
        tempCol = col;
        tempRow = row;
        canTouchEvent = false;
        //add sound effect stairs
    }

    public void cupboardHall() {
        gp.aSetter.setObjectAfterStart("Old Cardigan", gp.currentMap, 24, 11);

    }

    public void kitchenCupBoard() {
        gp.aSetter.setObjectAfterStart("Spatula", gp.currentMap, 23, 13);

    }

    public void lightPillsEvent() {

        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "These light pills will help my dodgy eye\nfor a bit, phew!";

        gp.player.countDownTimerForItemEffect(gp.player.LIGHT_PILLS_COUNT_DOWN_VALUE, "LightPills");
    }
}
