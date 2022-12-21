package main;

import entity.PlayerDummy;

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

    public CutsceneManager(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        switch (sceneNum) {
            case trampolineCar -> scene_trampolineCar();
        }
    }

    public void scene_trampolineCar() {
        if (gp.player.missionState == MissionStates.MOVE_TRAMPOLINE_OFF_CAR && scenePhase == 0) {
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

            for (int i = 0; i < gp.npc[gp.currentMap].length; i++) { //add player character to cutscene
                if (gp.npc[gp.currentMap][i] != null && Objects.equals(gp.npc[gp.currentMap][i].name, "Dad")) {
                    gp.npc[gp.currentMap][i].worldX = 56 * gp.tileSize;
                    gp.npc[gp.currentMap][i].worldY = 6 * gp.tileSize;
                    gp.npc[gp.currentMap][i].direction = "down";
                    gp.ui.npc = gp.npc[gp.currentMap][i];
                    break;
                }
            }
        } else if (gp.player.missionState == MissionStates.MOVE_TRAMPOLINE_OFF_CAR && scenePhase == 1) {
            gp.player.worldY -= 2;

            if (gp.player.worldY <= 7 * gp.tileSize) {
                scenePhase++;
            }
        } else if (gp.player.missionState == MissionStates.MOVE_TRAMPOLINE_OFF_CAR && scenePhase == 2) {
            gp.ui.drawDialogueScreen(0);
        } else if (gp.player.missionState == MissionStates.MOVE_TRAMPOLINE_OFF_CAR && scenePhase == 3) {
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
