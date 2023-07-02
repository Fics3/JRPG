package org.example;

import org.example.Entity.Entity;
import org.example.Objects.Object;

import java.util.LinkedList;

public class CollisionChecker {
    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {
        int entityLeftX = entity.getX() + entity.getSolidArea().x;
        int entityRightX = entity.getX() + entity.getSolidArea().x + entity.getSolidArea().width;
        int entityTopY = entity.getY() + entity.getSolidArea().y;
        int entityBotY = entity.getY() + entity.getSolidArea().y + entity.getSolidArea().height;

        int entityLeftCol = entityLeftX / gamePanel.getTileSize();
        int entityRightCol = entityRightX / gamePanel.getTileSize();
        int entityTopRow = entityTopY / gamePanel.getTileSize();
        int entityBotRow = entityBotY / gamePanel.getTileSize();

        int tileN1, tileN2;
        switch (entity.getDirection()) {
            case "up":
                entityTopRow = (entityTopY - entity.getSpeed()) / gamePanel.getTileSize();
                tileN1 = gamePanel.tileManager.getMapColData(entityLeftCol, entityTopRow);
                tileN2 = gamePanel.tileManager.getMapColData(entityRightCol, entityTopRow);
                if (tileN1 == 1 && tileN2 == 1) {
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entityBotRow = (entityBotY + entity.getSpeed()) / gamePanel.getTileSize();
                tileN1 = gamePanel.tileManager.getMapColData(entityLeftCol, entityBotRow);
                tileN2 = gamePanel.tileManager.getMapColData(entityRightCol, entityBotRow);
                if (tileN1 == 1 && tileN2 == 1) {
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entityRightCol = (entityRightX + entity.getSpeed()) / gamePanel.getTileSize();
                tileN1 = gamePanel.tileManager.getMapColData(entityRightCol, entityTopRow);
                tileN2 = gamePanel.tileManager.getMapColData(entityRightCol, entityBotRow);
                if (tileN1 == 1 && tileN2 == 1) {
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entityTopRow = (entityLeftX - entity.getSpeed()) / gamePanel.getTileSize();
                tileN1 = gamePanel.tileManager.getMapColData(entityLeftCol, entityTopRow);
                tileN2 = gamePanel.tileManager.getMapColData(entityLeftCol, entityBotRow);
                if (tileN1 == 1 && tileN2 == 1) {
                    entity.setCollisionOn(true);
                }
                break;

        }
    }

    public void checkObject(Entity entity, boolean player) {
        int idx = 999;

        for (Object obj : gamePanel.getObjects()) {
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

    public void checkEntity(Entity entity, LinkedList<Entity> target) {
        for (Entity npc : target) {
            entity.setSolidArea(entity.getX() + entity.getSolidArea().x, entity.getY() + entity.getSolidArea().y);
            npc.setSolidArea(npc.getX() + npc.getSolidArea().x, npc.getY() + npc.getSolidArea().y);
            switch (entity.getDirection()) {
                case "up":
                    entity.setSolidY(entity.getSolidArea().y - entity.getSpeed() );
                    if (entity.getSolidArea().intersects(npc.getSolidArea())) {
                        System.out.println("up col");
                        entity.setCollisionOn(true);
                    }
                    break;
                case "down":
                    entity.setSolidY(entity.getSolidArea().y += entity.getSpeed() );
                    if (entity.getSolidArea().intersects(npc.getSolidArea())) {
                        entity.setCollisionOn(true);

                    }
                    break;
                case "right":
                    entity.setSolidX(entity.getSolidArea().x += entity.getSpeed() );
                    if (entity.getSolidArea().intersects(npc.getSolidArea())) {
                        entity.setCollisionOn(true);

                    }
                    break;
                case "left":
                    entity.setSolidX(entity.getSolidArea().x -= entity.getSpeed() );
                    if (entity.getSolidArea().intersects(npc.getSolidArea())) {
                        entity.setCollisionOn(true);
                    }
                    break;
            }
            entity.setSolidArea(entity.getSolidDefaultX(), entity.getSolidDefaultY());
            npc.setSolidArea(npc.getSolidDefaultX(), npc.getSolidDefaultX());
        }

    }
    public void checkPlayer(Entity entity){
            entity.setSolidArea(entity.getX() + entity.getSolidArea().x, entity.getY() + entity.getSolidArea().y);
            gamePanel.getPlayer().setSolidArea(gamePanel.getPlayer().getX() + gamePanel.getPlayer().getSolidArea().x,
                    gamePanel.getPlayer().getY() + gamePanel.getPlayer().getSolidArea().y);
            switch (entity.getDirection()) {
                case "up":
                    entity.setSolidY(entity.getSolidArea().y - entity.getSpeed() / 2);
                    if (entity.getSolidArea().intersects(gamePanel.getPlayer().getSolidArea())) {
                        System.out.println("up col");
                        entity.setCollisionOn(true);
                    }
                    break;
                case "down":
                    entity.setSolidY(entity.getSolidArea().y += entity.getSpeed() / 2);
                    if (entity.getSolidArea().intersects(gamePanel.getPlayer().getSolidArea())) {
                        entity.setCollisionOn(true);

                    }
                    break;
                case "right":
                    entity.setSolidX(entity.getSolidArea().x += entity.getSpeed() / 2);
                    if (entity.getSolidArea().intersects(gamePanel.getPlayer().getSolidArea())) {
                        entity.setCollisionOn(true);

                    }
                    break;
                case "left":
                    entity.setSolidX(entity.getSolidArea().x -= entity.getSpeed() / 2);
                    if (entity.getSolidArea().intersects(gamePanel.getPlayer().getSolidArea())) {
                        entity.setCollisionOn(true);
                    }
                    break;
            }
            entity.setSolidArea(entity.getSolidDefaultX(), entity.getSolidDefaultY());
            gamePanel.getPlayer().setSolidArea(gamePanel.getPlayer().getSolidDefaultX(), gamePanel.getPlayer().getSolidDefaultX());
    }
}


