package main;

import entity.NPC_Dad;
import object.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

    }
    public void setNPC() {
        gp.npc[0] = new NPC_Dad(gp);
        gp.npc[0].worldX = gp.tileSize*14;
        gp.npc[0].worldY = gp.tileSize*10;
    }
}
