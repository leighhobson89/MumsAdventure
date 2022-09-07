package object;

import javax.imageio.ImageIO;

public class OBJ_Guitar1 extends SuperObject {
    public OBJ_Guitar1() {
        name = "Guitar1";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guitar1.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
