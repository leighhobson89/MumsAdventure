package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BackGate extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "BackGate";

    public OBJ_BackGate(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "BackGate";
        direction = "down";
        image = setup("/objects/backGate", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/backGateOpen", gp.tileSize, gp.tileSize);
        down1 = image;
        gp.player.backGateState = 1; //for upstairs correct image

        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDialogue();
    }

    public void setDialogue() {
        dialogueText[0][0] = "Back gate opened.";
        dialogueText[1][0] = "The back gate's already open!";
    }
    public void interact() {
        if (!opened) {
            gp.playSFX(4);

//            StringBuilder sb = new StringBuilder(); //for loot objects only like chests
//            sb.append("You opened the door");

            startDialogue(this, 0);
            down1 = image2;
            gp.player.backGateState = 2; //for upstairs correct image
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
            startDialogue(this, 1);
        }
        gp.keyH.enterPressed = false;
    }
}
