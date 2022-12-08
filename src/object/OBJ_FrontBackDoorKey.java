package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_FrontBackDoorKey extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "FrontBackDoorKey";

    public OBJ_FrontBackDoorKey(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        name = OBJ_NAME;
        displayName = "Key";
        description = "[" + name + "]\nKey For Front And\nBack Door";
        isSaleable = false;
        down1 = setup("/objects/frontBackDoorKey", gp.tileSize, gp.tileSize);
        direction = "down";
        type = type_consumable;

        setDialogue();
    }

    public void setDialogue() {
        dialogueText[0][0] = "This door is unlocked now!";
        dialogueText[1][0] = "I need to use this on the front or back door.";
    }

    public boolean use(Entity entity) {

        int objIndexFront = getDetected(entity, gp.obj, "FrontDoor");
        int objIndexBack = getDetected(entity, gp.obj, "BackDoor");

        if (objIndexFront != 999) {
            startDialogue(this, 0);
            doorUnlockedCount++;
            gp.playSFX(3);
            gp.obj[gp.currentMap][objIndexFront] = null;
            gp.aSetter.setObjectAfterStart("FrontBackDoorOpen", gp.currentMap, 16, 11, false);
        } else if (objIndexBack != 999) {
            startDialogue(this, 0);
            doorUnlockedCount++;
            gp.playSFX(3);
            gp.obj[gp.currentMap][objIndexBack] = null;
            gp.aSetter.setObjectAfterStart("FrontBackDoorOpen", gp.currentMap, 30, 11, false);
        } else {
            startDialogue(this, 1);
            return false;
        }
        return doorUnlockedCount >= 2;
    }
}
