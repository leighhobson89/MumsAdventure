package entity;

import main.GamePanel;

import java.util.Objects;

public class Projectile extends Entity {

    Entity user;

    public Projectile (GamePanel gp) {
        super(gp);
    }

    public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.stressLevel = 0;
    }

    public void update () {

        int projX = ((worldX + solidArea.x)/gp.tileSize);
        int projY = ((worldY + solidArea.y)/gp.tileSize);
        if(Objects.equals(this.name, "Pip's Bone")) {
            gp.aSetter.boneX = (worldX + solidArea.x)/gp.tileSize;
            gp.aSetter.boneY = (worldY + solidArea.y)/gp.tileSize;
        }

        if (user == gp.player) {
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            boolean tileState = gp.cChecker.checkTile(this);

            if (monsterIndex != 999) {
                gp.aSetter.setObjectAfterStart("Pip's Bone", gp.currentMap, projX, projY);
                gp.player.damageMonster(monsterIndex, this, attack, knockBackPower);
                generateParticle(user.projectile, gp.monster[gp.currentMap][monsterIndex]);
                gp.playSFX(20);
                alive = false;
            }
            if (npcIndex != 999) {
                gp.aSetter.setObjectAfterStart("Pip's Bone", gp.currentMap, projX, projY);
                gp.gameState = gp.dialogueState;
                if (Objects.equals(gp.npc[gp.currentMap][npcIndex].name, "Dad")) {
                    gp.npc[gp.currentMap][npcIndex].startDialogue(gp.npc[gp.currentMap][npcIndex], 62);
                } else if (Objects.equals(gp.npc[gp.currentMap][npcIndex].name, "Phoebe") || Objects.equals(gp.npc[gp.currentMap][npcIndex].name, "Pip")) {
                    gp.npc[gp.currentMap][npcIndex].startDialogue(gp.npc[gp.currentMap][npcIndex], 7);
                } else if (Objects.equals(gp.npc[gp.currentMap][npcIndex].name, "Merchant")) {
                    gp.npc[gp.currentMap][npcIndex].startDialogue(gp.npc[gp.currentMap][npcIndex], 1);
                }
                gp.playSFX(20);
                alive = false;
            }
            if (tileState) {
                gp.aSetter.setObjectAfterStart("Pip's Bone", gp.currentMap, projX, projY);
                gp.playSFX(20);
                alive = false;
            }
        }
        if (user != gp.player) {
            boolean contactPlayer = gp.cChecker.checkPlayer(this);
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            boolean tileState = gp.cChecker.checkTile(this);
            if (!gp.player.invincible && contactPlayer) {
                damagePlayer(attack);
//                generateParticle(user.projectile, user.projectile); //ONLY IF NPC OR MONSTER THROWS OBJECT
                gp.gameState = gp.dialogueState;
                startDialogue(gp.player, 11);
                gp.playSFX(8);
                alive = false;
            }
            if (monsterIndex != 999) {
                gp.player.damageMonster(monsterIndex, this, attack, knockBackPower);
                gp.playSFX(21);
                alive = false;
            }
            if (tileState) {
                gp.playSFX(21);
                alive = false;
            }
        }

        switch (direction) {
            case "up": worldY -= speed; break;
            case "down": worldY += speed; break;
            case "left": worldX -= speed; break;
            case "right": worldX += speed; break;
        }

        stressLevel++;

        if(stressLevel >= maxStress) { //if throwing item reaches end of throwing range
            gp.aSetter.setObjectAfterStart("Pip's Bone", gp.currentMap, projX, projY);
            alive = false;
            gp.playSFX(20);
        }

        spriteCounter++;
        if(spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public boolean haveResource(Entity user) {
        return false;
    }

    public void subtractResource(Entity user) {
    }
}
