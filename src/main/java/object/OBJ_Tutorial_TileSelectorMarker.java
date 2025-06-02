package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Tutorial_TileSelectorMarker extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "Tutorial_TileSelectorMarker";

    public OBJ_Tutorial_TileSelectorMarker(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        name = OBJ_NAME;
        displayName = "Tutorial_TileSelectorMarker";
        image = setup("/objects/tutorial_tileSelectorMarker", gp.tileSize, gp.tileSize);
        down1 = image;
        direction = "down";
    }
}
