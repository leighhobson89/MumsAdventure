package main;

import entity.NPC_Dad;
import entity.NPC_Merchant;
import entity.NPC_Phoebe;
import entity.NPC_Pip;
import monster.MON_Spider;
import monster.MON_WaspSwarm;
import object.*;
import tile_interactive.IT_WeedTile;

import java.util.Random;

public class AssetSetter {
    GamePanel gp;

    //INDICES FOR INSTANCES OF MONSTERS (can add NPC and OBJ equivalents later if required)
    public int monsterNumber = 0;
    public int mapNumTotal;
    public int boneX;
    public int boneY;

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
                gp.obj[mapNum][i] = new OBJ_DogsBone_NotMagic(gp);
        }

        gp.obj[mapNum][i].worldX = x * gp.tileSize;
        gp.obj[mapNum][i].worldY = y * gp.tileSize;
        boneX = x * gp.tileSize;
        boneY = y * gp.tileSize;

    }

    public void setObject() {
        int mapNum = 0;
        int i = 0;

        gp.obj[mapNum][i] = new OBJ_LightPills(gp);
        gp.obj[mapNum][i].worldX = 28 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 18 * gp.tileSize;
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
        gp.obj[mapNum][i].worldY = 17 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_FrontDoorOpen(gp);
        gp.obj[mapNum][i].worldX = 16 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 11 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_FrontDoor(gp);
        gp.obj[mapNum][i].worldX = 16 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 11 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_FrontDoorOpen(gp);
        gp.obj[mapNum][i].worldX = 30 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 11 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_FrontDoor(gp);
        gp.obj[mapNum][i].worldX = 30 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 11 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_TelephoneHall(gp);
        gp.obj[mapNum][i].worldX = 17 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 10 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_StairsHall(gp);
        gp.obj[mapNum][i].worldX = 19 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 10 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_FrontDoorKey(gp);
        gp.obj[mapNum][i].worldX = 28 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 17 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_BackGate(gp);
        gp.obj[mapNum][i].worldX = 31 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 9 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Lavendar_Crocs(gp);
        gp.obj[mapNum][i].worldX = 21 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 14 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_GrandmasCardigan(gp);
        gp.obj[mapNum][i].worldX = 26 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 10 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Spatula(gp);
        gp.obj[mapNum][i].worldX = 23 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 14 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_FrontGateOpen(gp);
        gp.obj[mapNum][i].worldX = 10 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 8 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_FrontGate(gp);
        gp.obj[mapNum][i].worldX = 10 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 8 * gp.tileSize;
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

        gp.obj[mapNum][i] = new OBJ_DogsBone_NotMagic(gp);
        gp.obj[mapNum][i].worldX = 25 * gp.tileSize; boneX = 25;
        gp.obj[mapNum][i].worldY = 18 * gp.tileSize; boneY = 18;
        i++;

        gp.obj[mapNum][i] = new OBJ_BackGateSideways(gp);
        gp.obj[mapNum][i].worldX = 23 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 13 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Shovel(gp);
        gp.obj[mapNum][i].worldX = 45 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 8 * gp.tileSize;
        i++;

//        mapNum = 1;  //ADD OBJECTS TO NEXT MAP LIKE THIS
//
//        gp.obj[mapNum][i] = new OBJ_BackGateSideways(gp);
//        gp.obj[mapNum][i].worldX = 23 * gp.tileSize;
//        gp.obj[mapNum][i].worldY = 13 * gp.tileSize;
//        i++;
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

        //        mapNum = 1;  //ADD OBJECTS TO NEXT MAP LIKE THIS
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
        System.out.println("MonsterNumber: " + monsterNumber);

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
