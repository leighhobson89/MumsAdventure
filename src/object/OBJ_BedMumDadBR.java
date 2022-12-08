package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BedMumDadBR extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "BedMumDadBR";

    public OBJ_BedMumDadBR(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "BedMumDadBR";
        image = setup("/objects/BRbedMumDadBedroom", gp.tileSize, gp.tileSize);
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
