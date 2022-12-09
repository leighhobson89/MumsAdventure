package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Toolhut1_Center extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "Toolhut1_Center";

    public OBJ_Toolhut1_Center(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = true;
        type = type_hut;
        name = OBJ_NAME;
        displayName = "Toolhut1_Center";
        image = setup("/objects/toolhut1_center", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/toolhut1_center_open", gp.tileSize, gp.tileSize);
        down1 = image;
        direction = "down";
        collision = true;
        goesTransparentWhenHit = false;
        goesTransparentWhenStoodOnToolHut = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDialogue();
    }

    public void setDialogue() {
        dialogueText[0][0] = "You unlock the tool shed.";
        dialogueText[1][0] = "I need the tool shed key to get in here.";
        dialogueText[2][0] = "It's already unlocked!";
    }

    public void interact() {
        if (!opened && checkIfPlayerHasItem(gp.player.inventory, "ToolHutKey")) {
            gp.playSFX(3);
            gp.eHandler.removeItemFromPlayerInventory(gp.player.inventory, "ToolHutKey");
            startDialogue(this, 0);
            changeOtherObjectImage("Toolhut2_Center", 37, 7, 2);
            down1 = image2; //set open tool hut Image
            gp.player.toolHutState = 1;
            collision = false;
            opened = true;
            gp.keyH.enterPressed = false;
        } else if (!opened && !checkIfPlayerHasItem(gp.player.inventory, "ToolHutKey")) {
            startDialogue(this, 1);
            //debug
//            gp.player.inventory.add(new OBJ_ToolHutKey(gp));
            gp.keyH.enterPressed = false;
        } else if (opened) {
            startDialogue(this, 2);
            gp.keyH.enterPressed = false;
        }
    }
}
