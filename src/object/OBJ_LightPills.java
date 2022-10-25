package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_LightPills extends Entity {

    GamePanel gp;
    boolean consumableNow = false;

    public OBJ_LightPills(GamePanel gp) {

        super(gp);

        this.gp = gp;
        type = type_light;
        name = "Anti Brightness Pills";
        down1 = setup("/objects/lightPills", gp.tileSize, gp.tileSize);
        direction = "down";
        description = "[" + name + "]\nYour Anti Brightness Pills";
        isSaleable = true;
        price = 8;
        stackable = true;
        lightRadius = 576;
    }

        public boolean use(Entity entity) {

            gp.gameState = gp.dialogueState;
            if (gp.eManager.lighting.dayState != gp.eManager.lighting.day) { //only possible to consume after light level changes from standard
                consumableNow = true;
            }

            if (consumableNow) {
                gp.eHandler.lightPillsEvent();
                gp.player.usedItemOrNot = true;
                return true;
            } else {
                gp.ui.currentDialogue = "I better save these until the light\nis affecting me, 'cos they have some\ncrazy after effects!";
                return false;
            }
        }
    }
