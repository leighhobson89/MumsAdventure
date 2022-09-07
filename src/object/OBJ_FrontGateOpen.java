package object;

import javax.imageio.ImageIO;

public class OBJ_FrontGateOpen extends SuperObject {
    public OBJ_FrontGateOpen() {
        name = "FrontGateOpen";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/frontGateOpen.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
