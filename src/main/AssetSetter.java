package main;

import entity.*;
import monster.MON_Spider;
import monster.MON_WaspSwarm;
import object.*;
import tile_interactive.IT_WeedTile;

import java.util.Objects;
import java.util.Random;

public class AssetSetter {
    GamePanel gp;

    //INDICES FOR INSTANCES OF MONSTERS (can add NPC and OBJ equivalents later if required)
    public int monsterNumber = 0;
    public int mapNumTotal;
    public int boneX;
    public int boneY;
    public int choppedChickenX;
    public int choppedChickenY;
    public int choppedChickenPhoebeX;
    public int choppedChickenPhoebeY;
    public int choppedChickenPipX;
    public int choppedChickenPipY;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjectAfterStart(String name, int mapNum, int x, int y) { //finds first available slot in object array if setting object after start of game
        int count = 0;
        for (int i = 0; i < gp.obj[1].length; i++) {
            if (gp.obj[gp.currentMap][i] != null) {
                count++; //at end of loop, count will show the index of the last object in the array
            } else {
                break;
            }
        }

        int i = count;
        switch (name) { //chooses object
            case "Pip's Bone":
                gp.obj[mapNum][i] = new OBJ_PipsBone(gp);
                boneX = x * gp.tileSize;
                boneY = y * gp.tileSize;
                break;
            case "Chopped Chicken Phoebe":
                gp.obj[mapNum][i] = new OBJ_ChoppedChickenPhoebe(gp);
                choppedChickenPhoebeX = x * gp.tileSize;
                choppedChickenPhoebeY = y * gp.tileSize;
                break;
            case "Chopped Chicken Pip":
                gp.obj[mapNum][i] = new OBJ_ChoppedChickenPip(gp);
                choppedChickenPipX = x * gp.tileSize;
                choppedChickenPipY = y * gp.tileSize;
                break;
            case "Old Cardigan":
                gp.obj[mapNum][i] = new OBJ_GrandmasCardigan(gp);
                break;
            case "Spatula":
                gp.obj[mapNum][i] = new OBJ_Spatula(gp);
                break;
            case "Hatchet":
                gp.obj[mapNum][i] = new OBJ_Hatchet(gp);
                break;
            case "FrontBackDoorOpen":
                gp.obj[mapNum][i] = new OBJ_FrontBackDoorOpen(gp);
                break;
            case "HundredQuid":
                gp.obj[mapNum][i] = new OBJ_SuperCoin(gp);
                break;
            case "Garden Shovel":
                gp.obj[mapNum][i] = new OBJ_Shovel(gp);
                break;
            case "Lavender Crocs":
                gp.obj[mapNum][i] = new OBJ_Lavendar_Crocs(gp);
                break;
            case "Chicken":
                gp.obj[mapNum][i] = new OBJ_Chicken(gp);
                break;
            case "Chopped Chicken":
                gp.obj[mapNum][i] = new OBJ_ChoppedChicken(gp);
                break;
            case "Mop":
                gp.obj[mapNum][i] = new OBJ_Mop(gp);
        }

        gp.obj[mapNum][i].worldX = x * gp.tileSize;
        gp.obj[mapNum][i].worldY = y * gp.tileSize;

    }

    public void setNPCAfterStart(String name, int mapNum, int x, int y) {

        int count = 0;
        for (int i = 0; i < gp.npc[1].length; i++) {
            if (gp.npc[gp.currentMap][i] != null) {
                count++; //at end of loop, count will show the index of the last object in the array
            } else {
                break;
            }
        }

        int i = count;
        switch (name) { //chooses npc
            case "Andrea":
                gp.npc[mapNum][i] = new NPC_Andrea(gp);
                break;
            case "placeholder": //there to allow switch to work with only one case, so replace with extra npcs when needed
                gp.npc[mapNum][i] = new NPC_Dad(gp);
                break;
        }

        gp.npc[mapNum][i].worldX = x * gp.tileSize;
        gp.npc[mapNum][i].worldY = y * gp.tileSize;
    }

