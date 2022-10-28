package data;

import entity.Entity;
import main.GamePanel;
import monster.MON_Spider;
import monster.MON_WaspSwarm;
import object.*;

import java.io.*;

public class SaveLoad {
    GamePanel gp;

    public SaveLoad(GamePanel gp) {
        this.gp = gp;
    }
    boolean loadWithBoneEquipped;

    public Entity getObject(String itemName) {
        Entity obj = null;

        switch(itemName) {
            case "Pip's Bone": obj = new OBJ_DogsBone_NotMagic(gp); break;
            case "FrontBackDoorKey": obj = new OBJ_FrontBackDoorKey(gp); break;
            case "Old Cardigan": obj = new OBJ_GrandmasCardigan(gp); break;
            case "Acoustic Guitar": obj = new OBJ_Guitar1(gp); break;
            case "Electric Guitar": obj = new OBJ_Guitar2(gp); break;
            case "Lavender Crocs": obj = new OBJ_Lavendar_Crocs(gp); break;
            case "Anti Brightness Pills": obj = new OBJ_LightPills(gp); break;
            case "Tube of Pills": obj = new OBJ_Pills(gp); break;
            case "Garden Shovel": obj = new OBJ_Shovel(gp); break;
            case "Spatula": obj = new OBJ_Spatula(gp); break;
            case "InsideDoor": obj = new OBJ_InsideDoor(gp); break;
            case "InsideDoorSideways": obj = new OBJ_InsideDoorSideways(gp); break;
            case "Cupboard1": obj = new OBJ_Cupboard1(gp); break;
            case "Cupboard2": obj = new OBJ_Cupboard2(gp); break;
            case "Cupboard3": obj = new OBJ_Cupboard3(gp); break;
            case "Bin_Blue": obj = new OBJ_Bin_Blue(gp); break;
            case "Bin_Green": obj = new OBJ_Bin_Green(gp); break;
            case "Bin_Grey": obj = new OBJ_Bin_Grey(gp); break;
            case "BackGateSideways": obj = new OBJ_BackGateSideways(gp); break;
            case "BackGate": obj = new OBJ_BackGate(gp); break;
            case "MumsChair": obj = new OBJ_MumsChair(gp); break;
            case "FrontDoor": obj = new OBJ_FrontDoor(gp); break;
            case "BackDoor": obj = new OBJ_BackDoor(gp); break;
            case "BedMumDadBL": obj = new OBJ_BedMumDadBL(gp); break;
            case "BedMumDadBR": obj = new OBJ_BedMumDadBR(gp); break;
            case "BedMumDadTL": obj = new OBJ_BedMumDadTL(gp); break;
            case "BedMumDadTR": obj = new OBJ_BedMumDadTR(gp); break;
            case "FrontBackDoorOpen": obj = new OBJ_FrontBackDoorOpen(gp); break;
            case "A Pound Coin": obj = new OBJ_Coin(gp); break;
            case "StressBolt": obj = new OBJ_LightningBoltStress(gp); break;
            case "HundredQuid": obj = new OBJ_SuperCoin(gp); break;
            case "TelephoneHall": obj = new OBJ_TelephoneHall(gp); break;
        }
        return obj;
    }

    public Entity getMonster(String itemName) {
        Entity monster = null;

        switch(itemName) {
            case "Spider": monster = new MON_Spider(gp); break;
            case "WaspSwarm": monster = new MON_WaspSwarm(gp); break;
        }
        return monster;
    }

    public void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));

            DataStorage ds = new DataStorage();

            //MUSIC CONFIG
            ds.musicPlaying = gp.keyH.musicPlaying;

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
            ds.doorUnlockedCount = gp.player.doorUnlockedCount;
            ds.weedCount = gp.player.weedCount;
            ds.timesPassedOut = gp.player.timesPassedOut;
            ds.pillsConsumableNow = gp.player.pillsConsumableNow;
            ds.boneIndex = gp.player.boneIndex;
            if (gp.player.boneCount == 1) {
                ds.savedWithBoneEquipped = true;
            } else {
                ds.savedWithBoneEquipped = false;
            }

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
            gp.player.weedCount = ds.weedCount;
            gp.player.timesPassedOut = ds.timesPassedOut;
            gp.player.pillsConsumableNow = ds.pillsConsumableNow;
            gp.player.boneIndex = ds.boneIndex;
            gp.player.savedWithAWeaponEquipped = ds.savedWithAWeaponEquipped;
            gp.player.savedWithAnArmourEquipped = ds.savedWithAnArmourEquipped;
            loadWithBoneEquipped = ds.savedWithBoneEquipped;
            if (loadWithBoneEquipped) {
                gp.player.boneCount = 1;
                gp.player.firstTimePickUpBone = false;
                gp.player.haveResource = true;
            }

            //PLAYER OUTFIT
            gp.ui.colorOutfit = ds.colorOutfit;
            gp.ui.outfitChosen = ds.outfitChosen;
            gp.player.getImage(gp.ui.colorOutfit, true);

            //PLAYER INVENTORY
            for (int i = 0; i < ds.itemNames.size(); i++) {
                gp.player.inventory.add(getObject(ds.itemNames.get(i)));
                gp.player.inventory.get(i).amount = ds.itemAmounts.get(i);
            }

            //PLAYER EQUIPPED ITEMS
            if (gp.player.savedWithAWeaponEquipped) {
                gp.player.currentWeapon = gp.player.inventory.get(ds.currentWeaponSlot);
            }
            if (gp.player.savedWithAnArmourEquipped) {
                gp.player.currentArmour = gp.player.inventory.get(ds.currentArmourSlot);
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
                        gp.obj[mapNum][i] = getObject(ds.mapObjectNames[mapNum][i]);
                        gp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
                        gp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];
                        if (ds.mapObjectLootNames[mapNum][i] != null) {
                            gp.obj[mapNum][i].loot = getObject(ds.mapObjectLootNames[mapNum][i]);
                        }
                        gp.obj[mapNum][i].opened = ds.mapObjectOpened[mapNum][i];
                        if (gp.obj[mapNum][i].opened) {
                            gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].image2;
                            gp.obj[mapNum][i].collision = false;
                        }
                    }
                }
            }

            //MONSTERS ON MAP
            for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                for (int i = 0; i < gp.monster[1].length; i++) {
                    if (ds.mapObjectNames[mapNum][i].equals("NA")) {
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


        } catch (Exception e) {
            System.out.println("Load Exception!");
        }
    }
}
