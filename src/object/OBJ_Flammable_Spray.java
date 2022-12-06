package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Flammable_Spray extends Entity {

    public static final String OBJ_NAME = "Flammable Spray";

    public OBJ_Flammable_Spray(GamePanel gp) {
        super(gp);

        isUpdateable = false;
        type = type_consumable; //change to "type_short_weapon" when conditions are right
        name = OBJ_NAME;
        displayName = "Flammable Spray";
        image = setup("/objects/flammableSpray", gp.tileSize, gp.tileSize);
        down1 = image;
        //todo add image2 with flames for when its lit and turns into weapon
        direction = "down";
        attackValue = 2;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nSome super flammable spray";
        isSaleable = true;
        price = 13;
        knockBackPower = 3;
        isWeapon = false; //change to true when conditions are right
        motion1_duration = 5;
        motion2_duration = 25;

        setDialogue();
    }

    public void setDialogue() {
        dialogueText[0][0] = "Looks dangerous this stuff, it could burn if it got\nlit by mistake...";
        dialogueText[1][0] = "The spray can is lit!";
    }

}
