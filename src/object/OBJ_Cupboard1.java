package object;

import javax.imageio.ImageIO;

public class OBJ_Cupboard1 extends SuperObject {
    public OBJ_Cupboard1() {
        name = "Cupboard1";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/cupboard1.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
