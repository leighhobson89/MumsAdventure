package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_FortyQuidForAndrea extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "Forty Quid For Andrea";

    public OBJ_FortyQuidForAndrea(GamePanel gp) {

        super(gp);

        this.gp = gp;
        value = 40;
        name = OBJ_NAME;
        displayName = "Forty Quid For Andrea";
        down1 = setup("/objects/fortyQuidForAndrea", gp.tileSize, gp.tileSize);
        direction = "down";
        isSaleable = true;
        description = "[" + name + "]\nLend it Andrea!";
        price = -58;
    }

    public boolean use(Entity entity) {
        gp.playSFX(22);
        gp.player.coin += value;
        gp.ui.addMessage("Coin +" + value + " -> " + gp.player.coin);
        return true;
    }
}
