package main;

import entity.NPC_Dad;
import monster.MON_Spider;
import object.*;
import tile_interactive.IT_WeedTile;

import java.util.Random;

public class AssetSetter {
    GamePanel gp;

    //INDICES FOR INSTANCES OF MONSTERS (can add NPC and OBJ equivalents later if required)
    public int monsterNumber = 0;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setBone(int x, int y) {
        int i = 0;

        gp.obj[i] = new OBJ_DogsBone_NotMagic(gp);
        gp.obj[i].worldX = x * gp.tileSize;
        gp.obj[i].worldY = y * gp.tileSize;
    }

    public void setObject() {
        int i = 0;

        gp.obj[i] = new OBJ_Pills(gp);
        gp.obj[i].worldX = 16 * gp.tileSize;
        gp.obj[i].worldY = 10 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_DogsBone_NotMagic(gp);
        gp.obj[i].worldX = 22 * gp.tileSize;
        gp.obj[i].worldY = 13 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_Guitar1(gp);
        gp.obj[i].worldX = 17 * gp.tileSize;
        gp.obj[i].worldY = 8 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_Guitar2(gp);
        gp.obj[i].worldX = 18 * gp.tileSize;
        gp.obj[i].worldY = 13 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_InsideDoorOpen(gp);
        gp.obj[i].worldX = 15 * gp.tileSize;
        gp.obj[i].worldY = 7 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_InsideDoor(gp);
        gp.obj[i].worldX = 15 * gp.tileSize;
        gp.obj[i].worldY = 7 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_InsideDoorOpen(gp);
        gp.obj[i].worldX = 23 * gp.tileSize;
        gp.obj[i].worldY = 7 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_InsideDoor(gp);
        gp.obj[i].worldX = 23 * gp.tileSize;
        gp.obj[i].worldY = 7 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_InsideDoorOpenSideways(gp);
        gp.obj[i].worldX = 19 * gp.tileSize;
        gp.obj[i].worldY = 12 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_InsideDoorSideways(gp);
        gp.obj[i].worldX = 19 * gp.tileSize;
        gp.obj[i].worldY = 12 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_FrontDoorOpen(gp);
        gp.obj[i].worldX = 13 * gp.tileSize;
        gp.obj[i].worldY = 6 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_FrontDoor(gp);
        gp.obj[i].worldX = 13 * gp.tileSize;
        gp.obj[i].worldY = 6 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_FrontDoorOpen(gp);
        gp.obj[i].worldX = 27 * gp.tileSize;
        gp.obj[i].worldY = 6 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_FrontDoor(gp);
        gp.obj[i].worldX = 27 * gp.tileSize;
        gp.obj[i].worldY = 6 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_TelephoneHall(gp);
        gp.obj[i].worldX = 14 * gp.tileSize;
        gp.obj[i].worldY = 5 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_StairsHall(gp);
        gp.obj[i].worldX = 16 * gp.tileSize;
        gp.obj[i].worldY = 5 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_FrontDoorKey(gp);
        gp.obj[i].worldX = 25 * gp.tileSize;
        gp.obj[i].worldY = 12 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_BackGateOpen(gp);
        gp.obj[i].worldX = 28 * gp.tileSize;
        gp.obj[i].worldY = 4 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_BackGate(gp);
        gp.obj[i].worldX = 28 * gp.tileSize;
        gp.obj[i].worldY = 4 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_Lavendar_Crocs(gp);
        gp.obj[i].worldX = 18 * gp.tileSize;
        gp.obj[i].worldY = 9 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_GrandmasCardigan(gp);
        gp.obj[i].worldX = 23 * gp.tileSize;
        gp.obj[i].worldY = 5 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_Spatula(gp);
        gp.obj[i].worldX = 20 * gp.tileSize;
        gp.obj[i].worldY = 9 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_FrontGateOpen(gp);
        gp.obj[i].worldX = 7 * gp.tileSize;
        gp.obj[i].worldY = 3 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_FrontGate(gp);
        gp.obj[i].worldX = 7 * gp.tileSize;
        gp.obj[i].worldY = 3 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_Bin_Blue(gp);
        gp.obj[i].worldX = 24 * gp.tileSize;
        gp.obj[i].worldY = 2 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_Bin_Green(gp);
        gp.obj[i].worldX = 23 * gp.tileSize;
        gp.obj[i].worldY = 2 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_Bin_Grey(gp);
        gp.obj[i].worldX = 22 * gp.tileSize;
        gp.obj[i].worldY = 2 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_BackGateOpenSideways(gp);
        gp.obj[i].worldX = 20 * gp.tileSize;
        gp.obj[i].worldY = 8 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_BackGateSideways(gp);
        gp.obj[i].worldX = 20 * gp.tileSize;
        gp.obj[i].worldY = 8 * gp.tileSize;
        i++;

        gp.obj[i] = new OBJ_Shovel(gp);
        gp.obj[i].worldX = 42 * gp.tileSize;
        gp.obj[i].worldY = 3 * gp.tileSize;
        i++;
    }

    public void setNPC() {
        gp.npc[0] = new NPC_Dad(gp);
        gp.npc[0].worldX = gp.tileSize*14;
        gp.npc[0].worldY = gp.tileSize*10;
    }

    public int setMonster(String type, int monsterNumber, int x, int y, boolean randomizeLocation) {
        switch(type) {
            case "Spider":
                gp.monster[monsterNumber] = new MON_Spider(gp);
                gp.monster[monsterNumber].newMonster = true;
                break;
        }
        if (randomizeLocation) { // sets monster in any square up to 2 tiles away from player in any direction but never on the player
            Random rand = new Random();
            int randX = 0;
            int randY = 0;
            while (randX == 0 && randY == 0) {
                randX = rand.nextInt(4) - 2;
                randY = rand.nextInt(4) - 2;
                gp.monster[monsterNumber].worldX = gp.tileSize * (x + randX);
                gp.monster[monsterNumber].worldY = gp.tileSize * (y + randY);
            }

        } else { // will place monster exactly at specified x and y co-ordinates
            gp.monster[monsterNumber].worldX = gp.tileSize * x;
            gp.monster[monsterNumber].worldY = gp.tileSize * y;
        }



        monsterNumber++; //monster counter increments so that next call of method adds to next slot in monster array

        return monsterNumber;
    }

    public int setInteractiveTile() {
        int i = 0;
        int weedCount = 0;

        gp.iTile[i] = new IT_WeedTile(gp, 38, 6); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 39, 6); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 38, 7); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 39, 7); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 38, 8); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 39, 8); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 38, 9); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 39, 9); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 38, 10); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 39, 10); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 38, 11); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 39, 11); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 38, 12); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 39, 12); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 38, 13); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 39, 13); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 38, 14); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 39, 14); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 11, 1); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 13, 1); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 15, 1); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 19, 1); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 22, 1); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 25, 1); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 8, 2); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 9, 2); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 11, 2); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 17, 2); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 18, 2); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 19, 2); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 21, 2); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 27, 2); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 8, 4); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 9, 4); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 10, 4); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 8, 5); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 8, 9); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 8, 13); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 9, 14); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 11, 13); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 11, 12); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 11, 6); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 11, 5); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 25, 2); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 15, 2); i++; weedCount++;
        gp.iTile[i] = new IT_WeedTile(gp, 13, 2); i++; weedCount++;

        return weedCount; //return number of weeds to dig up
    }
}
