package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BookHutKey extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "BookHutKey";

    public OBJ_BookHutKey(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        name = OBJ_NAME;
        displayName = "Silver Key";
        description = "[" + name + "]\nA silver key";
        isSaleable = false;
        down1 = setup("/objects/bookHutKey", gp.tileSize, gp.tileSize);
        direction = "down";
        type = type_consumable;

        setDialogue();
    }

    public void setDialogue() {
        dialogueText[0][0] = "It seems to be a key to open a padlock.";
    }

    public boolean use(Entity entity) {
        if (gp.player.bookHutState == 0) {
            startDialogue(this, 0);
        }
        return false;
    }
}
