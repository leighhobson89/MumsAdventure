package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Music_Notes extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "MusicNotes";

    public OBJ_Music_Notes(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = true;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "MusicNotes";
        image = null;
        image2 = setup("/objects/musicNotes1", gp.tileSize, gp.tileSize);
        image3 = setup("/objects/musicNotes2", gp.tileSize, gp.tileSize);
        down1 = image;
        down2 = image3;
        direction = "down";
        collision = false;
        goesTransparentWhenHit = false;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        spriteCounter = 0;
    }

    public void update() {
        if (gp.player.dadPlayingGuitar) {
            worldX = 16*gp.tileSize;
            worldY = 14*gp.tileSize;
            if (spriteCounter < 50) {
                down1 = image2;
            } else {
                down1 = image3;
            }
            if (spriteCounter > 100) {
                spriteCounter = 0;
            }
            spriteCounter++;
        } else if (gp.player.musicCentreOn) {
            worldX = 19*gp.tileSize;
            worldY = 12*gp.tileSize;
            if (spriteCounter < 50) {
                down1 = image2;
            } else {
                down1 = image3;
            }
            if (spriteCounter > 100) {
                spriteCounter = 0;
            }
            spriteCounter++;
        } else {
            down1 = image;
            spriteCounter = 0;
        }
    }
}
