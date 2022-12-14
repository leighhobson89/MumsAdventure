package data;

import entity.*;
import main.GamePanel;
import main.MissionStates;
import monster.MON_CarGoingDown;
import monster.MON_CarGoingUp;
import monster.MON_Spider;
import monster.MON_WaspSwarm;
import object.OBJ_Flammable_Spray;
import tile_interactive.*;

import java.io.*;
import java.util.Objects;

public class SaveLoad {
    final GamePanel gp;

    public SaveLoad(GamePanel gp) {
        this.gp = gp;
    }
    boolean loadWithBoneEquipped;
    boolean loadWithChoppedChickenEquipped;

    public Entity getMonster(String itemName) {

        return switch (itemName) {
            case "Spider" -> new MON_Spider(gp);
            case "WaspSwarm" -> new MON_WaspSwarm(gp);
            case "CarGoingDown" -> new MON_CarGoingDown(gp);
            case "CarGoingUp" -> new MON_CarGoingUp(gp);
            default -> null;
        };
    }

    public Entity getNpc(String itemName) {

        return switch (itemName) {
            case "Andrea" -> new NPC_Andrea(gp);
            case "Baldy" -> new NPC_Baldy(gp);
            case "Dad" -> new NPC_Dad(gp);
            case "Merchant" -> new NPC_Merchant(gp);
            case "Phoebe" -> new NPC_Phoebe(gp);
            case "Pip" -> new NPC_Pip(gp);
            case "OldCooker" -> new NPC_RustyCooker(gp);
            default -> null;
        };
    }

    public Entity getITile(String itemName, int col, int row) {

        return switch (itemName) {
            case "IT_Weed" -> new IT_WeedTile(gp, col, row);
            case "IT_RockeryBare" -> new IT_BareRockery(gp, col, row);
            case "IT_Water" -> new IT_WaterTile(gp, col, row);
            case "IT_Bathroom" -> new IT_Bathroom(gp, col, row);
            default -> null;
        };
    }

    public void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save.dat"));

            DataStorage ds = new DataStorage();

            //DEBUG - PROJECTILE WORKAROUND
            Entity tempProjectile = gp.player.currentProjectile;
            gp.player.currentProjectile = null;
            //END DEBUG

            //MUSIC CONFIG
            ds.musicPlaying = gp.keyH.musicPlaying;

            //PLAYER POSITION
            ds.playerWorldX = gp.player.worldX;
            ds.playerWorldY = gp.player.worldY;

            //MAP SELECTION
            ds.currentMap = gp.currentMap;

