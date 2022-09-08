package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.util.Objects;

public class OBJ_Guitar2 extends SuperObject {

    GamePanel gp;

    public OBJ_Guitar2(GamePanel gp) {

        this.gp = gp;

        name = "Guitar2";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/guitar2.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
