package entity;

import main.GamePanel;
import main.MissionStates;
import object.*;

import java.awt.*;

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

        solidArea = new Rectangle(-49, 0,5,40); //contact area reaches over fence so player can interact
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void getImage() {
        up1 = setup("/NPC/buyer_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/NPC/buyer_up2", gp.tileSize, gp.tileSize);
        down1 = setup("/NPC/buyer_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/NPC/buyer_down2", gp.tileSize, gp.tileSize);
        left1 = setup("/NPC/buyer_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/NPC/buyer_left2", gp.tileSize, gp.tileSize);
        right1 = setup("/NPC/buyer_left1", gp.tileSize, gp.tileSize);
        right2 = setup("/NPC/buyer_left2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue() {
        //DEFAULT INTRO
        dialogueText[0][0] = "'Ere, d'ya wanna sell that camper van 'n dat?\nI have loads of good stuff here innit!";

        //BONE THROWN AT MERCHANT
        dialogueText[1][0] = "Don't throw dat at me innit!";

        //MERCHANT NORMAL TEXTS
        dialogueText[2][0] = "Ok den, I'll wait 'ere while\nyou tink about it innit!";
        dialogueText[3][0] = "Dat's not enough coin, you have to pay more innit!";
        dialogueText[4][0] = "My pockets are all full, I can't carry more!";
        dialogueText[5][0] = "You need to unequip your item before you can sell it!";
        dialogueText[6][0] = "You cannot sell this item";

        //MISSION TEXTS
        //SELL_DADS_ELECTRIC_GUITAR_TO_THE_MERCHANT
        dialogueText[7][0] = "So are yer gonna find me an electric guitar den?";
        dialogueText[8][0] = "Oh wow man, dats excellent init, here take this!";
        dialogueText[9][0] = "This is not electric init, he won't want dat one!";
    }

    public void setItems() {
        inventory.add(new OBJ_Pills(gp));
        inventory.add(new OBJ_Hatchet(gp));
        inventory.add(new OBJ_LightPills(gp));
    }

    public void speak() {

        facePlayer();
        if (gp.player.missionState == MissionStates.SELL_DADS_ELECTRIC_GUITAR_TO_THE_MERCHANT) {
            dialogueSet = 7;
        } else {
            dialogueSet = 0;
        }
        startDialogue(this, dialogueSet);

        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
    }
}