            // PLAYER STATS
            ds.level = gp.player.level;
            ds.maxStress = gp.player.maxStress;
            ds.stressLevel = gp.player.stressLevel;
            ds.maxMana = gp.player.maxMana;
            ds.mana = gp.player.mana;
            ds.strength = gp.player.strength;
            ds.dexterity = gp.player.dexterity;
            ds.exp = gp.player.exp;
            ds.nextLevelExp = gp.player.nextLevelExp;
            ds.coin = gp.player.coin;
            ds.dialogueSet = gp.player.dialogueSet;
            ds.doorUnlockedCount = gp.player.doorUnlockedCount;
            ds.weedCount = gp.player.weedCount;
            ds.timesPassedOut = gp.player.timesPassedOut;
            ds.pillsConsumableNow = gp.player.pillsConsumableNow;
            ds.boneIndex = gp.player.boneIndex;
            ds.chickenIndex = gp.player.chickenIndex;
            ds.choppedChickenCount = gp.player.choppedChickenCount;
            ds.thrownChickenCount = gp.player.thrownChickenCount;
            ds.spiderCount = gp.player.spiderCount;
            ds.missionState = gp.player.missionState;
            ds.missionSubState = gp.player.missionSubstate;
            ds.missionList = gp.player.missionList;
            ds.readyForNextPhoneMission = gp.player.readyForNextPhoneMission;
            ds.missionToSet = gp.player.missionToSet;
            ds.setShovelFlag = gp.player.setShovelFlag;
            ds.repeatSfx = gp.player.repeatSfx;
            ds.itemToThrow = gp.player.itemToThrow;
            ds.startCounterPhoebeEatingChicken = gp.player.startCounterPhoebeEatingChicken;
            ds.startCounterPipEatingChicken = gp.player.startCounterPipEatingChicken;
            ds.phoebeEatingChickenCounter = gp.player.phoebeEatingChickenCounter;
            ds.pipEatingChickenCounter = gp.player.pipEatingChickenCounter;
            ds.phoebeChickenEaten = gp.player.phoebeChickenEaten;
            ds.pipChickenEaten = gp.player.pipChickenEaten;
            ds.phoneRinging = gp.player.phoneRinging;
            ds.nextMissionIsPhoneMission = gp.player.nextMissionIsPhoneMission;
            ds.showerAlreadyRan = gp.player.showerAlreadyRan;
            ds.showerCounterStart = gp.player.showerCounterStart;
            ds.showerCounter = gp.player.showerCounter;
            ds.waterTileCount = gp.player.waterTileCount;
            ds.blockWoodState = gp.player.blockWoodState;
            ds.backGateState = gp.player.backGateState;
            ds.bookHutState = gp.player.bookHutState;
            ds.quizScoreCount = gp.player.quizScoreCount;
            ds.stainRemoverUsed = gp.player.stainRemoverUsed;
            ds.toolHutKeyDropped = gp.player.toolHutKeyDropped;
            ds.toolHutState = gp.player.toolHutState;
            ds.bucketFull = gp.player.bucketFull;
            ds.waspNestState = gp.player.waspNestState;
            ds.pillsInProcess = gp.player.pillsInProcess;
            ds.pillsCounter = gp.player.pillsCounter;
            ds.lightPillsInProcess = gp.player.lightPillsInProcess;
            ds.lightPillsCounter = gp.player.lightPillsCounter;
            ds.carCountDown = gp.player.carCountDown;
            ds.carCountUp = gp.player.carCountUp;
            ds.npcCanWalkOnWhenFollowing = gp.player.npcCanWalkOnWhenFollowing;
            ds.inLivingRoom = gp.player.inLivingRoom;
            ds.dadHasGuitar = gp.player.dadHasGuitar;
            ds.dadPlayingGuitar = gp.player.dadPlayingGuitar;
            ds.musicCentreOn = gp.player.musicCentreOn;
            ds.dadOption = gp.player.dadOption;
            ds.andreaSafe = gp.player.andreaSafe;

            //PLAYER OUTFIT
            ds.colorOutfit = gp.ui.colorOutfit;
            ds.outfitChosen = gp.ui.outfitChosen;

            //PLAYER INVENTORY
            for (int i = 0; i < gp.player.inventory.size(); i++) {
                ds.itemNames.add(gp.player.inventory.get(i).name);
                ds.itemAmounts.add(gp.player.inventory.get(i).amount);
            }

            //PLAYER EQUIPPED ITEMS
            if (gp.player.currentWeapon != null) {
                ds.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
                ds.savedWithAWeaponEquipped = true;
            }
            if (gp.player.currentArmour != null) {
                ds.currentArmourSlot = gp.player.getCurrentArmourSlot();
                ds.savedWithAnArmourEquipped = true;
            }
            if (gp.player.currentProjectile != null) {
                ds.currentProjectileSlot = gp.player.getCurrentProjectileSlot();
                if (Objects.equals(gp.player.currentProjectile.name, "Chopped Chicken")) {
                    ds.savedWithChoppedChickenEquipped = true;
                } else if (Objects.equals(gp.player.currentProjectile.name, "Pip's Bone")) {
                    ds.savedWithBoneEquipped = true;
                }
            }


