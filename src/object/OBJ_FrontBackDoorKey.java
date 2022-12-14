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

        solidArea.x = -3;
        solidArea.y = -3;
        solidArea.width = 51;
        solidArea.height = 60;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

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
            gp.playSFX(3);
            gp.obj[gp.currentMap][objIndexFront].down1 = gp.obj[gp.currentMap][objIndexFront].image2;
            gp.obj[gp.currentMap][objIndexFront].collision = false;
            gp.obj[gp.currentMap][objIndexFront].opened = true;
        } else if (objIndexBack != 999) {
            startDialogue(this, 0);
            gp.playSFX(3);
            gp.obj[gp.currentMap][objIndexBack].down1 = gp.obj[gp.currentMap][objIndexBack].image2;
            gp.obj[gp.currentMap][objIndexBack].collision = false;
            gp.obj[gp.currentMap][objIndexBack].opened = true;
        } else {
            startDialogue(this, 1);
            return false;
        }
        return false;
    }
}
