package object;

import javax.imageio.ImageIO;

public class OBJ_FrontDoorKey extends SuperObject {
    public OBJ_FrontDoorKey() {
        name = "FrontDoorKey";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/frontDoorKey.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
