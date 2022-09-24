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
        randomChummeringDialogues[0] = "Woof!";
        randomChummeringDialogues[1] = "Grr!";
        randomChummeringDialogues[2] = "Grr...Throw my bone...Grr!";
        randomChummeringDialogues[3] = "Woof! Pip was doing something funny to his bed!";
        randomChummeringDialogues[4] = "Growl...Chicken time!";
    }

    public void setAction() {

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
        super.speak();
    }
}
