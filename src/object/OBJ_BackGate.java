package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.util.Objects;

public class OBJ_BackGate extends SuperObject {

    GamePanel gp;

    public OBJ_BackGate(GamePanel gp) {

        this.gp = gp;

        name = "BackGate";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/backGate.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
