package org.example.Model.Main;


import java.util.Arrays;

public class LevelEditor {
    GameCFG gameCFG;
    private String name;
    private int[][] map;
    private int[][] dataMap;
    private int curTile= 5;
    private int[] objects;
    private int curObj;
    private int col = 0;
    private int row = 0;
    private int curCol = 0;
    private int curRow = 0;

    public LevelEditor(GameCFG gameCFG){
        this.gameCFG = gameCFG;
    }
    public void setLevel(String name){
        this.name = name;
        map = new int[col][row];
        dataMap = new int[col][row];
        for (int i = 0; i < col ; i++) {
            for (int j = 0; j < row; j++) {
                map[i][j] = 0;
                dataMap[i][j]=0;
            }
        }
    }
    public void placeTile(){
        map[curCol][curRow] = curTile;
        System.out.println(map[curCol][curRow]);
//        System.out.println(Arrays.deepToString(map));
    }
    public void setCollision(){
        dataMap[curCol][curRow] = 1;
    }

    public int[][] getMap() {
        return map;
    }
    public int getTile(int i, int j){
        return map[i][j];
    }
    public GameCFG getGameCFG() {
        return gameCFG;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getCurCol() {
        return curCol;
    }

    public int getCurRow() {
        return curRow;
    }

    public void setCurCol(int curCol) {
        this.curCol = curCol;
    }

    public void setCurRow(int curRow) {
        this.curRow = curRow;
    }

    public int getCurTile() {
        return curTile;
    }

    public void setCurTile(int curTile) {
        this.curTile = curTile;
    }
}
