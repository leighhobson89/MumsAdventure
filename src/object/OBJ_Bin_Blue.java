package object;

import javax.imageio.ImageIO;

public class OBJ_Bin_Blue extends SuperObject {
    public OBJ_Bin_Blue() {
        name = "Bin_Blue";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/bin_Blue.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
