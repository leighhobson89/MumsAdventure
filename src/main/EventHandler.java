package main;

import entity.Player;
import java.util.Random;

public class EventHandler {

    GamePanel gp;
    EventRect eventRect[][];
    UtilityTool uTool = new UtilityTool();

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    int spiderCount = 1;

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
            if (hit(20, 8, "any")) {spiderCount = spiderEvent(21, 9, gp.dialogueState, spiderCount);}
            if (hit(23, 2, "up")) {spiderCount = spiderEvent(24, 3, gp.dialogueState, spiderCount);}
            if (hit(16, 13, "any")) {chairDestressEvent(gp.dialogueState);}
            if (!gp.player.dizzyFlag) {
                if (hit(16, 10, "any")) {teleportPills(gp.dialogueState);}
            }
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

    public int spiderEvent(int col, int row, int gameState, int spiderCount) {
        gp.gameState = gameState;
        if (spiderCount == 1) {
            gp.ui.currentDialogue = "Aaagh a bloody big spider!";
            gp.aSetter.setMonster(spiderCount, col, row); // trigger a spider when the dialogue is closed
        } else if (spiderCount > 1 && spiderCount <= 10) {
            gp.ui.currentDialogue = "Aaagh another bloody spider!\nThat's " + uTool.parseNumberString(spiderCount) + " in one day, sick of it!";
            gp.aSetter.setMonster(spiderCount, col, row);
        } else {
            gp.ui.currentDialogue = "So many bloody spiders today, Peter will you\nsort this bloody house out?";
        }
        if (spiderCount <= 10) {
            gp.player.stressLevel += 3;
        }
        //eventRect[col][row].eventDone = true; //for non recurring events only
        canTouchEvent = false;
        spiderCount++;
        return spiderCount;
    }

    public void chairDestressEvent (int gameState) {
        //if (gp.keyH.enterPressed && gp.player.stressLevel > 0) { // to access event with a keypress only
        if (gp.player.stressLevel > 0) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "Nice to have a sit down, I feel less stressed\nstraight away!";
            gp.player.stressLevel -= 1;
            canTouchEvent = false;
        }
    }

    public void teleportPills (int gameState) {
        gp.gameState = gameState;
        int[][] optionArray = {{14,6},{15,8},{17,6},{20,9},{21,10},{25,6}};
        Random random = new Random();
        int randomLocation = random.nextInt(optionArray.length);
        int randX = optionArray[randomLocation][0];
        int randY = optionArray[randomLocation][1];

        gp.player.worldX = gp.tileSize*randX;
        gp.player.worldY = gp.tileSize*randY;

        gp.playSFX(2);
        gp.ui.currentDialogue = "Bloody pills, I can't think straight!\nWhat am I doing up here??\nThe stress has gone at least!";
        gp.player.countDownTimerForItemEffect(gp.player.PILLS_COUNT_DOWN_VALUE, "Pills");
        gp.player.stressLevel = 0;

        canTouchEvent = false;
    }
}
