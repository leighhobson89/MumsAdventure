package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_GarageKey extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "GarageKey";

    public OBJ_GarageKey(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        name = OBJ_NAME;
        displayName = "Key";
        description = "[" + name + "]\nKey For Garage";
        isSaleable = false;
        down1 = setup("/objects/garageKey", gp.tileSize, gp.tileSize);
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
        dialogueText[0][0] = "The garage is unlocked!";
        dialogueText[1][0] = "I need to use this on the garage door.";
    }

    public boolean use(Entity entity) {

        int objIndexFront = getDetected(entity, gp.obj, "GarageDoorWhite");

        if (objIndexFront != 999) {
            startDialogue(this, 0);
            gp.playSFX(3);
            gp.obj[gp.currentMap][objIndexFront].down1 = gp.obj[gp.currentMap][objIndexFront].image2;
            gp.obj[gp.currentMap][objIndexFront].collision = false;
            gp.obj[gp.currentMap][objIndexFront].opened = true;
        } else {
            startDialogue(this, 1);
            return false;
        }
        return false;
    }
}
