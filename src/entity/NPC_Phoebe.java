package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Objects;

public class NPC_Phoebe extends Entity {

    public final int TILE_DISTANCE_TO_BE_ON_PATH_TO_CHICKEN = 40;

    public NPC_Phoebe(GamePanel gp) {
        super(gp);

        name = "Phoebe";
        direction = "up";
        defaultSpeed = 2;
        speed = defaultSpeed;
        type = type_npc;
        goesTransparentWhenHit = true;

        getImage();
        setDialogue();

        solidArea = new Rectangle(8, 16,32,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getInitialImage();
    }

    public void getInitialImage() {

        up1Standard = setup("/NPC/phoebe_up1", gp.tileSize, gp.tileSize);
        up2Standard = setup("/NPC/phoebe_up2", gp.tileSize, gp.tileSize);
        down1Standard = setup("/NPC/phoebe_down1", gp.tileSize, gp.tileSize);
        down2Standard = setup("/NPC/phoebe_down2", gp.tileSize, gp.tileSize);
        left1Standard = setup("/NPC/phoebe_left1", gp.tileSize, gp.tileSize);
        left2Standard = setup("/NPC/phoebe_left2", gp.tileSize, gp.tileSize);
        right1Standard = setup("/NPC/phoebe_right1", gp.tileSize, gp.tileSize);
        right2Standard = setup("/NPC/phoebe_right2", gp.tileSize, gp.tileSize);

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

    //        //comment for not throw bone
//        projectile = new OBJ_PipsBone(gp);
//        //end bone throwing

    //        //comment for not throw bone
//        int i = new Random().nextInt(1000) + 1; //odds of throwing a bone
//        if (i > 999 && !projectile.alive && shotAvailableCounter == 30) {
//            projectile.set(worldX, worldY, direction, true, this);
//            gp.projectileList.add(projectile);
//            shotAvailableCounter = 0;
//        // end bone code
//        }

    public void setDialogue() {
        dialogueText[0][0] = "Woof!";
        dialogueText[1][0] = "Grr!";
        dialogueText[2][0] = "I'm doing the dinner dance!";
        dialogueText[3][0] = "Woof! Pip was doing something funny to his bed!";
        dialogueText[4][0] = "Growl...Chicken time!";
        dialogueText[5][0] = "Growl...Pip thinks he's the toughest dog on\nthe street...HaHa...Ruff!";
        dialogueText[6][0] = "Shall we play cheeky monkey?";
        dialogueText[7][0] = "Yelp!";
    }

    public void update() {
//        //DEBUG
//        System.out.println("time to wait: " + timeToBeOffMap + "\ntime passed: " + transitionCounter + "\noffMap: " + offMap);
//        for (int i = 0; i < gp.npc[gp.otherMap].length; i++) {
//            if (gp.npc[gp.otherMap][i] != null && Objects.equals(gp.npc[gp.otherMap][i].name, this.name)) {
//                System.out.println("offOtherMap: " + gp.npc[gp.otherMap][i].offMap + "onPath: " + onPath);
//            }
//        }
//        //
        if (offMap && (gp.player.currentProjectile == null || !Objects.equals(gp.player.currentProjectile.name, "Chopped Chicken"))) {
            gp.eHandler.generateOffMapTimerValue(this);
            if (timeToBeOffMap > transitionCounter) {
                transitionCounter++;
            } else {
                for (int i = 0; i < gp.npc[gp.otherMap].length; i++) {
                    if (gp.npc[gp.otherMap][i] != null && Objects.equals(gp.npc[gp.otherMap][i].name, this.name)) {
                        gp.npc[gp.otherMap][i].offMap = true;
                        gp.npc[gp.otherMap][i].onPath = false;
                        gp.npc[gp.otherMap][i].speed = 0;
                        if (gp.otherMap == 0) {
                            gp.npc[gp.otherMap][i].worldX = gp.tileSize;
                            gp.npc[gp.otherMap][i].worldY = gp.tileSize;
                        } else if (gp.otherMap == 1) {
                            gp.npc[gp.otherMap][i].worldX = 50 * gp.tileSize;
                            gp.npc[gp.otherMap][i].worldY = 5 * gp.tileSize;
                        }
                    }
                }
                onPath = false;
                timeToBeOffMap = 0;
                transitionCounter = 0;
                offMap = false;
                if (gp.currentMap == 0) {
                    worldX = 18 * gp.tileSize;
                    worldY = 10 * gp.tileSize;
                    direction = "down";
                } else if (gp.currentMap == 1) {
                    worldX = 25 * gp.tileSize;
                    worldY = 10 * gp.tileSize;
                    direction = "right";
                }
                speed = defaultSpeed;
            }
        } else {
            super.update();
            gp.eHandler.checkEvent(this);

            int xDistance = Math.abs(worldX - gp.player.worldX);
            int yDistance = Math.abs(worldY - gp.player.worldY);
            int tileDistance = (xDistance + yDistance)/gp.tileSize;

            if (tileDistance > 10) {
                withinView = false;
            } else {
                withinView = true;
            }
            //System.out.println("WithinViewPhoebe: " + withinView);

            if (gp.player.currentProjectile != null) {
                for (int i = 0; i < gp.npc[gp.otherMap].length; i++) {
                    if (gp.npc[gp.otherMap][i] != null && Objects.equals(gp.npc[gp.otherMap][i].name, this.name)) {
                        gp.npc[gp.otherMap][i].offMap = true;
                        gp.npc[gp.otherMap][i].speed = 0;
                        if (gp.otherMap == 0) {
                            gp.npc[gp.otherMap][i].worldX = gp.tileSize;
                            gp.npc[gp.otherMap][i].worldY = gp.tileSize;
                        } else if (gp.otherMap == 1) {
                            gp.npc[gp.otherMap][i].worldX = 50 * gp.tileSize;
                            gp.npc[gp.otherMap][i].worldY = 5 * gp.tileSize;
                        }
                    }
                }
                timeToBeOffMap = 0;
                transitionCounter = 0;
                offMap = false;
                if (gp.currentMap == 0 && callingNPCBack) {
                    worldX = 18 * gp.tileSize;
                    worldY = 10 * gp.tileSize;
                    direction = "down";
                    speed = defaultSpeed;
                    callingNPCBack = false;
                } else if (gp.currentMap == 1 && callingNPCBack) {
                    worldX = 25 * gp.tileSize;
                    worldY = 10 * gp.tileSize;
                    direction = "right";
                    speed = defaultSpeed;
                    callingNPCBack = false;
                }
                if ((Objects.equals(gp.player.currentProjectile.name, "Chopped Chicken") && tileDistance <= TILE_DISTANCE_TO_BE_ON_PATH_TO_CHICKEN)) {
                    onPath = true;
                    speed = 3;
                }
            }
            else if (gp.player.checkIfObjectOnMap("Chopped Chicken Phoebe") > 0) {
                //follow chicken
                xDistance = Math.abs(worldX - gp.aSetter.choppedChickenPhoebeX);
                yDistance = Math.abs(worldY - gp.aSetter.choppedChickenPhoebeY);
                tileDistance = (xDistance + yDistance)/gp.tileSize;
                if (tileDistance <= TILE_DISTANCE_TO_BE_ON_PATH_TO_CHICKEN && tileDistance >= 1) {
                    onPath = true;
                    speed = 3;
                } else if (tileDistance < 1) {
                    speed = 0;
                    if (!gp.player.phoebeChickenEaten) {
                        gp.player.startCounterPhoebeEatingChicken = true;
                    }
                }
                else {
                    speed = 2;
                    onPath = false;
                }
            }
        }
    }

    public void setAction(int goalCol, int goalRow) { //correct

        if (gp.player.currentProjectile != null) {
            goalCol = 0;
            goalRow = 0;
            if(onPath && Objects.equals(gp.player.currentProjectile.name, "Chopped Chicken") && gp.player.checkIfObjectOnMap("Chopped Chicken Phoebe") == 0) { //correct
                followingPlayer = true;
                if (Objects.equals(gp.player.direction, "up")) { //dog chase player but stay one square behind
                    goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
                    goalRow = ((gp.player.worldY + gp.player.solidArea.y)/gp.tileSize) + 1;
                } else if (Objects.equals(gp.player.direction, "down")) {
                    goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
                    goalRow = ((gp.player.worldY + gp.player.solidArea.y)/gp.tileSize) - 1;
                } else if (Objects.equals(gp.player.direction, "right")) {
                    goalCol = ((gp.player.worldX + gp.player.solidArea.x)/gp.tileSize) - 1;
                    goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
                } else if (Objects.equals(gp.player.direction, "left")) {
                    goalCol = ((gp.player.worldX + gp.player.solidArea.x)/gp.tileSize) + 1;
                    goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
                }
                searchPath(goalCol, goalRow);
            } else if (onPath && Objects.equals(gp.player.currentProjectile.name, "Chopped Chicken") && gp.player.checkIfObjectOnMap("Chopped Chicken Phoebe") > 0) {
                followingPlayer = false;
                goalCol = gp.aSetter.choppedChickenPhoebeX/gp.tileSize;
                goalRow = gp.aSetter.choppedChickenPhoebeY/gp.tileSize;
                searchPath(goalCol, goalRow);
            } else {
                if (checkEdgeOfMap(this)) {
                    turnEntityAround(this);
                } else {
                    getRandomDirection();
                }
            }
        } else if (onPath && (gp.player.checkIfObjectOnMap("Chopped Chicken Phoebe") > 0)) {
            followingPlayer = false;
            goalCol = gp.aSetter.choppedChickenPhoebeX/gp.tileSize;
            goalRow = gp.aSetter.choppedChickenPhoebeY/gp.tileSize;
            searchPath(goalCol, goalRow);
        } else {
            followingPlayer = false;
            if (checkEdgeOfMap(this)) {
                turnEntityAround(this);
            } else {
                getRandomDirection();
            }
        }
        //System.out.println("Phoebe Following Player: " + followingPlayer);
    }

    public void speak() {

        //character specific stuff here
        facePlayer();
        dialogueSet++;
        if (dialogueSet > 6) {
            dialogueSet = 0;
        }
        startDialogue(this, dialogueSet);
    }
}
