package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin;
import object.OBJ_LightningBoltStress;

import java.util.Random;

public class MON_WaspSwarm extends Entity {

    final GamePanel gp;
    public MON_WaspSwarm(GamePanel gp) {
        super(gp);

        this.gp = gp;

        goesTransparentWhenHit = true;
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

        getInitialImage();
    }

    public void getInitialImage() {
        dyingImage = setup("/monster/waspSwarm1", gp.tileSize, gp.tileSize);
        up1Standard = setup("/monster/waspSwarm1", gp.tileSize, gp.tileSize);
        up2Standard = setup("/monster/waspSwarm2", gp.tileSize, gp.tileSize);
        down1Standard = setup("/monster/waspSwarm1", gp.tileSize, gp.tileSize);
        down2Standard = setup("/monster/waspSwarm2", gp.tileSize, gp.tileSize);
        left1Standard = setup("/monster/waspSwarm1", gp.tileSize, gp.tileSize);
        left2Standard = setup("/monster/waspSwarm2", gp.tileSize, gp.tileSize);
        right1Standard = setup("/monster/waspSwarm1", gp.tileSize, gp.tileSize);
        right2Standard = setup("/monster/waspSwarm2", gp.tileSize, gp.tileSize);
    }

    public void getImage() {
        up1 = up1Standard;
        up2 = up2Standard;
        down1 = down1Standard;
        down2 = down2Standard;
        left1 = left1Standard;
        left2 = left2Standard;
        right1 = right1Standard;
        right2 = right2Standard;
    }

    public void update() {
        super.update();
        gp.eHandler.checkEvent(this);

        if (this.worldX > 44 * gp.tileSize && this.worldX < 53 * gp.tileSize) {
            if (this.worldY > 7 * gp.tileSize && this.worldY < 18 * gp.tileSize) {
                this.insideGarage = true;
            }
        } else {
            this.insideGarage = false;
        }

        if (this.worldX > 16 * gp.tileSize && this.worldX < 30 * gp.tileSize) {
            if (this.worldY > 9 * gp.tileSize && this.worldY < 20 * gp.tileSize) {
                this.insideHouse = true;
            }
        } else {
            this.insideHouse = false;
        }
    }

    public void setAction(int goalCol, int goalRow) {
        if(onPath) {
            //Check if it stops chasing
            checkStopsChasingOrNot(gp.player, 15, 100);
            //Search the direction to go
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));

        } else {
            //Check if it starts chasing
            damageJustReceived = checkStartsChasingOrNot(gp.player, 5, 100, damageJustReceived);
            //Set direction
            if (checkEdgeOfMap(this)) {
                turnEntityAround(this);
            } else {
                getRandomDirection();
            }
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

        if (rand < 50) { //random chance of counterattacking player if hit it, or maybe it runs away
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
            dropItem(new OBJ_Coin(gp), this.worldX/gp.tileSize, this.worldY/gp.tileSize);
        }
        if (rand >= 50 && rand < 80) {
            dropItem(new OBJ_LightningBoltStress(gp), this.worldX/gp.tileSize, this.worldY/gp.tileSize);
        }
    }
}
