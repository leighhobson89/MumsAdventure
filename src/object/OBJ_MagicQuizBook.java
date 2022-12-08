package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_MagicQuizBook extends Entity {

    final GamePanel gp;
    public static final String OBJ_NAME = "MagicQuizBook";

    public OBJ_MagicQuizBook(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        name = OBJ_NAME;
        displayName = "Magic Quiz Book";
        description = "[" + name + "]\nShow it Peter!";
        isSaleable = false;
        image = null;
        image2 = setup("/objects/quizBook", gp.tileSize, gp.tileSize);
        down1 = image;
        direction = "down";
        type = type_consumable;

        setDialogue();
    }

    public void setDialogue() {
        dialogueText[0][0] = "It's his favourite Magic Quiz Book!";
    }

    public boolean use(Entity entity) {
        startDialogue(this, 0);
        return false;
    }
}
