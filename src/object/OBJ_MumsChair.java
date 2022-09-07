package object;

import javax.imageio.ImageIO;

public class OBJ_MumsChair extends SuperObject {
    public OBJ_MumsChair() {
        name = "MumsChair";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/mumsChair.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
