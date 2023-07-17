package org.example.IO;

import java.io.Serializable;

public class DataStorageWorld implements Serializable {

    private int maxWorldCol;
    private int maxWorldRow;
    private int[][] map;
    private int[][] dataMap;
    private String name;

    public int getMaxWorldCol() {
        return maxWorldCol;
    }

    public void setMaxWorldCol(int maxWorldCol) {
        this.maxWorldCol = maxWorldCol;
    }

    public int getMaxWorldRow() {
        return maxWorldRow;
    }

    public void setMaxWorldRow(int maxWorldRow) {
        this.maxWorldRow = maxWorldRow;
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public int[][] getDataMap() {
        return dataMap;
    }

    public void setDataMap(int[][] dataMap) {
        this.dataMap = dataMap;
    }
}
