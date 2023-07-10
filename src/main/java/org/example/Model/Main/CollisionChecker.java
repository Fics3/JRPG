package org.example.Model.Main;

import org.example.Model.Entity.Entity;
import org.example.Model.Object;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CollisionChecker {
    GameCFG gameCFG;
    private int mapColData[][];
    Entity entity;


    public CollisionChecker(GameCFG gameCFG, Entity entity) throws IOException {
        this.entity = entity;
        this.gameCFG = gameCFG;
        mapColData = new int[gameCFG.getMaxWorldCol()][gameCFG.getMaxWorldRow()];
        getDataMap();
    }
    public void getDataMap() throws IOException {
        InputStream inputStreamData = getClass().getResourceAsStream("/DataMaps/dataMap_03.txt");
        BufferedReader bufferedReaderData = new BufferedReader(new InputStreamReader(inputStreamData));
        for (int i = 0; i < gameCFG.getMaxWorldRow(); i++) {
            String lineData= bufferedReaderData.readLine();
            for (int j = 0; j < gameCFG.getMaxWorldCol(); j++) {

                String[] collision=lineData.split(" ");
                mapColData[j][i]=Integer.parseInt(collision[j]);
            }
        }
    }

    public void checkTile(Entity entity) {
        int entityLeftX = entity.getX() + entity.getSolidArea().x;
        int entityRightX = entity.getX() + entity.getSolidArea().x + entity.getSolidArea().width;
        int entityTopY = entity.getY() + entity.getSolidArea().y;
        int entityBotY = entity.getY() + entity.getSolidArea().y + entity.getSolidArea().height;

        int entityLeftCol = entityLeftX / gameCFG.getTileSize();
        int entityRightCol = entityRightX / gameCFG.getTileSize();
        int entityTopRow = entityTopY / gameCFG.getTileSize();
        int entityBotRow = entityBotY / gameCFG.getTileSize();

        int tileN1, tileN2;
        switch (entity.getDirection()) {
            case "up":
                entityTopRow = (entityTopY - entity.getSpeed()) / gameCFG.getTileSize();
                tileN1 = getMapColData(entityLeftCol, entityTopRow);
                tileN2 = getMapColData(entityRightCol, entityTopRow);
                if (tileN1 == 1 && tileN2 == 1) {
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entityBotRow = (entityBotY + entity.getSpeed()) / gameCFG.getTileSize();
                tileN1 = getMapColData(entityLeftCol, entityBotRow);
                tileN2 = getMapColData(entityRightCol, entityBotRow);
                if (tileN1 == 1 && tileN2 == 1) {
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entityRightCol = (entityRightX + entity.getSpeed()) / gameCFG.getTileSize();
                tileN1 = getMapColData(entityRightCol, entityTopRow);
                tileN2 = getMapColData(entityRightCol, entityBotRow);
                if (tileN1 == 1 && tileN2 == 1) {
                    System.out.println(1);
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.getSpeed()) / gameCFG.getTileSize();
                tileN1 = getMapColData(entityLeftCol, entityTopRow);
                tileN2 = getMapColData(entityLeftCol, entityBotRow);
                if (tileN1 == 1 && tileN2 == 1) {
                    System.out.println(2);
                    entity.setCollisionOn(true);
                }
                break;

        }
    }

    public void checkObject(Entity entity, boolean player) {
        int idx = 999;
        for (Object obj : gameCFG.getObjects()) {
            if (obj != null) {
                entity.setSolidArea(entity.getX() + entity.getSolidArea().x, entity.getY() + entity.getSolidArea().y);
                obj.setSolidArea(obj.getX(), obj.getY());
                switch (entity.getDirection()) {
                    case "up":
                        entity.setSolidY(entity.getSolidArea().y - entity.getSpeed() / 2);
                        if (entity.getSolidArea().intersects(obj.getSolidArea())) {
                            System.out.println("up col");
                            if (obj.isCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if (player) {
                                idx = obj.getId();
                            }
                        }
                        break;
                    case "down":
                        entity.setSolidY(entity.getSolidArea().y += entity.getSpeed() / 2);
                        if (entity.getSolidArea().intersects(obj.getSolidArea())) {
                            if (obj.isCollision()) {
                                entity.setCollisionOn(true);
                            }
                        }
                        break;
                    case "right":
                        entity.setSolidX(entity.getSolidArea().x += entity.getSpeed() / 2);
                        if (entity.getSolidArea().intersects(obj.getSolidArea())) {
                            if (obj.isCollision()) {
                                entity.setCollisionOn(true);
                            }
                        }
                        break;
                    case "left":
                        entity.setSolidX(entity.getSolidArea().x -= entity.getSpeed() / 2);
                        if (entity.getSolidArea().intersects(obj.getSolidArea())) {
                            if (obj.isCollision()) {
                                entity.setCollisionOn(true);
                            }
                        }
                        break;
                }
            }
            entity.setSolidArea(entity.getSolidDefaultX(), entity.getSolidDefaultY());
            obj.setSolidArea(obj.getSolidAreaDefaultX(), obj.getSolidAreaDefaultY());
        }
    }

    public int checkEntity(Entity entity, ArrayList<Entity> target) {
        int tmpId=0;
        for (Entity npc : target) {
            entity.setSolidArea(entity.getX() + entity.getSolidArea().x, entity.getY() + entity.getSolidArea().y);
            npc.setSolidArea(npc.getX() + npc.getSolidArea().x, npc.getY() + npc.getSolidArea().y);
            switch (entity.getDirection()) {
                case "up":
                    entity.setSolidY(entity.getSolidArea().y -= entity.getSpeed() );
                    if (entity.getSolidArea().intersects(npc.getSolidArea())) {
//                        System.out.println("up col");
                        entity.setCollisionOn(true);
                        tmpId=npc.getId();
                    }
                    break;
                case "down":
                    entity.setSolidY(entity.getSolidArea().y += entity.getSpeed() );
                    if (entity.getSolidArea().intersects(npc.getSolidArea())) {
//                        System.out.println("DOWN");
                        entity.setCollisionOn(true);
                        tmpId=npc.getId();
                    }
                    break;
                case "right":
                    entity.setSolidX(entity.getSolidArea().x += entity.getSpeed() );
                    if (entity.getSolidArea().intersects(npc.getSolidArea())) {
//                        System.out.println("RIGHT");
                        entity.setCollisionOn(true);
                        tmpId=npc.getId();
                    }
                    break;
                case "left":
                    entity.setSolidX(entity.getSolidArea().x -= entity.getSpeed() );
                    if (entity.getSolidArea().intersects(npc.getSolidArea())) {
//                        System.out.println("left");
                        entity.setCollisionOn(true);
                        tmpId=npc.getId();
                    }
                    break;
            }
            entity.setSolidArea(entity.getSolidDefaultX(), entity.getSolidDefaultY());
            npc.setSolidArea(npc.getSolidDefaultX(), npc.getSolidDefaultX());
        }
        return tmpId;
    }
//    public void checkPlayer(Entity entity){
//            entity.setSolidArea(entity.getX() + entity.getSolidArea().x, entity.getY() + entity.getSolidArea().y);
//            gameCFG.getPlayer().setSolidArea(gameCFG.getPlayer().getX() + gameCFG.getPlayer().getSolidArea().x,
//                    gameCFG.getPlayer().getY() + gameCFG.getPlayer().getSolidArea().y);
//            switch (entity.getDirection()) {
//                case "up":
//                    entity.setSolidY(entity.getSolidArea().y - entity.getSpeed() / 2);
//                    if (entity.getSolidArea().intersects(gameCFG.getPlayer().getSolidArea())) {
//                        System.out.println("up col");
//                        entity.setCollisionOn(true);
//                    }
//                    break;
//                case "down":
//                    entity.setSolidY(entity.getSolidArea().y += entity.getSpeed() / 2);
//                    if (entity.getSolidArea().intersects(gameCFG.getPlayer().getSolidArea())) {
//                        entity.setCollisionOn(true);
//
//                    }
//                    break;
//                case "right":
//                    entity.setSolidX(entity.getSolidArea().x += entity.getSpeed() / 2);
//                    if (entity.getSolidArea().intersects(gameCFG.getPlayer().getSolidArea())) {
//                        entity.setCollisionOn(true);
//
//                    }
//                    break;
//                case "left":
//                    entity.setSolidX(entity.getSolidArea().x -= entity.getSpeed() / 2);
//                    if (entity.getSolidArea().intersects(gameCFG.getPlayer().getSolidArea())) {
//                        entity.setCollisionOn(true);
//                    }
//                    break;
//            }
//            entity.setSolidArea(entity.getSolidDefaultX(), entity.getSolidDefaultY());
//            gameCFG.getPlayer().setSolidArea(gameCFG.getPlayer().getSolidDefaultX(), gameCFG.getPlayer().getSolidDefaultX());
//    }
    public int getMapColData(int col,int row) {
        return mapColData[col][row];
    }
}


