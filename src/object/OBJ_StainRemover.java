package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_StainRemover extends Entity {

    public static final String OBJ_NAME = "StainRemover";

    public OBJ_StainRemover(GamePanel gp) {
        super(gp);

        isUpdateable = false;
        name = OBJ_NAME;
        displayName = "Stain Remover";
        down1 = setup("/objects/stainRemover", gp.tileSize, gp.tileSize);
        direction = "down";
        description = "[" + name + "]\nDodgy Chinese\nStain Remover";
        isSaleable = true;
        price = 20;
    }

}
