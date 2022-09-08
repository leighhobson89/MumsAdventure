package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.util.Objects;

public class OBJ_MumsChair extends SuperObject {

    GamePanel gp;

    public OBJ_MumsChair(GamePanel gp) {

        this.gp = gp;

        name = "MumsChair";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/mumsChair.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
