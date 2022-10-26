package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin;
import object.OBJ_LightningBoltStress;

import java.util.Random;

public class MON_WaspSwarm extends Entity {

    GamePanel gp;
    public MON_WaspSwarm(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_monster;
        name = "WaspSwarm";
        defaultSpeed = 2;
        chaseSpeed = 6;
        speed = defaultSpeed;
        monsterMaxStress = 10;
        stressLevel = 0;
        attack = 3;
        defense = 2;
        exp = 4;
        knockBackPower = 6;
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        dyingImage = setup("/monster/waspSwarm1", gp.tileSize, gp.tileSize);
        up1 = setup("/monster/waspSwarm1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/waspSwarm2", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/waspSwarm1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/waspSwarm2", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/waspSwarm1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/waspSwarm2", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/waspSwarm1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/waspSwarm2", gp.tileSize, gp.tileSize);
    }

    public void setAction() {
//        System.out.println("Wasps on Path: " + onPath);
//        System.out.println("Wasps speed: " + speed);
//        System.out.println("Wasp received damage just now: " + damageJustReceived);

        if(onPath) {
            //Check if it stops chasing
            checkStopsChasingOrNot(gp.player, 15, 100);
            //Search the direction to go
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));

        } else {
            //Check if it starts chasing
            damageJustReceived = checkStartsChasingOrNot(gp.player, 5, 100, damageJustReceived);
            //Get a random direction
            getRandomDirection();
        }

//        //Check if it attacks (applies to monsters with weapon to swing at player)
//        if (!attacking) {
//            checkAttackOrNot(30,gp.tileSize*4, gp.tileSize); //rate number smaller for more aggressive
//        }
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
