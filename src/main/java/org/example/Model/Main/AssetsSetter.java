package org.example.Model.Main;

import org.example.Model.Entity.Entity;
import org.example.Model.Object;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AssetsSetter {

    private GameCFG gameCFG;
    private int mapColData[][]=new int[50][50];

    public AssetsSetter(GameCFG gameCFG){
        this.gameCFG=gameCFG;
        try {
            getDataMap();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void setObject() throws IOException {
//        gameCFG.setObjects();
//        gamePanel.setObjects(new OBJ_Dungeon(gamePanel));
        for (int i = 0; i < gameCFG.getMaxWorldCol(); i++) {
            for (int j = 0; j < gameCFG.getMaxWorldRow(); j++) {
                int obj = mapColData[i][j];
                Object object = new Object(gameCFG,obj);
                if(obj>1 && object.getName()!=null){
                    object.setX(i* gameCFG.getTileSize());
                    object.setY(j* gameCFG.getTileSize());
                    object.setSolidAreaDefault(i* gameCFG.getTileSize(),j* gameCFG.getTileSize());
                    gameCFG.setObject(object);
                }
            }
        }
    }
//    public void setObject() throws IOException {
//        OBJ_Chest chest = new OBJ_Chest(gameCFG);
//        chest.setX(22* gameCFG.getTileSize());
//        chest.setY(20*gameCFG.getTileSize());
//        chest.setSolidArea(chest.getX(), chest.getY());
//        gameCFG.setObject(chest);
//        System.out.println(gameCFG.getObjects());
//    }
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
    public void setNpc() throws IOException {
        gameCFG.setNpcs(new Entity(gameCFG,false,"GreenBoy",gameCFG.getMaxWorldWight()/2,gameCFG.getMaxWorldHeight()/2+ gameCFG.getTileSize()*2,2));
                //,"GreenBoy",gameCFG.getMaxWorldWight()/2,gameCFG.getMaxWorldHeight()/2+ gameCFG.getTileSize()*2)) ;
    }
    public void setEnemy() throws IOException {
        gameCFG.setNpcs(new Entity(gameCFG,true,"GreenBoy",gameCFG.getMaxWorldWight()/2+gameCFG.getTileSize(),gameCFG.getMaxWorldHeight()/2+ gameCFG.getTileSize()*4,3));

        gameCFG.setNpcs(new Entity(gameCFG,true,"GreenBoy",gameCFG.getMaxWorldWight()/2+gameCFG.getTileSize(),gameCFG.getMaxWorldHeight()/2+ gameCFG.getTileSize()*3,3));
    }

    public int getMapColData(int i,int i1) {
        return mapColData[i][i1];
    }
}
