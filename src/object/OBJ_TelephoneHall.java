package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.util.Objects;

public class OBJ_TelephoneHall extends SuperObject {

    GamePanel gp;

    public OBJ_TelephoneHall(GamePanel gp) {

        this.gp = gp;

        name = "TelephoneHall";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/telephoneHall.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
