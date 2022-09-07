package object;

import javax.imageio.ImageIO;

public class OBJ_InsideDoorOpen extends SuperObject {
    public OBJ_InsideDoorOpen() {
        name = "InsideDoorOpen";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/insideDoorOpen.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
