package entity;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_DogsBone_NotMagic;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class Player extends Entity {
    KeyHandler keyH;

    public final int PILLS_COUNT_DOWN_VALUE = 20;
    public final int STRESS_LEVEL_NEEDED_TO_CONSUME_PILLS = 4;
    public final int LIGHT_LEVEL_NEEDED_TO_CONSUME_PILLS = 10; //work it out later
    final int MAX_SPEED_UNDER_INFLUENCE = 4;

    public final int screenX;
    public final int screenY;
    public static int interval;
    public int standCounter;
    public boolean attackCanceled;
    public boolean lightUpdated;
    public Timer timer;

    public void createTimer() {
        timer = new Timer();
    }


    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);

        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2); //put player in center of screen
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8, 16,32,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage(gp.ui.colorOutfit);
        setItems();
    }

    public void countDownTimerForItemEffect(int value, String effect) {
        createTimer();
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
                timeLeft[0] = (setInterval(effect));
            }
        }, value, period);
    }

    private int setInterval(String effect) {
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
        worldX = gp.tileSize * 19;
        worldY = gp.tileSize * 17;
        defaultSpeed = 2;
        pillsSpeed = 4;
        speed = defaultSpeed;
        direction = "up";
        spiderCount = 1;
        weedCount = 0;
        dizzyFlag = false;
        speedBoost = false;
        timesPassedOut = 0;
        pillsConsumableNow = false;
        firstTimePickUpBone = true;

        //PLAYER STATUS
        level = 1;
        strength = 1; //more strength the more damage he gives
        dexterity = 1; //more dexterity, less damage receives
        exp = 0;
        nextLevelExp = 6;
        coin = 10;
        maxStress = 10;
        maxMana = 5; //max number of things to throw
        mana = maxMana;
        boneCount = 0;
        stressLevel = 0;
        currentWeapon = null;
        currentArmour = null;
//        projectile = new OBJ_PipsToy_Magic(gp);
        projectile = new OBJ_DogsBone_NotMagic(gp); //activate this for projectile that doesn't affect toys in ui when wanting to add bone that can be picked up after throwing or something : Change sound too if needed
        attack = 0;
        defense = 0;
    }

    public void setDefaultPositions() {
          worldX = gp.tileSize * 19;
          worldY = gp.tileSize * 17;
          direction = "up";
    }

    public void restoreStressAndMana() {
        stressLevel = 0;
        mana = maxMana;
        invincible = false;
    }

    public void setItems() {
        inventory.clear();
    }

    public int getAttack() {
        if (currentWeapon != null) {
            attackArea = currentWeapon.attackArea;
            return attack = strength * currentWeapon.attackValue;
        } else {
            return 0;
        }
    }

    public int getDefense() {
        if (currentArmour != null) {
            return defense = dexterity * currentArmour.defenseValue;
        } else {
            return 0;
        }
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

        if (currentWeapon != null) {
            if (currentWeapon.type == type_short_weapon) {
                attackUp1 = setup("/player/mum_attack_up1_" + colorOutfit, gp.tileSize, gp.tileSize * 2); //16 x 32 images
                attackUp2 = setup("/player/mum_attack_up2_" + colorOutfit, gp.tileSize, gp.tileSize * 2);
                attackDown1 = setup("/player/mum_attack_down1_" + colorOutfit, gp.tileSize, gp.tileSize * 2);
                attackDown2 = setup("/player/mum_attack_down2_" + colorOutfit, gp.tileSize, gp.tileSize * 2);
                attackLeft1 = setup("/player/mum_attack_left1_" + colorOutfit, gp.tileSize * 2, gp.tileSize); //32 x 16 images
                attackLeft2 = setup("/player/mum_attack_left2_" + colorOutfit, gp.tileSize * 2, gp.tileSize);
                attackRight1 = setup("/player/mum_attack_right1_" + colorOutfit, gp.tileSize * 2, gp.tileSize);
                attackRight2 = setup("/player/mum_attack_right2_" + colorOutfit, gp.tileSize * 2, gp.tileSize);
            }
            if (currentWeapon.type == type_long_weapon || currentWeapon.type == type_gardeningShovel) {
                attackUp1 = setup("/player/mum_spatula_up1_" + colorOutfit, gp.tileSize, gp.tileSize * 2); //16 x 32 images
                attackUp2 = setup("/player/mum_spatula_up2_" + colorOutfit, gp.tileSize, gp.tileSize * 2);
                attackDown1 = setup("/player/mum_spatula_down1_" + colorOutfit, gp.tileSize, gp.tileSize * 2);
                attackDown2 = setup("/player/mum_spatula_down2_" + colorOutfit, gp.tileSize, gp.tileSize * 2);
                attackLeft1 = setup("/player/mum_spatula_left1_" + colorOutfit, gp.tileSize * 2, gp.tileSize); //32 x 16 images
                attackLeft2 = setup("/player/mum_spatula_left2_" + colorOutfit, gp.tileSize * 2, gp.tileSize);
                attackRight1 = setup("/player/mum_spatula_right1_" + colorOutfit, gp.tileSize * 2, gp.tileSize);
                attackRight2 = setup("/player/mum_spatula_right2_" + colorOutfit, gp.tileSize * 2, gp.tileSize);
            }
        }
    }

    public void update() {
        if (attacking) {
            attacking();
        } else if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.spacePressed) {
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
            }

            if (speedBoost && !dizzyFlag) {
                speed = pillsSpeed;
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
            pickUpObject(objIndex);

            //CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            //CHECK INTERACTIVE TILE COLLISION
            gp.cChecker.checkEntity(this, gp.iTile);

            // CHECK EVENT
            gp.eHandler.checkEvent();

            //IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!collisionOn && !keyH.spacePressed) {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            if (keyH.spacePressed && !attackCanceled && currentWeapon != null) {
                if (currentWeapon.type == type_short_weapon) {
                    gp.playSFX(5);
                } else if (currentWeapon.type == type_long_weapon || currentWeapon.type == type_gardeningShovel) {
                    gp.playSFX(19);
                }

                attacking = true;
                spriteCounter = 0;
            }

            attackCanceled = false;

            gp.keyH.spacePressed = false;

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

        if (gp.keyH.throwKeyPressed && !projectile.alive && shotAvailableCounter == 30 && projectile.haveResource(this)) {
            //SET DEFAULT COORDINATES, DIRECTION AND USER
            projectile.set(worldX, worldY, direction, true, this);

            //SUBTRACT THE COST FROM RESOURCES (TOYS ETC)
            projectile.subtractResource(this);
            gp.player.inventory.remove(boneIndex);

            //CHECK VACANCY
            for (int i= 0; i < gp.projectile[1].length; i++) {
                if (gp.projectile[gp.currentMap][i] == null) {
                    gp.projectile[gp.currentMap][i] = projectile;
                    break;
                }
            }

            shotAvailableCounter = 0;
        }
        //This needs to be outside of main throw if statement!
        if (invincible) {
            invincibleCounter++;
            if(invincibleCounter > 120) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if(shotAvailableCounter < 30) { //bug fix for close encounter projectile duplication
            shotAvailableCounter++;
        }
        if (stressLevel < 0) {
            stressLevel = 0;
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
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            damageMonster(monsterIndex, attack, currentWeapon.knockBackPower);
            hitNPC(npcIndex);

            //INTERACTIVE TILE COLLISION CHECKER
            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
            damageInteractiveTile(iTileIndex);

            int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
            damageProjectile(projectileIndex);

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

    public void pickUpObject(int i) {
        if (i != 999) {

            if (gp.obj[gp.currentMap][i].type == type_pickupOnly) {
                //PICKUP ONLY ITEMS
                gp.obj[gp.currentMap][i].use(this);
                gp.obj[gp.currentMap][i] = null;

            }  //OBSTACLE
            else if (gp.obj[gp.currentMap][i].type == type_obstacle) { //for doors and other obstacles that need to use objects to pass them
                if (keyH.enterPressed) {
                    attackCanceled = true;
                    gp.obj[gp.currentMap][i].interact();
                }
            } else {
                //INVENTORY ITEMS
                String text;
                int selectSfx;

                if (canObtainItem(gp.obj[gp.currentMap][i])) {
                    if (gp.obj[gp.currentMap][i].isWeapon) {
                        if (currentWeapon == null) {
                            currentWeapon = gp.obj[gp.currentMap][i];
                            attack = getAttack();
                            getPlayerAttackImage(gp.ui.outfitChosen);
                        }
                    } else if (gp.obj[gp.currentMap][i].isArmour) {
                        if (currentArmour == null) {
                            currentArmour = gp.obj[gp.currentMap][i];
                            defense = getDefense();
                        }
                    } else if (gp.obj[gp.currentMap][i].isWeapon && gp.obj[gp.currentMap][i] == currentWeapon) {
                        currentWeapon = null;
                    } else if (gp.obj[gp.currentMap][i].isArmour && gp.obj[gp.currentMap][i] == currentArmour) {
                        currentArmour = null;
                    } else if (Objects.equals(gp.obj[gp.currentMap][i].name, "Pip's Bone")) {
                        if (firstTimePickUpBone) {
                            gp.gameState = gp.dialogueState;
                            gp.ui.currentDialogue = "I can throw the bone for Pip.\nI need to find an open area and press 'T'!";
                            firstTimePickUpBone= false;
                        }
                        boneCount = 1;
                        gp.player.boneIndex = gp.player.inventory.size()-1;
                    }
                    selectSfx = selectSfx(gp.obj[gp.currentMap][i].name);
                    gp.playSFX(selectSfx);
                    text = "Picked up " + gp.obj[gp.currentMap][i].name + "!";
                    gp.ui.addMessage(text);
                    gp.obj[gp.currentMap][i] = null;
                } else if (inventory.size() >= maxInventorySize) {
                    text = "You cannot carry any more!";
                    gp.ui.addMessage(text);
                }
            }
        }
    }

    private int selectSfx(String object) {
        return switch (object) {
            case "Key" -> 1;
            case "Tube of Pills" -> 2;
            case "Bin_Blue", "Lavender Crocs" -> 14;
            case "Acoustic Guitar" -> 17;
            case "Electric Guitar" -> 16;
            case "InsideDoor", "InsideDoorSideways", "BackGate", "BackGateSideways" -> 4;
            case "FrontDoor" -> 3;
            case "Old Cardigan" -> 18;
            case "Spatula" -> 19;
            default -> 10;
        };
    }

    public void interactNPC(int i) {

        if (gp.keyH.enterPressed) {
            if (i != 999) {
                gp.gameState = gp.dialogueState;
                gp.npc[gp.currentMap][i].speak();
                keyH.enterPressed = false;
            }
        }
        if (gp.keyH.spacePressed) {
            if (i != 999) {
                attacking();
                keyH.spacePressed = false;
            }
        }
    }

    public void contactMonster(int i) {
        if (i != 999) {
            if (!invincible && !gp.monster[gp.currentMap][i].dying) {

                int damage = gp.monster[gp.currentMap][i].attack - defense;
                if (damage > 0) {
                    gp.ui.addMessage("The " + gp.monster[gp.currentMap][i].name + " got you! Your stress increases by " + damage + "!");
                    stressLevel+=1;
                    gp.player.pillsConsumableNow = gp.player.stressLevel >= STRESS_LEVEL_NEEDED_TO_CONSUME_PILLS;
                }
                if (damage <= 0) {
                    gp.ui.addMessage("The " + gp.monster[gp.currentMap][i].name + " got you but it can't stress you at your exp level!");
                    damage = 0;
                }
                invincible = true;
                checkIfPassOutFromStress();
            }
        }
    }

    public void hitNPC(int i) {
        if (i != 999) {
            gp.gameState = gp.dialogueState;
            int rand = new Random().nextInt(5);
            if (gp.npc[gp.currentMap][i].name == "Dad") {
                switch (rand) {
                    case 0 -> gp.ui.currentDialogue = "Stop that right now!";
                    case 1 -> gp.ui.currentDialogue = "Hey what the bloody hell are\nya doin'??";
                    case 2 -> gp.ui.currentDialogue = "I'll let that go...ONCE!!";
                    case 3 -> gp.ui.currentDialogue = "What yer playin' at?!";
                    case 4 -> gp.ui.currentDialogue = "Why you hittin' me wi that?!";
                }
            } else if (Objects.equals(gp.npc[gp.currentMap][i].name, "Phoebe") || Objects.equals(gp.npc[gp.currentMap][i].name, "Pip")) {
                gp.ui.currentDialogue = "Yelp!";
            }

            attacking = false;
        }
    }

    public void damageMonster(int i, int attack, int knockBackPower) {
        if (i != 999) {
            if (!gp.monster[gp.currentMap][i].invincible) {
                gp.playSFX(6);

                if(knockBackPower > 0) {
                    knockBack(gp.monster[gp.currentMap][i], knockBackPower);
                }

                int damage = attack - gp.monster[gp.currentMap][i].defense;
                if (damage < 0) {
                    damage = 0;
                }
                gp.monster[gp.currentMap][i].stressLevel += damage;
                gp.ui.addMessage(gp.monster[gp.currentMap][i].name + " receives " + damage + " damage!");
                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction(); //makes monster run away if player hits it


                if (gp.monster[gp.currentMap][i].stressLevel >= gp.monster[gp.currentMap][i].monsterMaxStress) {
                    gp.monster[gp.currentMap][i].dying = true;
                    gp.playSFX(7);
                    gp.playSFX(13);
                    gp.ui.addMessage("You killed the " + gp.monster[gp.currentMap][i].name + ", phew!");
                    gp.ui.addMessage("Exp +" + gp.monster[gp.currentMap][i].exp);
                    exp+= gp.monster[gp.currentMap][i].exp;
                    checkLevelUp();
                }
            }
        }
    }

    public void knockBack(Entity entity, int knockbackPower) {
        entity.direction = direction;
        entity.speed += knockbackPower;
        entity.knockBack = true;
    }

    public void damageInteractiveTile(int i) {

        if (i != 999 && gp.iTile[gp.currentMap][i].destructible && gp.iTile[gp.currentMap][i].isCorrectItem(this) && !gp.iTile[gp.currentMap][i].invincible) {
            gp.iTile[gp.currentMap][i].stressLevel++;
            if (Objects.equals(gp.iTile[gp.currentMap][i].name, "Weed Tile")) {
                if (gp.iTile[gp.currentMap][i].stressLevel >= gp.iTile[gp.currentMap][i].maxStress) {
                    if (gp.player.weedCount > 1) {
                        gp.player.weedCount--;
                        gp.iTile[1][i+gp.aSetter.mapNumTotal] = gp.iTile[1][i+gp.aSetter.mapNumTotal].getDestroyedForm(); //remove weed from upstairs view - **only works if interacive tiles in same location on all maps**
                    } else if (gp.player.weedCount == 1) {
                        gp.npc[gp.currentMap][0].randomChummeringDialogues[44] = "Is that Christina ever gonna shift\nall her junk out u't shed or what?";
                        gp.player.weedCount--;
                    } else if (gp.player.weedCount == 0) {
                        //end of weeding mission
                    }
                }
                gp.iTile[gp.currentMap][i].playSfx();
                gp.iTile[gp.currentMap][i].invincible = true;

                //GENERATE PARTICLE
                generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);

                if (gp.iTile[gp.currentMap][i].stressLevel >= gp.iTile[gp.currentMap][i].maxStress) {
                    int rand = new Random().nextInt(100);
                    gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
                    if (rand > 80) {
                        int playerX = gp.player.worldX/gp.tileSize;
                        int playerY = gp.player.worldY/ gp.tileSize;
                        gp.player.spiderCount = gp.eHandler.spiderEvent(playerX+2, playerY+2, gp.dialogueState, gp.player.spiderCount, false, true);
                    }
                }
            }
        }
    }

    public void damageProjectile(int i) {
        if (i != 999) {
            Entity projectile = gp.projectile[gp.currentMap][i];
            projectile.alive = false;
            generateParticle(projectile, projectile);
        }
    }

    public void checkLevelUp() {
        if (exp >= nextLevelExp) {
            level++;
            nextLevelExp = nextLevelExp*2;
//            maxStress += 2; need to draw subdialogue bigger if use this
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();

            gp.playSFX(9);
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "You have levelled up!\nNow you are level " + level + "!\n\nYou feel more able to cope with stress!";
        }
    }

    public void selectItem() {
        int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);

        if (itemIndex < inventory.size()) {
            Entity selectedItem = inventory.get(itemIndex);

            if ((selectedItem.type == type_short_weapon || selectedItem.type == type_long_weapon || selectedItem.type == type_gardeningShovel) && selectedItem != currentWeapon) {
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage(gp.ui.outfitChosen);
                gp.playSFX(11);
            } else if ((selectedItem.type == type_short_weapon || selectedItem.type == type_long_weapon || selectedItem.type == type_gardeningShovel)) {
                currentWeapon = null;
                attack = getAttack();
                gp.playSFX(11);
            }
            if (selectedItem.type == type_armour && selectedItem != currentArmour) {
                currentArmour = selectedItem;
                defense = getDefense();
                gp.playSFX(11);
            } else if (selectedItem.type == type_armour) {
                currentArmour = null;
                defense = getDefense();
                gp.playSFX(11);
            }
            if (selectedItem.type == type_light) {
                if (currentLight == selectedItem) {
                    currentLight = null;
                } else {
                    currentLight = selectedItem;
                }
                lightUpdated = true;
            }
            if (selectedItem.type == type_consumable) {
                if(selectedItem.use(this)) {
                    if (boneCount == 1 && itemIndex < boneIndex) {
                        boneIndex--;
                    }
                    if(selectedItem.amount > 1) {
                        selectedItem.amount--;
                    } else {
                        inventory.remove(itemIndex);
                    }
                }
            }
        }
    }

    public int searchItemInInventory(String itemName) {
        int itemIndex = 999;

        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).name.equals(itemName)) {
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }

    public boolean canObtainItem(Entity item) {
        boolean canObtain = false;

        // CHECK IF ITEM IS STACKABLE
        if(!Objects.equals(item.name, "FrontDoorOpen")) {
            if (item.stackable) {
                int index = searchItemInInventory(item.name);

                if (index != 999) {
                    inventory.get(index).amount++;
                    canObtain = true;
                } else { // New item so need to check vacancy in inventory
                    if (inventory.size() != maxInventorySize) {
                        inventory.add(item);
                        canObtain = true;
                    }
                }
            } else { //Not stackable so check vacancy in inventory
                if (inventory.size() != maxInventorySize) {
                    inventory.add(item);
                    canObtain = true;
                }
            }
        }
        return canObtain;
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

    }
}
