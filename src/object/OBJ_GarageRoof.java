package object;

import entity.Entity;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OBJ_GarageRoof extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "GarageRoof";

    public OBJ_GarageRoof(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = true;
        type = type_hut;
        name = OBJ_NAME;
        displayName = "GarageRoof";
        image = setup("/objects/roofGarage", gp.tileSize * 10, gp.tileSize * 12);
        down1 = image;
        direction = "down";
        collision = true;
        collisionType = 0;
        goesTransparentWhenHit = false;
        isScaledUpObject = true;
        transparent = false;
        goesTransparentWhenInGarage = true;
    }
}
