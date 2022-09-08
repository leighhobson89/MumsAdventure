package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.util.Objects;

public class OBJ_Bin_Green extends SuperObject {

    GamePanel gp;

    public OBJ_Bin_Green(GamePanel gp) {

        this.gp = gp;

        name = "Bin_Green";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/bin_Green.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
