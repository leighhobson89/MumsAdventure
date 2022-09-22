package main;

import java.util.Random;

public class EventHandler {

    GamePanel gp;
    EventRect[][] eventRect;
    UtilityTool uTool = new UtilityTool();

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if(col == gp.maxWorldCol) {
                col = 0;
                row++;
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
            if (hit(20, 8, "any")) {gp.player.spiderCount = spiderEvent(20, 8, gp.dialogueState, gp.player.spiderCount, true, false);}
            if (hit(23, 2, "any")) {gp.player.spiderCount = spiderEvent(24, 3, gp.dialogueState, gp.player.spiderCount, false, false);}
            if (hit(16, 13, "any")) {chairDestressEvent(gp.dialogueState);}
        }
    }

    public boolean hit(int col, int row, String reqDirection) {

        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col*gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row*gp.tileSize + eventRect[col][row].y;

        if (gp.player.solidArea.intersects(eventRect[col][row]) && !eventRect[col][row].eventDone) {
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;

                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;
    }

    public void openFrontBackDoor(int playerX, int playerY) {
        if ((playerX == 26 || playerX == 27 || playerX == 28) && playerY == 6) {
            gp.player.backDoorAlreadyUnlocked = true;
            gp.obj[13] = null;
        } else if ((playerX == 14 || playerX == 13 || playerX == 12) && playerY == 6) {
            gp.player.frontDoorAlreadyUnlocked = true;
            gp.obj[11] = null;
        }
        gp.playSFX(3);
    }

    public int spiderEvent(int col, int row, int gameState, int spiderCount, boolean randomizeLocation, boolean gardening) {
        boolean atBin = false;
        if (col == 24 && row == 3) {
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
                gp.player.checkPillsConsumable(gp.player.stressLevel);
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
            gp.player.checkPillsConsumable(gp.player.stressLevel);
            //eventRect[col][row].eventDone = true; //for non recurring events only
            canTouchEvent = false;
            spiderCount++;
            System.out.println("Spider: " + spiderCount);
        }
        return spiderCount;
    }

    public void chairDestressEvent (int gameState) {
        //if (gp.keyH.enterPressed && gp.player.stressLevel > 0) { // to access event with a key press only
            if (gp.player.stressLevel > 0) {
                gp.player.attackCanceled = true;
                gp.playSFX(12);
                gp.gameState = gameState;
                gp.ui.currentDialogue = "Nice to have a sit down, I feel less stressed\nstraight away!";
                gp.player.stressLevel -= 1;
                canTouchEvent = false;
            }
        }
    //}

    public void teleportPills () {
        gp.ui.currentDialogue = "Bloody pills, I can't think straight!\nWhat am I doing up here??\nThe stress has gone at least!";
        int[][] optionArray = {{14,6},{15,8},{17,6},{20,10},{21,10},{25,6}};
        Random random = new Random();
        int randomLocation = random.nextInt(optionArray.length);
        int randX = optionArray[randomLocation][0];
        int randY = optionArray[randomLocation][1];

        gp.player.worldX = gp.tileSize*randX;
        gp.player.worldY = gp.tileSize*randY;

        gp.playSFX(2);
        gp.player.countDownTimerForItemEffect(gp.player.PILLS_COUNT_DOWN_VALUE, "Pills");
        gp.player.stressLevel = 0;

    }
}
