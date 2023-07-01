package org.example;

import org.example.Entity.Entity;

public class CollisionChecker {
    GamePanel gamePanel;
    public CollisionChecker(GamePanel gamePanel){
        this.gamePanel=gamePanel;
    }
    public void checkTile(Entity entity){
        int entityLeftX=entity.getX()+entity.solidArea.x;
        int entityRightX=entity.getX()+entity.solidArea.x+entity.solidArea.width;
        int entityTopY=entity.getY()+entity.solidArea.y;
        int entityBotY=entity.getY()+entity.solidArea.y+entity.solidArea.height;

        int entityLeftCol=entityLeftX/gamePanel.getTileSize();
        int entityRightCol=entityRightX/gamePanel.getTileSize();
        int entityTopRow=entityTopY/gamePanel.getTileSize();
        int entityBotRow=entityBotY/gamePanel.getTileSize();

        int tileN1, tileN2;
        switch (entity.getDirection()){
            case "up":
                entityTopRow=(entityTopY-entity.getSpeed())/gamePanel.getTileSize();
                tileN1= gamePanel.tileManager.getMapColData(entityLeftCol,entityTopRow);
                tileN2= gamePanel.tileManager.getMapColData(entityRightCol,entityTopRow);
                if(tileN1==1 && tileN2==1){
                    entity.collisionOn=true;
                }
                break;
            case "down":
                entityBotRow=(entityBotY+entity.getSpeed())/gamePanel.getTileSize();
                tileN1= gamePanel.tileManager.getMapColData(entityLeftCol,entityBotRow);
                tileN2= gamePanel.tileManager.getMapColData(entityRightCol,entityBotRow);
                if(tileN1==1 && tileN2==1){
                    entity.collisionOn=true;
                }
                break;
            case "right":
                entityRightCol=(entityRightX+entity.getSpeed())/gamePanel.getTileSize();
                tileN1= gamePanel.tileManager.getMapColData(entityRightCol,entityTopRow);
                tileN2= gamePanel.tileManager.getMapColData(entityRightCol,entityBotRow);
                if(tileN1==1 && tileN2==1){
                    entity.collisionOn=true;
                }
                break;
            case "left":
                entityTopRow=(entityLeftX-entity.getSpeed())/gamePanel.getTileSize();
                tileN1= gamePanel.tileManager.getMapColData(entityLeftCol,entityTopRow);
                tileN2= gamePanel.tileManager.getMapColData(entityLeftCol,entityBotRow);
                if(tileN1==1 && tileN2==1){
                    entity.collisionOn=true;
                }
                break;

        }
    }
}
