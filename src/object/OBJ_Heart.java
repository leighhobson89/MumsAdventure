package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.util.Objects;

public class OBJ_Heart extends SuperObject {

    GamePanel gp;

    public OBJ_Heart(GamePanel gp) {

        this.gp = gp;

        name = "Heart";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/lifeBar/stress_full.png")));
            image2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/lifeBar/stress_half.png")));
            image3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/lifeBar/stress_none.png")));
            image = uTool.scaleImage(image, (int) (gp.tileSize*0.7), gp.tileSize);
            image2 = uTool.scaleImage(image2, (int) (gp.tileSize*0.7), gp.tileSize);
            image3 = uTool.scaleImage(image3, (int) (gp.tileSize*0.7), gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
