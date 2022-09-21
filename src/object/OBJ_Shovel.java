package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shovel extends Entity {

    GamePanel gp;

    public OBJ_Shovel(GamePanel gp) {

        super(gp);

        this.gp = gp;
        type = type_gardeningShovel;
        name = "Garden Shovel";
        down1 = setup("/objects/shovel", gp.tileSize, gp.tileSize);
        direction = "down";
        description = "[" + name + "]\nDig up the Rockery!";

        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;

        collectable = true;
        isOpenable = false;

        isWeapon = true;
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
