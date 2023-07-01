package org.example;

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
        gamePanel.setObjects(new OBJ_Chest());
        gamePanel.setObjects(new OBJ_Dungeon());

        for (int i = 0; i < gamePanel.getMaxWorldCol(); i++) {
            for (int j = 0; j < gamePanel.getMaxWorldRow(); j++) {
                int obj=gamePanel.tileManager.getMapColData(i,j);
                if(obj>1 && gamePanel.getObjects(obj)!=null){
                    gamePanel.getObjects(obj).setX(i);
                    gamePanel.getObjects(obj).setY(j);
                    gamePanel.getObjects(obj).setVisible(true);
                }
            }
        }
    }
}
