package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

import java.awt.*;

public class OBJ_PipsBone extends Projectile {

    final GamePanel gp;
    public static final String OBJ_NAME = "Pip's Bone";

    public OBJ_PipsBone(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        name = OBJ_NAME;
        displayName = "Pip's Bone";
        description = "[" + name + "]\nThrow it for him!";
        isSaleable = false;
        speed = 6;
        maxStress = 80;
        attack = 1;
        knockBackPower = 4;
        useCost = 1; //spend mana to use spell
        alive = false;
        isProjectile = true;
        getImage();

        solidArea = new Rectangle(9, 10,25,29);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
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

        gp.player.haveBoneResource = user.boneCount >= useCost;
        return gp.player.haveBoneResource;
    }

    public void subtractResource(Entity user) {
        user.boneCount -= useCost;
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
