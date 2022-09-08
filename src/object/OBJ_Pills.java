package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.util.Objects;

public class OBJ_Pills extends SuperObject {

    GamePanel gp;

    public OBJ_Pills(GamePanel gp) {

        this.gp = gp;

        name = "Pills";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/pills.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
