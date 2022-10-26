package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin;
import object.OBJ_LightningBoltStress;

import java.util.Random;

public class MON_Spider extends Entity {

    GamePanel gp;
    public MON_Spider(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_monster;
        name = "Spider";
        defaultSpeed = 3;
        chaseSpeed = 5;
        speed = defaultSpeed;
        monsterMaxStress = 10;
        stressLevel = 0;
        attack = 2;
        defense = 0;
        exp = 2;
        knockBackPower = 5;
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

        if(onPath) {
            //Check if it stops chasing
            checkStopsChasingOrNot(gp.player, 15, 100);
            //Search the direction to go
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));

            //CODE TO MAKE MONSTER FIRE PROJECTILE WHEN ONPATH (COUNTERATTACK)
            //Check if it shoots a projectile
            checkShootOrNot(200, 30);

        } else {
            //Check if it starts chasing
            checkStartsChasingOrNot(gp.player, 5, 100, damageJustReceived);
            //Get a random direction
            getRandomDirection();
        }
    }
    public void damageReaction() {
        //THROW A DICE
        int rand = new Random().nextInt(100) + 1;
        actionLockCounter = 0;

        if (rand < 50) { //random chance of counterattacking player if hit it, or maybe it run away
            onPath = false;
            direction = gp.player.direction;
            speed = defaultSpeed;
        } else {
            onPath = true; //attacks player then continues randomly
            speed = chaseSpeed;
        }
    }

    public void checkDrop() {
        //THROW A DICE
        int rand = new Random().nextInt(100) + 1;

        //SET THE MONSTER DROP
        if (rand < 50) {
            dropItem(new OBJ_Coin(gp));
        }
        if (rand >= 50 && rand < 80) {
            dropItem(new OBJ_LightningBoltStress(gp));
        }
    }
}
