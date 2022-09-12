package entity;

import main.GamePanel;
import main.KeyHandler;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Player extends Entity {
    public boolean dizzyFlag;
    KeyHandler keyH;

    public final int PILLS_COUNT_DOWN_VALUE = 20;
    final int MAX_SPEED_UNDER_INFLUENCE = 4;

    public final int screenX;
    public final int screenY;
    public boolean speedBoost;
    static int interval;
    public int standCounter;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);

        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2); //put player in center of screen
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8, 16,32,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage(gp.ui.colorOutfit);
    }

    public void countDownTimerForItemEffect(int value, String effect) {
        Timer timer = new Timer();
        if ("Pills".equals(effect)) {
            speed = 1;
        }
        int period;
        final int[] timeLeft = new int[1];
        period = 1000;
        interval = value;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeLeft[0] = (setInterval(effect, timer));
            }
        }, value, period);
    }

    private int setInterval(String effect, Timer timer) {
        if ("Pills".equals(effect)) {
            dizzyFlag = true;
            speed = (int) (Math.random() * MAX_SPEED_UNDER_INFLUENCE) + 1; //random speed every second while effect lasts
            if (Objects.equals(direction, "up")) {
                direction = "down";
            } else if (Objects.equals(direction, "down")) {
                direction = "up";
            } else if (Objects.equals(direction, "left")) {
                direction = "right";
            } else if (Objects.equals(direction, "right")) {
                direction = "left";
            }
        }
        if (interval == 1) {
            timer.cancel();
            if ("Pills".equals(effect)) {
                speed = 2;
                dizzyFlag = false;
            }
        }
        return --interval;
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 16;
        worldY = gp.tileSize * 12;
        defaultSpeed = 2;
        boostSpeed = 4;
        speed = defaultSpeed;
        direction = "up";
        //PLAYER STATUS
        maxStress = 10;
        stressLevel = 0;
    }

    public void getPlayerImage(int colorOutfit) {
        //MENU
        down1 = setup("/player/mum_down1");
        down1_red = setup("/player/mum_down1_red");
        down1_purple = setup("/player/mum_down1_purple");
        gp.ui.colorOutfit = 1;

        if (gp.gameState == gp.playState) {
            if (colorOutfit == 0) {
                up1 = setup("/player/mum_up1_red");
                up2 = setup("/player/mum_up2_red");
                down1 = setup("/player/mum_down1_red");
                down2 = setup("/player/mum_down2_red");
                left1 = setup("/player/mum_left1_red");
                left2 = setup("/player/mum_left2_red");
                right1 = setup("/player/mum_right1_red");
                right2 = setup("/player/mum_right2_red");
            } else if (colorOutfit == 1) {
                up1 = setup("/player/mum_up1");
                up2 = setup("/player/mum_up2");
                down1 = setup("/player/mum_down1");
                down2 = setup("/player/mum_down2");
                left1 = setup("/player/mum_left1");
                left2 = setup("/player/mum_left2");
                right1 = setup("/player/mum_right1");
                right2 = setup("/player/mum_right2");
            } else if (colorOutfit == 2) {
                up1 = setup("/player/mum_up1_purple");
                up2 = setup("/player/mum_up2_purple");
                down1 = setup("/player/mum_down1_purple");
                down2 = setup("/player/mum_down2_purple");
                left1 = setup("/player/mum_left1_purple");
                left2 = setup("/player/mum_left2_purple");
                right1 = setup("/player/mum_right1_purple");
                right2 = setup("/player/mum_right2_purple");
            }
        }
    }

    public void update() {
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed && !dizzyFlag) {
                direction = "up";
            } else if (keyH.downPressed && !dizzyFlag) {
                direction = "down";
            } else if (keyH.leftPressed && !dizzyFlag) {
                direction = "left";
            } else if (keyH.rightPressed && !dizzyFlag) {
                direction = "right";
            } else if (keyH.upPressed) {
                direction = "down";
            } else if (keyH.downPressed) {
                direction = "up";
            } else if (keyH.leftPressed) {
                direction = "right";
            } else  {
                direction = "left";
            }

            if (speedBoost && !dizzyFlag) {
                speed = boostSpeed;
            } else if (dizzyFlag) {
                System.out.println("Dizzy!" + speed);
            } else {
                speed = defaultSpeed;
            }

            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
//            pickUpObject(objIndex);

            //CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            // CHECK EVENT
            gp.eHandler.checkEvent();

            gp.keyH.enterPressed = false;

            //IF COLLISION IS FALSE, PLAYER CAN MOVE
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
        }
        else {
            standCounter++;
            if (standCounter == 30) {
                standCounter = 0;
                spriteNum = 1;
            }
        }
        //This needs to be outside of key if statement!
        if (invincible) {
            invincibleCounter++;
            if(invincibleCounter > 120) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

//    public void pickUpObject(int i) {
//        if (i != 999) {
//
//        }
//    }

    public void interactNPC(int i) {
        if (i != 999) {
            if (gp.keyH.enterPressed) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
    }

    public void contactMonster(int i) {
        if (i != 999) {
            if (!invincible) {
                stressLevel+=1;
                invincible = true;
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            }
        }

        if (invincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image, screenX, screenY,null);

        //RESET alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        //DEBUG
//      g2.setFont(new Font("Arial", Font.BOLD, 26));
//      g2.setColor(Color.BLACK);
//      g2.drawString("Invincible: " + invincibleCounter, 10, 400); //- UNCOMMENT TO DISPLAY INVINCIBILITY COUNTER
//      g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height); // - UNCOMMENT TO DISPLAY COLLISION RECTANGLE ON PLAYER
    }
}
