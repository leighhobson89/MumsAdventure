package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lavendar_Crocs extends Entity {

    public OBJ_Lavendar_Crocs(GamePanel gp) {
        super(gp);

        name = "Lavendar Crocs";
        down1 = setup("/objects/lavendarCrocs", gp.tileSize, gp.tileSize);
        attackValue = 2;
        description = "[" + name + "]\nYour Favourite Crocs!";
    }

}
