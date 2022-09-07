package object;

import javax.imageio.ImageIO;

public class OBJ_BackGate extends SuperObject {
    public OBJ_BackGate() {
        name = "BackGate";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/backGate.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
