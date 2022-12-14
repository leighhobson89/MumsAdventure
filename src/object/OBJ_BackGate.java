package object;

import entity.Entity;
import main.GamePanel;

import java.util.Objects;

public class OBJ_BackGate extends Entity {

    final GamePanel gp;
    boolean closeDoorFlag = false;
    int tempWorldX = 0;
    int tempWorldY = 0;
    int tileDistance = 0;
    int index = 0;
    public static final String OBJ_NAME = "BackGate";

    public OBJ_BackGate(GamePanel gp) {

        super(gp);
        this.gp = gp;

        npcCanWalkOnWhenFollowing = true;
        isUpdateable = true;
        type = type_closeable_door;
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

    public void update() {

        for (int i = 0; i < gp.npc[1].length; i++) {
            if (opened && gp.npc[gp.currentMap][i] != null && Objects.equals(gp.npc[gp.currentMap][i].name, "Dad")) {
                int touching = gp.cChecker.checkEntity(this, gp.npc);
                if (touching == gp.ARBITRARY_IDENTIFIER_CLOSEABLE_DOORS && !closeDoorFlag) {
                    index = i;
                    tempWorldX = gp.npc[gp.currentMap][i].worldX/gp.tileSize;
                    tempWorldY = gp.npc[gp.currentMap][i].worldY/gp.tileSize;
                    closeDoorFlag = true;
                }
            }
        }
        tileDistance = Math.abs((gp.npc[gp.currentMap][index].worldX/gp.tileSize - tempWorldX) + (gp.npc[gp.currentMap][index].worldY/gp.tileSize - tempWorldY));
        if (closeDoorFlag) {
            if (tileDistance > 1) {
                closeDoorFlag = false;
            }
            if (!closeDoorFlag) {
                down1 = image;
                collision = true;
                opened = false;
            }
        }
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
