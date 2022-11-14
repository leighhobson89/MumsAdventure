package data;

import entity.Entity;
import main.GamePanel;
import monster.MON_Spider;
import monster.MON_WaspSwarm;
import object.*;
import tile_interactive.IT_BareRockery;
import tile_interactive.IT_WeedTile;
import tile_interactive.InteractiveTile;

import java.io.*;
import java.util.Objects;

public class SaveLoad {
    GamePanel gp;

    public SaveLoad(GamePanel gp) {
        this.gp = gp;
    }
    boolean loadWithBoneEquipped;
    boolean loadWithChoppedChickenEquipped;

    public Entity getMonster(String itemName) {
        Entity monster = null;

        switch(itemName) {
            case "Spider": monster = new MON_Spider(gp); break;
            case "WaspSwarm": monster = new MON_WaspSwarm(gp); break;
        }
        return monster;
    }

    public Entity getITile(String itemName, int col, int row) {
        Entity iTile = null;

        switch(itemName) {
            case "Weed Tile": iTile = new IT_WeedTile(gp, col, row); break;
            case "Bare Rockery": iTile = new IT_BareRockery(gp, col, row); break;
        }
        return iTile;
    }

    public void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));

            DataStorage ds = new DataStorage();

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
            if (gp.player.boneCount > 0) {
                ds.savedWithBoneEquipped = true;
            } else {
                ds.savedWithBoneEquipped = false;
            }
            if (gp.player.choppedChickenCount > 0) {
                ds.savedWithChoppedChickenEquipped = true;
            } else {
                ds.savedWithChoppedChickenEquipped = false;
            }
            ds.thrownChickenCount = gp.player.thrownChickenCount;
            ds.spiderCount = gp.player.spiderCount;
            ds.missionState = gp.player.missionState;
            ds.missionList = gp.player.missionList;
            ds.readyForNextPhoneMission = gp.player.readyForNextPhoneMission;
            ds.missionToSet = gp.player.missionToSet;
            ds.setShovelFlag = gp.player.setShovelFlag;
            ds.repeatSfx = gp.player.repeatSfx;
            ds.currentProjectile = gp.player.currentProjectile;

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
            ds.firstTimeChattingToAndrea = gp.player.firstTimeChattingToAndrea;

            //Write the DataStorage Object
            oos.writeObject(ds);

        } catch (Exception e) {
            System.out.println("Save Exception!");
        }
    }

    public void load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));

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
            gp.player.missionList = ds.missionList;
            gp.player.readyForNextPhoneMission = ds.readyForNextPhoneMission;
            gp.player.missionToSet = ds.missionToSet;
            gp.player.randomCounter = gp.player.setRandomCounter();
            gp.player.setShovelFlag = ds.setShovelFlag;
            gp.player.repeatSfx = ds.repeatSfx;
            gp.player.choppedChickenCount = ds.choppedChickenCount;
            gp.player.thrownChickenCount = ds.thrownChickenCount;
            gp.player.currentProjectile = ds.currentProjectile;

            if (loadWithBoneEquipped) {
                gp.player.boneCount = 1;
                gp.player.firstTimePickUpBone = false;
                gp.player.haveBoneResource = true;
            }

            if (loadWithChoppedChickenEquipped) {
                gp.player.choppedChickenCount = ds.choppedChickenCount;
                gp.player.haveChoppedChickenResource = true;
            }

            //PLAYER OUTFIT
            gp.ui.colorOutfit = ds.colorOutfit;
            gp.ui.outfitChosen = ds.outfitChosen;
            gp.player.getImage(gp.ui.colorOutfit, true);

            //PLAYER INVENTORY
            for (int i = 0; i < ds.itemNames.size(); i++) {
                gp.player.inventory.add(gp.eGenerator.getObject(ds.itemNames.get(i)));
                gp.player.inventory.get(i).amount = ds.itemAmounts.get(i);
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
                            switch(gp.obj[mapNum][i].name) { //add cases here for objects that could be loaded in an open state but dont have 2 images
                                case "Cupboard2": gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].image; gp.obj[mapNum][i].collision = true; break;
                                case "Bin_Green": gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].image; gp.obj[mapNum][i].collision = true; break;
                                default:  gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].image2; gp.obj[mapNum][i].collision = false; break;
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
            gp.player.firstTimeChattingToAndrea = ds.firstTimeChattingToAndrea;


        } catch (Exception e) {
            System.out.println("Load Exception!");
        }
    }
}
