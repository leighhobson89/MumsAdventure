package main;

public class MissionStates {
    GamePanel gp;

    public MissionStates(GamePanel gp) {
        this.gp = gp;
    }

    public static final int BETWEEN_MISSIONS = 0;
    public static final int WEEDING_MISSION = 1;
    public static final int HELP_ANDREA_OUT = 2;
    public static final int SELL_DADS_ELECTRIC_GUITAR_TO_THE_MERCHANT = 3;
}

