package object;

import javax.imageio.ImageIO;

public class OBJ_Pills extends SuperObject {
    public OBJ_Pills() {
        name = "Pills";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/pills.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
