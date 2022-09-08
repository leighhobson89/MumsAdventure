package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.util.Objects;

public class OBJ_InsideDoorSideways extends SuperObject {

    GamePanel gp;

    public OBJ_InsideDoorSideways(GamePanel gp) {

        this.gp = gp;

        name = "InsideDoorSideways";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/insideDoorSideways.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
