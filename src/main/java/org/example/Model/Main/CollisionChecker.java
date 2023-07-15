package org.example.Model.Main;

import org.example.IO.InOut;
import org.example.Model.Entity.Entity;
import org.example.Model.Object.Object;

import java.util.ArrayList;

public class CollisionChecker {
    GameCFG gameCFG;
    private int[][] mapColData;
    Entity entity;


    public CollisionChecker(GameCFG gameCFG, Entity entity) {
        InOut inOut = new InOut();
        this.entity = entity;
        this.gameCFG = gameCFG;
        mapColData = new int[gameCFG.getMaxWorldCol()][gameCFG.getMaxWorldRow()];
        mapColData = inOut.getDataMap(gameCFG);
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

    public Integer checkObject(Entity entity, boolean player) {
        for (Object obj : gameCFG.getObjects()) {
            if (obj != null) {
                entity.setSolidArea(entity.getX() + entity.getSolidArea().x, entity.getY() + entity.getSolidArea().y);
                obj.setSolidArea(obj.getX(), obj.getY());
                switch (entity.getDirection()) {
                    case "up":
                        entity.setSolidY(entity.getSolidArea().y - entity.getSpeed() / 2);
                        if (entity.getSolidArea().intersects(obj.getSolidArea())) {
                            if (obj.isCollision()) {
                                entity.setCollisionOn(true);
                                entity.setSolidArea(entity.getSolidDefaultX(), entity.getSolidDefaultY());
                                assert obj != null;
                                obj.setSolidArea(obj.getSolidAreaDefaultX(), obj.getSolidAreaDefaultY());
                                return obj.getId();
                            }
                            else {
                                if(player){
                                    entity.setSolidArea(entity.getSolidDefaultX(), entity.getSolidDefaultY());
                                    assert obj != null;
                                    obj.setSolidArea(obj.getSolidAreaDefaultX(), obj.getSolidAreaDefaultY());
                                    return obj.getId();
                                }
                            }
                        }
                        break;
                    case "down":
                        entity.setSolidY(entity.getSolidArea().y += entity.getSpeed() / 2);
                        if (entity.getSolidArea().intersects(obj.getSolidArea())) {
                            if (obj.isCollision()) {
                                entity.setCollisionOn(true);
                                entity.setSolidArea(entity.getSolidDefaultX(), entity.getSolidDefaultY());
                                assert obj != null;
                                obj.setSolidArea(obj.getSolidAreaDefaultX(), obj.getSolidAreaDefaultY());
                                return obj.getId();
                            }
                            else {
                                if(player){
                                    entity.setSolidArea(entity.getSolidDefaultX(), entity.getSolidDefaultY());
                                    assert obj != null;
                                    obj.setSolidArea(obj.getSolidAreaDefaultX(), obj.getSolidAreaDefaultY());
                                    return obj.getId();
                                }
                            }
                        }
                        break;
                    case "right":
                        entity.setSolidX(entity.getSolidArea().x += entity.getSpeed() / 2);
                        if (entity.getSolidArea().intersects(obj.getSolidArea())) {
                            if (obj.isCollision()) {
                                entity.setCollisionOn(true);
                                entity.setSolidArea(entity.getSolidDefaultX(), entity.getSolidDefaultY());
                                assert obj != null;
                                obj.setSolidArea(obj.getSolidAreaDefaultX(), obj.getSolidAreaDefaultY());
                                return obj.getId();
                            }
                            else {
                                if(player){
                                    entity.setSolidArea(entity.getSolidDefaultX(), entity.getSolidDefaultY());
                                    assert obj != null;
                                    obj.setSolidArea(obj.getSolidAreaDefaultX(), obj.getSolidAreaDefaultY());
                                    return obj.getId();

                                }
                            }
                        }
                        break;
                    case "left":
                        entity.setSolidX(entity.getSolidArea().x -= entity.getSpeed() / 2);
                        if (entity.getSolidArea().intersects(obj.getSolidArea())) {
                            if (obj.isCollision()) {
                                entity.setCollisionOn(true);
                                entity.setSolidArea(entity.getSolidDefaultX(), entity.getSolidDefaultY());
                                assert obj != null;
                                obj.setSolidArea(obj.getSolidAreaDefaultX(), obj.getSolidAreaDefaultY());
                                return obj.getId();
                            }
                            else {
                                if(player){
                                    entity.setSolidArea(entity.getSolidDefaultX(), entity.getSolidDefaultY());
                                    obj.setSolidArea(obj.getSolidAreaDefaultX(), obj.getSolidAreaDefaultY());
                                    return obj.getId();
                                }
                            }
                        }
                        break;
                }
            }
            entity.setSolidArea(entity.getSolidDefaultX(), entity.getSolidDefaultY());
            assert obj != null;
            obj.setSolidArea(obj.getSolidAreaDefaultX(), obj.getSolidAreaDefaultY());

        }
        return null;
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
    public boolean checkPlayer(Entity entity){
        boolean collision = false;
            entity.setSolidArea(entity.getX() + entity.getSolidArea().x, entity.getY() + entity.getSolidArea().y);
            gameCFG.getPlayer().setSolidArea(gameCFG.getPlayer().getX() + gameCFG.getPlayer().getSolidArea().x,
                    gameCFG.getPlayer().getY() + gameCFG.getPlayer().getSolidArea().y);
            switch (entity.getDirection()) {
                case "up":
                    entity.setSolidY(entity.getSolidArea().y - entity.getSpeed() / 2);
                    if (entity.getSolidArea().intersects(gameCFG.getPlayer().getSolidArea())) {
                        collision=true;
                        entity.setCollisionOn(true);
                    }
                    break;
                case "down":
                    entity.setSolidY(entity.getSolidArea().y += entity.getSpeed() / 2);
                    if (entity.getSolidArea().intersects(gameCFG.getPlayer().getSolidArea())) {
                        collision=true;
                        entity.setCollisionOn(true);

                    }
                    break;
                case "right":
                    entity.setSolidX(entity.getSolidArea().x += entity.getSpeed() / 2);
                    if (entity.getSolidArea().intersects(gameCFG.getPlayer().getSolidArea())) {
                        collision=true;
                        entity.setCollisionOn(true);

                    }
                    break;
                case "left":
                    entity.setSolidX(entity.getSolidArea().x -= entity.getSpeed() / 2);
                    if (entity.getSolidArea().intersects(gameCFG.getPlayer().getSolidArea())) {
                        collision=true;
                        entity.setCollisionOn(true);
                    }
                    break;
            }
            entity.setSolidArea(entity.getSolidDefaultX(), entity.getSolidDefaultY());
            gameCFG.getPlayer().setSolidArea(gameCFG.getPlayer().getSolidDefaultX(), gameCFG.getPlayer().getSolidDefaultX());
            return collision;
    }
    public int getMapColData(int col,int row) {
        return mapColData[col][row];
    }
}


