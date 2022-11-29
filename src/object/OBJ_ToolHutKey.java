package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ToolHutKey extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "ToolHutKey";

    public OBJ_ToolHutKey(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        name = OBJ_NAME;
        displayName = "Gold Key";
        description = "[" + name + "]\nA gold key";
        isSaleable = false;
        down1 = setup("/objects/toolHutKey", gp.tileSize, gp.tileSize);
        direction = "down";
        type = type_consumable;

        setDialogue();
    }

    public void setDialogue() {
        dialogueText[0][0] = "It seems to be a key to open a padlock.";
    }

    public boolean use(Entity entity) {
        if (gp.player.toolHutState == 0) {
            startDialogue(this, 0);
        }
        return false;
    }
}
