package entity;

import main.GamePanel;
import main.UtilityTool;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

public class Entity {

    GamePanel gp;

    ArrayList<Integer> usedChummeringDialogues = new ArrayList<>();
    public BufferedImage dyingImage, up1, up2, down1, down2, left1, left2, right1, right2, down1_red, down1_purple;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(8, 16, 32, 32);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    String[] randomChummeringDialogues = new String[100];
    public Entity attacker;

    //STATE
    public int worldX, worldY;
    public String direction = "right";
    public int spriteNum = 1;
    public boolean collisionOn = false;
    public boolean invincible;
    public boolean newMonster;
    public boolean attacking;
    public boolean alive = true;
    public boolean dying;
    boolean hpBarOn = false;
    public int boneIndex;
    public boolean onPath = false;
    public boolean knockBack = false;
    public String knockBackDirection;

    //COUNTER
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;
    public int shotAvailableCounter = 0;
    int knockBackCounter = 0;

    //CHARACTER ATTRIBUTES
    public String name;
    public int speed;
    public int defaultSpeed;
    public int pillsSpeed;
    public boolean isWeapon;
    public boolean isArmour;
    public boolean pillsConsumableNow;
    public int maxStress;
    public int stressLevel;
    public int maxMana;
    public int mana;
    public int boneCount;
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
    public Projectile projectile;
    public int weedCount;
    public int timesPassedOut;
    public boolean dizzyFlag;
    public boolean speedBoost;
    public boolean firstTimePickUpBone;
    public int doorUnlockedCount;

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
    public int amount = 1;
    public int lightRadius; //for different objects affecting lighting

    //WORLD ATTRIBUTES

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
    public void setAction() {}
    public void damageReaction() {
        //overridden in specific monster class
    }
    public void speak() { //GENERAL CHARACTER SPEAK BEHAVIOUR
        Random rand = new Random();
        int dialogueCount = 0;

        for (int i = 0; i < randomChummeringDialogues.length; i++) {
            if (!(randomChummeringDialogues[i] == null)) {
                dialogueCount++;
            }
        }

        int randomValue = rand.nextInt(randomChummeringDialogues.length); //init only;

        while (randomChummeringDialogues[randomValue] == null || usedChummeringDialogues.contains(randomValue)) {
            randomValue = rand.nextInt(dialogueCount);

            if (usedChummeringDialogues.size() >= dialogueCount) {
                usedChummeringDialogues.clear(); // cycle round when used all dialogues
            }
        }

        gp.ui.currentDialogue = randomChummeringDialogues[randomValue];
        usedChummeringDialogues.add(randomValue);

        switch (gp.player.direction) {
            case "up" -> direction = "down";
            case "down" -> direction = "up";
            case "left" -> direction = "right";
            case "right" -> direction = "left";
        }
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

        if (knockBack) {
            checkCollision();

            if(collisionOn) {
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
            setAction();
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

        if (invincible) {
            invincibleCounter++;
            if(invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if(shotAvailableCounter < 30) { //bug fix for close encounter projectile duplication
            shotAvailableCounter++;
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
                gp.player.damageMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);
                gp.player.hitNPC(npcIndex);

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
            if (damage > 0) {
                gp.ui.addMessage("The " + this.name + " got you! Your stress increases by " + damage + "!");
            }
            if (damage <= 0) {
                gp.ui.addMessage("The " + this.name + " got you but it can't stress you at your exp level!");
                damage = 0;
            }
            System.out.println("this.name = " + this.name);
            if (Objects.equals(this.name, "Spider")) {
                gp.playSFX(6);
            } else if (Objects.equals(this.name, "WaspSwarm")) {
                gp.playSFX(26);
            }
            gp.player.stressLevel += damage;
            gp.player.pillsConsumableNow = gp.player.stressLevel >= gp.player.STRESS_LEVEL_NEEDED_TO_CONSUME_PILLS;
            gp.player.invincible = true;

            checkIfPassOutFromStress(); //gameover method - can pass out from stress twice, next time game over
        }
    }

    public void setKnockBack(Entity target, Entity attacker, int knockbackPower) {
        this.attacker = attacker;
        target.knockBackDirection = attacker.direction;
        target.speed += knockbackPower;
        target.knockBack = true;
    }

    public void checkIfPassOutFromStress() {
        if (gp.player.stressLevel >= gp.player.maxStress) {
            timesPassedOut++;
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "You pass out from the stress!";
            System.out.println("Times Passed Out:" + timesPassedOut);
            if (timesPassedOut <= 2) {
                gp.playSFX(25);
                gp.player.worldX = gp.tileSize*19;
                gp.player.worldY = gp.tileSize*17;
                gp.player.stressLevel = 0;
                gp.gameState = gp.sleepState;
            } else if (timesPassedOut > 2) { //change to be able to pass out this many times before passing out
                gp.gameState = gp.gameOverState;
                gp.stopMusic();
                gp.playSFX(24);
            }
        }
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
                        changeAlpha(g2, 0.4F);
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
            case "up": nextWorldY = user.getTopY() - user.speed; break;
            case "down": nextWorldY = user.getBottomY() + user.speed; break;
            case "left": nextWorldX = user.getLeftX() - user.speed; break;
            case "right": nextWorldX = user.getRightX() + user.speed; break;
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
}
