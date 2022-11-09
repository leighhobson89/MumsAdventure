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

    public void endMissionTasks(int missionToAddToCompletedList) {
        gp.player.missionState = MissionStates.BETWEEN_MISSIONS;
        gp.player.missionList.add(missionToAddToCompletedList); //add completed weeding mission to missionList
        gp.player.missionToSet = missionToAddToCompletedList + 1;
    }
}

