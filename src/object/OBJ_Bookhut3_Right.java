package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bookhut3_Right extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "Bookhut3_Right";

    public OBJ_Bookhut3_Right(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "Bookhut3_Right";
        image = setup("/objects/bookhut3_right", gp.tileSize, gp.tileSize);
        down1 = image;
        direction = "down";
        collision = true;
        goesTransparentWhenHit = false;
        goesTransparentWhenStoodOn = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }

    public void update() {
        makeObjectTransparentAndTempRemoveCollision(this);
    }

    public void makeObjectTransparentAndTempRemoveCollision(Entity entity) {
        if (gp.player.bookHutState == 1 && gp.player.insideShed) {
            collision = false;
            if (gp.player.worldX == this.worldX && gp.player.worldY == this.worldY) {
                transparent = true;
            }
        }
         else {
            transparent = false;
            collision = true;
        }
    }
}
