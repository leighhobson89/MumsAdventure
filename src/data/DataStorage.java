package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    int spiderCount;
    int missionState;
    public List<Integer> missionList = new ArrayList<>();
    boolean readyForNextPhoneMission;
    int missionToSet;
    int playerWorldX;
    int playerWorldY;
    int currentMap;
    boolean setShovelFlag;
    boolean repeatSfx;
    int dialogueSet;

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

    //INTERACTIVE TILES STATUS ON MAP
    String [][] mapITileNames;
    int[][] mapITileWorldX;
    int[][] mapITileWorldY;
}
