package main;

import entity.PlayerDummy;
import object.OBJ_Tutorial_Arrow_Right;
import object.OBJ_Tutorial_TileSelectorMarker;

import java.awt.*;
import java.util.Objects;

public class CutsceneManager {

    GamePanel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase;

    //Scene Number
    public final int NA = 0;
    public final int trampolineCar = 1;
    public final int asianOnPhone = 2;
    public final int andreaOnPhone = 3;

    public CutsceneManager(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        switch (sceneNum) {
            case trampolineCar -> scene_trampolineCar();
            case asianOnPhone -> scene_asianOnPhone();
            case andreaOnPhone -> scene_andreaOnPhone();
        }
    }

    public void scene_andreaOnPhone() {
        if (scenePhase == 0) {
            gp.player.drawing = false;

            for (int i = 0; i < gp.npc[gp.currentMap].length; i++) { //add player character to cutscene
                if (gp.npc[gp.currentMap][i] == null) {
                    gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
                    gp.npc[gp.currentMap][i].worldX = 17 * gp.tileSize;
                    gp.npc[gp.currentMap][i].worldY = 11 * gp.tileSize;
                    gp.npc[gp.currentMap][i].direction = "up";
                    break;
                }
            }

            gp.player.worldX = 13 * gp.tileSize;
            gp.player.worldY = 6 * gp.tileSize;

            scenePhase++;
        } else if (scenePhase == 1) {
            for (int i = 0; i < gp.obj[gp.currentMap].length; i++) {
                if (gp.obj[gp.currentMap][i] != null && Objects.equals(gp.obj[gp.currentMap][i].name, "TelephoneHall")) {
                    gp.obj[gp.currentMap][i].dialogueSet = gp.player.missionState;
                    gp.ui.npc = gp.obj[gp.currentMap][i];
                }
            }
            gp.ui.drawDialogueScreen(0);
        } else if (scenePhase == 2) {
            for (int i = 0; i < gp.npc[gp.currentMap].length; i++) { //return to player
                if (gp.npc[gp.currentMap][i] != null && Objects.equals(gp.npc[gp.currentMap][i].name, "PlayerDummy")) {
                    gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
                    gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
                    gp.player.direction = gp.npc[gp.currentMap][i].direction;

                    gp.npc[gp.currentMap][i] = null; //delete dummy
                    break;
                }
            }

            gp.player.drawing = true;
            sceneNum = NA;
            scenePhase = 0;
            gp.gameState = gp.playState;
        }
    }

    public void scene_asianOnPhone() {
        if (scenePhase == 0) {
            gp.player.drawing = false;
            gp.player.tempPlayerWorldX = gp.player.worldX;
            gp.player.tempPlayerWorldY = gp.player.worldY;
            gp.player.worldX = 55 * gp.tileSize;
            gp.player.worldY = 9 * gp.tileSize;
            if (gp.player.missionState == MissionStates.DRAG_COOKER_TO_BINS) {
                gp.aSetter.setObjectAfterStart(OBJ_Tutorial_Arrow_Right.OBJ_NAME, gp.currentMap, 59, 12, false);
                gp.aSetter.setObjectAfterStart(OBJ_Tutorial_TileSelectorMarker.OBJ_NAME, gp.currentMap, 60, 12, false);
            }
            scenePhase++;
        } else if (scenePhase == 1) {
            for (int i = 0; i < gp.obj[gp.currentMap].length; i++) {
                if (gp.obj[gp.currentMap][i] != null && Objects.equals(gp.obj[gp.currentMap][i].name, "TelephoneHall")) {
                    gp.obj[gp.currentMap][i].dialogueSet = gp.player.missionState;
                    gp.ui.npc = gp.obj[gp.currentMap][i];
                }
            }
            gp.ui.drawDialogueScreen(0);
        } else if (scenePhase == 2) {
            if (gp.player.missionState == MissionStates.NOT_GET_PAID_FOR_OLD_COOKER) {
                for (int i = 0; i < gp.npc[gp.currentMap].length; i++) {
                    if (gp.npc[gp.currentMap][i] != null && Objects.equals(gp.npc[gp.currentMap][i].name, "Merchant")) {
                        gp.npc[gp.currentMap][i].direction = "down";
                        gp.npc[gp.currentMap][i].worldY += 4;
                        if (gp.npc[gp.currentMap][i].worldY > 18 * gp.tileSize) {
                            gp.npc[gp.currentMap][i] = null;
                            scenePhase++;
                        }
                    }
                }
            } else {
                scenePhase++;
            }
        } else if (scenePhase == 3) {
            gp.player.worldX = gp.player.tempPlayerWorldX;
            gp.player.worldY = gp.player.tempPlayerWorldY;

            if (gp.player.missionState == MissionStates.DRAG_COOKER_TO_BINS) {
                gp.aSetter.removeCutSceneObjectFromMap(OBJ_Tutorial_TileSelectorMarker.OBJ_NAME, gp.currentMap);
                gp.aSetter.removeCutSceneObjectFromMap(OBJ_Tutorial_Arrow_Right.OBJ_NAME, gp.currentMap);
            }

            gp.player.drawing = true;
            sceneNum = NA;
            scenePhase = 0;
            gp.gameState = gp.playState;
        }
    }

    public void scene_trampolineCar() {
        if (scenePhase == 0) {
            gp.player.drawing = false;
            gp.player.worldX = 55 * gp.tileSize;

            for (int i = 0; i < gp.npc[gp.currentMap].length; i++) { //add player character to cutscene
                if (gp.npc[gp.currentMap][i] == null) {
                    gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
                    gp.npc[gp.currentMap][i].worldX = 58 * gp.tileSize;
                    gp.npc[gp.currentMap][i].worldY = 7 * gp.tileSize;
                    gp.npc[gp.currentMap][i].direction = "left";
                    scenePhase++;
                    break;
                }
            }

            for (int i = 0; i < gp.npc[gp.currentMap].length; i++) {
                if (gp.npc[gp.currentMap][i] != null && Objects.equals(gp.npc[gp.currentMap][i].name, "Dad")) {
                    gp.npc[gp.currentMap][i].worldX = 56 * gp.tileSize;
                    gp.npc[gp.currentMap][i].worldY = 6 * gp.tileSize;
                    gp.npc[gp.currentMap][i].direction = "down";
                    gp.ui.npc = gp.npc[gp.currentMap][i];
                    break;
                }
            }
        } else if (scenePhase == 1) {
            gp.player.worldY -= 2;

            if (gp.player.worldY <= 7 * gp.tileSize) {
                scenePhase++;
            }
        } else if (scenePhase == 2) {
            gp.ui.drawDialogueScreen(0);
        } else if (scenePhase == 3) {
            for (int i = 0; i < gp.npc[gp.currentMap].length; i++) { //return to player
                if (gp.npc[gp.currentMap][i] != null && Objects.equals(gp.npc[gp.currentMap][i].name, "PlayerDummy")) {
                    gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
                    gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
                    gp.player.direction = gp.npc[gp.currentMap][i].direction;

                    gp.npc[gp.currentMap][i] = null; //delete dummy
                }
            }
            gp.player.drawing = true;
            sceneNum = NA;
            scenePhase = 0;
            gp.player.missionSubstate = 3;
            gp.gameState = gp.playState;
        }
    }
}
