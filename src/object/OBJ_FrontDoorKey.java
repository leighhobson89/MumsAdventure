package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_FrontDoorKey extends Entity {

    GamePanel gp;

    public OBJ_FrontDoorKey(GamePanel gp) {

        super(gp);
        this.gp = gp;

        name = "Key";
        down1 = setup("/objects/frontDoorKey", gp.tileSize, gp.tileSize);
        direction = "down";
        description = "[" + name + "]\nKey For Front And\nBack Door";
        collectable = true;
        isOpenable = false;

    }

    public void use(Entity entity, boolean consumable, boolean useable) {

        gp.gameState = gp.dialogueState;
        int playerX = ((gp.player.worldX + gp.player.solidArea.x)/gp.tileSize);
        int playerY = ((gp.player.worldY + gp.player.solidArea.y)/gp.tileSize);

        gp.eHandler.openFrontBackDoor(playerX, playerY);
    }
}
