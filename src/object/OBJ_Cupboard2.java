package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.util.Objects;

public class OBJ_Cupboard2 extends SuperObject {

    GamePanel gp;

    public OBJ_Cupboard2(GamePanel gp) {

        this.gp = gp;

        name = "Cupboard2";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/cupboard2.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
