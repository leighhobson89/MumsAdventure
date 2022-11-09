package entity;

import main.GamePanel;
import main.MissionStates;

import java.awt.*;
import java.util.Objects;

public class NPC_Andrea extends Entity {
    public NPC_Andrea(GamePanel gp) {
        super(gp);

        name = "Andrea";
        direction = "down";
        defaultSpeed = 0;
        speed = 1;
        type = type_npc;
        onPath = true;

        getImage();
        setDialogue();

        solidArea = new Rectangle(8, 16,32,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void getImage() {

        up1 = setup("/NPC/dad_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/NPC/dad_up2", gp.tileSize, gp.tileSize);
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
    }

    public void update() {
        super.update();

        System.out.println("Andrea Speed: " + speed);
        int xDistance = Math.abs(worldX - gp.tileSize*9);
        int yDistance = Math.abs(worldY - gp.tileSize*9);
        int tileDistance = (xDistance + yDistance)/gp.tileSize;

        if (tileDistance == 0) {
            speed = defaultSpeed;
            direction = "right";
            onPath = false;
            solidArea = new Rectangle(0, -64,100,128); //contact area reaches over gate so player can interact
        }
    }

    public void setAction() {

        if(onPath) {
            int goalCol = 9;
            int goalRow = 9;

            searchPath(goalCol, goalRow);
        }
    }

    public void speak() {
        dialogueSet = 0;
        facePlayer();
        startDialogue(this, dialogueSet);
        gp.ui.npc = this;
    }
}
