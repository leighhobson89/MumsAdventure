package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

import java.awt.*;

public class OBJ_PipsToy_Magic extends Projectile {

    GamePanel gp;
    public static final String OBJ_NAME = "Pip's Toy";

    public OBJ_PipsToy_Magic(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        name = OBJ_NAME;
        displayName = "Pip's Toy";
        speed = 5;
        maxStress = 50;
        attack = 1;
        useCost = 1; //spend "squeakyToy_UI's" to use item
        alive = false;
        getImage();

        solidArea = new Rectangle(13, 12,23,24);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void getImage() {

        up1 = setup("/projectiles/pips_toy_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/projectiles/pips_toy_up2", gp.tileSize, gp.tileSize);
        down1 = setup("/projectiles/pips_toy_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/projectiles/pips_toy_down2", gp.tileSize, gp.tileSize);
        left1 = setup("/projectiles/pips_toy_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/projectiles/pips_toy_left2", gp.tileSize, gp.tileSize);
        right1 = setup("/projectiles/pips_toy_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/projectiles/pips_toy_right2", gp.tileSize, gp.tileSize);
    }

    public boolean haveResource(Entity user) {
        boolean haveResource = false;

        if (user.mana >= useCost) {
            haveResource = true;
        }
        return haveResource;
    }

    public void subtractResource(Entity user) {
        user.mana -= useCost;
    }
}
