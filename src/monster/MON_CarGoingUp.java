package monster;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class MON_CarGoingUp extends Entity {

    final GamePanel gp;

    boolean setupCar = false;
    int randomTime;

    final public static String monName = "CarGoingUp";
    final static int IMAGE_SCALE_FACTOR = 2;
    public MON_CarGoingUp(GamePanel gp) {
        super(gp);

        this.gp = gp;

        goesTransparentWhenHit = false;
        type = type_monster;
        name = monName;
        defaultSpeed = 4;
        speed = defaultSpeed;
        direction = "up";

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = gp.tileSize*IMAGE_SCALE_FACTOR;
        solidArea.height = gp.tileSize*IMAGE_SCALE_FACTOR;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
        up1 = image;
        up2 = image;
    }

    public void getImage() {
        image = setup("/monster/carGoingUp2", gp.tileSize*IMAGE_SCALE_FACTOR, gp.tileSize*IMAGE_SCALE_FACTOR);
        image2 = setup("/monster/carGoingUp2", gp.tileSize*IMAGE_SCALE_FACTOR, gp.tileSize*IMAGE_SCALE_FACTOR);
        image3 = setup("/monster/carGoingUp3", gp.tileSize*IMAGE_SCALE_FACTOR, gp.tileSize*IMAGE_SCALE_FACTOR);
        image4 = setup("/monster/carGoingUp4", gp.tileSize*IMAGE_SCALE_FACTOR, gp.tileSize*IMAGE_SCALE_FACTOR);
        image5 = setup("/monster/carInvisible", gp.tileSize*IMAGE_SCALE_FACTOR, gp.tileSize*IMAGE_SCALE_FACTOR);
    }

    public void setAction(int goalCol, int goalRow) {
        // IF CAR GETS TO TOP...

        if (up1 != image5 && up2 != image5) {
            if (worldY < gp.tileSize*2 - 40) {
                randomTime = new Random().nextInt(900) + 500;
                // STOP MOVEMENT AND MAKE INVISIBLE
                speed = 0;
                up1 = image5;
                up2 = image5;
                setupCar = true;
            }
        }
        if (setupCar) { // SETUP NEXT CAR
            if (carCountUp < randomTime) {
                carCountUp++;
            }
        }

        if (carCountUp >= randomTime && setupCar) {
            setupCar = false;
            carCountUp = 0;
            // MOVE TO TOP
            worldY = gp.tileSize*25;
            // SET COLOUR
            int ran = new Random().nextInt(100);
            if (ran < 25) {
                up1 = image;
                up2 = image;
            } else if (ran < 50) {
                up1 = image2;
                up2 = image2;
            } else if (ran < 75) {
                up1 = image3;
                up2 = image3;
            } else {
                up1 = image4;
                up2 = image4;
            }
            // SET SPEED
            speed = defaultSpeed;
        }
    }
}
