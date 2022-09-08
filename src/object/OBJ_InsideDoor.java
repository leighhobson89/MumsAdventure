package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.util.Objects;

public class OBJ_InsideDoor extends SuperObject {

    GamePanel gp;

    public OBJ_InsideDoor(GamePanel gp) {

        this.gp = gp;

        name = "InsideDoor";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/insideDoor.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
