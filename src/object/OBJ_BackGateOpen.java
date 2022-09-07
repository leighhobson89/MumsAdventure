package object;

import javax.imageio.ImageIO;

public class OBJ_BackGateOpen extends SuperObject {
    public OBJ_BackGateOpen() {
        name = "BackGateOpen";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/backGateOpen.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
