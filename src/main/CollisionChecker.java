package main;

import entity.Entity;

import java.util.Objects;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public boolean checkTile(Entity entity) {
        boolean tileState = false;
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        //Use a temporary direction when being knocked back
        String direction = entity.direction;
        if (entity.knockBack) {
            direction = entity.knockBackDirection;
        }

        switch(direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                    tileState = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                    tileState = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                    tileState = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                    tileState = true;
                }
                break;
        }
        return tileState;
    }
    public int checkObject(Entity entity, boolean player) {

        int index = 999;

        for (int i = 0; i < gp.obj[1].length; i++) {
            if (gp.obj[gp.currentMap][i] != null) {
                //Get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //Get the object's solid area position
                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].worldX + gp.obj[gp.currentMap][i].solidArea.x;
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].worldY + gp.obj[gp.currentMap][i].solidArea.y;

                switch (entity.direction) {
                    case "up": entity.solidArea.y -= entity.speed; break;
                    case "down": entity.solidArea.y += entity.speed; break;
                    case "left": entity.solidArea.x -= entity.speed; break;
                    case "right": entity.solidArea.x += entity.speed; break;
                }

                if (gp.player.missionState == MissionStates.CHOP_CHICKEN_FOR_DOGS && entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea) && !Objects.equals(gp.obj[gp.currentMap][i].name, "BackGateSideways") && !Objects.equals(gp.obj[gp.currentMap][i].name, "BackGate") && !Objects.equals(gp.obj[gp.currentMap][i].name, "Fridge")) { //if entity touches object...
                    if (gp.obj[gp.currentMap][i].collision) {
                        entity.collisionOn = true;
                    }
                    if (player) {
                        index = i;
                    }
                } else if (entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea) && gp.player.missionState != MissionStates.CHOP_CHICKEN_FOR_DOGS) {
                    if (gp.obj[gp.currentMap][i].collision) {
                        entity.collisionOn = true;
                    }
                    if (player) {
                        index = i;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].solidAreaDefaultX;
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].solidAreaDefaultY;
            }
        }

        return index;
    }

    //NPC OR MONSTER COLLISION
    public int checkEntity(Entity entity, Entity[][] target) {

        int index = 999;

        //Use a temporary direction when being knocked back
        String direction = entity.direction;
        if (entity.knockBack) {
            direction = entity.knockBackDirection;
        }

        for (int i = 0; i < target[1].length; i++) {
            if (target[gp.currentMap][i] != null) {
                //Get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //Get the object's solid area position
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].worldX + target[gp.currentMap][i].solidArea.x;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].worldY + target[gp.currentMap][i].solidArea.y;

                switch (direction) {
                    case "up": entity.solidArea.y -= entity.speed; break;
                    case "down": entity.solidArea.y += entity.speed; break;
                    case "left": entity.solidArea.x -= entity.speed; break;
                    case "right": entity.solidArea.x += entity.speed; break;
                }

                if (entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) { //if entity touches object...
                    if(target[gp.currentMap][i] != entity) {
                        if (!Objects.equals(target[gp.currentMap][i].name, "Weed Tile") && entity.type != entity.type_monster) { //lets monsters run about on destructable tiles
                            entity.collisionOn = true;
                            index = i;
                        } else if (Objects.equals(target[gp.currentMap][i].name, "Weed Tile") && entity.type != entity.type_monster) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        if ((Objects.equals(target[gp.currentMap][i].name, "Phoebe") || Objects.equals(target[gp.currentMap][i].name, "Pip"))  && (entity.type == entity.type_npc || entity.type == entity.type_player)) {
                            entity.collisionOn = false;
                            index = i;
                        }
                        if ((Objects.equals(target[gp.currentMap][i].name, "Dad"))  && entity.type == entity.type_player) {
                            entity.collisionOn = false;
                            index = i;
                        }
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultX;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;
            }
        }

        return index;
    }
    public boolean checkPlayer(Entity entity) {
        boolean contactPlayer = false;

        //Get entity's solid area position
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
        //Get the object's solid area position
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch (entity.direction) {
            case "up": entity.solidArea.y -= entity.speed; break;
            case "down": entity.solidArea.y += entity.speed; break;
            case "left": entity.solidArea.x -= entity.speed; break;
            case "right": entity.solidArea.x += entity.speed; break;
        }

        if (entity.solidArea.intersects(gp.player.solidArea)) { //if player touches object...
            contactPlayer = true;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

        return contactPlayer;
    }
}
