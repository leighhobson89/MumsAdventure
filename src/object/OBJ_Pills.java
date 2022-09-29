package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Pills extends Entity {

    GamePanel gp;

    public OBJ_Pills(GamePanel gp) {

        super(gp);

        this.gp = gp;
        type = type_consumable;
        name = "Tube of Pills";
        down1 = setup("/objects/pills", gp.tileSize, gp.tileSize);
        direction = "down";
        description = "[" + name + "]\nYour Daily Pills";
        price = 5;
    }

        public boolean use(Entity entity) {

            gp.gameState = gp.dialogueState;
            gp.player.pillsConsumableNow = gp.player.stressLevel >= gp.player.STRESS_LEVEL_NEEDED_TO_CONSUME_PILLS;

            if (gp.player.pillsConsumableNow) {
                gp.eHandler.teleportPills(gp.currentMap);
                return true;
            }
            gp.ui.currentDialogue = "I better save these until I'm stressed\nout, 'cos they have some crazy after\neffects!";
            return false;
        }
    }
