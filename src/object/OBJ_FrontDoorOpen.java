package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.util.Objects;

public class OBJ_FrontDoorOpen extends SuperObject {

    GamePanel gp;

    public OBJ_FrontDoorOpen(GamePanel gp) {

        this.gp = gp;

        name = "FrontDoorOpen";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/frontDoorOpen.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
