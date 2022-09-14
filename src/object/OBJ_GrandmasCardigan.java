package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_GrandmasCardigan extends Entity {

    public OBJ_GrandmasCardigan(GamePanel gp) {
        super(gp);

        name = "Grandma's Cardigan";
        down1 = setup("/objects/grandmasCardigan", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        description = "[" + name + "]\nAn Old Cardigan";;
    }
}
