package object;

import javax.imageio.ImageIO;

public class OBJ_FrontDoorOpen extends SuperObject {
    public OBJ_FrontDoorOpen() {
        name = "FrontDoorOpen";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/frontDoorOpen.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
