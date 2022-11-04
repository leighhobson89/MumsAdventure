package object;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

public class OBJ_LightningBoltStress extends Entity {

    GamePanel gp;

    public OBJ_LightningBoltStress(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "StressBolt";
        displayName = "some stress relief!";
        type = type_pickupOnly;
        value = 2;
        direction = "down";
        down1 = setup("/lifeBar/stress_full", gp.tileSize/2, (int) (gp.tileSize*0.7));
        image = setup("/lifeBar/stress_full", (int) (gp.tileSize*0.7), gp.tileSize);
        image2 = setup("/lifeBar/stress_half", (int) (gp.tileSize*0.7), gp.tileSize);
        image3 = setup("/lifeBar/stress_none", (int) (gp.tileSize*0.7), gp.tileSize);
    }

    public boolean use(Entity entity) {
        gp.playSFX(12);
        gp.player.stressLevel-= value;
        if (gp.player.stressLevel >= 0) {
            gp.ui.addMessage("Stress -" + value + " -> " + gp.player.stressLevel);
        } else {
            gp.ui.addMessage("Stress -" + value + " -> " + "0");
        }
        return true;
    }
}
