package monster;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class MON_CarGoingDown extends Entity {

    final GamePanel gp;

    boolean setupCar = false;
    int randomTime;

    final public static String monName = "CarGoingDown";
    final static int IMAGE_SCALE_FACTOR = 2;
    public MON_CarGoingDown(GamePanel gp) {
        super(gp);

        this.gp = gp;

        goesTransparentWhenHit = false;
        type = type_monster;
        name = monName;
        defaultSpeed = 4;
        speed = defaultSpeed;
        direction = "down";

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = gp.tileSize*IMAGE_SCALE_FACTOR;
        solidArea.height = gp.tileSize*IMAGE_SCALE_FACTOR;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
        down1 = image;
        down2 = image;
    }

    public void getImage() {
        image = setup("/monster/carGoingDown2", gp.tileSize*IMAGE_SCALE_FACTOR, gp.tileSize*IMAGE_SCALE_FACTOR);
        image2 = setup("/monster/carGoingDown2", gp.tileSize*IMAGE_SCALE_FACTOR, gp.tileSize*IMAGE_SCALE_FACTOR);
        image3 = setup("/monster/carGoingDown3", gp.tileSize*IMAGE_SCALE_FACTOR, gp.tileSize*IMAGE_SCALE_FACTOR);
        image4 = setup("/monster/carGoingDown4", gp.tileSize*IMAGE_SCALE_FACTOR, gp.tileSize*IMAGE_SCALE_FACTOR);
        image5 = setup("/monster/carInvisible", gp.tileSize*IMAGE_SCALE_FACTOR, gp.tileSize*IMAGE_SCALE_FACTOR);
    }

    public void setAction(int goalCol, int goalRow) {
        // IF CAR GETS TO BOTTOM...

        if (down1 != image5 && down2 != image5) {
            if (worldY > gp.tileSize*23 + 24) {
                randomTime = new Random().nextInt(900) + 500;
                // STOP MOVEMENT AND MAKE INVISIBLE
                speed = 0;
                down1 = image5;
                down2 = image5;
                setupCar = true;
            }
        }
        if (setupCar) { // SETUP NEXT CAR
            if (carCountDown < randomTime) {
                carCountDown++;
            }
        }

        if (carCountDown >= randomTime && setupCar) {
            setupCar = false;
            carCountDown = 0;
            // MOVE TO TOP
            worldY = gp.tileSize;
            // SET COLOUR
            int ran = new Random().nextInt(100);
            if (ran < 25) {
                down1 = image;
                down2 = image;
            } else if (ran < 50) {
                down1 = image2;
                down2 = image2;
            } else if (ran < 75) {
                down1 = image3;
                down2 = image3;
            } else {
                down1 = image4;
                down2 = image4;
            }
            // SET SPEED
            speed = defaultSpeed;
        }
    }
}
