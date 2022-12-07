package object;

import entity.Entity;
import main.GamePanel;
import main.MissionStates;

import java.util.Objects;

public class OBJ_Lighter extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "Lighter";

    public OBJ_Lighter(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_consumable;
        name = OBJ_NAME;
        displayName = "Lighter";
        down1 = setup("/objects/lighter", gp.tileSize, gp.tileSize);
        direction = "down";
        description = "[" + name + "]\nA Lighter";
        price = 4;
        stackable = false;
        isSaleable = false;

        setDialogue();
    }

        public boolean use(Entity entity) {

            if (gp.player.missionState != MissionStates.GET_RID_OF_WASP_NEST) {
                startDialogue(this, 0);
                gp.keyH.enterPressed = false;
                return false;
            } else if (gp.player.missionSubstate == 1 && !checkIfPlayerHasMissionItem(gp.player.inventory, MissionStates.GET_RID_OF_WASP_NEST, 1)) {
                startDialogue(this, 1);
                gp.keyH.enterPressed = false;
                return false;
            } else if (gp.player.missionSubstate == 1 && checkIfPlayerHasMissionItem(gp.player.inventory, MissionStates.GET_RID_OF_WASP_NEST, 1)) {
                for (int i = 0; i < gp.player.inventory.size(); i++) {
                    if (Objects.equals(gp.player.inventory.get(i).name, "FlammableSpray")) {
                        gp.player.inventory.get(i).name = "FlammableSprayWeapon";
                        gp.player.inventory.get(i).isWeapon = true;
                        gp.player.inventory.get(i).type = type_flamingAerosol;
                        gp.player.inventory.get(i).down1 = image2;
                        gp.player.currentWeapon = gp.player.inventory.get(i);
                        gp.player.attack = gp.player.getAttack();
                        gp.player.getAttackImage(gp.ui.outfitChosen);
                    }
                    if (Objects.equals(gp.player.inventory.get(i).name, "Lighter")) {
                        //noinspection SuspiciousListRemoveInLoop
                        gp.player.inventory.remove(i);
                    }
                }
                startDialogue(this, 2);
            }
            gp.keyH.enterPressed = false;
            return true;
        }

        public void setDialogue() {
            dialogueText[0][0] = "A lighter.  It still works, now what can I use this for?";
            dialogueText[1][0] = "I'm gonna burn this bloody thing down!";
            dialogueText[1][1] = "Now if I could just find something potent enough\nto light and then use on the wasp nest!";
            dialogueText[2][0] = "That's the aerosol lit!  I better hurry up now!";
        }
    }
