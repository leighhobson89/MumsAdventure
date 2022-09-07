package object;

import javax.imageio.ImageIO;

public class OBJ_StairsHall extends SuperObject {
    public OBJ_StairsHall() {
        name = "StairsHall";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/stairsHall.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
