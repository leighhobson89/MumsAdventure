package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.util.Objects;

public class OBJ_Bin_Blue extends SuperObject {

    GamePanel gp;

    public OBJ_Bin_Blue(GamePanel gp) {

        this.gp = gp;

        name = "Bin_Blue";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/bin_Blue.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
