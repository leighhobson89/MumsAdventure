package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.util.Objects;

public class OBJ_InsideDoorOpen extends SuperObject {

    GamePanel gp;

    public OBJ_InsideDoorOpen(GamePanel gp) {

        this.gp = gp;

        name = "InsideDoorOpen";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/insideDoorOpen.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
