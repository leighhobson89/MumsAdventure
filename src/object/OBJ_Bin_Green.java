package object;

import javax.imageio.ImageIO;

public class OBJ_Bin_Green extends SuperObject {
    public OBJ_Bin_Green() {
        name = "Bin_Green";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/bin_Green.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
