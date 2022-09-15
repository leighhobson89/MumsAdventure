package monster;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class MON_Spider extends Entity {

    GamePanel gp;
    public MON_Spider(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_monster;
        name = "Spider";
        speed = 4;
        monsterMaxStress = 3;
        stressLevel = 0;
        attack = 2;
        defense = 0;
        exp = 2;
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        dyingImage = setup("/monster/spiderDead", gp.tileSize, gp.tileSize);
        up1 = setup("/monster/spider_Up1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/spider_Up2", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/spider_Down1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/spider_Down2", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/spider_Left1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/spider_Left2", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/spider_Right1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/spider_Right2", gp.tileSize, gp.tileSize);
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
    public void damageReaction() {
        actionLockCounter = 0;
        direction = gp.player.direction;
    }
}
