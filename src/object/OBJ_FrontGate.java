package object;

import javax.imageio.ImageIO;

public class OBJ_FrontGate extends SuperObject {
    public OBJ_FrontGate() {
        name = "FrontGate";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/frontGate.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
