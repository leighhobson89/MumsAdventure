package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

import java.awt.*;

public class OBJ_DogsBone_NotMagic extends Projectile {

    GamePanel gp;

    public OBJ_DogsBone_NotMagic(GamePanel gp) {

        super(gp);
        this.gp = gp;

        name = "Phoebe's Bone";
        speed = 4;
        maxStress = 50;
        attack = 1;
        useCost = 0; //spend mana to use spell
        alive = false;
        getImage();

        solidArea = new Rectangle(9, 10,25,29);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        collectable = true;
    }

    public void getImage() {

        up1 = setup("/projectiles/bone", gp.tileSize, gp.tileSize);
        up2 = setup("/projectiles/bone", gp.tileSize, gp.tileSize);
        down1 = setup("/projectiles/bone", gp.tileSize, gp.tileSize);
        down2 = setup("/projectiles/bone", gp.tileSize, gp.tileSize);
        left1 = setup("/projectiles/bone", gp.tileSize, gp.tileSize);
        left2 = setup("/projectiles/bone", gp.tileSize, gp.tileSize);
        right1 = setup("/projectiles/bone", gp.tileSize, gp.tileSize);
        right2 = setup("/projectiles/bone", gp.tileSize, gp.tileSize);
    }

    public boolean haveResource(Entity user) {
        boolean haveResource = false;

        if (user.ammo >= useCost) {
            haveResource = true;
        }
        return haveResource;
    }

    public void subtractResource(Entity user) {
        user.ammo -= useCost;
    }
}
