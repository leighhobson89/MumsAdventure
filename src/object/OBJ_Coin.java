package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "A Pound Coin";

    public OBJ_Coin(GamePanel gp) {

        super(gp);

        this.gp = gp;

        isUpdateable = false;
        type = type_pickupOnly;
        value = 1;
        name = OBJ_NAME;
        displayName = "A Pound Coin";
        down1 = setup("/objects/goldCoin", gp.tileSize, gp.tileSize);
        direction = "down";
        description = "[" + name + "]\nA Quid!";
    }

    public boolean use(Entity entity) {
        gp.playSFX(22);
        gp.player.coin += value;
        gp.ui.addMessage("Coin +" + value + " -> " + gp.player.coin);
        return true;
    }
}
