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
        stackable = true;
        isSaleable = true;
    }

        public boolean use(Entity entity) {

            gp.gameState = gp.dialogueState;
            gp.player.pillsConsumableNow = gp.player.stressLevel >= gp.player.STRESS_LEVEL_NEEDED_TO_CONSUME_PILLS;

            if (gp.player.pillsConsumableNow) {
                gp.eHandler.teleportPills(gp.currentMap);
                gp.eManager.lighting.dayState = gp.eManager.lighting.day;
                gp.eManager.lighting.filterAlpha = 0;
                gp.eManager.lighting.dayCounter = 0;
                return true;
            }
            gp.ui.currentDialogue = "I better save these until I'm stressed\nout, 'cos they have some crazy after\neffects!";
            return false;
        }
    }
