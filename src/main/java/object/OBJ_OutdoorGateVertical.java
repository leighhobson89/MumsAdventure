package object;

import entity.Entity;
import main.GamePanel;

import java.util.Objects;

public class OBJ_OutdoorGateVertical extends Entity {

    final GamePanel gp;
    boolean closeDoorFlag = false;
    int tempWorldX = 0;
    int tempWorldY = 0;
    int tileDistance = 0;
    int index = 0;
    public static final String OBJ_NAME = "OutdoorGateVerticalClosed";

    public OBJ_OutdoorGateVertical(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = true;
        type = type_closeable_door;
        name = OBJ_NAME;
        displayName = "OutdoorGateVerticalClosed";
        direction = "down";
        image = setup("/objects/outdoorGateVerticalClosed", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/outdoorGateVerticalOpened", gp.tileSize, gp.tileSize);
        down1 = image;

        collision = true;

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 15;
        solidArea.height = 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDialogue();
    }

    public void setDialogue() {
        dialogueText[0][0] = "The gates's already open!";
    }

    public void update() {

        for (int i = 0; i < gp.npc[1].length; i++) {
            if (opened && gp.npc[gp.currentMap][i] != null && Objects.equals(gp.npc[gp.currentMap][i].name, "Dad")) {
                int touching = gp.cChecker.checkEntity(this, gp.npc);
                if (touching == gp.ARBITRARY_IDENTIFIER_CLOSEABLE_DOORS && !closeDoorFlag) {
                    index = i;
                    tempWorldX = gp.npc[gp.currentMap][i].worldX/gp.tileSize;
                    tempWorldY = gp.npc[gp.currentMap][i].worldY/gp.tileSize;
                    closeDoorFlag = true;
                }
            }
        }
        tileDistance = Math.abs((gp.npc[gp.currentMap][index].worldX/gp.tileSize - tempWorldX) + (gp.npc[gp.currentMap][index].worldY/gp.tileSize - tempWorldY));
        if (closeDoorFlag) {
            if (tileDistance > 1) {
                closeDoorFlag = false;
            }
            if (!closeDoorFlag) {
                down1 = image;
                collision = true;
                opened = false;
            }
        }
    }

    public void interact() {
        if (!opened) {
            gp.playSFX(4);

            down1 = image2;
            collision = false;
            opened = true;
        }
        else {
            startDialogue(this, 0);
        }
        gp.keyH.enterPressed = false;
    }
}
