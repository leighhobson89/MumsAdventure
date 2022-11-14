package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_Phoebe extends Entity {
    public NPC_Phoebe(GamePanel gp) {
        super(gp);

        name = "Phoebe";
        direction = "up";
        speed = 2;
        type = type_npc;
        goesTransparentWhenHit = true;
//        //comment for not throw bone
//        projectile = new OBJ_DogsBone_NotMagic(gp);
//        //end bone throwing

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
        dialogueText[2][0] = "I'm doing the dinner dance!";
        dialogueText[3][0] = "Woof! Pip was doing something funny to his bed!";
        dialogueText[4][0] = "Growl...Chicken time!";
        dialogueText[5][0] = "Growl...Pip thinks he's the toughest dog on\nthe street...HaHa...Ruff!";
        dialogueText[6][0] = "Shall we play cheeky monkey?";
        dialogueText[7][0] = "Yelp!";
    }

    public void setAction(int goalCol, int goalRow) {
        if (checkEdgeOfMap(this)) {
            turnEntityAround(this);
        } else {
            getRandomDirection();
        }

//        //comment for not throw bone
//        int i = new Random().nextInt(1000) + 1; //odds of throwing a bone
//        if (i > 999 && !projectile.alive && shotAvailableCounter == 30) {
//            projectile.set(worldX, worldY, direction, true, this);
//            gp.projectileList.add(projectile);
//            shotAvailableCounter = 0;
//        // end bone code
//        }
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
