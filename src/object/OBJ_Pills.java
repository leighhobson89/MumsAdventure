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

        collectable = true;
        isOpenable = false;
    }

        public void use(Entity entity, boolean consumable, boolean useable) {

            gp.gameState = gp.dialogueState;
            System.out.println("pill consumable" + pillsConsumableNow);

            if (consumable) {
                gp.eHandler.teleportPills();
                gp.player.checkPillsConsumable(gp.player.stressLevel);
            }
        }
    }
