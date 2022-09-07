package object;

import javax.imageio.ImageIO;

public class OBJ_InsideDoor extends SuperObject {
    public OBJ_InsideDoor() {
        name = "InsideDoor";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/insideDoor.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
