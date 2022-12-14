package object;

import entity.Entity;
import main.GamePanel;

import java.util.Objects;

public class OBJ_BackDoor extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "BackDoor";
    boolean closeDoorFlag = false;
    int tempWorldX = 0;
    int tempWorldY = 0;
    int tileDistance = 0;
    int index = 0;

    public OBJ_BackDoor(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = true;
        type = type_closeable_door;
        name = OBJ_NAME;
        displayName = "BackDoor";
        image = setup("/objects/backDoor", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/frontBackDoorOpen", gp.tileSize, gp.tileSize);
        down1 = image;
        direction = "down";
        collision = true;

        solidArea.x = 33;
        solidArea.y = 0;
        solidArea.width = 15;
        solidArea.height = 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDialogue();
    }

    public void setDialogue() {
        dialogueText[0][0] = "You need a key to open the door";
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
        if (gp.npc[gp.currentMap][index] != null) {
            tileDistance = Math.abs((gp.npc[gp.currentMap][index].worldX / gp.tileSize - tempWorldX) + (gp.npc[gp.currentMap][index].worldY / gp.tileSize - tempWorldY));
        }
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
        startDialogue(this, 0);
        gp.keyH.enterPressed = false;
    }
}
