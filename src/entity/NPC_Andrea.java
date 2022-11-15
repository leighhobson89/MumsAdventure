package entity;

import main.GamePanel;
import main.MissionStates;
import object.OBJ_AmandaCoat;
import object.OBJ_Pills;

import java.awt.*;

public class NPC_Andrea extends Entity {

    public NPC_Andrea(GamePanel gp) {
        super(gp);

        name = "Andrea";
        direction = "down";
        defaultSpeed = 0;
        speed = 1;
        type = type_npc;
        onPath = true;
        goesTransparentWhenHit = true;

        getImage();
        setDialogue();
        setItems();

        solidArea = new Rectangle(8, 16,32,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void getImage() {

        up1 = setup("/NPC/andrea_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/NPC/andrea_up2", gp.tileSize, gp.tileSize);
        down1 = setup("/NPC/dad_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/NPC/dad_down2", gp.tileSize, gp.tileSize);
        left1 = setup("/NPC/dad_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/NPC/dad_left2", gp.tileSize, gp.tileSize);
        right1 = setup("/NPC/dad_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/NPC/dad_right2", gp.tileSize, gp.tileSize);

    }

    public void setDialogue() {
        //MISSION DIALOGUE SETS
        //HELP ANDREA OUT MISSION
        dialogueText[0][0] = "Hiya, y'alright";
        dialogueText[0][1] = "Right bloody carry on huh huh\nOh here's that coat before I forget. Amanda says\n cheers.";
        dialogueText[0][2] = "Can I borrow that forty quid then for some\nfags and petrol.  I'll give it you back\n eventually.";
        dialogueText[0][3] = "Oh and if you don't mind can I borrow them red\nboots? You don't have to...";

        //BONE THROWN DIALOG
        dialogueText[1][0] ="Ow! Bloody hell Sharon, what's that for?";

        //TRADESCREEN
        dialogueText[2][0] = "Trade the items with Andrea.  You only get one\nchance because she's off to the petrol station\nfor fags and petrol now!";
        dialogueText[3][0] = "Reyt, ta, I'll see ya la....ER!";
        dialogueText[4][0] = "You're not having that off me Sharon!";
        dialogueText[5][0] = "I love these boots!";
        dialogueText[5][1] = "I'll bring 'em back after the party!";
        dialogueText[6][0] = "Cheers, I can get my fags now!\nAlways bloody going up aren't they!";
        dialogueText[6][1] = "Oh, and petrol aswell yeah...";
        dialogueText[6][2] = "Reyt I'll see ya laaaa...ER!";
        dialogueText[7][0] = "I don't need that but cheers.";
        dialogueText[8][0] = "Cheers, don't forget this coat!";
        dialogueText[9][0] = "Ok, can you lend me that money then?";
    }

    public void setItems() {
        inventory.add(new OBJ_AmandaCoat(gp));
        inventory.add(new OBJ_Pills(gp));
    }

    public void update() {
        super.update();

        int xDistance = Math.abs(worldX - gp.tileSize* gp.player.andreaTempGoalCol);
        int yDistance = Math.abs(worldY - gp.tileSize* gp.player.andreaTempGoalRow);
        int tileDistance = (xDistance + yDistance)/gp.tileSize;

        if (tileDistance == 0) {
            if (gp.player.andreaTempGoalRow*gp.tileSize < 2 && gp.player.andreaTempGoalCol*gp.tileSize < 2) {
                speed = defaultSpeed;
                onPath = false;
            } else {
                speed = defaultSpeed;
                direction = "right";
                onPath = false;
                solidArea = new Rectangle(0, -64,100,128); //contact area reaches over gate so player can interact
            }
        }
    }

    public void setAction(int goalCol, int goalRow) {

        if(onPath) {
            if (gp.player.missionState == MissionStates.HELP_ANDREA_OUT) {
                goalCol = 9;
                goalRow = 9;
            }

            searchPath(goalCol, goalRow);
        }
    }

    public void speak() {
        if (firstTimeChattingToAndrea) {
            dialogueSet = 0;
        } else {
            dialogueSet = 2;
        }
        facePlayer();
        startDialogue(this, dialogueSet);
        gp.ui.npc = this;
        if (!firstTimeChattingToAndrea) {
            gp.ui.commandNum = 0;
            gp.gameState = gp.tradeState;
        }
        firstTimeChattingToAndrea = false;
    }
}
