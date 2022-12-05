package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_TV_Lounge extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "TV_Lounge";

    public OBJ_TV_Lounge(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = true;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "TV_Lounge";
        image = setup("/objects/tvLoungeOff", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/tvLoungeOn1", gp.tileSize, gp.tileSize);
        image3 = setup("/objects/tvLoungeOn2", gp.tileSize, gp.tileSize);
        down1 = image;
        down2 = image3;
        direction = "down";
        collision = true;
        goesTransparentWhenHit = false;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        spriteCounter = 0;
        setDialogue();

    }

    public void update() {
        if (!gp.player.tvIsOff) {
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
            spriteCounter = 0;
        }
    }

    public void setDialogue() {
        dialogueText[0][0] = "The TV.  Its Off.  Take a seat to watch it!";
    }

    public void interact() {
        startDialogue(this, 0);
        gp.keyH.enterPressed = false;
    }
}
