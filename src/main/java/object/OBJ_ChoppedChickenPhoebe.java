package object;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class OBJ_ChoppedChickenPhoebe extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "Chopped Chicken Phoebe";

    public OBJ_ChoppedChickenPhoebe(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        name = OBJ_NAME;
        displayName = "some chopped up Raw Chicken";
        down1 = setup("/objects/choppedChicken", gp.tileSize, gp.tileSize);
        direction = "down";
        description = "[" + name + "]\nChopped Raw Chicken\nFor Phoebe!";
        price = 0;
        stackable = true;
        isSaleable = false;
        collision = false;

        type = type_obstacle;
        getImage();

        solidArea = new Rectangle(9, 10,25,29);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void getImage() {

        up1 = setup("/projectiles/choppedChicken", gp.tileSize, gp.tileSize);
        up2 = setup("/projectiles/choppedChicken", gp.tileSize, gp.tileSize);
        down1 = setup("/projectiles/choppedChicken", gp.tileSize, gp.tileSize);
        down2 = setup("/projectiles/choppedChicken", gp.tileSize, gp.tileSize);
        left1 = setup("/projectiles/choppedChicken", gp.tileSize, gp.tileSize);
        left2 = setup("/projectiles/choppedChicken", gp.tileSize, gp.tileSize);
        right1 = setup("/projectiles/choppedChicken", gp.tileSize, gp.tileSize);
        right2 = setup("/projectiles/choppedChicken", gp.tileSize, gp.tileSize);
    }
}
