package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Pills extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "Tube of Pills";

    public OBJ_Pills(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_consumable;
        name = OBJ_NAME;
        displayName = "Tube of Pills";
        down1 = setup("/objects/pills", gp.tileSize, gp.tileSize);
        direction = "down";
        description = "[" + name + "]\nYour Daily Pills";
        price = 5;
        stackable = true;
        isSaleable = true;

        solidArea.x = -3;
        solidArea.y = -3;
        solidArea.width = 51;
        solidArea.height = 51;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDialogue();
    }

        public boolean use(Entity entity) {

            gp.player.pillsConsumableNow = gp.player.stressLevel >= gp.player.STRESS_LEVEL_NEEDED_TO_CONSUME_PILLS;

            if (gp.player.pillsConsumableNow) {
                gp.eHandler.teleportPills(gp.currentMap);
                gp.playSFX(2);
                gp.player.countDownTimerForItemEffect("Pills");
                return true;
            }
            startDialogue(this, 0);
            return false;
        }

        public void setDialogue() {
            dialogueText[0][0] = "I better save these until I'm stressed\nout, 'cos they have some crazy after\neffects!";
        }
    }
