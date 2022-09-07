package object;

import javax.imageio.ImageIO;

public class OBJ_Door2 extends SuperObject {
    public OBJ_Door2() {
        name = "Door2";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door2.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
