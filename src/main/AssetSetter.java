package main;

import entity.*;
import monster.MON_CarGoingDown;
import monster.MON_CarGoingUp;
import monster.MON_Spider;
import monster.MON_WaspSwarm;
import object.*;
import tile_interactive.IT_CookerTile;
import tile_interactive.IT_WaterTile;
import tile_interactive.IT_WeedTile;

import java.util.Objects;
import java.util.Random;

public class AssetSetter {
    final GamePanel gp;

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

    public void setObjectAfterStart(String name, int mapNum, int x, int y, boolean isItARandomMonsterDrop) { //finds first available slot in object array if setting object after start of game
        int startX = x - 2;
        int startY = y - 2;
        int thisTile = gp.tileM.mapTileNum[mapNum][x][y];
        Entity testEntity = new OBJ_Cupboard2(gp);

        int count = 0;
        for (int i = 0; i < gp.obj[gp.currentMap].length; i++) {
            if (gp.obj[mapNum][i] != null) {
                count++; //at end of loop, count will show the index of the next available slot in the array
            } else {
                break;
            }
        }

        int i = count;
        if (!gp.tileM.tile[thisTile].collision && !gp.player.checkIfObjectInWay(testEntity)) {
            switch (name) { //chooses object
                case OBJ_PipsBone.OBJ_NAME -> {
                    gp.obj[mapNum][i] = new OBJ_PipsBone(gp);
                    boneX = x * gp.tileSize;
                    boneY = y * gp.tileSize;
                }
                case OBJ_ChoppedChickenPhoebe.OBJ_NAME -> {
                    gp.obj[mapNum][i] = new OBJ_ChoppedChickenPhoebe(gp);
                    choppedChickenPhoebeX = x * gp.tileSize;
                    choppedChickenPhoebeY = y * gp.tileSize;
                }
                case OBJ_ChoppedChickenPip.OBJ_NAME -> {
                    gp.obj[mapNum][i] = new OBJ_ChoppedChickenPip(gp);
                    choppedChickenPipX = x * gp.tileSize;
                    choppedChickenPipY = y * gp.tileSize;
                }
                case OBJ_GrandmasCardigan.OBJ_NAME -> gp.obj[mapNum][i] = new OBJ_GrandmasCardigan(gp);
                case OBJ_Spatula.OBJ_NAME -> gp.obj[mapNum][i] = new OBJ_Spatula(gp);
                case OBJ_Hatchet.OBJ_NAME -> gp.obj[mapNum][i] = new OBJ_Hatchet(gp);
                case OBJ_SuperCoin.OBJ_NAME -> gp.obj[mapNum][i] = new OBJ_SuperCoin(gp);
                case OBJ_Shovel.OBJ_NAME -> gp.obj[mapNum][i] = new OBJ_Shovel(gp);
                case OBJ_Lavender_Crocs.OBJ_NAME -> gp.obj[mapNum][i] = new OBJ_Lavender_Crocs(gp);
                case OBJ_Chicken.OBJ_NAME -> gp.obj[mapNum][i] = new OBJ_Chicken(gp);
                case OBJ_ChoppedChicken.OBJ_NAME -> gp.obj[mapNum][i] = new OBJ_ChoppedChicken(gp);
                case OBJ_Mop.OBJ_NAME -> gp.obj[mapNum][i] = new OBJ_Mop(gp);
                case OBJ_WaspNest.OBJ_NAME -> gp.obj[mapNum][i] = new OBJ_WaspNest(gp);
                case OBJ_Lighter.OBJ_NAME -> gp.obj[mapNum][i] = new OBJ_Lighter(gp);
                case OBJ_BookHutKey.OBJ_NAME -> gp.obj[mapNum][i] = new OBJ_BookHutKey(gp);
                case OBJ_Tutorial_TileSelectorMarker.OBJ_NAME -> gp.obj[mapNum][i] = new OBJ_Tutorial_TileSelectorMarker(gp);
                case OBJ_Tutorial_Arrow_Right.OBJ_NAME -> gp.obj[mapNum][i] = new OBJ_Tutorial_Arrow_Right(gp);
                case OBJ_TruckTipCooker.OBJ_NAME -> gp.obj[mapNum][i] = new OBJ_TruckTipCooker(gp);
            }

            gp.obj[mapNum][i].worldX = x * gp.tileSize;
            gp.obj[mapNum][i].worldY = y * gp.tileSize;
        } else if (isItARandomMonsterDrop) {
            testEntity.worldX = startX;
            testEntity.worldY = startY;
            while (gp.tileM.tile[thisTile].collision || gp.player.checkIfObjectInWay(testEntity) || (testEntity.worldX < 11 || testEntity.worldX > 60) || (testEntity.worldY < 5 || testEntity.worldY > 19)) {
                testEntity.worldX++;
                if (testEntity.worldX - startX > 4) {
                    testEntity.worldY++;
                    testEntity.worldX = startX;
                }
                if (testEntity.worldY - startY > 4) {
                    break;
                }
                thisTile = gp.tileM.mapTileNum[mapNum][testEntity.worldX][testEntity.worldY];
            }
            testEntity.worldX = testEntity.worldX * gp.tileSize;
            testEntity.worldY = testEntity.worldY * gp.tileSize;
            //noinspection LoopStatementThatDoesntLoop
            for (int j = 0; j < gp.obj[1].length; //noinspection UnusedAssignment
                 j++) {
                switch (name) { //chooses object
                    case OBJ_PipsBone.OBJ_NAME -> {
                        gp.obj[mapNum][i] = new OBJ_PipsBone(gp);
                        boneX = x * gp.tileSize;
                        boneY = y * gp.tileSize;
                    }
                    case OBJ_ChoppedChickenPhoebe.OBJ_NAME -> {
                        gp.obj[mapNum][i] = new OBJ_ChoppedChickenPhoebe(gp);
                        choppedChickenPhoebeX = x * gp.tileSize;
                        choppedChickenPhoebeY = y * gp.tileSize;
                    }
                    case OBJ_ChoppedChickenPip.OBJ_NAME -> {
                        gp.obj[mapNum][i] = new OBJ_ChoppedChickenPip(gp);
                        choppedChickenPipX = x * gp.tileSize;
                        choppedChickenPipY = y * gp.tileSize;
                    }
                    case OBJ_GrandmasCardigan.OBJ_NAME -> gp.obj[mapNum][i] = new OBJ_GrandmasCardigan(gp);
                    case OBJ_Spatula.OBJ_NAME -> gp.obj[mapNum][i] = new OBJ_Spatula(gp);
                    case OBJ_Hatchet.OBJ_NAME -> gp.obj[mapNum][i] = new OBJ_Hatchet(gp);
                    case OBJ_SuperCoin.OBJ_NAME -> gp.obj[mapNum][i] = new OBJ_SuperCoin(gp);
                    case OBJ_Shovel.OBJ_NAME -> gp.obj[mapNum][i] = new OBJ_Shovel(gp);
                    case OBJ_Lavender_Crocs.OBJ_NAME -> gp.obj[mapNum][i] = new OBJ_Lavender_Crocs(gp);
                    case OBJ_Chicken.OBJ_NAME -> gp.obj[mapNum][i] = new OBJ_Chicken(gp);
                    case OBJ_ChoppedChicken.OBJ_NAME -> gp.obj[mapNum][i] = new OBJ_ChoppedChicken(gp);
                    case OBJ_Mop.OBJ_NAME -> gp.obj[mapNum][i] = new OBJ_Mop(gp);
                    case OBJ_WaspNest.OBJ_NAME -> gp.obj[mapNum][i] = new OBJ_WaspNest(gp);
                    case OBJ_Lighter.OBJ_NAME -> gp.obj[mapNum][i] = new OBJ_Lighter(gp);
                    case OBJ_BookHutKey.OBJ_NAME -> gp.obj[mapNum][i] = new OBJ_BookHutKey(gp);
                    case OBJ_Tutorial_TileSelectorMarker.OBJ_NAME -> gp.obj[mapNum][i] = new OBJ_Tutorial_TileSelectorMarker(gp);
                    case OBJ_Tutorial_Arrow_Right.OBJ_NAME -> gp.obj[mapNum][i] = new OBJ_Tutorial_Arrow_Right(gp);
                }

                gp.obj[mapNum][j].worldX = testEntity.worldX;
                gp.obj[mapNum][j].worldY = testEntity.worldY;
                break;
            }
        }
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
            case "Andrea" -> gp.npc[mapNum][i] = new NPC_Andrea(gp);
            case "TipDude" -> gp.npc[mapNum][i] = new NPC_TipDude(gp);
        }