    public void setObject() {
        int mapNum = 0;
        int i = 0;

        gp.obj[mapNum][i] = new OBJ_LightPills(gp);
        gp.obj[mapNum][i].worldX = 28 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 18 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Cupboard2(gp);
        gp.obj[mapNum][i].worldX = 24 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 10 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Cupboard1(gp);
        gp.obj[mapNum][i].worldX = 17 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 13 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Pills(gp);
        gp.obj[mapNum][i].worldX = 19 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 15 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Guitar1(gp);
        gp.obj[mapNum][i].worldX = 20 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 13 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Guitar2(gp);
        gp.obj[mapNum][i].worldX = 21 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 18 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_InsideDoor(gp);
        gp.obj[mapNum][i].worldX = 18 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 12 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_InsideDoor(gp);
        gp.obj[mapNum][i].worldX = 26 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 12 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_InsideDoorSideways(gp);
        gp.obj[mapNum][i].worldX = 22 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 11 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Cupboard3(gp);
        gp.obj[mapNum][i].worldX = 19 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 13 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_KitchenCupboard1(gp);
        gp.obj[mapNum][i].worldX = 27 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 14 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_MumsChair(gp);
        gp.obj[mapNum][i].worldX = 19 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 18 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_InsideDoorSideways(gp);
        gp.obj[mapNum][i].worldX = 22 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 17 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_FrontDoor(gp);
        gp.obj[mapNum][i].worldX = 16 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 11 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_BackDoor(gp);
        gp.obj[mapNum][i].worldX = 30 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 11 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_TelephoneHall(gp);
        gp.obj[mapNum][i].worldX = 17 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 10 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_FrontBackDoorKey(gp);
        gp.obj[mapNum][i].worldX = 28 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 17 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_BackGate(gp);
        gp.obj[mapNum][i].worldX = 31 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 9 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Bin_Blue(gp);
        gp.obj[mapNum][i].worldX = 27 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 7 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Bin_Green(gp);
        gp.obj[mapNum][i].worldX = 26 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 7 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Bin_Grey(gp);
        gp.obj[mapNum][i].worldX = 25 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 7 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_PipsBone(gp);
        gp.obj[mapNum][i].worldX = 25 * gp.tileSize; boneX = 25;
        gp.obj[mapNum][i].worldY = 18 * gp.tileSize; boneY = 18;
        i++;

        gp.obj[mapNum][i] = new OBJ_BackGateSideways(gp);
        gp.obj[mapNum][i].worldX = 23 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 13 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_BlockOfWood(gp);
        gp.obj[mapNum][i].worldX = 34 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 11 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Fridge(gp);
        gp.obj[mapNum][i].worldX = 26 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 10 * gp.tileSize;
        i++;

        mapNum = 1;  //ADD OBJECTS TO NEXT MAP LIKE THIS

        gp.obj[mapNum][i] = new OBJ_BedMumDadBL(gp);
        gp.obj[mapNum][i].worldX = 27 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 15 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_BedMumDadBR(gp);
        gp.obj[mapNum][i].worldX = 28 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 15 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_BedMumDadTL(gp);
        gp.obj[mapNum][i].worldX = 27 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 14 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_BedMumDadTR(gp);
        gp.obj[mapNum][i].worldX = 28 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 14 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Bin_Blue(gp);
        gp.obj[mapNum][i].worldX = 27 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 7 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Bin_Green(gp);
        gp.obj[mapNum][i].worldX = 26 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 7 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Bin_Grey(gp);
        gp.obj[mapNum][i].worldX = 25 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 7 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_RedBoots(gp);
        gp.obj[mapNum][i].worldX = 29 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 17 * gp.tileSize;
        i++;
    }

