package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin;
import object.OBJ_LightningBoltStress;
import object.OBJ_ToolHutKey;

import java.util.Random;

public class MON_Spider extends Entity {

    final GamePanel gp;
    public MON_Spider(GamePanel gp) {
        super(gp);

        this.gp = gp;

        goesTransparentWhenHit = true;
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

        getInitialImage();
    }

    public void getInitialImage() {
        dyingImage = setup("/monster/spiderDead", gp.tileSize, gp.tileSize);
        up1Standard = setup("/monster/spider_Up1", gp.tileSize, gp.tileSize);
        up2Standard = setup("/monster/spider_Up2", gp.tileSize, gp.tileSize);
        down1Standard = setup("/monster/spider_Down1", gp.tileSize, gp.tileSize);
        down2Standard = setup("/monster/spider_Down2", gp.tileSize, gp.tileSize);
        left1Standard = setup("/monster/spider_Left1", gp.tileSize, gp.tileSize);
        left2Standard = setup("/monster/spider_Left2", gp.tileSize, gp.tileSize);
        right1Standard = setup("/monster/spider_Right1", gp.tileSize, gp.tileSize);
        right2Standard = setup("/monster/spider_Right2", gp.tileSize, gp.tileSize);
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

            //CODE TO MAKE MONSTER FIRE PROJECTILE WHEN ONPATH (COUNTERATTACK)
            //Check if it shoots a projectile
            //checkShootOrNot(200, 30);

        } else {
            //Check if it starts chasing
            checkStartsChasingOrNot(gp.player, 5, 100, damageJustReceived);
            //Set direction
            if (checkEdgeOfMap(this)) {
                turnEntityAround(this);
            } else {
                getRandomDirection();
            }
        }
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
        if (gp.player.spiderCount >= 0 && !gp.player.toolHutKeyDropped) {
            gp.player.toolHutKeyDropped = true;
            dropItem(new OBJ_ToolHutKey(gp), this.worldX/gp.tileSize, this.worldY/gp.tileSize);
        } else {
            if (rand < 50) {
                dropItem(new OBJ_Coin(gp), this.worldX/gp.tileSize, this.worldY/gp.tileSize);
            }
            if (rand >= 50 && rand < 80) {
                dropItem(new OBJ_LightningBoltStress(gp), this.worldX/gp.tileSize, this.worldY/gp.tileSize);
            }
            if (rand >= 80 && rand < 100 && !gp.player.toolHutKeyDropped) {
                dropItem(new OBJ_ToolHutKey(gp), this.worldX/gp.tileSize, this.worldY/gp.tileSize);
                gp.player.toolHutKeyDropped = true;
            }
        }
    }
}
