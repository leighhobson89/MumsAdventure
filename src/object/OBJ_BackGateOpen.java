package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.util.Objects;

public class OBJ_BackGateOpen extends SuperObject {

    GamePanel gp;

    public OBJ_BackGateOpen(GamePanel gp) {

        this.gp = gp;

        name = "BackGateOpen";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/backGateOpen.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
