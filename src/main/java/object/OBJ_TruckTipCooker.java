package object;

import entity.Entity;
import main.GamePanel;
import main.MissionStates;

public class OBJ_TruckTipCooker extends Entity {

    final GamePanel gp;

    final public static String OBJ_NAME = "TruckTipCooker";
    final static int IMAGE_SCALE_FACTOR = 4;
    public OBJ_TruckTipCooker(GamePanel gp) {
        super(gp);

        this.gp = gp;

        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        direction = "down";
        isScaledUpObject = true;

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
        image = setup("/objects/truckTipCooker", gp.tileSize*IMAGE_SCALE_FACTOR, gp.tileSize*IMAGE_SCALE_FACTOR);
    }
}
