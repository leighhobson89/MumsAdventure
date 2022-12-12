package monster;

import entity.Entity;
import main.GamePanel;

public class MON_CarGoingDown extends Entity {

    final GamePanel gp;
    final public static String monName = "CarGoingDown";
    final static int IMAGE_SCALE_FACTOR = 2;
    public MON_CarGoingDown(GamePanel gp) {
        super(gp);

        this.gp = gp;

        goesTransparentWhenHit = false;
        type = type_monster;
        name = monName;
        defaultSpeed = 0;
        speed = defaultSpeed;

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = gp.tileSize*IMAGE_SCALE_FACTOR;
        solidArea.height = gp.tileSize*IMAGE_SCALE_FACTOR;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        dyingImage = setup("/monster/carGoingDown", gp.tileSize*IMAGE_SCALE_FACTOR, gp.tileSize*IMAGE_SCALE_FACTOR);
        up1 = setup("/monster/carGoingDown", gp.tileSize*IMAGE_SCALE_FACTOR, gp.tileSize*IMAGE_SCALE_FACTOR);
        up2 = setup("/monster/carGoingDown", gp.tileSize*IMAGE_SCALE_FACTOR, gp.tileSize*IMAGE_SCALE_FACTOR);
        down1 = setup("/monster/carGoingDown", gp.tileSize*IMAGE_SCALE_FACTOR, gp.tileSize*IMAGE_SCALE_FACTOR);
        down2 = setup("/monster/carGoingDown", gp.tileSize*IMAGE_SCALE_FACTOR, gp.tileSize*IMAGE_SCALE_FACTOR);
        left1 = setup("/monster/carGoingDown", gp.tileSize*IMAGE_SCALE_FACTOR, gp.tileSize*IMAGE_SCALE_FACTOR);
        left2 = setup("/monster/carGoingDown", gp.tileSize*IMAGE_SCALE_FACTOR, gp.tileSize*IMAGE_SCALE_FACTOR);
        right1 = setup("/monster/carGoingDown", gp.tileSize*IMAGE_SCALE_FACTOR, gp.tileSize*IMAGE_SCALE_FACTOR);
        right2 = setup("/monster/carGoingDown", gp.tileSize*IMAGE_SCALE_FACTOR, gp.tileSize*IMAGE_SCALE_FACTOR);
    }

    public void setAction(int goalCol, int goalRow) {

    }
}
