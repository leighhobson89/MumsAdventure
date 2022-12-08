package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

import java.awt.*;

public class OBJ_ChoppedChicken extends Projectile {

    final GamePanel gp;
    public static final String OBJ_NAME = "Chopped Chicken";

    public OBJ_ChoppedChicken(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        name = OBJ_NAME;
        displayName = "some chopped up Raw Chicken";
        down1 = setup("/objects/choppedChicken", gp.tileSize, gp.tileSize);
        direction = "down";
        description = "[" + name + "]\nChopped Raw Chicken\nThrow for the dogs!";
        price = 0;
        stackable = true;
        isSaleable = false;
        isProjectile = true;

        speed = 6;
        maxStress = 80;
        attack = 1;
        knockBackPower = 3;
        useCost = 1; //spend mana to use spell
        alive = false;
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

    public boolean haveResource(Entity user) {

        gp.player.haveChoppedChickenResource = user.choppedChickenCount >= useCost;
        return gp.player.haveChoppedChickenResource;
    }

    public void subtractResource(Entity user) {
        user.choppedChickenCount -= useCost;
    }

    public Color getParticleColor() {
        return new Color(65,50,30);
    }
    public int getParticleSize() {
        return 4;
    }
    public int getParticleSpeed() {
        return 1;
    }
    public int getParticleMaxLife() {
        return 20;
    }
}
