package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BedMumDadTR extends Entity {

    GamePanel gp;

    public OBJ_BedMumDadTR(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "BedMumDadTR";
        image = setup("/objects/TRbedMumDadBedroom", gp.tileSize, gp.tileSize);
        down1 = image;
        direction = "down";

        collision = true;
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 48;
        solidArea.height = 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void interact() {
        gp.gameState = gp.dialogueState;
        gp.eHandler.DestressEvent();
        gp.keyH.enterPressed = false;
    }
}
