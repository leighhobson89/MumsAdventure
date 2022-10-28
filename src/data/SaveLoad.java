package data;

import main.GamePanel;

import java.io.*;

public class SaveLoad {
    GamePanel gp;

    public SaveLoad(GamePanel gp) {
        this.gp = gp;
    }

    public void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));

            DataStorage ds = new DataStorage();

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
            ds.colorOutfit = gp.ui.colorOutfit;
            ds.outfitChosen = gp.ui.outfitChosen;

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
            gp.ui.colorOutfit = ds.colorOutfit;
            gp.ui.outfitChosen = ds.outfitChosen;
            gp.player.getImage(gp.ui.colorOutfit, true);
            gp.player.getAttackImage(gp.ui.outfitChosen);

        } catch (Exception e) {
            System.out.println("Load Exception!");
        }
    }
}
