package object;

import entity.Entity;
import main.GamePanel;
import main.MissionStates;

import java.util.Objects;

public class OBJ_WaspNest extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "WaspNest";

    public OBJ_WaspNest(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = true;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "WaspNest";
        description = "[" + name + "]\nThe burnt out shell\nof the wasp nest!";
        image = setup("/objects/waspNest", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/waspNestOnFire", gp.tileSize, gp.tileSize);
        image3 = setup("/objects/waspNestOnFire2", gp.tileSize, gp.tileSize);
        image4 = setup("/objects/waspNestDestroyed", gp.tileSize, gp.tileSize);
        down1 = image;
        direction = "down";
        collision = true;

        stressLevel = 0;
        monsterMaxStress = 3;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDialogue();
    }

    public void update() {
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if (gp.player.waspNestState == 1) {
            if (spriteCounter < 20) {
                down1 = image2;
            } else {
                down1 = image3;
            }
            if (spriteCounter > 40) {
                spriteCounter = 0;
            }
            spriteCounter++;
        } else {
            spriteCounter = 0;
        }
    }

    public void setDialogue() {
        dialogueText[0][0] = "Looks quiet at the moment but I need to\n get rid of it sharpish!";
        dialogueText[1][0] = "Aaaaghhhhh!";
        dialogueText[2][0] = "Burn it to the ground with the flaming aerosol,\nquick!";
        dialogueText[3][0] = "Quick! Get water, its burning the bloody house\ndown!!";
        dialogueText[4][0] = "Phew! The fire is out, and the wasp nest\nis destroyed, thank God for that!";
        dialogueText[5][0] = "Quick! I need a container and water, this\nfire is burning the bloody house down!!";
        dialogueText[6][0] = "The wasp's nest is destroyed, phew!";
    }

    public void interact() {
        boolean hasBucket = false;
        int bucketIndex = 0;
        for (int i = 0; i < gp.player.inventory.size(); i++) {
            if (Objects.equals(gp.player.inventory.get(i).name, "Bucket")) {
                hasBucket = true;
                bucketIndex = i;
            }
        }
        if (opened) {
            startDialogue(this, 6);
        }
        if (gp.player.missionState == MissionStates.DESTROY_WASP_NEST && gp.player.missionSubstate < 1) {
            gp.aSetter.setMonster("WaspSwarm", gp.aSetter.monsterNumber, 17, 16, gp.currentMap, false);
            startDialogue(this, 1);
            gp.player.missionSubstate = 1;
            gp.playSFX(8);
        } else if (gp.player.missionState == MissionStates.DESTROY_WASP_NEST && gp.player.missionSubstate == 1) {
            startDialogue(this, 0);
        } else if (gp.player.missionState == MissionStates.DESTROY_WASP_NEST && gp.player.missionSubstate == 2) {
            startDialogue(this, 2);
        } else if (gp.player.missionState == MissionStates.DESTROY_WASP_NEST && gp.player.missionSubstate == 3) {
            if (hasBucket) {
                if (gp.player.bucketFull) {
                    startDialogue(this, 4);
                    opened = true;
                    gp.player.waspNestState = 2;
                    gp.player.inventory.get(bucketIndex).down1 = gp.player.inventory.get(bucketIndex).image;
                    gp.player.inventory.get(bucketIndex).displayName = "An Empty Bucket";
                    gp.player.inventory.get(bucketIndex).description = "[" + gp.player.inventory.get(bucketIndex).name + "]\nAn Empty Bucket!";
                    down1 = image4;
                    gp.player.bucketFull = false;
                    for (int i = 0; i < gp.player.inventory.size(); i++) {
                        if (Objects.equals(gp.player.inventory.get(i).name, "FlammableSprayWeapon")) {
                            gp.eHandler.removeItemFromPlayerInventory(gp.player.inventory, "FlammableSprayWeapon");
                            gp.player.currentWeapon = null;
                        }
                    }
                    gp.misStat.endMissionTasks(MissionStates.DESTROY_WASP_NEST, false);
                } else {
                    startDialogue(this, 3);
                }
            } else {
                startDialogue(this, 5);
            }
        }
        gp.keyH.enterPressed = false;
        }
    }
