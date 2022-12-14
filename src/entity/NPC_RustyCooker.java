package entity;

import main.GamePanel;
import main.MissionStates;
import tile_interactive.IT_CookerTile;
import tile_interactive.InteractiveTile;

import java.awt.*;
import java.util.ArrayList;

public class NPC_RustyCooker extends Entity {

    final GamePanel gp;
    public static final String npcName = "OldCooker";

    public NPC_RustyCooker(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = npcName;
        direction = "down";
        speed = 0;
        collision = true;
        onPath = false;

        dialogueSet = 0;

        getImage();
        setDialogue();

        solidArea = new Rectangle(10, 6,20,40);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void getImage() {

        up1 = setup("/NPC/oldCooker", gp.tileSize, gp.tileSize);
        up2 = setup("/NPC/oldCooker", gp.tileSize, gp.tileSize);
        down1 = setup("/NPC/oldCooker", gp.tileSize, gp.tileSize);
        down2 = setup("/NPC/oldCooker", gp.tileSize, gp.tileSize);
        left1 = setup("/NPC/oldCooker", gp.tileSize, gp.tileSize);
        left2 = setup("/NPC/oldCooker", gp.tileSize, gp.tileSize);
        right1 = setup("/NPC/oldCooker", gp.tileSize, gp.tileSize);
        right2 = setup("/NPC/oldCooker", gp.tileSize, gp.tileSize);
    }

    public void setDialogue() {
        dialogueText[0][0] = "An old rusty cooker!";
        dialogueText[1][0] = "You can pull the cooker to where the bins are so it\ncan be collected next week!";
        dialogueText[1][1] = "If you get it stuck, just go upstairs and back down\nand it will magically move back to the start!";
        dialogueText[1][2] = "Look for a tile on its own next to the gate, and\ndump it there for collection!";
        dialogueText[2][0] = "There, that's another old bit of junk sorted\nhope they hurry up before he sees it else he'll\nno doubt try and rescue it again!";
        dialogueText[2][1] = "'If they don't come Miss, I'll sort it out for you init!'";
    }

    public void speak() {

        //character specific stuff here
        facePlayer();
        startDialogue(this, dialogueSet);
        if (gp.player.missionState == MissionStates.DRAG_COOKER_TO_BINS) {
            dialogueSet = 1;
        }
    }

    public void setAction(int goalCol, int goalRow) {
        if (gp.player.missionState == MissionStates.DRAG_COOKER_TO_BINS) {
            speed = 4;
        }
    }

    public void move(String d) {
        if (gp.player.missionState == MissionStates.DRAG_COOKER_TO_BINS) {
            this.direction = d;

            checkCollision();

            if(!collisionOn) {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }
            detectPlate();
        }
    }

    public void detectPlate() {
        ArrayList<InteractiveTile> padList = new ArrayList<>();
        ArrayList<Entity> cookerList = new ArrayList<>();

        //Create a list of pads
        for (int i = 0; i < gp.iTile[1].length; i++) {
            if (gp.iTile[gp.currentMap][i] != null && gp.iTile[gp.currentMap][i].name != null && gp.iTile[gp.currentMap][i].name.equals(IT_CookerTile.itName)) {
                padList.add(gp.iTile[gp.currentMap][i]);
            }
        }

        //Create a list of movable items e.g. cooker
        for (int i = 0; i < gp.npc[1].length; i++) {
            if (gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(NPC_RustyCooker.npcName)) {
                cookerList.add(gp.npc[gp.currentMap][i]);
            }
        }

        int count = 0;

        // Scan the pad list
        for (InteractiveTile interactiveTile : padList) {
            int xDistance = Math.abs(worldX - interactiveTile.worldX);
            int yDistance = Math.abs(worldY - interactiveTile.worldY);
            int distance = Math.max(xDistance, yDistance);

            if (distance < 10) {
                if (linkedEntity == null) {
                    linkedEntity = interactiveTile;
                    gp.playSFX(10); //change to cooker in right place sfx
                    this.worldX = 60 * gp.tileSize;
                    this.worldY = 12 * gp.tileSize;
                }
            } else {
                if (linkedEntity == interactiveTile) {
                    linkedEntity = null;
                }
            }
        }

        //Scan the movable item list
        for (Entity entity : cookerList) {
            //Count the items on the pads
            if (entity.linkedEntity != null) {
                count++;
            }
        }

        //if all good mission can complete
        if (count == cookerList.size()) {
            startDialogue(this, 2);
            gp.misStat.endMissionTasks(MissionStates.DRAG_COOKER_TO_BINS, true);
            collision = false;
        }
    }
}
