package monster;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class MON_Spider extends Entity {
    public MON_Spider(GamePanel gp) {
        super(gp);

        type = 2;
        name = "Spider";
        speed = 5;
        maxStress = 2;
        stressLevel = maxStress;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        up1 = setup("/monster/spider_Up1");
        up2 = setup("/monster/spider_Up2");
        down1 = setup("/monster/spider_Down1");
        down2 = setup("/monster/spider_Down2");
        left1 = setup("/monster/spider_Left1");
        left2 = setup("/monster/spider_Left2");
        right1 = setup("/monster/spider_Right1");
        right2 = setup("/monster/spider_Right2");
    }

    public void setAction() {

        actionLockCounter ++;

        if (actionLockCounter == 30) {
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
