package main;

import entity.Entity;
import entity.Player;
import tile.Tile;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile size
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //actual size 48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //768 px
    public final int screenHeight = tileSize * maxScreenRow; //576 px
    public final boolean musicSetToPlayFromStart = true; //Change to true to play music from start
    //WORLD SETTINGS
    public final int maxWorldCol = 66;
    public final int maxWorldRow = 16;

    //FPS
    int FPS = 60;

    //SYSTEM
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound sfx = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;

    //ENTITY AND OBJECT
    public Player player = new Player(this,keyH);
    public Entity[] obj = new Entity[30];
    public Entity[] npc = new Entity[10];
    public Entity[] monster = new Entity[20];
    ArrayList<Entity> entityList = new ArrayList<>();

    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;


    public GamePanel() throws IOException, FontFormatException { //constructor
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        aSetter.setObject();
        aSetter.setNPC();
        playMusic(0, false, 0);
        if (!musicSetToPlayFromStart) {
            keyH.musicPlaying = false;
            stopMusic();
        }
        gameState = titleState;
    }

    public void startGameThread() {
        gameThread = new Thread(this); //instantiate gameThread
        gameThread.start();
    }

    @Override
    public void run() { //update screen, redraw it according to the FPS set

        double drawInterval = 1000000000 / FPS; // 0.016667 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            //1 UPDATE screen
            update();
            //2 DRAW screen
            repaint();
            drawCount++;

            if (timer >= 1000000000) { //output FPS in console
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000; //convert nano to milli

                if (remainingTime < 0) { //if no sleep required due to taking longer than permitted time to redraw, then handle this
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
}
    public void update() {
        if (gameState == playState) {
            //PLAYER
            player.update();
            //NPC
            for (Entity entity : npc) {
                if (entity != null) {
                    entity.update();
                }
            }
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    if (monster[i].alive && !monster[i].dying); {
                        monster[i].update();
                    }
                    if (!monster[i].alive) {
                        monster[i] = null;
                    }
                }
            }
        }
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        //DEBUG
        long drawStart = 0;
        if (keyH.checkDrawTime) {
            drawStart = System.nanoTime();
        }

        //TITLE SCREEN
        if (gameState == titleState) {
            ui.draw(g2);
        }

        //OTHERS
        else {
            //TILE
            tileM.draw(g2); //bottom layer first

            //ADD ENTITIES TO THE LIST
            entityList.add(player);

            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    entityList.add(npc[i]);
                }
            }

            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    entityList.add(obj[i]);
                }
            }

            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    entityList.add(monster[i]);
                }
            }

            //SORT
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {

                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });

            //DRAW ENTITIES
            for (int i= 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }

            //EMPTY ENTITY LIST
            entityList.clear();

            //UI
            ui.draw(g2);
        }

        //DEBUG
        if (keyH.checkDrawTime) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 42F));
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }

        g2.dispose();
    }
    public void playMusic(long position, boolean pausing, int i) {
        music.setFile(i);
        music.play(position, pausing);
        music.loop();
    }

    public void stopMusic() {
        music.stop(false);
    }

    public long pauseMusic() {
        return music.stop(true);
    }

    public void playSFX(int i) {
        sfx.setFile(i);
        sfx.play(0, false);
    }
}
