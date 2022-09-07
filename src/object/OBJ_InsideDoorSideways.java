package object;

import javax.imageio.ImageIO;

public class OBJ_InsideDoorSideways extends SuperObject {
    public OBJ_InsideDoorSideways() {
        name = "InsideDoorSideways";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/insideDoorSideways.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