        gp.npc[mapNum][i].worldX = x * gp.tileSize;
        gp.npc[mapNum][i].worldY = y * gp.tileSize;
    }

    public void setObject() {
        int mapNum = 0;
        int i = 0;

        gp.obj[mapNum][i] = new OBJ_InsideDoorSideways(gp);
        gp.obj[mapNum][i].worldX = 22 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 17 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_InsideDoorSideways(gp);
        gp.obj[mapNum][i].worldX = 22 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 23 * gp.tileSize;
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

        gp.obj[mapNum][i] = new OBJ_KitchenPantry(gp);
        gp.obj[mapNum][i].worldX = 23 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 13 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Music_Center(gp);
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

        gp.obj[mapNum][i] = new OBJ_Cooker(gp);
        gp.obj[mapNum][i].worldX = 23 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 14 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Fridge(gp);
        gp.obj[mapNum][i].worldX = 25 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 10 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_TV_Lounge(gp);
        gp.obj[mapNum][i].worldX = 21 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 13 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_KitchenSink(gp);
        gp.obj[mapNum][i].worldX = 29 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 15 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Cupboard2(gp);
        gp.obj[mapNum][i].worldX = 24 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 10 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Cupboard1(gp);
        gp.obj[mapNum][i].worldX = 17 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 13 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_FishTankDrawer(gp);
        gp.obj[mapNum][i].worldX = 17 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 18 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_LightPills(gp);
        gp.obj[mapNum][i].worldX = 18 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 16 * gp.tileSize - 10;
        i++;

        gp.obj[mapNum][i] = new OBJ_Pills(gp);
        gp.obj[mapNum][i].worldX = 17 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 16 * gp.tileSize - 10;
        i++;

        gp.obj[mapNum][i] = new OBJ_GuitarElectric(gp);
        gp.obj[mapNum][i].worldX = 20 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 13 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_GuitarAcoustic(gp);
        gp.obj[mapNum][i].worldX = 21 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 18 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_TelephoneHall(gp);
        gp.obj[mapNum][i].worldX = 17 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 10 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_FrontBackDoorKey(gp);
        gp.obj[mapNum][i].worldX = 28 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 17 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Spatula(gp);
        gp.obj[mapNum][i].worldX = 24 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 15 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_PipsBone(gp);
        gp.obj[mapNum][i].worldX = 26 * gp.tileSize; boneX = 26;
        gp.obj[mapNum][i].worldY = 10 * gp.tileSize; boneY = 10;
        i++;

        gp.obj[mapNum][i] = new OBJ_HouseRoof(gp);
        gp.obj[mapNum][i].worldX = 16 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 9 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_GarageDoorWhite(gp);
        gp.obj[mapNum][i].worldX = 44 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 10 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_GarageRoof(gp);
        gp.obj[mapNum][i].worldX = 44 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 7 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Shovel(gp);
        gp.obj[mapNum][i].worldX = 45 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 8 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_TreeTopGarden_1(gp);
        gp.obj[mapNum][i].worldX = 57 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 14 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_TreeTopGarden_2(gp);
        gp.obj[mapNum][i].worldX = 59 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 14 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_TreeTopGarden_3(gp);
        gp.obj[mapNum][i].worldX = 57 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 16 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_TreeTopGarden_4(gp);
        gp.obj[mapNum][i].worldX = 59 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 16 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_TreeTopGarden_5(gp);
        gp.obj[mapNum][i].worldX = 57 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 18 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_TreeTopGarden_6(gp);
        gp.obj[mapNum][i].worldX = 59 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 18 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_OutdoorGateVertical(gp);
        gp.obj[mapNum][i].worldX = 44 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 19 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_OutdoorGateVertical(gp);
        gp.obj[mapNum][i].worldX = 44 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 6 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Bucket(gp);
        gp.obj[mapNum][i].worldX = 31 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 19 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_FrontDoor(gp);
        gp.obj[mapNum][i].worldX = 16 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 11 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_BackDoor(gp);
        gp.obj[mapNum][i].worldX = 30 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 11 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Music_Notes(gp);
        gp.obj[mapNum][i].worldX = 16 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 14 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Music_Notes(gp);
        gp.obj[mapNum][i].worldX = 19 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 12 * gp.tileSize;
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

        gp.obj[mapNum][i] = new OBJ_BlockOfWood(gp);
        gp.obj[mapNum][i].worldX = 34 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 11 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Camper1(gp);
        gp.obj[mapNum][i].worldX = 54 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 9 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Camper2(gp);
        gp.obj[mapNum][i].worldX = 55 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 9 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Camper3(gp);
        gp.obj[mapNum][i].worldX = 56 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 9 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Camper4(gp);
        gp.obj[mapNum][i].worldX = 57 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 9 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Camper5(gp);
        gp.obj[mapNum][i].worldX = 58 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 9 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Camper6(gp);
        gp.obj[mapNum][i].worldX = 59 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 9 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Camper7(gp);
        gp.obj[mapNum][i].worldX = 54 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 10 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Camper8(gp);
        gp.obj[mapNum][i].worldX = 55 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 10 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Camper9(gp);
        gp.obj[mapNum][i].worldX = 56 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 10 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Camper10(gp);
        gp.obj[mapNum][i].worldX = 57 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 10 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Camper11(gp);
        gp.obj[mapNum][i].worldX = 58 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 10 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Camper12(gp);
        gp.obj[mapNum][i].worldX = 59 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 10 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Bookhut1_Left(gp);
        gp.obj[mapNum][i].worldX = 36 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 8 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Bookhut1_Center(gp);
        gp.obj[mapNum][i].worldX = 37 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 8 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Bookhut1_Right(gp);
        gp.obj[mapNum][i].worldX = 38 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 8 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Bookhut2_Left(gp);
        gp.obj[mapNum][i].worldX = 36 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 7 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Bookhut2_Center(gp);
        gp.obj[mapNum][i].worldX = 37 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 7 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Bookhut2_Right(gp);
        gp.obj[mapNum][i].worldX = 38 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 7 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Bookhut3_Left(gp);
        gp.obj[mapNum][i].worldX = 36 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 6 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Bookhut3_Center(gp);
        gp.obj[mapNum][i].worldX = 37 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 6 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_MagicQuizBook(gp);
        gp.obj[mapNum][i].worldX = 37 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 6 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Bookhut3_Right(gp);
        gp.obj[mapNum][i].worldX = 38 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 6 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Toolhut1_Left(gp);
        gp.obj[mapNum][i].worldX = 32 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 8 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Toolhut1_Center(gp);
        gp.obj[mapNum][i].worldX = 33 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 8 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Toolhut1_Right(gp);
        gp.obj[mapNum][i].worldX = 34 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 8 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Toolhut2_Left(gp);
        gp.obj[mapNum][i].worldX = 32 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 7 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Toolhut2_Center(gp);
        gp.obj[mapNum][i].worldX = 33 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 7 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Toolhut2_Right(gp);
        gp.obj[mapNum][i].worldX = 34 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 7 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Toolhut3_Left(gp);
        gp.obj[mapNum][i].worldX = 32 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 6 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Toolhut3_Center(gp);
        gp.obj[mapNum][i].worldX = 33 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 6 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Flammable_Spray(gp);
        gp.obj[mapNum][i].worldX = 33 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 6 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Toolhut3_Right(gp);
        gp.obj[mapNum][i].worldX = 34 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 6 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_CarTopDrive(gp);
        gp.obj[mapNum][i].worldX = 55 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 7 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Trampoline(gp);
        gp.obj[mapNum][i].worldX = 56 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 2 * gp.tileSize - 20;
        i++;


        mapNum = 1;  //ADD OBJECTS TO NEXT MAP LIKE THIS

        gp.obj[mapNum][i] = new OBJ_InsideDoorSideways(gp);
        gp.obj[mapNum][i].worldX = 23 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 23 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_InsideDoor(gp);
        gp.obj[mapNum][i].worldX = 25 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 13 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_InsideDoor(gp);
        gp.obj[mapNum][i].worldX = 22 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 13 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_InsideDoorSideways(gp);
        gp.obj[mapNum][i].worldX = 21 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 12 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_HouseRoof(gp);
        gp.obj[mapNum][i].worldX = 16 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 9 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_BackGate(gp);
        gp.obj[mapNum][i].worldX = 31 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 9 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_BlockOfWood(gp);
        gp.obj[mapNum][i].worldX = 34 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 11 * gp.tileSize;
        i++;

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

        gp.obj[mapNum][i] = new OBJ_Bucket(gp);
        gp.obj[mapNum][i].worldX = 31 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 19 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_BathLeft(gp);
        gp.obj[mapNum][i].worldX = 28 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 10 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_BathRight(gp);
        gp.obj[mapNum][i].worldX = 29 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 10 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Bookhut1_Left(gp);
        gp.obj[mapNum][i].worldX = 36 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 8 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Bookhut1_Center(gp);
        gp.obj[mapNum][i].worldX = 37 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 8 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Bookhut1_Right(gp);
        gp.obj[mapNum][i].worldX = 38 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 8 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Bookhut2_Left(gp);
        gp.obj[mapNum][i].worldX = 36 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 7 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Bookhut2_Center(gp);
        gp.obj[mapNum][i].worldX = 37 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 7 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Bookhut2_Right(gp);
        gp.obj[mapNum][i].worldX = 38 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 7 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Bookhut3_Left(gp);
        gp.obj[mapNum][i].worldX = 36 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 6 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Bookhut3_Center(gp);
        gp.obj[mapNum][i].worldX = 37 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 6 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Bookhut3_Right(gp);
        gp.obj[mapNum][i].worldX = 38 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 6 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Toolhut1_Left(gp);
        gp.obj[mapNum][i].worldX = 32 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 8 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Toolhut1_Center(gp);
        gp.obj[mapNum][i].worldX = 33 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 8 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Toolhut1_Right(gp);
        gp.obj[mapNum][i].worldX = 34 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 8 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Toolhut2_Left(gp);
        gp.obj[mapNum][i].worldX = 32 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 7 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Toolhut2_Center(gp);
        gp.obj[mapNum][i].worldX = 33 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 7 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Toolhut2_Right(gp);
        gp.obj[mapNum][i].worldX = 34 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 7 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Toolhut3_Left(gp);
        gp.obj[mapNum][i].worldX = 32 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 6 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Toolhut3_Center(gp);
        gp.obj[mapNum][i].worldX = 33 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 6 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_Toolhut3_Right(gp);
        gp.obj[mapNum][i].worldX = 34 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 6 * gp.tileSize;
        //i++;
    }

    public void setNPC() {
        int mapNum = 0; //DOWNSTAIRS
        int i = 0;

        gp.npc[mapNum][i] = new NPC_Dad(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*18;
        gp.npc[mapNum][i].worldY = gp.tileSize*14;
        gp.npc[mapNum][i].getImage();
        i++;

        gp.npc[mapNum][i] = new NPC_Phoebe(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*23; //23
        gp.npc[mapNum][i].worldY = gp.tileSize*17; //17
        gp.npc[mapNum][i].getImage();
        i++;

        gp.npc[mapNum][i] = new NPC_Pip(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*20;
        gp.npc[mapNum][i].worldY = gp.tileSize*11;
        gp.npc[mapNum][i].getImage();
        i++;

        gp.npc[mapNum][i] = new NPC_Merchant(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*62;
        gp.npc[mapNum][i].worldY = gp.tileSize*9;
        gp.npc[mapNum][i].getImage();
        i++;

        gp.npc[mapNum][i] = new NPC_Baldy(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*18;
        gp.npc[mapNum][i].worldY = gp.tileSize*23;
        gp.npc[mapNum][i].getImage();
        i++;

        gp.npc[mapNum][i] = new NPC_RustyCooker(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*39;
        gp.npc[mapNum][i].worldY = gp.tileSize*8;
//      i++;

        mapNum = 1;  //UPSTAIRS
        i = 0;

        gp.npc[mapNum][i] = new NPC_Dad(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*50;
        gp.npc[mapNum][i].worldY = gp.tileSize*5;
        gp.npc[mapNum][i].getImage();
        i++;

        gp.npc[mapNum][i] = new NPC_Phoebe(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*50;
        gp.npc[mapNum][i].worldY = gp.tileSize*5;
        gp.npc[mapNum][i].getImage();
        i++;

        gp.npc[mapNum][i] = new NPC_Pip(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*50;
        gp.npc[mapNum][i].worldY = gp.tileSize*5;
        gp.npc[mapNum][i].getImage();
        //i++;


    }

    public int setMonster(String type, int monsterNumber, int x, int y, int mapNum, boolean randomizeLocation) {
        switch (type) {
            case "Spider" -> {
                gp.monster[mapNum][monsterNumber] = new MON_Spider(gp);
                gp.monster[mapNum][monsterNumber].newMonster = true;
            }
            case "WaspSwarm" -> {
                gp.monster[mapNum][monsterNumber] = new MON_WaspSwarm(gp);
                gp.monster[mapNum][monsterNumber].newMonster = true;
            }
            case "CarGoingDown" -> {
                gp.monster[mapNum][monsterNumber] = new MON_CarGoingDown(gp);
                gp.monster[mapNum][monsterNumber].newMonster = true;
            }
            case "CarGoingUp" -> {
                gp.monster[mapNum][monsterNumber] = new MON_CarGoingUp(gp);
                gp.monster[mapNum][monsterNumber].newMonster = true;
            }
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

        if (gp.monster[mapNum][monsterNumber].worldX/gp.tileSize > 16 && gp.monster[mapNum][monsterNumber].worldX/gp.tileSize < 30) {
            if (gp.monster[mapNum][monsterNumber].worldY/gp.tileSize > 9 && gp.monster[mapNum][monsterNumber].worldY/gp.tileSize < 20) {
                gp.monster[mapNum][monsterNumber].insideHouse = true;
                if (gp.player.insideHouse) {
                    gp.monster[mapNum][monsterNumber].getImage();
                }
            } else {
                gp.monster[mapNum][monsterNumber].insideHouse = false;
                if (!gp.player.insideHouse) {
                    gp.monster[mapNum][monsterNumber].getImage();
                }
            }
        } else {
            gp.monster[mapNum][monsterNumber].insideHouse = false;
            if (!gp.player.insideHouse) {
                gp.monster[mapNum][monsterNumber].getImage();
            }
        }

        monsterNumber++; //monster counter increments so that next call of method adds to next slot in monster array

        return monsterNumber;
    }

    public int setInteractiveTilesAfterStart(int missionTrigger) {
        int count = 0;
        int colToAdd;
        int rowToAdd;
        int mapNum = 0;

        if (missionTrigger == MissionStates.MOP_UP_THE_SHOWER_WATER) { //need a better way to do this
            mapNum = 1;
            colToAdd = 27;
            rowToAdd = 12;
            for (int i = 0; i < gp.iTile[mapNum].length; i++) {
                if (gp.iTile[mapNum][i] == null) {
                    gp.iTile[mapNum][i] = new IT_WaterTile(gp, colToAdd, rowToAdd); i++; count++;
                    colToAdd++;
                    if (colToAdd > 29) {
                        colToAdd = 28;
                        rowToAdd--;
                        if (rowToAdd < 11) {
                            break;
                        }
                    }
                }
            }
        }
        if (missionTrigger == MissionStates.DRAG_COOKER_TO_BINS) {
//          mapNum = 0; // already set above
            colToAdd = 60;
            rowToAdd = 12;
            for (int i = 0; i < gp.iTile[mapNum].length; i++) {
                if (gp.iTile[mapNum][i] == null) {
                    gp.iTile[mapNum][i] = new IT_CookerTile(gp, colToAdd, rowToAdd); i++; count++;
                }
            }
        }
        return count;
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
        gp.iTile[mapNum][i] = new IT_WeedTile(gp, 16, 7);


        return weedCount; //return number of weeds to dig up
    }

    public void removeCutSceneObjectFromMap(String entityName, int mapNum) {
        for (int i = 0; i < gp.obj[mapNum].length; i++) {
            if (gp.obj[mapNum][i] != null && Objects.equals(gp.obj[mapNum][i].name, entityName)) {
                gp.obj[mapNum][i] = null;
            }
        }
    }
}
