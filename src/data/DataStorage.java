package data;

import entity.Entity;

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
    int chickenIndex;
    boolean musicPlaying;
    boolean savedWithAWeaponEquipped;
    boolean savedWithAnArmourEquipped;
    boolean savedWithBoneEquipped;
    boolean savedWithChoppedChickenEquipped;
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
    boolean andreaOnMap;
    boolean firstTimeChattingToAndrea;
    int choppedChickenCount;
    int thrownChickenCount = 0;
    Entity currentProjectile;
    String itemToThrow;
    boolean startCounterPipEatingChicken;
    boolean startCounterPhoebeEatingChicken;
    int pipEatingChickenCounter;
    int phoebeEatingChickenCounter;
    boolean pipChickenEaten;
    boolean phoebeChickenEaten;
    boolean phoneRinging;
    boolean nextMissionIsPhoneMission;

    //PLAYER INVENTORY
    ArrayList<String> itemNames = new ArrayList<>();
    ArrayList<Integer> itemAmounts = new ArrayList<>();
    int currentWeaponSlot;
    int currentArmourSlot;
    int currentProjectileSlot;

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
