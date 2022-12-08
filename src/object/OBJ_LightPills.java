package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_LightPills extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "Anti Brightness Pills";

    boolean consumableNow = false;

    public OBJ_LightPills(GamePanel gp) {

        super(gp);

        this.gp = gp;

        isUpdateable = false;
        type = type_light;
        name = OBJ_NAME;
        displayName = "Anti Brightness Pills";
        down1 = setup("/objects/lightPills", gp.tileSize, gp.tileSize);
        direction = "down";
        description = "[" + name + "]\nYour Anti Brightness Pills";
        isSaleable = true;
        price = 8;
        stackable = true;
        lightRadius = 576;

        setDialogue();
    }

        public void setDialogue() {
         dialogueText[0][0] = "I better save these until the light\nis affecting me, 'cos they have some\ncrazy after effects!";
        }

        public boolean use(Entity entity) {

            if (gp.eManager.lighting.dayState != gp.eManager.lighting.day) { //only possible to consume after light level changes from standard
                consumableNow = true;
            }

            if (consumableNow) {
                gp.eHandler.lightPillsEvent();
                gp.player.usedItemOrNot = true;
                return true;
            } else {
                startDialogue(this, 0);
                return false;
            }
        }
    }
