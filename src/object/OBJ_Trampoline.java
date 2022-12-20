package object;

import entity.Entity;
import main.GamePanel;
import main.MissionStates;

public class OBJ_Trampoline extends Entity {

    final GamePanel gp;

    final public static String OBJ_NAME = "Trampoline";
    final static int IMAGE_SCALE_FACTOR = 2;
    public OBJ_Trampoline(GamePanel gp) {
        super(gp);

        this.gp = gp;

        isUpdateable = true;
        type = type_obstacle;
        name = OBJ_NAME;
        direction = "down";
        collision = true;

        solidArea.x = 0;
        solidArea.y = 20;
        solidArea.width = gp.tileSize*IMAGE_SCALE_FACTOR;
        solidArea.height = gp.tileSize*IMAGE_SCALE_FACTOR - 20;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
        down1 = image;
    }

    public void getImage() {
        image = setup("/objects/trampoline", gp.tileSize*IMAGE_SCALE_FACTOR, gp.tileSize*IMAGE_SCALE_FACTOR);
        image2 = setup("/objects/trampolineBroken", gp.tileSize*IMAGE_SCALE_FACTOR, gp.tileSize*IMAGE_SCALE_FACTOR);
    }

    public void update() {
        if (gp.player.missionState == MissionStates.MOVE_TRAMPOLINE_OFF_CAR && gp.player.missionSubstate == 0) {
            down1 = image2;
        }
    }
}
