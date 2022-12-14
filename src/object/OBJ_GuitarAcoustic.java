package object;

import entity.Entity;
import main.GamePanel;

import java.awt.*;
import java.util.Objects;

public class OBJ_GuitarAcoustic extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "Acoustic Guitar";

    public OBJ_GuitarAcoustic(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = true;
        npcCanWalkOnWhenFollowing = true;
        type = type_dads_guitar;
        name = OBJ_NAME;
        displayName = "Acoustic Guitar";
        image = setup("/objects/guitar1", gp.tileSize, gp.tileSize);
        image2 = null;
        down1 = image;
        direction = "down";
        description = "[" + name + "]\nAcoustic Guitar";
        price = 80;
        isSaleable = false;
        collision = true;

        solidArea = new Rectangle(-16, 0,80,48);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDialogue();
    }

    public void setDialogue() {
        dialogueText[0][0] = "Oi! Don't touch my guitar, you'll wreck it!";
    }

    public void update() {
        if (gp.player.inLivingRoom && !gp.player.guitarToMusicCenterTransitionPart1) {
            for (int i = 0; i < gp.npc[1].length; i++) {
                if (gp.npc[gp.currentMap][i] != null && Objects.equals(gp.npc[gp.currentMap][i].name, "Dad")) {
                    int touching = gp.cChecker.checkEntity(this, gp.npc);
                    if (touching != 999) {
                        down1 = image2;
                        collision = false;
                        gp.player.dadHasGuitar = true;
                    }
                }
            }
        } else if (gp.player.dadHasGuitar) {
            for (int i = 0; i < gp.npc[1].length; i++) {
                if (gp.npc[gp.currentMap][i] != null && Objects.equals(gp.npc[gp.currentMap][i].name, "Dad")) {
                    int touching = gp.cChecker.checkEntity(this, gp.npc);
                    if (touching == gp.ARBITRARY_IDENTIFIER_DAD) {
                        down1 = image;
                        collision = true;
                    }
                }
            }
        }
    }

    public void interact() {
        if (down1 == image) {
            startDialogue(this, 0);

        }
        gp.keyH.enterPressed = false;
    }
}
