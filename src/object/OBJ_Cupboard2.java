package object;

import javax.imageio.ImageIO;

public class OBJ_Cupboard2 extends SuperObject {
    public OBJ_Cupboard2() {
        name = "Cupboard2";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/cupboard2.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
