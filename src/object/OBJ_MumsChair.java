package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_MumsChair extends Entity {

    GamePanel gp;

    public OBJ_MumsChair(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "MumsChair";
        displayName = "MumsChair";
        down1 = setup("/objects/mumsChair", gp.tileSize, gp.tileSize);
        direction = "down";

        collision = true;

        solidArea.x = 0;
        solidArea.y = 20;
        solidArea.width = 48;
        solidArea.height = 28;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void interact() {
        gp.gameState = gp.dialogueState;
        gp.eHandler.DestressEvent();
        gp.keyH.enterPressed = false;
    }
}
