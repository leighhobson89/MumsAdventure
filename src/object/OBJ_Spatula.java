package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Spatula extends Entity {

    public OBJ_Spatula(GamePanel gp) {
        super(gp);

        type = type_long_weapon;
        name = "Spatula";
        down1 = setup("/objects/spatulaWeapon", gp.tileSize, gp.tileSize);
        direction = "down";
        attackValue = 3;
        attackArea.width = 48;
        attackArea.height = 36;
        description = "[" + name + "]\nSpatula for cooking. Also\nhandy for splatting bugs!";
        price = 10;

        collectable = true;
        isOpenable = false;

        isWeapon = true;
    }

}
