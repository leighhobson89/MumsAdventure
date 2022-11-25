package main;

public class MissionStates {
    GamePanel gp;

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

    public void endMissionTasks(int missionToAddToCompletedList, boolean nextMissionIsPhoneMission) {
        if (nextMissionIsPhoneMission) {
            gp.player.nextMissionIsPhoneMission = true;
        } else {
            gp.player.nextMissionIsPhoneMission = false;
        }
        gp.player.missionState = MissionStates.BETWEEN_MISSIONS;
        gp.player.missionList.add(missionToAddToCompletedList); //add completed weeding mission to missionList
        gp.player.missionToSet = missionToAddToCompletedList + 1;
        gp.player.missionSubstate = 0;
    }
}

