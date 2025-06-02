package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_SqueakyToy_UI extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "Squeaky Toy UI";

    public OBJ_SqueakyToy_UI(GamePanel gp) {
        super(gp);
        this.gp = gp;

        isUpdateable = false;
        name = OBJ_NAME;
        displayName = "Squeaky Toy UI";

        image = setup("/projectiles/pips_toy_down1", gp.tileSize,gp.tileSize);
        image2 = setup("/projectiles/pips_toy_down1_blank", gp.tileSize,gp.tileSize);
    }
}
