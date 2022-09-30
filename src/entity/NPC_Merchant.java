package entity;

import main.GamePanel;
import object.*;

import java.awt.*;
import java.util.Random;

public class NPC_Merchant extends Entity {
    public NPC_Merchant(GamePanel gp) {
        super(gp);

        name = "Merchant";
        direction = "left";
        speed = 0;
        type = type_npc;

        getImage();
        setDialogue();
        setItems();

        solidArea = new Rectangle(-64, -64,128,128); //contact area reaches over fence so player can interact
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void getImage() {
        up1 = setup("/NPC/buyer_left1", gp.tileSize, gp.tileSize);
        up2 = setup("/NPC/buyer_left2", gp.tileSize, gp.tileSize);
        down1 = setup("/NPC/buyer_left1", gp.tileSize, gp.tileSize);
        down2 = setup("/NPC/buyer_left2", gp.tileSize, gp.tileSize);
        left1 = setup("/NPC/buyer_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/NPC/buyer_left2", gp.tileSize, gp.tileSize);
        right1 = setup("/NPC/buyer_left1", gp.tileSize, gp.tileSize);
        right2 = setup("/NPC/buyer_left2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue() {
        randomChummeringDialogues[0] = "'Ere, d'ya wanna sell that camper van 'n dat?\nI have loads of good stuff here innit!";
    }

    public void setItems() {
        inventory.add(new OBJ_Pills(gp));
        inventory.add(new OBJ_Spatula(gp));
        inventory.add(new OBJ_Guitar2(gp));
        inventory.add(new OBJ_LightPills(gp));
    }

    public void speak() {

        //character specific stuff here
        super.speak();
        gp.gameState = gp.tradeState;
        gp.ui.merchant = this;
    }

}
