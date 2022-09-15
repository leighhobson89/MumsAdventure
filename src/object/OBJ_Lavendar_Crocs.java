package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lavendar_Crocs extends Entity {

    public OBJ_Lavendar_Crocs(GamePanel gp) {
        super(gp);

        type = type_short_weapon;
        name = "Lavender Crocs";
        down1 = setup("/objects/lavendarCrocs", gp.tileSize, gp.tileSize);
        direction = "down";
        attackValue = 2;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nYour Favourite Lavender\nCrocs!";

        collectable = true;
        isOpenable = false;

        isWeapon = true;
    }

}
