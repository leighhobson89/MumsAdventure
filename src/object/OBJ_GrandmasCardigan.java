package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_GrandmasCardigan extends Entity {

    public OBJ_GrandmasCardigan(GamePanel gp) {
        super(gp);

        type = type_armour;
        name = "Old Cardigan";
        down1 = setup("/objects/grandmasCardigan", gp.tileSize, gp.tileSize);
        direction = "down";
        defenseValue = 1;
        description = "[" + name + "]\nAn Old Cardigan";
        collectable = true;
        isOpenable = false;

        isArmour = true;
    }
}
