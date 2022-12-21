package entity;

import main.GamePanel;

public class PlayerDummy extends Entity {
    public static final String npcName = "PlayerDummy";

    public PlayerDummy(GamePanel gp) {
        super(gp);
        name = npcName;
        getImage(gp.ui.colorOutfit, false);
    }

    public void getImage(String colorOutfit, boolean loadGame) {
        //MENU
        down1 = setup("/player/mum_down1_brown", gp.tileSize, gp.tileSize);
        down1_red = setup("/player/mum_down1_red", gp.tileSize, gp.tileSize);
        down1_purple = setup("/player/mum_down1_purple", gp.tileSize, gp.tileSize);
        left1 = setup("/player/mum_left1_red", gp.tileSize, gp.tileSize);
        right2 = setup("/player/mum_right2_purple", gp.tileSize, gp.tileSize);
        dadDown1 = setup("/NPC/dad_down1", gp.tileSize, gp.tileSize);
        phoebeLeft1 = setup("/NPC/phoebe_left1", gp.tileSize, gp.tileSize);
        phoebeRight2 = setup("/NPC/phoebe_right2", gp.tileSize, gp.tileSize);

        if (gp.gameState == gp.playState || loadGame) {
            up1 = setup("/player/mum_up1_" + colorOutfit, gp.tileSize, gp.tileSize); //16 x 16 images
            up2 = setup("/player/mum_up2_" + colorOutfit, gp.tileSize, gp.tileSize);
            down1 = setup("/player/mum_down1_" + colorOutfit, gp.tileSize, gp.tileSize);
            down2 = setup("/player/mum_down2_" + colorOutfit, gp.tileSize, gp.tileSize);
            left1 = setup("/player/mum_left1_" + colorOutfit, gp.tileSize, gp.tileSize);
            left2 = setup("/player/mum_left2_" + colorOutfit, gp.tileSize, gp.tileSize);
            right1 = setup("/player/mum_right1_" + colorOutfit, gp.tileSize, gp.tileSize);
            right2 = setup("/player/mum_right2_" + colorOutfit, gp.tileSize, gp.tileSize);
        }
    }
}
