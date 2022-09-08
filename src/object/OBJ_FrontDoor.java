package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.util.Objects;

public class OBJ_FrontDoor extends SuperObject {

    GamePanel gp;

    public OBJ_FrontDoor(GamePanel gp) {

        this.gp = gp;

        name = "FrontDoor";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/frontDoor.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
