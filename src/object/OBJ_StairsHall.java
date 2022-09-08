package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.util.Objects;

public class OBJ_StairsHall extends SuperObject {

    GamePanel gp;

    public OBJ_StairsHall(GamePanel gp) {

        this.gp = gp;

        name = "StairsHall";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/stairsHall.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
