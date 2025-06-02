package entity;

import main.GamePanel;

import java.awt.*;

public class NPC_Baldy extends Entity {
    public NPC_Baldy(GamePanel gp) {
        super(gp);

        name = "Baldy";
        direction = "right";
        speed = 1;
        type = type_npc;

        getImage();

        solidArea = new Rectangle(8, 16,32,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void getImage() {

        up1 = setup("/NPC/baldy_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/NPC/baldy_up2", gp.tileSize, gp.tileSize);
        down1 = setup("/NPC/baldy_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/NPC/baldy_down2", gp.tileSize, gp.tileSize);
        left1 = setup("/NPC/baldy_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/NPC/baldy_left2", gp.tileSize, gp.tileSize);
        right1 = setup("/NPC/baldy_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/NPC/baldy_right2", gp.tileSize, gp.tileSize);

    }

    public void setAction(int goalCol, int goalRow) {

        if (checkEdgeOfMap(this)) {
            turnEntityAround(this);
        } else {
            getRandomDirection();
        }
    }
}
