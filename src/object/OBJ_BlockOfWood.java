package object;

import entity.Entity;
import main.GamePanel;

import java.util.ArrayList;
import java.util.Objects;

public class OBJ_BlockOfWood extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "BlockOfWood";

    public OBJ_BlockOfWood(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = true;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "Chopping Block";
        image = setup("/objects/blockOfWood", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/blockOfWoodChicken", gp.tileSize, gp.tileSize);
        image3 = setup("/objects/blockOfWoodBlood", gp.tileSize, gp.tileSize);
        down1 = image;
        direction = "down";
        collision = false;
        goesTransparentWhenHit = false;

        stressLevel = 0;
        monsterMaxStress = 5;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDialogue();
    }

    public void setDialogue() {
        dialogueText[0][0] = "I'll get this chopped up nicely for Phoebe and Pip!\n(Whack it with your axe a few times!)";
        dialogueText[1][0] = "A chopping block covered in dried blood!";
        dialogueText[2][0] = "I need some chicken to chop!";
    }

    public boolean checkIfPlayerHasChicken(ArrayList<Entity> inventory) {
        boolean playerHasChicken= false;
        for (int i = 0; i < inventory.size(); i++) {
            if (Objects.equals(inventory.get(i).name, "Chicken")) {
                playerHasChicken = true;
            }
        }
        return playerHasChicken;
    }

    public void interact() {
        if (!opened && gp.player.missionState == 4 && checkIfPlayerHasChicken(gp.player.inventory)) {
            gp.playSFX(4); //change to chicken slapping sound
            gp.eHandler.removeChickenFromPlayerInventory(gp.player.inventory);
            startDialogue(this, 0);
            down1 = image2; //set chicken on wood image
            opened = true;
            gp.keyH.enterPressed = false;
        } else if (!opened && !checkIfPlayerHasChicken(gp.player.inventory) && gp.player.missionState == 4) {
            startDialogue(this, 2);
            gp.keyH.enterPressed = false;
        } else if (!opened && gp.player.missionState < 4) {
            startDialogue(this, 1);
            gp.keyH.enterPressed = false;
        }
    }
}
