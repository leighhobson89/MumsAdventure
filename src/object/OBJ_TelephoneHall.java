package object;

import javax.imageio.ImageIO;

public class OBJ_TelephoneHall extends SuperObject {
    public OBJ_TelephoneHall() {
        name = "TelephoneHall";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/telephoneHall.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
