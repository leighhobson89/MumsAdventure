package main;

public class MissionStates {
    final GamePanel gp;

    public MissionStates(GamePanel gp) {
        this.gp = gp;
    }

    public static final int BETWEEN_MISSIONS = 0;
    public static final int WEEDING_MISSION = 1;
    public static final int SELL_DADS_ELECTRIC_GUITAR_TO_THE_MERCHANT = 2;
    public static final int HELP_ANDREA_OUT = 3;
    public static final int CHOP_CHICKEN_FOR_DOGS = 4;
    public static final int MOP_UP_THE_SHOWER_WATER = 5;
    public static final int MAGIC_BOOK_QUIZ = 6;
    public static final int DRAG_COOKER_TO_BINS = 7;
    public static final int NOT_GET_PAID_FOR_OLD_COOKER = 8;
    public static final int DESTROY_WASP_NEST = 9;
    public static final int CHUCK_WASP_NEST_IN_BIN = 10;

    public void endMissionTasks(int missionToAddToCompletedList, boolean nextMissionIsPhoneMission) {
        gp.player.nextMissionIsPhoneMission = nextMissionIsPhoneMission;
        gp.player.missionState = MissionStates.BETWEEN_MISSIONS;
        gp.player.missionList.add(missionToAddToCompletedList); //add completed weeding mission to missionList
        System.out.println( gp.player.missionList);
        gp.player.missionToSet = missionToAddToCompletedList + 1;
        gp.player.missionSubstate = 0;
    }
}

