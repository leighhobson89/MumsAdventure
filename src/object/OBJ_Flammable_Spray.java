package object;

import entity.Entity;
import main.GamePanel;
import main.MissionStates;

public class OBJ_Flammable_Spray extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "FlammableSpray";

    public OBJ_Flammable_Spray(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_consumable;
        name = OBJ_NAME;
        displayName = "Flammable Spray";
        image = null;
        image2 = setup("/objects/flammableSpray", gp.tileSize, gp.tileSize);
        image3 = setup("/objects/flammableSprayLit", gp.tileSize, gp.tileSize);
        down1 = image;
        direction = "down";
        attackValue = 5;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nSome super flammable\nspray";
        isSaleable = false;
        price = 0;
        knockBackPower = 3;
        isWeapon = false;
        motion1_duration = 5;
        motion2_duration = 25;

        setDialogue();
    }

    public void setDialogue() {
        dialogueText[0][0] = "Looks dangerous this stuff, it could burn if it got\nlit by mistake...";
    }

    public boolean use(Entity entity) {
        startDialogue(this, 0);
        return false;
    }
}
