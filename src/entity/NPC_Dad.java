package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_Dad extends Entity {
    public NPC_Dad(GamePanel gp) {
        super(gp);

        direction = "right";
        speed = 2;

        getImage();
    }

    public void getImage() {

        up1 = setup("/NPC/dad_up1");
        up2 = setup("/NPC/dad_up2");
        down1 = setup("/NPC/dad_down1");
        down2 = setup("/NPC/dad_down2");
        left1 = setup("/NPC/dad_left1");
        left2 = setup("/NPC/dad_left2");
        right1 = setup("/NPC/dad_right1");
        right2 = setup("/NPC/dad_right2");

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
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }

    }
}
