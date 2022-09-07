package object;

import javax.imageio.ImageIO;

public class OBJ_Tree1 extends SuperObject {
    public OBJ_Tree1() {
        name = "Tree1";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/tree1.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
