package org.example.IO;

import org.example.Model.Main.GameCFG;

import java.io.*;

public class SaveLoadWorld {

    private GameCFG gameCFG;
    boolean load = false;
    public SaveLoadWorld(GameCFG gameCFG){
        this.gameCFG = gameCFG;
    }

    public void save(String filename){
        gameCFG.getLevelEditor().getMap();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(filename+".dat")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DataStorageWorld dataStorageWorld = new DataStorageWorld();

        dataStorageWorld.setMaxWorldCol(gameCFG.getLevelEditor().getCol());
        dataStorageWorld.setMaxWorldRow(gameCFG.getLevelEditor().getRow());
        dataStorageWorld.setMap(gameCFG.getLevelEditor().getMap());
        dataStorageWorld.setDataMap(gameCFG.getLevelEditor().getDataMap());

        try {
            objectOutputStream.writeObject(dataStorageWorld);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void load(String name){
        ObjectInputStream objectInputStream = null;
        File file = new File(name+".dat");
        if(file.exists()) {
            try {
                objectInputStream = new ObjectInputStream(new FileInputStream(new File(name + ".dat")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            DataStorageWorld dataStorageWorld = null;

            try {
                dataStorageWorld = (DataStorageWorld) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            gameCFG.setMaxWorldCol(dataStorageWorld.getMaxWorldCol());
            gameCFG.setMaxWorldRow(dataStorageWorld.getMaxWorldRow());
            gameCFG.setDataMap(dataStorageWorld.getDataMap());
            gameCFG.setMapDataNum(dataStorageWorld.getMap());
            load =true;
        }
    }

    public void setLoad(boolean load) {
        this.load = load;
    }

    public boolean isLoad() {
        return load;
    }
}
