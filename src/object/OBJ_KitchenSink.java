package object;

import entity.Entity;
import main.GamePanel;
import main.MissionStates;

import java.util.Objects;

public class OBJ_KitchenSink extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "KitchenSink";

    public OBJ_KitchenSink(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "KitchenSink";
        down1 = setup("/objects/kitSink", gp.tileSize, gp.tileSize);
        direction = "down";
        collision = true;

        solidArea.x = -1;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDialogue();
    }

    public void setDialogue() {
        dialogueText[0][0] = "The kitchen sink.  It can fill things with water!";
        dialogueText[1][0] = "That's the bucket full of water!";
        dialogueText[2][0] = "The bucket is already full of water!";
    }

    public void interact() {
        boolean hasBucket = false;
        int bucketIndex = 0;
        for (int i = 0; i < gp.player.inventory.size(); i++) {
            if (Objects.equals(gp.player.inventory.get(i).name, "Bucket")) {
                bucketIndex = i;
                hasBucket = true;
            }
        }
        if (hasBucket && !gp.player.bucketFull) {
            gp.player.inventory.get(bucketIndex).down1 = gp.player.inventory.get(bucketIndex).image2;
            gp.player.inventory.get(bucketIndex).description = "[" + gp.player.inventory.get(bucketIndex).name + "]\nA Bucket Full Of\nWater!";
            gp.player.bucketFull = true;
            gp.playSFX(37);
            startDialogue(this, 1);
        } else if (!hasBucket) {
            startDialogue(this, 0);
        } else {
            startDialogue(this, 2);
        }
        gp.keyH.enterPressed = false;
    }
}
