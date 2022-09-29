package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_FrontDoorKey extends Entity {

    GamePanel gp;

    public OBJ_FrontDoorKey(GamePanel gp) {

        super(gp);
        this.gp = gp;

        name = "Key";
        down1 = setup("/objects/frontDoorKey", gp.tileSize, gp.tileSize);
        direction = "down";
        description = "[" + name + "]\nKey For Front And\nBack Door";
        type = type_consumable;

    }

    public boolean use(Entity entity) {

        gp.gameState = gp.dialogueState;
        int objIndex = getDetected(entity, gp.obj, "FrontDoor");

        if (objIndex != 999) {
            gp.ui.currentDialogue = "This door is unlocked now!";
            doorUnlockedCount++;
            System.out.println("DoorUnlockedCount " + doorUnlockedCount);
            gp.playSFX(3);
            gp.obj[gp.currentMap][objIndex] = null;
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
