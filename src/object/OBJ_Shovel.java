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
}
