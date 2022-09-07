package object;

import javax.imageio.ImageIO;

public class OBJ_Tree2 extends SuperObject {
    public OBJ_Tree2() {
        name = "Tree2";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/tree2.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
