package object;

import javax.imageio.ImageIO;

public class OBJ_FrontDoor extends SuperObject {
    public OBJ_FrontDoor() {
        name = "FrontDoor";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/frontDoor.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
