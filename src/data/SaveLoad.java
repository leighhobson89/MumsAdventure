package data;

import entity.Entity;
import main.GamePanel;
import object.*;

import java.io.*;

public class SaveLoad {
    GamePanel gp;

    public SaveLoad(GamePanel gp) {
        this.gp = gp;
    }

    public Entity getObject(String itemName) {
        Entity obj = null;

        switch(itemName) {
            case "Pip's Bone": obj = new OBJ_DogsBone_NotMagic(gp); gp.player.boneCount = 1; gp.player.firstTimePickUpBone = false; break;
            case "Key": obj = new OBJ_FrontDoorKey(gp); break;
            case "Old Cardigan": obj = new OBJ_GrandmasCardigan(gp); break;
            case "Acoustic Guitar": obj = new OBJ_Guitar1(gp); break;
            case "Electric Guitar": obj = new OBJ_Guitar2(gp); break;
            case "Lavender Crocs": obj = new OBJ_Lavendar_Crocs(gp); break;
            case "Anti Brightness Pills": obj = new OBJ_LightPills(gp); break;
            case "Tube of Pills": obj = new OBJ_Pills(gp); break;
            case "Garden Shovel": obj = new OBJ_Shovel(gp); break;
            case "Spatula": obj = new OBJ_Spatula(gp); break;
            case "InsideDoor": obj = new OBJ_InsideDoor(gp); break; //
            case "InsideDoorSideways": obj = new OBJ_InsideDoorSideways(gp); break;
            case "Cupboard2": obj = new OBJ_Cupboard2(gp); break;
            case "Bin_Green": obj = new OBJ_Bin_Green(gp); break;
            case "BackGateSideways": obj = new OBJ_BackGateSideways(gp); break;
            case "BackGate": obj = new OBJ_BackGate(gp); break;
        }
        return obj;
    }

    public void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));

            DataStorage ds = new DataStorage();

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

            //PLAYER OUTFIT
            ds.colorOutfit = gp.ui.colorOutfit;
            ds.outfitChosen = gp.ui.outfitChosen;

            //PLAYER INVENTORY
            for (int i = 0; i < gp.player.inventory.size(); i++) {
                ds.itemNames.add(gp.player.inventory.get(i).name);
                ds.itemAmounts.add(gp.player.inventory.get(i).amount);
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

            //PLAYER OUTFIT
            gp.ui.colorOutfit = ds.colorOutfit;
            gp.ui.outfitChosen = ds.outfitChosen;
            gp.player.getImage(gp.ui.colorOutfit, true);
            gp.player.getAttackImage(gp.ui.outfitChosen);

            //PLAYER INVENTORY
            for (int i = 0; i < ds.itemNames.size(); i++) {
                gp.player.inventory.add(getObject(ds.itemNames.get(i)));
                gp.player.inventory.get(i).amount = ds.itemAmounts.get(i);
            }

        } catch (Exception e) {
            System.out.println("Load Exception!");
        }
    }
}
