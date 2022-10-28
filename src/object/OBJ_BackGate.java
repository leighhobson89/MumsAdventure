package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BackGate extends Entity {

    GamePanel gp;

    public OBJ_BackGate(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = type_obstacle;

        name = "BackGate";
        direction = "down";
        image = setup("/objects/backGate", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/backGateOpen", gp.tileSize, gp.tileSize);
        down1 = image;

        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    public void interact() {
        gp.gameState = gp.dialogueState;
        if (!opened) {
            gp.playSFX(4);

//            StringBuilder sb = new StringBuilder(); //for loot
//            sb.append("You opened the door");

            gp.ui.currentDialogue = "Back gate opened.";
            down1 = image2;
            collision = false;
            opened = true;

//            if (gp.player.canObtainItem(loot) == false) { //for loot
//                sb.append("\n...But you cannot carry any more!");
//            } else {
//                sb.append("\n I got a " + loot.name + "!");
//                down1 = image2;
//                opened = true;
//            }
//            gp.ui.currentDialogue = sb.toString();
        }
        else {
            gp.ui.currentDialogue = "The back gate's already open!";
        }
        gp.keyH.enterPressed = false;
    }
}