            //OBJECTS ON MAP
            ds.mapObjectNames = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldX = new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldY = new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectLootNames = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectOpened = new boolean[gp.maxMap][gp.obj[1].length];

            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                for (int i = 0; i < gp.obj[1].length; i++) {
                    if (gp.obj[mapNum][i] == null) {
                        ds.mapObjectNames[mapNum][i] = "NA";
                    } else {
                        ds.mapObjectNames[mapNum][i] = gp.obj[mapNum][i].name;
                        ds.mapObjectWorldX[mapNum][i] = gp.obj[mapNum][i].worldX;
                        ds.mapObjectWorldY[mapNum][i] = gp.obj[mapNum][i].worldY;
                        if (gp.obj[mapNum][i].loot != null) {
                            ds.mapObjectLootNames[mapNum][i] = gp.obj[mapNum][i].loot.name;
                        }
                        ds.mapObjectOpened[mapNum][i] = gp.obj[mapNum][i].opened;
                    }
                }
            }

            //MONSTERS ON MAP
            ds.mapMonsterNames = new String[gp.maxMap][gp.monster[1].length];
            ds.mapMonsterWorldX = new int[gp.maxMap][gp.monster[1].length];
            ds.mapMonsterWorldY = new int[gp.maxMap][gp.monster[1].length];
            ds.mapMonsterHealth = new int[gp.maxMap][gp.monster[1].length];
            ds.mapMonsterDirection = new String[gp.maxMap][gp.monster[1].length];

            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                for (int i = 0; i < gp.monster[1].length; i++) {
                    if (gp.monster[mapNum][i] == null) {
                        ds.mapMonsterNames[mapNum][i] = "NA";
                    } else {
                        ds.mapMonsterNames[mapNum][i] = gp.monster[mapNum][i].name;
                        ds.mapMonsterWorldX[mapNum][i] = gp.monster[mapNum][i].worldX;
                        ds.mapMonsterWorldY[mapNum][i] = gp.monster[mapNum][i].worldY;
                        ds.mapMonsterHealth[mapNum][i] = gp.monster[mapNum][i].stressLevel;
                        ds.mapMonsterDirection[mapNum][i] = gp.monster[mapNum][i].direction;
                    }
                }
            }

            //INTERACTIVE TILES ON MAP
            ds.mapITileNames = new String[gp.maxMap][gp.iTile[1].length];
            ds.mapITileWorldX = new int[gp.maxMap][gp.iTile[1].length];
            ds.mapITileWorldY = new int[gp.maxMap][gp.iTile[1].length];

            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                for (int i = 0; i < gp.iTile[1].length; i++) {
                    if (gp.iTile[mapNum][i] == null) {
                        ds.mapITileNames[mapNum][i] = "NA";
                    } else {
                        ds.mapITileNames[mapNum][i] = gp.iTile[mapNum][i].name;
                        ds.mapITileWorldX[mapNum][i] = gp.iTile[mapNum][i].worldX;
                        ds.mapITileWorldY[mapNum][i] = gp.iTile[mapNum][i].worldY;
                    }
                }
            }

            //NPCS ON MAP
            ds.andreaOnMap = gp.player.andreaOnMap;

            ds.mapNpcNames = new String[gp.maxMap][gp.npc[1].length];
            ds.mapNpcWorldX = new int[gp.maxMap][gp.npc[1].length];
            ds.mapNpcWorldY = new int[gp.maxMap][gp.npc[1].length];
            ds.mapNpcDirection = new String[gp.maxMap][gp.npc[1].length];

            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                for (int i = 0; i < gp.npc[1].length; i++) {
                    if (gp.npc[mapNum][i] == null) {
                        ds.mapNpcNames[mapNum][i] = "NA";
                    } else {
                        ds.mapNpcNames[mapNum][i] = gp.npc[mapNum][i].name;
                        ds.mapNpcWorldX[mapNum][i] = gp.npc[mapNum][i].worldX;
                        ds.mapNpcWorldY[mapNum][i] = gp.npc[mapNum][i].worldY;
                        ds.mapNpcDirection[mapNum][i] = gp.npc[mapNum][i].direction;
                    }
                }
            }

            //Write the DataStorage Object

            oos.writeObject(ds);

            //DEBUG
            //reset projectile for workaround
            gp.player.currentProjectile = tempProjectile;
            //END DEBUG

        } catch (Exception e) {
            System.out.println("Save Exception!");
        }
    }

    public void load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save.dat"));

            //Read the DataStorage Object
            DataStorage ds = (DataStorage)ois.readObject();

            //MUSIC CONFIG
            gp.keyH.musicPlaying = ds.musicPlaying;
            if (!gp.keyH.musicPlaying) {
                gp.stopMusic();
            }

            //MAP SELECTION
            gp.currentMap = ds.currentMap;

            //PLAYER POSITION
            gp.player.worldX = ds.playerWorldX;
            gp.player.worldY = ds.playerWorldY;

            //PLAYER STATS
            gp.player.level = ds.level;
            gp.player.maxStress = ds.maxStress;
            gp.player.stressLevel = ds.stressLevel;
            gp.player.maxMana = ds.maxMana;
            gp.player.mana = ds.mana;
            gp.player.strength = ds.strength;
            gp.player.dexterity = ds.dexterity;
            gp.player.exp = ds.exp;
            gp.player.nextLevelExp = ds.nextLevelExp;
            gp.player.coin = ds.coin;
            gp.player.doorUnlockedCount = ds.doorUnlockedCount;
            gp.player.timesPassedOut = ds.timesPassedOut;
            gp.player.pillsConsumableNow = ds.pillsConsumableNow;
            gp.player.boneIndex = ds.boneIndex;
            gp.player.chickenIndex = ds.chickenIndex;
            gp.player.savedWithAWeaponEquipped = ds.savedWithAWeaponEquipped;
            gp.player.savedWithAnArmourEquipped = ds.savedWithAnArmourEquipped;
            gp.player.dialogueSet = ds.dialogueSet;
            loadWithBoneEquipped = ds.savedWithBoneEquipped;
            loadWithChoppedChickenEquipped = ds.savedWithChoppedChickenEquipped;
            gp.player.weedCount = ds.weedCount;
            gp.player.spiderCount = ds.spiderCount;
            gp.player.missionState = ds.missionState;
            gp.player.missionSubstate = ds.missionSubState;
            gp.player.missionList = ds.missionList;
            gp.player.readyForNextPhoneMission = ds.readyForNextPhoneMission;
            gp.player.missionToSet = ds.missionToSet;
            gp.player.randomCounter = gp.player.setRandomCounter();
            gp.player.setShovelFlag = ds.setShovelFlag;
            gp.player.repeatSfx = ds.repeatSfx;
            gp.player.choppedChickenCount = ds.choppedChickenCount;
            gp.player.thrownChickenCount = ds.thrownChickenCount;
            gp.player.currentProjectile = ds.currentProjectile;
            gp.player.itemToThrow = ds.itemToThrow;
            gp.player.startCounterPhoebeEatingChicken = ds.startCounterPhoebeEatingChicken;
            gp.player.startCounterPipEatingChicken = ds.startCounterPipEatingChicken;
            gp.player.phoebeEatingChickenCounter = ds.phoebeEatingChickenCounter;
            gp.player.pipEatingChickenCounter = ds.pipEatingChickenCounter;
            gp.player.phoebeChickenEaten = ds.phoebeChickenEaten;
            gp.player.pipChickenEaten = ds.pipChickenEaten;
            gp.player.phoneRinging = ds.phoneRinging;
            gp.player.nextMissionIsPhoneMission = ds.nextMissionIsPhoneMission;
            gp.player.showerAlreadyRan = ds.showerAlreadyRan;
            gp.player.showerCounterStart = ds.showerCounterStart;
            gp.player.showerCounter = ds.showerCounter;
            gp.player.waterTileCount = ds.waterTileCount;
            gp.player.blockWoodState = ds.blockWoodState;
            gp.player.backGateState = ds.backGateState;
            gp.player.bookHutState = ds.bookHutState;
            gp.player.quizScoreCount = ds.quizScoreCount;
            gp.player.stainRemoverUsed = ds.stainRemoverUsed;
            gp.player.toolHutKeyDropped = ds.toolHutKeyDropped;
            gp.player.toolHutState = ds.toolHutState;
            gp.player.bucketFull = ds.bucketFull;
            gp.player.waspNestState = ds.waspNestState;
            gp.player.pillsInProcess = ds.pillsInProcess;
            gp.player.pillsCounter = ds.pillsCounter;
            gp.player.lightPillsInProcess = ds.lightPillsInProcess;
            gp.player.lightPillsCounter = ds.lightPillsCounter;
            gp.player.carCountDown = ds.carCountDown;
            gp.player.carCountUp = ds.carCountUp;
            gp.player.npcCanWalkOnWhenFollowing = ds.npcCanWalkOnWhenFollowing;
            gp.player.inLivingRoom = ds.inLivingRoom;
            gp.player.dadHasGuitar = ds.dadHasGuitar;
            gp.player.dadPlayingGuitar = ds.dadPlayingGuitar;
            gp.player.musicCentreOn = ds.musicCentreOn;
            gp.player.dadOption = ds.dadOption;
            gp.player.andreaSafe = ds.andreaSafe;

            if (loadWithBoneEquipped) {
                gp.player.boneCount = 1;
                gp.player.firstTimePickUpBone = false;
                gp.player.haveBoneResource = true;
            }

            if (loadWithChoppedChickenEquipped) {
                gp.player.haveChoppedChickenResource = true;
            }

            //PLAYER OUTFIT
            gp.ui.colorOutfit = ds.colorOutfit;
            gp.ui.outfitChosen = ds.outfitChosen;
            gp.player.getImage(gp.ui.colorOutfit, true);

            //PLAYER INVENTORY
            for (int i = 0; i < ds.itemNames.size(); i++) {
                if (ds.itemNames.get(i).equals("FlammableSprayWeapon")) {
                    gp.player.inventory.add(new OBJ_Flammable_Spray(gp));
                    for (int j = 0; j < gp.player.inventory.size(); j++) {
                        if (Objects.equals(gp.player.inventory.get(j).name, "FlammableSpray")) {
                            gp.player.inventory.get(i).isWeapon = true;
                            gp.player.inventory.get(i).type = gp.player.type_flaming_aerosol;
                            gp.player.inventory.get(i).down1 =  gp.player.inventory.get(i).image3;
                            gp.player.attack = gp.player.getAttack();
                            gp.player.getAttackImage(gp.ui.outfitChosen);
                            gp.player.inventory.get(i).name = "FlammableSprayWeapon";
                            gp.player.inventory.get(i).displayName = "Flaming Aerosol";
                            gp.player.inventory.get(i).description = "A Flaming Aerosol!";
                        }
                    }
                } else {
                    gp.player.inventory.add(gp.eGenerator.getObject(ds.itemNames.get(i)));
                    gp.player.inventory.get(i).amount = ds.itemAmounts.get(i);
                }
            }

            //PLAYER EQUIPPED ITEMS
            if (gp.player.savedWithAWeaponEquipped) {
                gp.player.currentWeapon = gp.player.inventory.get(ds.currentWeaponSlot);
            }
            if (gp.player.savedWithAnArmourEquipped) {
                gp.player.currentArmour = gp.player.inventory.get(ds.currentArmourSlot);
            }
            if (gp.player.savedWithAProjectileEquipped) {
                gp.player.currentProjectile = gp.player.inventory.get(ds.currentProjectileSlot);
            }
            if (gp.player.bucketFull) {
                for (int i = 0; i < gp.player.inventory.size(); i++) {
                    if (Objects.equals(gp.player.inventory.get(i).name, "Bucket")) {
                        gp.player.inventory.get(i).down1 = gp.player.inventory.get(i).image2;
                    }
                }
            }
            gp.player.getAttack();
            gp.player.getDefense();
            gp.player.getAttackImage(gp.ui.outfitChosen);

            //OBJECTS ON MAP
            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                for (int i = 0; i < gp.obj[1].length; i++) {
                    if (ds.mapObjectNames[mapNum][i].equals("NA")) {
                        gp.obj[mapNum][i] = null;
                    } else {
                        gp.obj[mapNum][i] = gp.eGenerator.getObject(ds.mapObjectNames[mapNum][i]);
                        gp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
                        gp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];
                        if (ds.mapObjectLootNames[mapNum][i] != null) {
                            gp.obj[mapNum][i].loot = gp.eGenerator.getObject(ds.mapObjectLootNames[mapNum][i]);
                        }
                        gp.obj[mapNum][i].opened = ds.mapObjectOpened[mapNum][i];
                        if (gp.obj[mapNum][i].opened) {
                            switch (gp.obj[mapNum][i].name) { //add cases here for objects that could be loaded in an open state but don't have 2 images
                                case "Cupboard2", "Bin_Green", "BathLeft", "BathRight", "Fridge", "FishTankDrawer" -> {
                                    gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].image;
                                    gp.obj[mapNum][i].collision = true;
                                }
                                default -> {
                                    gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].image2;
                                    gp.obj[mapNum][i].collision = false;
                                }
                            }
                        }
                    }
                }
            }

            //MONSTERS ON MAP
            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                for (int i = 0; i < gp.monster[1].length; i++) {
                    if (ds.mapMonsterNames[mapNum][i].equals("NA")) {
                        gp.monster[mapNum][i] = null;
                    } else {
                        gp.monster[mapNum][i] = getMonster(ds.mapMonsterNames[mapNum][i]);
                        gp.monster[mapNum][i].worldX = ds.mapMonsterWorldX[mapNum][i];
                        gp.monster[mapNum][i].worldY = ds.mapMonsterWorldY[mapNum][i];
                        gp.monster[mapNum][i].stressLevel = ds.mapMonsterHealth[mapNum][i];
                        gp.monster[mapNum][i].direction = ds.mapMonsterDirection[mapNum][i];
                    }
                }
            }

            //INTERACTIVE TILES ON MAP
            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                for (int i = 0; i < gp.iTile[1].length; i++) {
                    if (ds.mapITileNames[mapNum][i].equals("NA")) {
                        gp.iTile[mapNum][i] = null;
                    } else {
                        gp.iTile[mapNum][i] = (InteractiveTile) getITile(ds.mapITileNames[mapNum][i], ds.mapITileWorldX[mapNum][i], ds.mapITileWorldY[mapNum][i]);
                        gp.iTile[mapNum][i].worldX = ds.mapITileWorldX[mapNum][i];
                        gp.iTile[mapNum][i].worldY = ds.mapITileWorldY[mapNum][i];
                    }
                }
            }

            //NPCS ON MAP
            gp.player.andreaOnMap = ds.andreaOnMap;

            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                for (int i = 0; i < gp.npc[1].length; i++) {
                    if (gp.npc[mapNum][i] != null && ((Objects.equals(gp.npc[mapNum][i].name, "OldCooker") || Objects.equals(gp.npc[mapNum][i].name, "Merchant")) && ds.missionState >= MissionStates.NOT_GET_PAID_FOR_OLD_COOKER)) {
                        gp.npc[mapNum][i] = null;
                    }
                    if (gp.player.andreaSafe) {
                        if (gp.npc[mapNum][i] != null && Objects.equals(gp.npc[mapNum][i].name, "Andrea")) {
                            gp.npc[mapNum][i] = null;
                        }
                    }
                    if (ds.mapNpcNames[mapNum][i].equals("NA")) {
                        gp.npc[mapNum][i] = null;
                    } else {
                        gp.npc[mapNum][i] = getNpc(ds.mapNpcNames[mapNum][i]);
                        gp.npc[mapNum][i].worldX = ds.mapNpcWorldX[mapNum][i];
                        gp.npc[mapNum][i].worldY = ds.mapNpcWorldY[mapNum][i];
                        gp.npc[mapNum][i].direction = ds.mapNpcDirection[mapNum][i];
                    }
                }
            }

            //SET CORRECT IMAGE STATES
            gp.eHandler.setImageStates(gp.currentMap);

            //DEBUG
            //add system.out here for load
            //END DEBUG


        } catch (Exception e) {
            System.out.println("Load Exception!");
        }
    }
}
