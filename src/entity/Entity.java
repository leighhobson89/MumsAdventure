package entity;

import main.GamePanel;
import main.MissionStates;
import main.UtilityTool;
import object.OBJ_FortyQuidForAndrea;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Entity {

    GamePanel gp;

    public BufferedImage dyingImage, up1, up2, down1, down2, left1, left2, right1, right2, down1_red, down1_purple, dadDown1, phoebeRight2, phoebeLeft1;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2, guardUp, guardDown, guardLeft, guardRight;
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(8, 16, 32, 32);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    public String[][] dialogueText = new String[100][20];
    public Entity attacker;

    //STATE
    public int worldX, worldY;
    public int phoneNormalWorldX = 816;
    public String direction = "right";
    public int spriteNum = 1;
    public int dialogueSet = 61;
    public int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible;
    public boolean newMonster;
    public boolean attacking;
    public boolean alive = true;
    public boolean dying;
    boolean hpBarOn = false;
    public int boneIndex;
    public int chickenIndex;
    public int thrownChickenCount = 0;
    public String itemToThrow = "";
    public boolean onPath = false;
    public boolean knockBack = false;
    public String knockBackDirection;
    public boolean guarding = false;
    public boolean transparent = false;
    public boolean offBalance = false;
    public Entity loot;
    public boolean opened = false;
    public int missionState = MissionStates.BETWEEN_MISSIONS;
    public List<Integer> missionList = new ArrayList<>();
    public boolean readyForNextPhoneMission;
    public boolean nextMissionIsPhoneMission;
    public int missionToSet = 1;
    public int missionSubstate = 0;
    public Random rand = new Random();
    public boolean repeatSfx = true;
    public boolean andreaOnMap;
    public boolean firstTimeChattingToAndrea = true;
    public int andreaTempGoalCol;
    public int andreaTempGoalRow;
    public boolean goesTransparentWhenHit;
    public boolean goesTransparentWhenStoodOn;
    public boolean insideShed;
    public boolean isUpdateable;
    public boolean pipChickenEaten;
    public boolean phoebeChickenEaten;
    public boolean startCounterPhoebeEatingChicken;
    public boolean startCounterPipEatingChicken;
    public boolean phoneRinging;
    public boolean showerAlreadyRan;
    public boolean showerCounterStart;
    public int waterTileCount;
    public int blockWoodState; //for upstairs correct image
    public int backGateState;
    public int bookHutState = 0;

    //COUNTER
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;
    public int shotAvailableCounter = 0;
    int knockBackCounter = 0;
    public int guardCounter = 0;
    public int offBalanceCounter = 0;
    public int missionEndingCounter = 0;
    public int randomCounter; //used for random time in between missions before phone rings
    public int buzzCounter = 0;
    public int phoebeEatingChickenCounter;
    public int pipEatingChickenCounter;
    public int showerCounter = 0;

    //CHARACTER ATTRIBUTES
    public String name;
    public String displayName;
    public int speed;
    public int defaultSpeed;
    public int pillsSpeed;
    public boolean isWeapon;
    public boolean isArmour;
    public boolean isProjectile;
    public boolean pillsConsumableNow;
    public int maxStress;
    public int stressLevel;
    public int maxMana;
    public int mana;
    public int boneCount;
    public int choppedChickenCount;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public int motion1_duration;
    public int motion2_duration;
    public Entity currentWeapon;
    public Entity currentArmour;
    public Entity currentLight;
    public Entity currentProjectile;
    public Projectile projectile;
    public int weedCount;
    public boolean setShovelFlag;
    public int timesPassedOut;
    public boolean dizzyFlag;
    public boolean speedBoost;
    public boolean firstTimePickUpBone;
    public int doorUnlockedCount;
    public boolean savedWithAWeaponEquipped;
    public boolean savedWithAnArmourEquipped;
    public boolean savedWithAProjectileEquipped;
    public boolean haveBoneResource;
    public boolean haveChoppedChickenResource;

    //ITEM ATTRIBUTES
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    public int value;
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useCost;
    public int price;
    public int knockBackPower = 0;
    public boolean stackable;
    public boolean isSaleable;
    public int amount = 1;
    public int lightRadius; //for different objects affecting lighting

    //WORLD ATTRIBUTES
    public static final int MAX_WORLD_X_COORDINATE = 69;
    public static final int MAX_WORLD_Y_COORDINATE = 24;
    public static final int MIN_WORLD_X_COORDINATE = 0;
    public static final int MIN_WORLD_Y_COORDINATE = 0;

    //MONSTER ATTRIBUTES
    public int monsterMaxStress;
    public int spiderCount;
    public int chaseSpeed;
    public boolean damageJustReceived;

    //TYPE
    public int type;
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_short_weapon = 3;
    public final int type_long_weapon = 4;
    public final int type_armour = 5;
    public final int type_consumable = 6;
    public final int type_pickupOnly = 7;
    public final int type_gardeningShovel = 8;
    public final int type_obstacle = 9;
    public final int type_light = 10;
    public final int type_axe = 11;
    public final int type_mop = 12;
    public final int type_switchable_interactive_tile = 13;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }
    public int getLeftX() {
        return worldX + solidArea.x;
    }
    public int getRightX() {
        return worldX + solidArea.x + solidArea.width;
    }
    public int getTopY() {
        return worldY + solidArea.y;
    }
    public int getBottomY() {
        return worldY + solidArea.y + solidArea.height;
    }
    public int getCol() {
        return (worldX + solidArea.x)/gp.tileSize;
    }
    public int getRow() {
        return (worldY + solidArea.y)/gp.tileSize;
    }

    public int getXdistance(Entity target) {
        int xDistance = Math.abs(worldX - target.worldX);
        return xDistance;
    }
    public int getYdistance(Entity target) {
        int yDistance = Math.abs(worldY - target.worldY);
        return yDistance;
    }
    public int getTileDistance(Entity target) {
        int tileDistance = (getXdistance(target) + getYdistance(target)) /gp.tileSize;
        return tileDistance;
    }
    public int getGoalCol(Entity target) {
        int goalCol = (target.worldX + target.solidArea.x)/gp.tileSize;
        return goalCol;
    }
    public int getGoalRow(Entity target) {
        int goalRow = (target.worldY + target.solidArea.y)/gp.tileSize;
        return goalRow;
    }
    public void resetCounter() {
        spriteCounter = 0;
        actionLockCounter = 0;
        invincibleCounter = 0;
        dyingCounter = 0;
        hpBarCounter = 0;
        shotAvailableCounter = 0;
        knockBackCounter = 0;
        guardCounter = 0;
        offBalanceCounter = 0;
        missionEndingCounter = 0;
        randomCounter = setRandomCounter();
        pipEatingChickenCounter = 0;
        phoebeEatingChickenCounter = 0;
        showerCounter = 0;
    }
    public void setLoot(Entity loot) {}
    public void setAction(int goalCol, int goalRow) {
        //overridden in specific entity class
    }
    public void damageReaction() {
        //overridden in specific monster class
    }
    public void speak() {
        //overridden in specific entity class
    }
    public void makeObjectTransparentAndTempRemoveCollision(Entity entity) {
        //overridden in specific entity class
    }

    public void facePlayer() {
        switch (gp.player.direction) {
            case "up" -> direction = "down";
            case "down" -> direction = "up";
            case "left" -> direction = "right";
            case "right" -> direction = "left";
        }
    }
    public void startDialogue(Entity entity, int setNum) {
        gp.gameState = gp.dialogueState;
        gp.ui.npc = entity;
        dialogueSet = setNum;
    }

    //DEBUG - CAN CHANGE THIS TO SPEED UP TELEPHONE RINGING
    public int setRandomCounter() {
        if (this.gp.player != null) {
            return gp.player.rand.nextInt(300) + 400; //3800 + 1200 for normal game
        }
        return 0;
    }

    public int chooseRandomDialogueFromSet(String entityAffected, String action) {
        Random random = new Random();
        int bound;
        int randSet = 0;

        if (Objects.equals(action, "HitWithWeaponOrProjectile")) {
            switch (entityAffected) {
                case "Dad":
                    bound = 4;
                    randSet = random.nextInt(bound) + 4;
                    break;
            }
        } else if (Objects.equals(action, "NormalChat")) {
            switch (entityAffected) {
                case "Dad":
                    bound = 60; //increase if more random dialogues added
                    randSet = random.nextInt(bound);
                    break;
            }
        }
        return randSet;
    }

    public void interact() {
    }

    public boolean use(Entity entity) {
        return false;
    }
    public void checkDrop() {

    }

    public void dropItem(Entity droppedItem) {
        for (int i = 0; i < gp.obj[1].length; i++) {
            if (gp.obj[gp.currentMap][i] == null) {
                gp.obj[gp.currentMap][i] = droppedItem;
                if (Objects.equals(droppedItem.name, "StressBolt")) {
                    gp.obj[gp.currentMap][i].worldX = worldX + 20; //Get the dead monster's worldX
                    gp.obj[gp.currentMap][i].worldY = worldY + 20;
                } else {
                    gp.obj[gp.currentMap][i].worldX = worldX;
                    gp.obj[gp.currentMap][i].worldY = worldY;
                }
                break;
            }
        }
    }

    public Color getParticleColor() {
        Color color = null;
        return color;
    }
    public int getParticleSize() {
        int size = 0; //6 pixels
        return size;
    }
    public int getParticleSpeed() {
        int speed = 0;
        return speed;
    }
    public int getParticleMaxLife() {
        int maxLife = 0;
        return maxLife;
    }

    public void generateParticle(Entity generator, Entity target) {
        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();

        Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -2, -1);
        Particle p2 = new Particle(gp, target, color, size, speed, maxLife, 2, -1);
        Particle p3 = new Particle(gp, target, color, size, speed, maxLife, -2, 1);
        Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1);

        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);
    }

    public void checkCollision() {
        collisionOn = false;
        boolean tileState = gp.cChecker.checkTile(this);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.iTile);
        if (newMonster && tileState) {
            collisionOn = false;
        }
        if (newMonster && !tileState) {
            newMonster = false;
            collisionOn = true;
        }
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if(this.type == type_monster && contactPlayer) {
            damagePlayer(attack);
        }
    }

    public void update() {

        int iTileIndex;
        if (gp.player.bookHutState == 1) {
            iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
            gp.player.switchInteractiveTile(iTileIndex);
        }

        if (gp.player.missionState == MissionStates.CHOP_CHICKEN_FOR_DOGS && gp.player.missionSubstate >= MissionStates.SELL_DADS_ELECTRIC_GUITAR_TO_THE_MERCHANT) {
            gp.misStat.endMissionTasks(MissionStates.CHOP_CHICKEN_FOR_DOGS, false);
            gp.ui.addMessage("That's the dogs fed, nice one!");
            gp.player.phoebeEatingChickenCounter = 0;
            gp.player.startCounterPhoebeEatingChicken = false;
            gp.player.pipEatingChickenCounter = 0;
            gp.player.startCounterPipEatingChicken = false;
        }

        if (gp.player.startCounterPhoebeEatingChicken) {
            gp.player.phoebeEatingChickenCounter++;
            if (gp.player.phoebeEatingChickenCounter >= 2000) {
                for (int i = 0; i < gp.obj[1].length; i++) {
                    if (gp.obj[gp.currentMap][i] != null) {
                        if (Objects.equals(gp.obj[gp.currentMap][i].name, "Chopped Chicken Phoebe")) {
                            gp.obj[gp.currentMap][i] = null;
                            gp.player.phoebeChickenEaten = true;
                            gp.player.missionSubstate++;
                        }
                    }
                }
            }
        }
        if (gp.player.startCounterPipEatingChicken) {
            gp.player.pipEatingChickenCounter++;
            if (gp.player.pipEatingChickenCounter >= 2000) {
                for (int i = 0; i < gp.obj[1].length; i++) {
                    if (gp.obj[gp.currentMap][i] != null) {
                        if (Objects.equals(gp.obj[gp.currentMap][i].name, "Chopped Chicken Pip")) {
                            gp.obj[gp.currentMap][i] = null;
                            gp.player.pipChickenEaten = true;
                            gp.player.missionSubstate++;
                        }
                    }
                }
            }
        }

        if (!isUpdateable) {
            if (knockBack) {
                checkCollision();

                if (collisionOn) {
                    knockBackCounter = 0;
                    knockBack = false;
                    speed = defaultSpeed;
                } else {
                    switch (knockBackDirection) {
                        case "up" -> worldY -= speed;
                        case "down" -> worldY += speed;
                        case "left" -> worldX -= speed;
                        case "right" -> worldX += speed;
                    }
                }

                knockBackCounter++;
                if (knockBackCounter == 10) {
                    knockBackCounter = 0;
                    knockBack = false;
                    speed = defaultSpeed;
                }
            } else if (attacking) {
                attacking();
            } else {
                setAction(andreaTempGoalCol, andreaTempGoalRow);
                checkCollision();

                //IF COLLISION IS FALSE, ENTITY CAN MOVE
                if (!collisionOn) {
                    switch (direction) {
                        case "up" -> worldY -= speed;
                        case "down" -> worldY += speed;
                        case "left" -> worldX -= speed;
                        case "right" -> worldX += speed;
                    }
                }
                spriteCounter++;
                if (spriteCounter > 24) { //walking speed of animation
                    if (spriteNum == 1) {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            }

            if (shotAvailableCounter < 30) { //bug fix for close encounter projectile duplication
                shotAvailableCounter++;
            }

            if (offBalance) {
                offBalanceCounter++;
                if (offBalanceCounter > 60) {
                    offBalance = false;
                    offBalanceCounter = 0;
                }
            }
        }
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

        public void checkAttackOrNot(int rate, int straight, int horizontal) { //for monsters that attack with weapon
            boolean targetInRange = false;
            int xDis = getXdistance(gp.player);
            int yDis = getYdistance(gp.player);

            switch (direction) {
                case "up":
                    if (gp.player.worldY < worldY && yDis < straight && xDis < horizontal) {
                        targetInRange = true;
                    }
                    break;
                case "down":
                    if (gp.player.worldY > worldY && yDis < straight && xDis < horizontal) {
                        targetInRange = true;
                    }
                    break;
                case "left":
                    if (gp.player.worldX < worldX && xDis < straight && yDis < horizontal) {
                        targetInRange = true;
                    }
                    break;
                case "right":
                    if (gp.player.worldX > worldX && xDis < straight && yDis < horizontal) {
                        targetInRange = true;
                    }
                    break;
            }

            if (targetInRange) {
                // Check if it initiates an attack
                int i = new Random().nextInt(rate);
                if (i == 0) {
                    attacking = true;
                    spriteNum = 1;
                    spriteCounter = 0;
                    shotAvailableCounter = 0;
                }
            }
        }

    public void checkShootOrNot(int rate, int shotInterval) {
                    int i = new Random().nextInt(rate) + 1;
            if (i == 0 && !projectile.alive && shotAvailableCounter == shotInterval) {
                projectile.set(worldX, worldY, direction, true, this);

                //CHECK VACANCY
                for (int j = 0; j < gp.projectile[1].length; j++) {
                    if (gp.projectile[gp.currentMap][j] == null) {
                        gp.projectile[gp.currentMap][j] = projectile;
                        break;
                    }
                }
                shotAvailableCounter = 0;
            }
    }

    public boolean checkStartsChasingOrNot(Entity target, int distance, int rate, boolean damagedJustReceived) {
        if (getTileDistance(target) < distance) {
            int i = new Random().nextInt(rate);
            if (i == 0 && damagedJustReceived) {
                speed = chaseSpeed;
                onPath = true;
            }
            damagedJustReceived = false;
        }
        return damagedJustReceived;
    }

    public void checkStopsChasingOrNot(Entity target, int distance, int rate) {
        if (getTileDistance(target) > distance) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                speed = defaultSpeed;
                onPath = false;
            }
        }
    }

    public void getRandomDirection() {
        actionLockCounter++;

        if (actionLockCounter == 60) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; //pick up a number from 1 to 100

            if (i <= 25) {direction = "up";}
            if (i > 25 && i <= 50) {direction = "down";}
            if (i > 50 && i <= 75) {direction = "left";}
            if (i > 75) {direction = "right";}
            actionLockCounter = 0;
        }
    }

    public String getOppositeDirection(String direction) {
        String oppositeDirection = "";

        switch (direction) {
            case "up":
                oppositeDirection = "down";
                break;
            case "down":
                oppositeDirection = "up";
                break;
            case "left":
                oppositeDirection = "right";
                break;
            case "right":
                oppositeDirection = "left";
                break;
        }
        return oppositeDirection;
    }

    public void attacking() {
        spriteCounter++; //spriteCounter is a timer for how long to show each sprite within the current frame of animation

        if (spriteCounter <= motion1_duration) {
            spriteNum = 1;
        }
        if (spriteCounter > motion1_duration && spriteCounter <= motion2_duration) {
            spriteNum = 2;

            //Save the current worldX, worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //Adjust the player's worldX/Y for the attackArea
            switch(direction) {
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }

            // attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            if (type == type_monster) {
                if (gp.cChecker.checkPlayer(this)) {
                    damagePlayer(attack);
                }
            } else { //Player
                // Check monster collision with the updated worldX, worldY and solidArea
                int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
                int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
                int objIndex = gp.cChecker.checkEntity(this, gp.obj);
                gp.player.damageMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);
                gp.player.hitNPC(npcIndex);
                gp.player.damageObject(objIndex);

                //INTERACTIVE TILE COLLISION CHECKER
                int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
                gp.player.damageInteractiveTile(iTileIndex);

                int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
                gp.player.damageProjectile(projectileIndex);
            }

            // After checking collision, restore the original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }
        if (spriteCounter > motion2_duration) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void damagePlayer(int attack) {
        if (Objects.equals(this.name, "Pip") || Objects.equals(this.name, "Phoebe")) {
            collisionOn = false;
        } else if (!gp.player.invincible && !this.dying) {

            int damage = attack - gp.player.defense;

            //Setup guard direction
            String canGuardDirection = getOppositeDirection(direction);

            if (gp.player.guarding && gp.player.direction.equals(canGuardDirection)) { //if successful guard block
                //PARRY
                if (gp.player.guardCounter < 10) { //this number can be up to 60 - easier to parry the higher the number
                    damage = 0;
//                    gp.playSFX(PARRY SFX); //add sound fx for parry
                    setKnockBack(this, gp.player, knockBackPower);
                    offBalance = true;
                    spriteCounter -= 60;
                } else {
                    //NORMAL GUARD
                    damage /= 3; //damage reduced to a third
//                  gp.playSFX(GUARDING/BLOCKING SFX); //add sound fx for guard/block
                }
            } else { //not guarding
                if (damage > 0) {
                    gp.ui.addMessage("The " + this.name + " got you! Your stress increases by " + damage + "!");
                }
                if (damage <= 0) {
                    gp.ui.addMessage("The " + this.name + " got you but it can barely stress you at this level");
                    damage = 1;
                    gp.ui.addMessage("Stress increased by " + damage + "!");
                }
                if (Objects.equals(this.name, "Spider")) {
                    gp.playSFX(6);
                } else if (Objects.equals(this.name, "WaspSwarm")) {
                    gp.playSFX(26);
                }
            }
            if (damage != 0) {
                gp.player.transparent = true;
                setKnockBack(gp.player, this, knockBackPower);
            }

            gp.player.stressLevel += damage;
            gp.player.pillsConsumableNow = gp.player.stressLevel >= gp.player.STRESS_LEVEL_NEEDED_TO_CONSUME_PILLS;
            gp.player.invincible = true;

            gp.player.checkIfPassOutFromStress(); //gameover method - can pass out from stress twice, next time game over
        }
    }

    public void setKnockBack(Entity target, Entity attacker, int knockbackPower) {
        this.attacker = attacker;
        target.knockBackDirection = attacker.direction;
        target.speed += knockbackPower;
        target.knockBack = true;
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
                && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            int tempScreenX = screenX;
            int tempScreenY = screenY;

            switch (direction) {
                case "up":
                    if (!attacking) {
                        if (spriteNum == 1) { image = up1;}
                        if (spriteNum == 2) { image = up2;}
                    }
                    if (attacking) {
                        tempScreenY = screenY - gp.tileSize;
                        if (spriteNum == 1) { image = attackUp1;}
                        if (spriteNum == 2) { image = attackUp2;}
                    }
                    break;
                case "down":
                    if (!attacking) {
                        if (spriteNum == 1) { image = down1;}
                        if (spriteNum == 2) { image = down2;}
                    }
                    if (attacking) {
                        if (spriteNum == 1) { image = attackDown1;}
                        if (spriteNum == 2) { image = attackDown2;}
                    }
                    break;
                case "left":
                    if (!attacking) {
                        if (spriteNum == 1) { image = left1;}
                        if (spriteNum == 2) { image = left2;}
                    }
                    if (attacking) {
                        tempScreenX = screenX - gp.tileSize;
                        if (spriteNum == 1) { image = attackLeft1;}
                        if (spriteNum == 2) { image = attackLeft2;}
                    }
                    break;
                case "right":
                    if (!attacking) {
                        if (spriteNum == 1) { image = right1;}
                        if (spriteNum == 2) { image = right2;}
                    }
                    if (attacking) {
                        if (spriteNum == 1) { image = attackRight1;}
                        if (spriteNum == 2) { image = attackRight2;}
                    }
                    break;
            }

            //Monster HP bar
            if(type == type_monster && hpBarOn) {

                double oneScale = (double)gp.tileSize/monsterMaxStress;
                double hpBarValue = gp.tileSize - (oneScale * stressLevel);

                g2.setColor(new Color(35,35,35));
                g2.fillRect(screenX-2, screenY-17, gp.tileSize+4, 14);
                g2.setColor(new Color(255,0,30));
                g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);

                hpBarCounter++;

                if (hpBarCounter < 300) {
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }

                    if (invincible) {
                        hpBarOn = true;
                        hpBarCounter = 0;
                        if (this.goesTransparentWhenHit) {
                            changeAlpha(g2, 0.4F);
                        }
                    }
                    if (dying) {
                        image = dyingImage;
                        dyingAnimation(g2);
                    }

            g2.drawImage(image, tempScreenX, tempScreenY, null);

            changeAlpha(g2, 1F);
        }
    }

    public void dyingAnimation(Graphics2D g2) {
        dyingCounter++;

        int i = 10; //speed of flashing

        if (dyingCounter <= i) {changeAlpha(g2, 0f);}
        if (dyingCounter > i && dyingCounter <= i*2) {changeAlpha(g2, 1f);}
        if (dyingCounter > i*2 && dyingCounter <= i*3) {changeAlpha(g2, 0f);}
        if (dyingCounter > i*3 && dyingCounter <= i*4) {changeAlpha(g2, 1f);}
        if (dyingCounter > i*4 && dyingCounter <= i*5) {changeAlpha(g2, 0f);}
        if (dyingCounter > i*5 && dyingCounter <= i*6) {changeAlpha(g2, 1f);}
        if (dyingCounter > i*6 && dyingCounter <= i*7) {changeAlpha(g2, 0f);}
        if (dyingCounter > i*7 && dyingCounter <= i*8) {changeAlpha(g2, 1f);}
        if(dyingCounter > i*8) {
            dying = false;
            alive = false;
        }
    }
    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = uTool.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void searchPath(int goalCol, int goalRow) {

        int startCol = (worldX + solidArea.x)/gp.tileSize;
        int startRow = (worldY + solidArea.y)/gp.tileSize;

        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow, this);

        if (gp.pFinder.search()) {
            //Next worldX & worldY
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

            // Entity's solidArea position
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "up";
            } else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "down";
            } else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
                //left or right
                if (enLeftX > nextX) {
                    direction = "left";
                }
                if (enLeftX < nextX) {
                    direction = "right";
                }
            }
            else if (enTopY > nextY && enLeftX > nextX) {
                // up or left
                direction = "up";
                checkCollision();
                if (collisionOn) {
                    direction = "left";
                }
            }
            else if (enTopY > nextY && enLeftX < nextX) {
                // up or right
                direction = "up";
                checkCollision();
                if (collisionOn) {
                    direction = "right";
                }
            }
            else if (enTopY < nextY && enLeftX > nextX) {
                // down or left
                direction = "down";
                checkCollision();
                if (collisionOn) {
                    direction = "left";
                }
            }
            else if (enTopY < nextY && enLeftX < nextX) {
                // down or left
                direction = "down";
                checkCollision();
                if (collisionOn) {
                    direction = "right";
                }
            }

            //If reaches the goal, stop the search
            int nextCol = gp.pFinder.pathList.get(0).col;
            int nextRow = gp.pFinder.pathList.get(0).row;

            if (nextCol == goalCol && nextRow == goalRow) {
                onPath = false;
            }
        }
    }

    public int getDetected(Entity user, Entity target[][], String targetName) { //detect object next to user ie opening doors
        int index = 999;

        //Check the surrounding object
        int nextWorldX = user.getLeftX();
        int nextWorldY = user.getTopY();

        switch (user.direction) {
            case "up": nextWorldY = user.getTopY() - gp.player.speed; break;
            case "down": nextWorldY = user.getBottomY() + gp.player.speed; break;
            case "left": nextWorldX = user.getLeftX() - gp.player.speed; break;
            case "right": nextWorldX = user.getRightX() + gp.player.speed; break;
        }

        int col = nextWorldX/gp.tileSize;
        int row = nextWorldY/gp.tileSize;

        for (int i = 0; i < target[1].length; i++) {
            if (target[gp.currentMap][i] != null) {
                if (target[gp.currentMap][i].getCol() == col &&
                target[gp.currentMap][i].getRow() == row &&
                target[gp.currentMap][i].name.equals(targetName)) {
                index = i;
                break;
                }
            }
        }
        return index;
    }

    public void turnEntityAround(Entity entity) {
        //IF ENTITY REACHES EDGE OF WORLD, TURN THEM AROUND BEFORE ARRAY OUT OF BOUNDS ERROR
        entity.direction = entity.getOppositeDirection(entity.direction);
    }

    public boolean checkEdgeOfMap(Entity entity) {
        int worldX = entity.worldX/gp.tileSize;
        int worldY = entity.worldY/gp.tileSize;

        if ((worldX == MAX_WORLD_X_COORDINATE && entity.direction.equals("right"))
                || (worldY == MAX_WORLD_Y_COORDINATE && entity.direction.equals("down"))
                || (worldX == MIN_WORLD_X_COORDINATE && entity.direction.equals("left"))
                || (worldY == MIN_WORLD_Y_COORDINATE && entity.direction.equals("up"))) {
            return true;
        }
        return false;
    }

    public void setNewMissionState(boolean readyForNextPhoneMission, int missionState, int missionToSet) {
        if (readyForNextPhoneMission && missionState == MissionStates.BETWEEN_MISSIONS) {
            switch (missionToSet) {
                case 1 -> gp.player.missionState = MissionStates.WEEDING_MISSION;
                case 2 -> gp.player.missionState = MissionStates.SELL_DADS_ELECTRIC_GUITAR_TO_THE_MERCHANT;
                case 3 -> {
                    gp.player.missionState = MissionStates.HELP_ANDREA_OUT;
                    gp.player.inventory.add(new OBJ_FortyQuidForAndrea(gp)); //add mission object
                    gp.player.andreaOnMap = true;
                }
                case 4 -> gp.player.missionState = MissionStates.CHOP_CHICKEN_FOR_DOGS;
            }
            gp.player.readyForNextPhoneMission = false;
        }
    }

    public void AndreaLeaveSetup(Entity npc) {
        npc.speed = 1;
        npc.solidArea = new Rectangle(8, 16,32,32);
        if (Objects.equals(npc.name, "Andrea")) {
            npc.onPath = true;
        }
        npc.setAction(gp.player.andreaTempGoalCol, gp.player.andreaTempGoalRow);
    }

    public int checkIfObjectOnMap(String object) {
        int count = 0;
        for (int i = 0; i < gp.obj[1].length; i++) {
            if (gp.obj[gp.currentMap][i] != null) {
                if (Objects.equals(gp.obj[gp.currentMap][i].name, object)) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean checkIfPlayerHasMissionItem(ArrayList<Entity> inventory, int missionState, int missionSubState) {
        boolean playerHasRequiredItem = false;
        for (int i = 0; i < inventory.size(); i++) {
            switch(missionState) {
                case MissionStates.CHOP_CHICKEN_FOR_DOGS:
                    if (Objects.equals(inventory.get(i).name, "Chicken")) {
                        playerHasRequiredItem = true;
                    }
                    break;
                case MissionStates.MAGIC_BOOK_QUIZ:
                    if (Objects.equals(inventory.get(i).name, "BookHutKey") && missionSubState == 0) {
                        playerHasRequiredItem = true;
                    }
                    break;
            }

        }
        return playerHasRequiredItem;
    }
}
