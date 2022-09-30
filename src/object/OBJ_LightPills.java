package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_LightPills extends Entity {

    GamePanel gp;

    public OBJ_LightPills(GamePanel gp) {

        super(gp);

        this.gp = gp;
        type = type_light;
        name = "Anti Brightness Pills";
        down1 = setup("/objects/lightPills", gp.tileSize, gp.tileSize);
        direction = "down";
        description = "[" + name + "]\nYour Anti Brightness Pills";
        price = 8;
        stackable = true;

        lightRadius = 576;
    }

        public boolean use(Entity entity) {

            gp.gameState = gp.dialogueState;
            gp.player.pillsConsumableNow = gp.player.stressLevel >= gp.player.LIGHT_LEVEL_NEEDED_TO_CONSUME_PILLS;

            if (gp.player.pillsConsumableNow) {
                removeLight();
                return true;
            }
            gp.ui.currentDialogue = "I better save these until the light\nis affecting me, 'cos they have some\ncrazy after effects!";
            return false;
        }

        public void removeLight() {

        }
    }
