package main;

import object.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_Guitar1();
        gp.obj[0].worldX = 17 * gp.tileSize;
        gp.obj[0].worldY = 8 * gp.tileSize;

        gp.obj[1] = new OBJ_Guitar2();
        gp.obj[1].worldX = 18 * gp.tileSize;
        gp.obj[1].worldY = 13 * gp.tileSize;

        gp.obj[14] = new OBJ_InsideDoor();
        gp.obj[14].worldX = 15 * gp.tileSize;
        gp.obj[14].worldY = 7 * gp.tileSize;

        gp.obj[15] = new OBJ_InsideDoor();
        gp.obj[15].worldX = 25 * gp.tileSize;
        gp.obj[15].worldY = 7 * gp.tileSize;

        gp.obj[16] = new OBJ_InsideDoorSideways();
        gp.obj[16].worldX = 19 * gp.tileSize;
        gp.obj[16].worldY = 12 * gp.tileSize;

        gp.obj[12] = new OBJ_FrontDoor();
        gp.obj[12].worldX = 13 * gp.tileSize;
        gp.obj[12].worldY = 6 * gp.tileSize;

        gp.obj[13] = new OBJ_FrontDoor();
        gp.obj[13].worldX = 27 * gp.tileSize;
        gp.obj[13].worldY = 6 * gp.tileSize;

        gp.obj[7] = new OBJ_TelephoneHall();
        gp.obj[7].worldX = 14 * gp.tileSize;
        gp.obj[7].worldY = 5 * gp.tileSize;

        gp.obj[8] = new OBJ_StairsHall();
        gp.obj[8].worldX = 16 * gp.tileSize;
        gp.obj[8].worldY = 5 * gp.tileSize;

        gp.obj[9] = new OBJ_Pills();
        gp.obj[9].worldX = 16 * gp.tileSize;
        gp.obj[9].worldY = 10 * gp.tileSize;

        gp.obj[10] = new OBJ_FrontDoorKey();
        gp.obj[10].worldX = 24 * gp.tileSize;
        gp.obj[10].worldY = 11 * gp.tileSize;

        gp.obj[11] = new OBJ_MumsChair();
        gp.obj[11].worldX = 16 * gp.tileSize;
        gp.obj[11].worldY = 13 * gp.tileSize;

        gp.obj[5] = new OBJ_FrontDoorOpen();
        gp.obj[5].worldX = 13 * gp.tileSize;
        gp.obj[5].worldY = 6 * gp.tileSize;

        gp.obj[6] = new OBJ_FrontDoorOpen();
        gp.obj[6].worldX = 27 * gp.tileSize;
        gp.obj[6].worldY = 6 * gp.tileSize;

        gp.obj[2] = new OBJ_InsideDoorOpen();
        gp.obj[2].worldX = 15 * gp.tileSize;
        gp.obj[2].worldY = 7 * gp.tileSize;

        gp.obj[3] = new OBJ_InsideDoorOpen();
        gp.obj[3].worldX = 25 * gp.tileSize;
        gp.obj[3].worldY = 7 * gp.tileSize;

        gp.obj[4] = new OBJ_InsideDoorOpenSideways();
        gp.obj[4].worldX = 19 * gp.tileSize;
        gp.obj[4].worldY = 12 * gp.tileSize;

        gp.obj[17] = new OBJ_BackGateOpen();
        gp.obj[17].worldX = 28 * gp.tileSize;
        gp.obj[17].worldY = 4 * gp.tileSize;

        gp.obj[18] = new OBJ_BackGate();
        gp.obj[18].worldX = 28 * gp.tileSize;
        gp.obj[18].worldY = 4 * gp.tileSize;

        gp.obj[19] = new OBJ_FrontGateOpen();
        gp.obj[19].worldX = 7 * gp.tileSize;
        gp.obj[19].worldY = 3 * gp.tileSize;

        gp.obj[20] = new OBJ_FrontGate();
        gp.obj[20].worldX = 7 * gp.tileSize;
        gp.obj[20].worldY = 3 * gp.tileSize;
    }
}
