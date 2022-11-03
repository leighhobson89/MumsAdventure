package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class NPC_Pip extends Entity {
    public NPC_Pip(GamePanel gp) {
        super(gp);

        name = "Pip";
        direction = "right";
        speed = 2;
        type = type_npc;

        getImage();
        setDialogue();

        solidArea = new Rectangle(8, 16,32,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void getImage() {

        up1 = setup("/NPC/phoebe_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/NPC/phoebe_up2", gp.tileSize, gp.tileSize);
        down1 = setup("/NPC/phoebe_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/NPC/phoebe_down2", gp.tileSize, gp.tileSize);
        left1 = setup("/NPC/phoebe_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/NPC/phoebe_left2", gp.tileSize, gp.tileSize);
        right1 = setup("/NPC/phoebe_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/NPC/phoebe_right2", gp.tileSize, gp.tileSize);

    }

    public void setDialogue() {
        dialogueText[0][0] = "Woof!";
        dialogueText[1][0] = "Grr!";
        dialogueText[2][0] = "Grr...I'm off to my basket, don't look...Grr!";
        dialogueText[3][0] = "Woof! Phoebe stinks!";
        dialogueText[4][0] = "Growl...I'm not having a bath!";
        dialogueText[5][0] = "Growl...I'm the toughest dog on the street...Ruff!";
        dialogueText[6][0] = "Grr...Throw my bone...Grr!";
        dialogueText[7][0] = "Yelp!";
    }

    public void update() {
        super.update();

        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance)/gp.tileSize;

        if (gp.player.boneCount == 1 && tileDistance <= 15) {
            //follow player
            onPath = true;
            speed = 4;
            System.out.println("Pip On Path!");
        } else if (gp.player.boneCount == 1){
            speed = 2;
            onPath = false;
        } else if (gp.player.boneCount == 0) {
            //follow bone
            xDistance = Math.abs(worldX - gp.aSetter.boneX);
            yDistance = Math.abs(worldY - gp.aSetter.boneY);
            tileDistance = (xDistance + yDistance)/gp.tileSize;
            if (tileDistance <= 15 && tileDistance >= 1) {
                onPath = true;
                speed = 5;
            } else if (tileDistance < 1) {
                speed = 0;
            }
            else {
                speed = 2;
                onPath = false;
            }
        }
    }

    public void setAction() {

        if(onPath && gp.player.boneCount == 1) {
            int goalCol = 0;
            int goalRow = 0;
            if (Objects.equals(gp.player.direction, "up")) { //dog chase player but stay one square behind
                goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
                goalRow = ((gp.player.worldY + gp.player.solidArea.y)/gp.tileSize) + 1;
            } else if (Objects.equals(gp.player.direction, "down")) {
                goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
                goalRow = ((gp.player.worldY + gp.player.solidArea.y)/gp.tileSize) - 1;
            } else if (Objects.equals(gp.player.direction, "right")) {
                goalCol = ((gp.player.worldX + gp.player.solidArea.x)/gp.tileSize) - 1;
                goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
            } else if (Objects.equals(gp.player.direction, "left")) {
                goalCol = ((gp.player.worldX + gp.player.solidArea.x)/gp.tileSize) + 1;
                goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
            }


            searchPath(goalCol, goalRow);

        } else if (onPath && gp.player.boneCount == 0) {
            int goalCol = (gp.aSetter.boneX)/gp.tileSize;
            int goalRow = (gp.aSetter.boneY)/gp.tileSize;

            searchPath(goalCol, goalRow);
        } else {
            actionLockCounter ++;

            if (actionLockCounter == 60) {
                Random random = new Random();
                int i = random.nextInt(100) + 1; //pick up a number from 1 to 100

                if (i <= 25) {
                    direction = "up";
                }
                if (i > 25 && i <= 50) {
                    direction = "down";
                }
                if (i > 50 && i <= 75) {
                    direction = "left";
                }
                if (i > 75) {
                    direction = "right";
                }
                actionLockCounter = 0;

            }
        }
    }

    public void speak() {

        //character specific stuff here
        facePlayer();
        dialogueSet++;
        if (dialogueSet > 6) {
            dialogueSet = 0;
        }
        startDialogue(this, dialogueSet);
    }
}
