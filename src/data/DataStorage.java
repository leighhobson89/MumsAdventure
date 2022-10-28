package data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable {

    //PLAYER STATS
    int level;
    int maxStress;
    int stressLevel;
    int maxMana;
    int mana;
    int strength;
    int dexterity;
    int exp;
    int nextLevelExp;
    int coin;
    String colorOutfit;
    String outfitChosen;
    int doorUnlockedCount;
    int weedCount;
    int timesPassedOut;
    boolean pillsConsumableNow;
    int boneIndex;
    boolean musicPlaying;
    boolean savedWithAWeaponEquipped;
    boolean savedWithAnArmourEquipped;
    boolean savedWithBoneEquipped;

    //PLAYER INVENTORY
    ArrayList<String> itemNames = new ArrayList<>();
    ArrayList<Integer> itemAmounts = new ArrayList<>();
    int currentWeaponSlot;
    int currentArmourSlot;

    //OBJECT ON MAP
    String[][] mapObjectNames;
    int[][] mapObjectWorldX;
    int[][] mapObjectWorldY;
    String[][] mapObjectLootNames; //Only needed if loot functionality implemented
    boolean[][] mapObjectOpened;

    //MONSTERS ON MAP
    String[][] mapMonsterNames;
    int[][] mapMonsterWorldX;
    int[][] mapMonsterWorldY;
    int[][] mapMonsterHealth;
    String[][] mapMonsterDirection;

}
