package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bin_Green extends Entity {

    GamePanel gp;

    public OBJ_Bin_Green(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "Bin_Green";
        image = setup("/objects/bin_Green", gp.tileSize, gp.tileSize);
        down1 = image;
        direction = "down";

        collision = true;
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void interact() {
        gp.gameState = gp.dialogueState;
        if (!opened) {
            gp.playSFX(14);

            gp.player.spiderCount = gp.eHandler.spiderEvent(27, 8, gp.dialogueState, gp.player.spiderCount, true, false);
            opened = true;
        } else {
            gp.gameState = gp.playState;
        }
        gp.keyH.enterPressed = false;
    }
}
