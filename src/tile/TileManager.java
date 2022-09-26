package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[99];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/world01.txt", 0);
        loadMap("/maps/upstairs.txt", 1);
    }

    public void getTileImage() {

            setup(0, "lawn1", false);
            setup(1, "brick1", true);
            setup(2, "carpetLounge", false);
            setup(3, "carpetStairsLanding", false);
            setup(4, "carpetDining", false);
            setup(5, "carpetKitchen", false);
            setup(6, "internalWall1", true);
            setup(7, "lawn2", false);
            setup(8, "brick2", true);
            setup(9, "carpetBathroom", false);
            setup(10, "carpetMumsRoom", false);
            setup(11, "carpetSpareRoom", false);
            setup(12, "driveWayStones", false);
            setup(13, "garageBlock", true);
            setup(14, "garageFloor", false);
            setup(15, "pathHorizontal", false);
            setup(16, "pathVertical", false);
            setup(17, "rockery", true);
            setup(18, "hedge", true);
            setup(19, "fence1", true);
            //setup(20, "tree1", true);
            //setup(21, "tree2", true);
            setup(22, "sofaTop", false);
            setup(23, "sofaBottom", true);
            setup(24, "tableTL", false);
            setup(25, "tableTR", true);
            setup(26, "tableBL", true);
            setup(27, "tableBR", true);
            setup(28, "tableLivingRoomTop", false);
            setup(29, "tableLivingRoomBottom", true);
            setup(30, "cupboard1HorLounge", true);
            setup(31, "cupboard1VerLounge", true);
            setup(32, "cupboard1HorHall", true);
            setup(33, "tvLounge", true);
            setup(34, "cupboard2HorLounge", true);
            setup(35, "roadKerb", false);
            setup(36, "road", false);
            setup(37, "tree1Tile", true);
            setup(38, "tree2Tile", true);
            setup(39, "rockery2", true);
            setup(40, "rockery3", true);
            setup(41, "fireplaceTop", true);
            setup(42, "fireplaceBottom", true);
            setup(43, "roadKerb2", false);
            setup(44, "fishTankL", true);
            setup(45, "fishTankR", true);
            setup(46, "kitCup1", true);
            setup(47, "kitCup2", true);
            setup(48, "kitCup3", true);
            setup(49, "kitCup4", true);
            setup(50, "kitCup5", true);
            setup(51, "kitCup6", true);
            setup(52, "kitCup7", true);
            setup(53, "kitCup8", true);
            setup(54, "mumsChairTile", false);
            setup(55, "rockery4", true);
            setup(56, "rockery5", true);
            setup(57, "rockery6", true);
            setup(58, "rockery2", false);
            setup(59, "rockery3", false);

    }

    public void setup(int index, String imageName, boolean collision) {

        UtilityTool uTool = new UtilityTool();

        try {

            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + imageName + ".png")));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath, int map) {
        try {
            InputStream is = getClass().getResourceAsStream((filePath));
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {

        }
    }

    public void draw(Graphics2D g2) { //draw tiles
        int worldCol = 0;
        int worldRow = 0;


        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
                    && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                    && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                    && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
