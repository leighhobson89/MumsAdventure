package object;

import javax.imageio.ImageIO;

public class OBJ_Bin_Grey extends SuperObject {
    public OBJ_Bin_Grey() {
        name = "Bin_Grey";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/bin_Grey.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
