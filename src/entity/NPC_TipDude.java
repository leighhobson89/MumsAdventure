package entity;

import main.GamePanel;

import java.awt.*;

public class NPC_TipDude extends Entity {
    public NPC_TipDude(GamePanel gp) {
        super(gp);

        name = "TipDude";
        direction = "down";
        speed = 1;
        type = type_npc;

        getImage();
        setDialogue();

        solidArea = new Rectangle(8, 16,32,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void getImage() {

        up1 = setup("/NPC/tipDude_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/NPC/tipDude_up2", gp.tileSize, gp.tileSize);
        down1 = setup("/NPC/tipDude_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/NPC/tipDude_down2", gp.tileSize, gp.tileSize);
        left1 = setup("/NPC/tipDude_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/NPC/tipDude_left2", gp.tileSize, gp.tileSize);
        right1 = setup("/NPC/tipDude_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/NPC/tipDude_right2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue() {
        //DEFAULT INTRO
        dialogueText[0][0] = "Do you know Sharon and Peter Hobson?";
        dialogueText[0][1] = "Yes mayte, why innit?";
        dialogueText[0][2] = "I have some money here for this old cooker.";
        dialogueText[0][3] = "Will you pass them it?  I can't be bothered going down\nto the house.";
        dialogueText[0][4] = "Yes mayte, no problem innit, I'll give them\nit straight away, honest mate!";
        dialogueText[0][5] = "By the way mayte, do you know anyone selling\na camper van, they won't sell theirs innit!";
        dialogueText[0][6] = "Sorry no I don't, right I'm off!\nMake sure they get the money, I'm gonna ring them now!";
        dialogueText[0][7] = "Oh yes mayte, definitely they will get it innit!\n*WINK..WINK*";
    }
}
