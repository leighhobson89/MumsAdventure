package entity;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_FrontDoorKey;
import object.OBJ_GrandmasCardigan;
import object.OBJ_Lavendar_Crocs;
import object.OBJ_Pills;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Player extends Entity {
    public boolean dizzyFlag;
    KeyHandler keyH;

    public final int PILLS_COUNT_DOWN_VALUE = 20;
    final int MAX_SPEED_UNDER_INFLUENCE = 4;

    public final int screenX;
    public final int screenY;
    public boolean speedBoost;
    static int interval;
    public int standCounter;
    public boolean attackCanceled;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);

        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2); //put player in center of screen
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8, 16,32,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage(gp.ui.colorOutfit);
        setItems();
    }

    public void countDownTimerForItemEffect(int value, String effect) {
        Timer timer = new Timer();
        if ("Pills".equals(effect)) {
            speed = 1;
        }
        int period;
        final int[] timeLeft = new int[1];
        period = 1000;
        interval = value;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeLeft[0] = (setInterval(effect, timer));
            }
        }, value, period);
    }

    private int setInterval(String effect, Timer timer) {
        if ("Pills".equals(effect)) {
            dizzyFlag = true;
            speed = (int) (Math.random() * MAX_SPEED_UNDER_INFLUENCE) + 1; //random speed every second while effect lasts
            if (Objects.equals(direction, "up")) {
                direction = "down";
            } else if (Objects.equals(direction, "down")) {
                direction = "up";
            } else if (Objects.equals(direction, "left")) {
                direction = "right";
            } else if (Objects.equals(direction, "right")) {
                direction = "left";
            }
        }
        if (interval == 1) {
            timer.cancel();
            if ("Pills".equals(effect)) {
                speed = 2;
                dizzyFlag = false;
            }
        }
        return --interval;
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 16;
        worldY = gp.tileSize * 12;
        defaultSpeed = 2;
        boostSpeed = 4;
        speed = defaultSpeed;
        direction = "up";

        //PLAYER STATUS
        level = 1;
        strength = 1; //more strength the more damage he gives
        dexterity = 1; //more dexterity, less damage receives
        exp = 0;
        nextLevelExp = 6;
        coin = 10;
        maxStress = 10;
        stressLevel = 0;
        currentWeapon = new OBJ_Lavendar_Crocs(gp);
        currentShield = new OBJ_GrandmasCardigan(gp);
        attack = getAttack(); // total attack value decided by strength and weapon
        defense = getDefense(); // total defense value decided by dexterity and shield
    }

    public void setItems() {
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_FrontDoorKey(gp));
        inventory.add(new OBJ_Pills(gp));
    }

    public int getAttack() {
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefense() {
        return defense = dexterity * currentShield.defenseValue;
    }

    public void getPlayerImage(String colorOutfit) {
        //MENU
        down1 = setup("/player/mum_down1_brown", gp.tileSize, gp.tileSize);
        down1_red = setup("/player/mum_down1_red", gp.tileSize, gp.tileSize);
        down1_purple = setup("/player/mum_down1_purple", gp.tileSize, gp.tileSize);

        if (gp.gameState == gp.playState) {
            up1 = setup("/player/mum_up1_" + colorOutfit, gp.tileSize, gp.tileSize); //16 x 16 images
            up2 = setup("/player/mum_up2_" + colorOutfit, gp.tileSize, gp.tileSize);
            down1 = setup("/player/mum_down1_" + colorOutfit, gp.tileSize, gp.tileSize);
            down2 = setup("/player/mum_down2_" + colorOutfit, gp.tileSize, gp.tileSize);
            left1 = setup("/player/mum_left1_" + colorOutfit, gp.tileSize, gp.tileSize);
            left2 = setup("/player/mum_left2_" + colorOutfit, gp.tileSize, gp.tileSize);
            right1 = setup("/player/mum_right1_" + colorOutfit, gp.tileSize, gp.tileSize);
            right2 = setup("/player/mum_right2_" + colorOutfit, gp.tileSize, gp.tileSize);
        }
    }

    public void getPlayerAttackImage(String colorOutfit) {

        attackUp1 = setup("/player/mum_attack_up1_" + colorOutfit, gp.tileSize, gp.tileSize * 2); //16 x 32 images
        attackUp2 = setup("/player/mum_attack_up2_" + colorOutfit, gp.tileSize, gp.tileSize * 2);
        attackDown1 = setup("/player/mum_attack_down1_" + colorOutfit, gp.tileSize, gp.tileSize * 2);
        attackDown2 = setup("/player/mum_attack_down2_" + colorOutfit, gp.tileSize, gp.tileSize * 2);
        attackLeft1 = setup("/player/mum_attack_left1_" + colorOutfit, gp.tileSize * 2, gp.tileSize); //32 x 16 images
        attackLeft2 = setup("/player/mum_attack_left2_" + colorOutfit, gp.tileSize * 2, gp.tileSize);
        attackRight1 = setup("/player/mum_attack_right1_" + colorOutfit, gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup("/player/mum_attack_right2_" + colorOutfit, gp.tileSize * 2, gp.tileSize);
    }

    public void update() {

        if (attacking) {
            //attacking stuff
            attacking();
        } else if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed) {
            if (keyH.upPressed && !dizzyFlag) {
                direction = "up";
            } else if (keyH.downPressed && !dizzyFlag) {
                direction = "down";
            } else if (keyH.leftPressed && !dizzyFlag) {
                direction = "left";
            } else if (keyH.rightPressed && !dizzyFlag) {
                direction = "right";
            } else if (keyH.upPressed) {
                direction = "down";
            } else if (keyH.downPressed) {
                direction = "up";
            } else if (keyH.leftPressed) {
                direction = "right";
            } else  {
                direction = "left";
            }

            if (speedBoost && !dizzyFlag) {
                speed = boostSpeed;
            } else if (dizzyFlag) {
                System.out.println("Dizzy!" + speed);
            } else {
                speed = defaultSpeed;
            }

            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
//            pickUpObject(objIndex);

            //CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            // CHECK EVENT
            gp.eHandler.checkEvent();

            //IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!collisionOn && !keyH.enterPressed) {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            if (keyH.enterPressed && !attackCanceled) {
                gp.playSFX(5);
                attacking = true;
                spriteCounter = 0;
            }

            attackCanceled = false;

            gp.keyH.enterPressed = false;

            spriteCounter++;
            if (spriteCounter > 12) { //walking speed of animation
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        else {
            standCounter++;
            if (standCounter == 30) {
                standCounter = 0;
                spriteNum = 1;
            }
        }
        //This needs to be outside of key if statement!
        if (invincible) {
            invincibleCounter++;
            if(invincibleCounter > 120) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void attacking() {
        spriteCounter++; //spriteCounter is a timer for how long to show each sprite within the current frame of animation

        if (spriteCounter <= 5) {
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {
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

            // Check monster collision with the updated worldX, worldY and solidArea
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            // After checking collision, restore the original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }
        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

//    public void pickUpObject(int i) {
//        if (i != 999) {
//
//        }
//    }

    public void interactNPC(int i) {

        if (gp.keyH.enterPressed) {
            if (i != 999) {
                attackCanceled = true;
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            } else {
                attacking = true;
                gp.playSFX(5);
            }
        }
    }

    public void contactMonster(int i) {
        if (i != 999) {
            if (!invincible) {

                int damage = gp.monster[i].attack - defense;
                if (damage > 0) {
                    gp.ui.addMessage("The " + gp.monster[i].name + " got you! Your stress increases by " + damage + "!");
                    stressLevel+=1;
                }
                if (damage <= 0) {
                    gp.ui.addMessage("The " + gp.monster[i].name + " got you but it can't stress you at your exp level!");
                    damage = 0;
                }
                invincible = true;
            }
        }
    }

    public void damageMonster(int i) {
        if (i != 999) {
            if (!gp.monster[i].invincible) {
                gp.playSFX(6);
                int damage = attack - gp.monster[i].defense;
                if (damage < 0) {
                    damage = 0;
                }
                gp.monster[i].stressLevel += damage;
                gp.ui.addMessage(gp.monster[i].name + " receives " + damage + " damage!");
                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction(); //makes monster run away if player hits it


                if (gp.monster[i].stressLevel >= gp.monster[i].monsterMaxStress) {
                    gp.monster[i].dying = true;
                    gp.playSFX(7);
                    gp.playSFX(13);
                    gp.ui.addMessage("You killed the " + gp.monster[i].name + ", phew!");
                    gp.ui.addMessage("Exp +" + gp.monster[i].exp);
                    exp+= gp.monster[i].exp;
                    checkLevelUp();
                }
            }
        }
    }

    public void checkLevelUp() {
        if (exp >= nextLevelExp) {
            level++;
            nextLevelExp = nextLevelExp*2;
            maxStress += 2;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();

            gp.playSFX(9);
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "You have levelled up!\nNow you are level " + level + "!\n\nYou feel more able to cope with stress!";
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
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

        if (invincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }
        g2.drawImage(image, tempScreenX, tempScreenY, null);

        //RESET alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        //DEBUG
//      g2.setFont(new Font("Arial", Font.BOLD, 26));
//      g2.setColor(Color.BLACK);
//      g2.drawString("Invincible: " + invincibleCounter, 10, 400); //- UNCOMMENT TO DISPLAY INVINCIBILITY COUNTER
//      g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height); // - UNCOMMENT TO DISPLAY COLLISION RECTANGLE ON PLAYER
    }
}
