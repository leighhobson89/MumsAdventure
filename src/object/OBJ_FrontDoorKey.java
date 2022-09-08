package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.util.Objects;

public class OBJ_FrontDoorKey extends SuperObject {

    GamePanel gp;

    public OBJ_FrontDoorKey(GamePanel gp) {

        this.gp = gp;

        name = "FrontDoorKey";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/frontDoorKey.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
