package entity;

import main.GamePanel;
import main.KeyHandler;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Player extends Entity {
    KeyHandler keyH;

    final int PILLS_COUNT_DOWN_VALUE = 20;
    final int MAX_SPEED_UNDER_INFLUENCE = 4;

    public final int screenX;
    public final int screenY;
    boolean dizzyFlag;
    public static boolean speedBoost;
    static int interval;
    Timer timer = new Timer();
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
        getPlayerImage();
    }

    public void countDownTimerForItemEffect(int value, String effect) {
        switch (effect) {
            case "Pills":
                speed = 1;
                break;
        }
        int period;
        final int[] timeLeft = new int[1];
        period = 1000;
        interval = value;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeLeft[0] = (setInterval(effect));
            }
        }, value, period);
    }

    private final int setInterval(String effect) {
        switch (effect) {
            case "Pills":
                dizzyFlag = true;
                speed = (int)(Math.random() * MAX_SPEED_UNDER_INFLUENCE) + 1; //random speed every second while effect lasts
                if (Objects.equals(direction, "up")) {
                    direction = "down";
                } else if (Objects.equals(direction, "down")) {
                    direction = "up";
                } else if (Objects.equals(direction, "left")) {
                    direction = "right";
                } else if (Objects.equals(direction, "right")) {
                    direction = "left";
                }
                break;
        }
        if (interval == 1) {
            timer.cancel();
            switch (effect) {
                case "Pills":
                    speed = 2;
                    dizzyFlag = false;
                    break;
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
    }

    public void getPlayerImage () {

        up1 = setup("/player/mum_up1");
        up2 = setup("/player/mum_up2");
        down1 = setup("/player/mum_down1");
        down2 = setup("/player/mum_down2");
        left1 = setup("/player/mum_left1");
        left2 = setup("/player/mum_left2");
        right1 = setup("/player/mum_right1");
        right2 = setup("/player/mum_right2");

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
            pickUpObject(objIndex);

            //CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
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
    }

    public void pickUpObject(int i) {
        if (i != 999) {

        }
    }

    public void interactNPC(int i) {
        if (i != 999) {
            System.out.println("NPC COLL");
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        switch(direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY,null);

          //DEBUG - UNCOMMENT TO DISPLAY COLLISION RECTANGLE ON PLAYER
//        g2.setColor(Color.RED);
//        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }
}