    public void setNPC() {
        int mapNum = 0;
        int i = 0;

        gp.npc[mapNum][i] = new NPC_Dad(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*17;
        gp.npc[mapNum][i].worldY = gp.tileSize*15;
        i++;

        gp.npc[mapNum][i] = new NPC_Phoebe(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*23;
        gp.npc[mapNum][i].worldY = gp.tileSize*17;
        i++;

        gp.npc[mapNum][i] = new NPC_Pip(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*20;
        gp.npc[mapNum][i].worldY = gp.tileSize*11;
        i++;

        gp.npc[mapNum][i] = new NPC_Merchant(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*62;
        gp.npc[mapNum][i].worldY = gp.tileSize*9;
        i++;

        //        mapNum = 1;  //ADD NPCs TO NEXT MAP LIKE THIS
//        i = 0;
//        gp.obj[mapNum][i] = new OBJ_BackGateSideways(gp);
//        gp.obj[mapNum][i].worldX = 23 * gp.tileSize;
//        gp.obj[mapNum][i].worldY = 13 * gp.tileSize;
//        i++;
    }

    public int setMonster(String type, int monsterNumber, int x, int y, boolean randomizeLocation) {
        int mapNum = 0;

        switch(type) {
            case "Spider":
                gp.monster[mapNum][monsterNumber] = new MON_Spider(gp);
                gp.monster[mapNum][monsterNumber].newMonster = true;
                break;
            case "WaspSwarm":
                gp.monster[mapNum][monsterNumber] = new MON_WaspSwarm(gp);
                gp.monster[mapNum][monsterNumber].newMonster = true;
        }
        if (randomizeLocation) { // sets monster in any square up to 2 tiles away from player in any direction but never on the player
            Random rand = new Random();
            int randX = 0;
            int randY = 0;
            while (randX == 0 && randY == 0) {
                randX = rand.nextInt(4) - 2;
                randY = rand.nextInt(4) - 2;
                gp.monster[mapNum][monsterNumber].worldX = gp.tileSize * (x + randX);
                gp.monster[mapNum][monsterNumber].worldY = gp.tileSize * (y + randY);
            }

        } else { // will place monster exactly at specified x and y co-ordinates
            gp.monster[mapNum][monsterNumber].worldX = gp.tileSize * x;
            gp.monster[mapNum][monsterNumber].worldY = gp.tileSize * y;
        }

        monsterNumber++; //monster counter increments so that next call of method adds to next slot in monster array

        return monsterNumber;
    }

    public int setInteractiveTile() {
        int mapNum = 0;
        int i = 0;
        int weedCount = 0;

        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 41, 11); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 42, 11); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 41, 12); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 42, 12); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 41, 13); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 42, 13); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 41, 14); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 42, 14); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 41, 15); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 42, 15); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 41, 16); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 42, 16); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 41, 17); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 42, 17); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 41, 18); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 42, 18); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 41, 19); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 42, 19); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 14, 6); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 16, 6); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 18, 6); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 22, 6); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 25, 6); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 28, 6); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 11, 7); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 12, 7); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 14, 7); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 20, 7); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 21, 7); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 22, 7); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 24, 7); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 30, 7); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 11, 9); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 12, 9); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 13, 9); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 11, 10); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 11, 14); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 11, 18); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 12, 19); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 14, 18); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 14, 17); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 14, 11); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 14, 10); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 28, 7); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 18, 7); i++; weedCount++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 16, 7); i++; weedCount++;
        mapNumTotal = weedCount;

        mapNum = 1;

        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 41, 11); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 42, 11); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 41, 12); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 42, 12); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 41, 13); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 42, 13); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 41, 14); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 42, 14); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 41, 15); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 42, 15); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 41, 16); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 42, 16); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 41, 17); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 42, 17); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 41, 18); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 42, 18); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 41, 19); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 42, 19); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 14, 6); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 16, 6); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 18, 6); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 22, 6); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 25, 6); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 28, 6); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 11, 7); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 12, 7); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 14, 7); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 20, 7); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 21, 7); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 22, 7); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 24, 7); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 30, 7); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 11, 9); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 12, 9); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 13, 9); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 11, 10); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 11, 14); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 11, 18); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 12, 19); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 14, 18); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 14, 17); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 14, 11); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 14, 10); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 28, 7); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 18, 7); i++;
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 16, 7); i++;


        return weedCount; //return number of weeds to dig up
    }
}
