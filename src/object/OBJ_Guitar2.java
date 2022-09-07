package object;

import javax.imageio.ImageIO;

public class OBJ_Guitar2 extends SuperObject {
    public OBJ_Guitar2() {
        name = "Guitar2";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guitar2.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
