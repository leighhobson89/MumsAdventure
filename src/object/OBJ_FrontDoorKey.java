package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_FrontDoorKey extends Entity {

    GamePanel gp;

    public OBJ_FrontDoorKey(GamePanel gp) {

        super(gp);
        this.gp = gp;

        name = "Key";
        description = "[" + name + "]\nKey For Front And\nBack Door";
        isSaleable = false;
        down1 = setup("/objects/frontDoorKey", gp.tileSize, gp.tileSize);
        direction = "down";
        type = type_consumable;

    }

    public boolean use(Entity entity) {

        gp.gameState = gp.dialogueState;
        int objIndexFront = getDetected(entity, gp.obj, "FrontDoor");
        int objIndexBack = getDetected(entity, gp.obj, "BackDoor");

        if (objIndexFront != 999) {
            gp.ui.currentDialogue = "This door is unlocked now!";
            doorUnlockedCount++;
            System.out.println("DoorUnlockedCount " + doorUnlockedCount);
            gp.playSFX(3);
            gp.obj[gp.currentMap][objIndexFront] = null;
            gp.aSetter.setObjectAfterStart("FrontDoorOpen", gp.currentMap, 16, 11);
        } else if (objIndexBack != 999) {
            gp.ui.currentDialogue = "This door is unlocked now!";
            doorUnlockedCount++;
            System.out.println("DoorUnlockedCount " + doorUnlockedCount);
            gp.playSFX(3);
            gp.obj[gp.currentMap][objIndexBack] = null;
            gp.aSetter.setObjectAfterStart("FrontDoorOpen", gp.currentMap, 30, 11);
        } else {
            gp.ui.currentDialogue = "I need to use this on the front or back door.";
            return false;
        }
        if (doorUnlockedCount >= 2) {
            return true;
        } else {
            return false;
        }
    }
}
