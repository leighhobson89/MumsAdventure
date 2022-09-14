package main;

import entity.NPC_Dad;
import monster.MON_Spider;
import object.*;

import java.util.Random;

public class AssetSetter {
    GamePanel gp;

    //INDICES FOR INSTANCES OF MONSTERS (can add NPC and OBJ equivalents later if required)
    public int monsterNumber = 0;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_Guitar1(gp);
        gp.obj[0].worldX = 17 * gp.tileSize;
        gp.obj[0].worldY = 8 * gp.tileSize;

        gp.obj[1] = new OBJ_Guitar2(gp);
        gp.obj[1].worldX = 18 * gp.tileSize;
        gp.obj[1].worldY = 13 * gp.tileSize;

        gp.obj[14] = new OBJ_InsideDoor(gp);
        gp.obj[14].worldX = 15 * gp.tileSize;
        gp.obj[14].worldY = 7 * gp.tileSize;

        gp.obj[15] = new OBJ_InsideDoor(gp);
        gp.obj[15].worldX = 23 * gp.tileSize;
        gp.obj[15].worldY = 7 * gp.tileSize;

        gp.obj[16] = new OBJ_InsideDoorSideways(gp);
        gp.obj[16].worldX = 19 * gp.tileSize;
        gp.obj[16].worldY = 12 * gp.tileSize;

        gp.obj[12] = new OBJ_FrontDoor(gp);
        gp.obj[12].worldX = 13 * gp.tileSize;
        gp.obj[12].worldY = 6 * gp.tileSize;

        gp.obj[13] = new OBJ_FrontDoor(gp);
        gp.obj[13].worldX = 27 * gp.tileSize;
        gp.obj[13].worldY = 6 * gp.tileSize;

        gp.obj[7] = new OBJ_TelephoneHall(gp);
        gp.obj[7].worldX = 14 * gp.tileSize;
        gp.obj[7].worldY = 5 * gp.tileSize;

        gp.obj[8] = new OBJ_StairsHall(gp);
        gp.obj[8].worldX = 16 * gp.tileSize;
        gp.obj[8].worldY = 5 * gp.tileSize;

        gp.obj[10] = new OBJ_FrontDoorKey(gp);
        gp.obj[10].worldX = 25 * gp.tileSize;
        gp.obj[10].worldY = 12 * gp.tileSize;

        gp.obj[11] = new OBJ_MumsChair(gp);
        gp.obj[11].worldX = 16 * gp.tileSize;
        gp.obj[11].worldY = 13 * gp.tileSize;

        gp.obj[5] = new OBJ_FrontDoorOpen(gp);
        gp.obj[5].worldX = 13 * gp.tileSize;
        gp.obj[5].worldY = 6 * gp.tileSize;

        gp.obj[6] = new OBJ_FrontDoorOpen(gp);
        gp.obj[6].worldX = 27 * gp.tileSize;
        gp.obj[6].worldY = 6 * gp.tileSize;

        gp.obj[2] = new OBJ_InsideDoorOpen(gp);
        gp.obj[2].worldX = 15 * gp.tileSize;
        gp.obj[2].worldY = 7 * gp.tileSize;

        gp.obj[3] = new OBJ_InsideDoorOpen(gp);
        gp.obj[3].worldX = 23 * gp.tileSize;
        gp.obj[3].worldY = 7 * gp.tileSize;

        gp.obj[4] = new OBJ_InsideDoorOpenSideways(gp);
        gp.obj[4].worldX = 19 * gp.tileSize;
        gp.obj[4].worldY = 12 * gp.tileSize;

        gp.obj[17] = new OBJ_BackGateOpen(gp);
        gp.obj[17].worldX = 28 * gp.tileSize;
        gp.obj[17].worldY = 4 * gp.tileSize;

        gp.obj[18] = new OBJ_BackGate(gp);
        gp.obj[18].worldX = 28 * gp.tileSize;
        gp.obj[18].worldY = 4 * gp.tileSize;

        gp.obj[19] = new OBJ_FrontGateOpen(gp);
        gp.obj[19].worldX = 7 * gp.tileSize;
        gp.obj[19].worldY = 3 * gp.tileSize;

        gp.obj[20] = new OBJ_FrontGate(gp);
        gp.obj[20].worldX = 7 * gp.tileSize;
        gp.obj[20].worldY = 3 * gp.tileSize;

        gp.obj[21] = new OBJ_Bin_Blue(gp);
        gp.obj[21].worldX = 24 * gp.tileSize;
        gp.obj[21].worldY = 2 * gp.tileSize;

        gp.obj[22] = new OBJ_Bin_Green(gp);
        gp.obj[22].worldX = 23 * gp.tileSize;
        gp.obj[22].worldY = 2 * gp.tileSize;

        gp.obj[23] = new OBJ_Bin_Grey(gp);
        gp.obj[23].worldX = 22 * gp.tileSize;
        gp.obj[23].worldY = 2 * gp.tileSize;

        gp.obj[24] = new OBJ_BackGateOpenSideways(gp);
        gp.obj[24].worldX = 20 * gp.tileSize;
        gp.obj[24].worldY = 8 * gp.tileSize;

        gp.obj[25] = new OBJ_BackGateSideways(gp);
        gp.obj[25].worldX = 20 * gp.tileSize;
        gp.obj[25].worldY = 8 * gp.tileSize;
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
}
