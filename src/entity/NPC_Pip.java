package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Objects;

public class NPC_Pip extends Entity {

    public final int TILE_DISTANCE_TO_BE_ON_PATH_TO_BONE = 20;
    public final int TILE_DISTANCE_TO_BE_ON_PATH_TO_CHICKEN = 40;
    public NPC_Pip(GamePanel gp) {
        super(gp);

        name = "Pip";
        direction = "right";
        defaultSpeed = 2;
        speed = defaultSpeed;
        type = type_npc;
        goesTransparentWhenHit = true;

        getImage();
        setDialogue();

        solidArea = new Rectangle(8, 16,32,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void getImage() {

        up1 = setup("/NPC/phoebe_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/NPC/phoebe_up2", gp.tileSize, gp.tileSize);
        down1 = setup("/NPC/phoebe_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/NPC/phoebe_down2", gp.tileSize, gp.tileSize);
        left1 = setup("/NPC/phoebe_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/NPC/phoebe_left2", gp.tileSize, gp.tileSize);
        right1 = setup("/NPC/phoebe_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/NPC/phoebe_right2", gp.tileSize, gp.tileSize);

    }

    public void setDialogue() {
        dialogueText[0][0] = "Woof!";
        dialogueText[1][0] = "Grr!";
        dialogueText[2][0] = "Grr...I'm off to my basket, don't look...Grr!";
        dialogueText[3][0] = "Woof! Phoebe stinks!";
        dialogueText[4][0] = "Growl...I'm not having a bath!";
        dialogueText[5][0] = "Growl...I'm the toughest dog on the street...Ruff!";
        dialogueText[6][0] = "Grr...Throw my bone...Grr!";
        dialogueText[7][0] = "Yelp!";
    }

    public void update() {
        System.out.println("PipFollowing: " + followingPlayer);
//        //DEBUG
//        System.out.println("time to wait: " + timeToBeOffMap + "\ntime passed: " + transitionCounter + "\noffMap: " + offMap);
//        for (int i = 0; i < gp.npc[gp.otherMap].length; i++) {
//            if (gp.npc[gp.otherMap][i] != null && Objects.equals(gp.npc[gp.otherMap][i].name, this.name)) {
//                System.out.println("offOtherMap: " + gp.npc[gp.otherMap][i].offMap + "onPath: " + onPath);
//            }
//        }
//        //
        if (offMap && gp.player.currentProjectile == null) {
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
            //System.out.println("WithinViewPip: " + withinView);

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
                if (Objects.equals(gp.player.currentProjectile.name, "Pip's Bone") && tileDistance <= TILE_DISTANCE_TO_BE_ON_PATH_TO_BONE && gp.player.checkIfObjectOnMap("Chopped Chicken Pip") == 0) {
                    //follow player
                    onPath = true;
                    speed = 4;
                } else if (Objects.equals(gp.player.currentProjectile.name, "Pip's Bone") && tileDistance > TILE_DISTANCE_TO_BE_ON_PATH_TO_BONE) {
                    speed = 2;
                    onPath = false;
                } else if ((Objects.equals(gp.player.currentProjectile.name, "Chopped Chicken") && tileDistance <= TILE_DISTANCE_TO_BE_ON_PATH_TO_CHICKEN) && gp.player.checkIfObjectOnMap("Pip's Bone") > 0) {
                    onPath = true;
                    speed = 5;
                } else if ((Objects.equals(gp.player.currentProjectile.name, "Chopped Chicken") && tileDistance <= TILE_DISTANCE_TO_BE_ON_PATH_TO_CHICKEN)) {
                    onPath = true;
                    speed = 5;
                }
            }
            else if (gp.player.checkIfObjectOnMap("Chopped Chicken Pip") == 0 && gp.player.checkIfObjectOnMap("Pip's Bone") > 0 && gp.player.checkIfObjectOnMap("Chopped Chicken Phoebe") > 0) {
                onPath = true;
                speed = 5;
            }
            else if (gp.player.checkIfObjectOnMap("Chopped Chicken Pip") == 0 && gp.player.checkIfObjectOnMap("Pip's Bone") > 0) {
                //follow bone
                xDistance = Math.abs(worldX - gp.aSetter.boneX);
                yDistance = Math.abs(worldY - gp.aSetter.boneY);
                tileDistance = (xDistance + yDistance)/gp.tileSize;
                if (tileDistance <= TILE_DISTANCE_TO_BE_ON_PATH_TO_BONE && tileDistance >= 1) {
                    onPath = true;
                    speed = 5;
                } else if (tileDistance < 1) {
                    speed = 0;
                }
                else {
                    speed = 2;
                    onPath = false;
                }
            } else if (gp.player.checkIfObjectOnMap("Chopped Chicken Pip") > 0) {
                //follow chicken
                xDistance = Math.abs(worldX - gp.aSetter.choppedChickenPipX);
                yDistance = Math.abs(worldY - gp.aSetter.choppedChickenPipY);
                tileDistance = (xDistance + yDistance)/gp.tileSize;
                if (tileDistance <= TILE_DISTANCE_TO_BE_ON_PATH_TO_CHICKEN && tileDistance >= 1) {
                    onPath = true;
                    speed = 5;
                } else if (tileDistance < 1) {
                    speed = 0;
                    if (!gp.player.pipChickenEaten) {
                        gp.player.startCounterPipEatingChicken = true;
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
            if(onPath && (Objects.equals(gp.player.currentProjectile.name, "Pip's Bone") || Objects.equals(gp.player.currentProjectile.name, "Chopped Chicken")) && gp.player.checkIfObjectOnMap("Chopped Chicken Pip") == 0) { //correct
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
            }
        } else if (onPath && (gp.player.checkIfObjectOnMap("Chopped Chicken Pip") > 0) || (gp.player.checkIfObjectOnMap("Chopped Chicken Pip") > 0 && gp.player.checkIfObjectOnMap("Pip's Bone") > 0)) {
            followingPlayer = false;
            goalCol = gp.aSetter.choppedChickenPipX/gp.tileSize;
            goalRow = gp.aSetter.choppedChickenPipY/gp.tileSize;
            searchPath(goalCol, goalRow);
        } else if (onPath && gp.player.checkIfObjectOnMap("Pip's Bone") > 0) {
            followingPlayer = false;
            goalCol = (gp.aSetter.boneX)/gp.tileSize;
            goalRow = (gp.aSetter.boneY)/gp.tileSize;
            searchPath(goalCol, goalRow);
        } else {
            followingPlayer = false;
            if (checkEdgeOfMap(this)) {
                turnEntityAround(this);
            } else {
                getRandomDirection();
            }
        }
        //System.out.println("Pip Following Player: " + followingPlayer);
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
