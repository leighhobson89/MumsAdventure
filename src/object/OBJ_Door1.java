package object;

import javax.imageio.ImageIO;

public class OBJ_Door1 extends SuperObject {
    public OBJ_Door1() {
        name = "Door1";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door1.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
