package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

public class Entity {

    GamePanel gp;

    ArrayList<Integer> usedDialogues = new ArrayList<>();
    public BufferedImage dyingImage, up1, up2, down1, down2, left1, left2, right1, right2, down1_red, down1_purple;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public BufferedImage image, image2, image3;
    UtilityTool uTool = new UtilityTool();
    public Rectangle solidArea = new Rectangle(8, 16, 32, 32);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    String[] dialogues = new String[100];

    //STATE
    public int worldX, worldY;
    public String direction = "up";
    public int spriteNum = 1;
    public int speed, defaultSpeed, boostSpeed;
    int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible;
    public boolean newMonster;
    public boolean attacking;
    public boolean alive = true;
    public boolean dying;
    boolean hpBarOn = false;

    //COUNTER
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter =0;
    int dyingCounter = 0;
    int hpBarCounter = 0;

    //CHARACTER ATTRIBUTES
    public String name;
    public boolean isWeapon;
    public boolean isArmour;
    public boolean collectable;
    public boolean isOpenable;
    public int maxStress;
    public int monsterMaxStress;
    public int stressLevel;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentShield;

    //ITEM ATTRIBUTES
    public int attackValue;
    public int defenseValue;
    public String description = "";

    //TYPE
    public int type;
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_short_weapon = 3;
    public final int type_long_weapon = 4;
    public final int type_armour = 5;
    public final int type_consumable = 6;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {}
    public void damageReaction() {
        //overridden in specific monster class
    }
    public void speak() { //GENERAL CHARACTER SPEAK BEHAVIOUR
        Random rand = new Random();
        int dialogueCount = 0;

        for (int i = 0; i < dialogues.length; i++) {
            if (!(dialogues[i] == null)) {
                dialogueCount++;
            }
        }

        int randomValue = rand.nextInt(dialogues.length); //init only;

        while (dialogues[randomValue] == null || usedDialogues.contains(randomValue)) {
            randomValue = rand.nextInt(dialogueCount);

            if (usedDialogues.size() >= dialogueCount) {
                usedDialogues.clear(); // cycle round when used all dialogues
            }
        }

        gp.ui.currentDialogue = dialogues[randomValue];
        usedDialogues.add(randomValue);


        switch (gp.player.direction) {
            case "up" -> direction = "down";
            case "down" -> direction = "up";
            case "left" -> direction = "right";
            case "right" -> direction = "left";
        }
    }
    public void use(Entity entity) {
        //overridden in Player class
    }
    public void update() {
        setAction();

        collisionOn = false;
        boolean tileState = gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        if (newMonster && tileState) {
            collisionOn = false;
        }
        if (newMonster && !tileState) {
            newMonster = false;
            collisionOn = true;
        }
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if(this.type == type_monster && contactPlayer) {
            if (!gp.player.invincible && !this.dying) {
                //we can give damage

                int damage = attack - gp.player.defense;
                if (damage > 0) {
                    gp.ui.addMessage("The " + this.name + " got you! Your stress increases by " + damage + "!");
                }
                if (damage <= 0) {
                    gp.ui.addMessage("The " + this.name + " got you but it can't stress you at your exp level!");
                    damage = 0;
                }
                gp.player.stressLevel += damage;
                gp.player.invincible = true;
            }
        }

        //IF COLLISION IS FALSE, ENTITY CAN MOVE
        if (!collisionOn) {
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }
        }
        spriteCounter++;
        if (spriteCounter > 12) { //walking speed of animation
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        if (invincible) {
            invincibleCounter++;
            if(invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
                && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            switch (direction) {
                case "up":
                    if (spriteNum == 1) {image = up1;}
                    if (spriteNum == 2) {image = up2;}
                        break;
                case "down":
                    if (spriteNum == 1) {image = down1;}
                    if (spriteNum == 2) {image = down2;}
                        break;
                case "left":
                    if (spriteNum == 1) {image = left1;}
                    if (spriteNum == 2) {image = left2;}
                        break;
                case "right":
                    if (spriteNum == 1) {image = right1;}
                    if (spriteNum == 2) {image = right2;}
                        break;
                    }

            //Monster HP bar
            if(type == type_monster && hpBarOn) {

                double oneScale = (double)gp.tileSize/monsterMaxStress;
                double hpBarValue = gp.tileSize - (oneScale * stressLevel);

                g2.setColor(new Color(35,35,35));
                g2.fillRect(screenX-2, screenY-17, gp.tileSize+4, 14);
                g2.setColor(new Color(255,0,30));
                g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);

                hpBarCounter++;

                if (hpBarCounter < 300) {
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }

                    if (invincible) {
                        hpBarOn = true;
                        hpBarCounter = 0;
                        changeAlpha(g2, 0.4F);
                    }
                    if (dying) {
                        image = dyingImage;
                        dyingAnimation(g2);
                    }

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            changeAlpha(g2, 1F);
        }
    }

    public void dyingAnimation(Graphics2D g2) {
        dyingCounter++;

        int i = 10; //speed of flashing

        if (dyingCounter <= i) {changeAlpha(g2, 0f);}
        if (dyingCounter > i && dyingCounter <= i*2) {changeAlpha(g2, 1f);}
        if (dyingCounter > i*2 && dyingCounter <= i*3) {changeAlpha(g2, 0f);}
        if (dyingCounter > i*3 && dyingCounter <= i*4) {changeAlpha(g2, 1f);}
        if (dyingCounter > i*4 && dyingCounter <= i*5) {changeAlpha(g2, 0f);}
        if (dyingCounter > i*5 && dyingCounter <= i*6) {changeAlpha(g2, 1f);}
        if (dyingCounter > i*6 && dyingCounter <= i*7) {changeAlpha(g2, 0f);}
        if (dyingCounter > i*7 && dyingCounter <= i*8) {changeAlpha(g2, 1f);}
        if(dyingCounter > i*8) {
            dying = false;
            alive = false;
        }
    }
    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = uTool.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
