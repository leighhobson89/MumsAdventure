package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_MumsChair extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "MumsChair";

    public OBJ_MumsChair(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "MumsChair";
        down1 = setup("/objects/mumsChair", gp.tileSize, gp.tileSize);
        direction = "down";

        collision = true;

        solidArea.x = 0;
        solidArea.y = 20;
        solidArea.width = 48;
        solidArea.height = 28;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDialogue();
    }

    public void setDialogue() {
        dialogueText[0][0] = "Should I sit quietly and destress, or watch TV?";
    }

    public void interact() {
        startDialogue(this, 0);
        gp.quizSubState = gp.mumsChair;
        gp.gameState = gp.quizState;
        gp.keyH.enterPressed = false;
    }
}
