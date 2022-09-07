package object;

import javax.imageio.ImageIO;

public class OBJ_InsideDoorOpenSideways extends SuperObject {
    public OBJ_InsideDoorOpenSideways() {
        name = "InsideDoorOpenSideways";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/insideDoorOpenSideways.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
