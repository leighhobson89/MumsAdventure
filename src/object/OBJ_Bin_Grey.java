package object;

import entity.Entity;
import main.GamePanel;

import java.util.Objects;

public class OBJ_Bin_Grey extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "Bin_Grey";

    public OBJ_Bin_Grey(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "Bin_Grey";
        down1 = setup("/objects/bin_Grey", gp.tileSize, gp.tileSize);
        direction = "down";
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
        dialogueText[0][0] = "Pooh! It stinks! Yuk!";
        dialogueText[1][0] = "There that's that crap rid of!\nI do wish he'd stop wasting money on that crap\noff ebay!";
        dialogueText[2][0] = "No need to open that festering horrid bin!";
    }

    public void interact() {
        if (!opened) {
            gp.playSFX(14);
            startDialogue(this, 0);
            for (int i = 0; i < gp.player.inventory.size(); i++) {
                if (Objects.equals(gp.player.inventory.get(i).name, "StainRemover")) {
                    startDialogue(this, 1);
                    opened = true;
                    gp.player.inventory.remove(i);
                    break;
                }
            }
        } else {
            startDialogue(this, 2);
        }
        gp.keyH.enterPressed = false;
    }
}
