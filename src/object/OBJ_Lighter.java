package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lighter extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "Lighter";

    public OBJ_Lighter(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_consumable;
        name = OBJ_NAME;
        displayName = "Lighter";
        down1 = setup("/objects/lighter", gp.tileSize, gp.tileSize);
        direction = "down";
        description = "[" + name + "]\nA Lighter";
        price = 4;
        stackable = false;
        isSaleable = false;

        setDialogue();
    }

//        public boolean use(Entity entity) {
//
//            if (readytouse) {
//                startDialogue(this, 1);
//                return true;
//            }
//            startDialogue(this, 0);
//            return false;
//        }

        public void setDialogue() {
            dialogueText[0][0] = "A lighter, must be one of Leigh's from years\nago, oh it still works, I'll hang on to it in case!";
        }
    }
