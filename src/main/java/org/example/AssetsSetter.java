package org.example;

import org.example.Entity.NPC_GreenBoy;
import org.example.Objects.OBJ_Chest;
import org.example.Objects.OBJ_Dungeon;
import org.example.Objects.Object;
import org.example.Tiles.TileManager;

import java.io.IOException;

public class AssetsSetter {

    GamePanel gamePanel;
    public AssetsSetter(GamePanel gamePanel){
        this.gamePanel=gamePanel;
    }

    public void setObject() throws IOException {
        gamePanel.setObjects(new OBJ_Chest(gamePanel));
//        gamePanel.setObjects(new OBJ_Dungeon(gamePanel));

        for (int i = 0; i < gamePanel.getMaxWorldCol(); i++) {
            for (int j = 0; j < gamePanel.getMaxWorldRow(); j++) {
                int obj=gamePanel.tileManager.getMapColData(i,j);
                if(obj>1 && gamePanel.getObject(obj)!=null){
                    gamePanel.getObject(obj).setX(i* gamePanel.getTileSize());
                    gamePanel.getObject(obj).setY(j* gamePanel.getTileSize());
                    gamePanel.getObject(obj).setSolidAreaDefault(i* gamePanel.getTileSize(),j* gamePanel.getTileSize());
                    gamePanel.getObject(obj).setVisible(true);
                }
            }
        }
    }
    public void setNpc() throws IOException {
        gamePanel.setNpcs(new NPC_GreenBoy(gamePanel,gamePanel.getMaxWorldWight()/2,gamePanel.getMaxWorldHeight()/2+ gamePanel.getTileSize())) ;

    }
}
