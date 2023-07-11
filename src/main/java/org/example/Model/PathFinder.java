package org.example.Model;


import org.example.IO.InOut;
import org.example.Model.Entity.Entity;
import org.example.Model.Main.GameCFG;

import java.util.ArrayList;

public class PathFinder {
    private GameCFG gameCFG;
    private Node[][] node;
    private ArrayList<Node> openList = new ArrayList<>();
    private ArrayList<Node> pathList = new ArrayList<>();
    private Node startNode, goalNode, currentNode;
    private boolean goalReached = false;
    private int step = 0;
    private int[][] mapColData;
    public PathFinder (GameCFG gameCFG){
        this.gameCFG = gameCFG;
        InOut inOut = new InOut();
        mapColData = inOut.getDataMap(gameCFG);
        node = new Node[gameCFG.getMaxWorldCol()][gameCFG.getMaxWorldRow()];
        instantiateNodes();
    }

    public void instantiateNodes() {

        for (int i = 0; i < gameCFG.getMaxWorldCol(); i++) {
            for (int j = 0; j < gameCFG.getMaxWorldRow(); j++) {
                node[i][j] = new Node(i, j);
            }
        }
        for (int i = 0; i < gameCFG.getMaxWorldCol(); i++) {
            for (int j = 0; j < gameCFG.getMaxWorldRow(); j++) {
                if (mapColData[i][j] > 1) {
                    node[i][j].solid = true;
                }
            }
        }
    }
    public void resetNodes(){
        int col = 0;
        int row = 0;

        while (col< gameCFG.getMaxWorldCol() && row<gameCFG.getMaxWorldRow()){
            node[col][row].open=false;
            node[col][row].checked = false;
            node[col][row].solid = false;
            col++;
            if(col==gameCFG.getMaxWorldCol()){
                col = 0;
                row++;
            }
        }

        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }
    public void setNode(int startCol, int startRow, int goalCol, int goalRow){

        resetNodes();

        startNode = node[startCol][startRow];
        currentNode  = startNode;
        goalNode = node [goalCol][goalRow];
        openList.add(currentNode);

        int col = 0;
        int row = 0;

        while (col< gameCFG.getMaxWorldCol() && row<gameCFG.getMaxWorldRow()){
            getCost(node[col][row]);

            col++;
            if(col == gameCFG.getMaxWorldCol()){
                col =0;
                row++;
            }
        }
    }
    public void getCost(Node node){
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row -startNode.row);

        node.gCost=xDistance+yDistance;

        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);

        node.hCost=xDistance+yDistance;

        node.fCost=node.gCost+node.hCost;
    }
    public boolean search(){
        while (!goalReached){
            int col = currentNode.col;
            int row = currentNode.row;

            currentNode.checked = true;
            openList.remove(currentNode);

            if(row - 1 >= 0){
                openNode(node[col][row-1]);
            }
            if(row + 1 < gameCFG.getMaxWorldRow()){
                openNode(node[col][row+1]);
            }
            if(col - 1 >= 0){
                openNode(node[col-1][row]);
            }
            if(col + 1 < gameCFG.getMaxWorldCol()){
                openNode(node[col+1][row]);
            }
            int bestNodeIndex=0;
            int bestNodefCost=999;

            for (int i = 0; i < openList.size(); i++) {
                if(openList.get(i).fCost<bestNodefCost){
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                }
                else if(openList.get(i).fCost==bestNodefCost){
                    if(openList.get(i).gCost<openList.get(bestNodeIndex).gCost){
                        bestNodeIndex=i;
                    }
                }
            }
            if(openList.size()==0){
                break;
            }

            currentNode = openList.get(bestNodeIndex);

            if(currentNode == goalNode){
                goalReached = true;
                trackThePath();
            }
            step++;
        }
        return goalReached;
    }
    public void openNode(Node node){
        if (!node.open && !node.checked && !node.solid){
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }

    public void trackThePath(){
        Node current = goalNode;

        while (current != startNode){

            pathList.add(0, current);
            current = current.parent;
        }
    }


    public GameCFG getGameCFG() {
        return gameCFG;
    }

    public void setGameCFG(GameCFG gameCFG) {
        this.gameCFG = gameCFG;
    }

    public ArrayList<Node> getOpenList() {
        return openList;
    }

    public void setOpenList(ArrayList<Node> openList) {
        this.openList = openList;
    }

    public ArrayList<Node> getPathList() {
        return pathList;
    }

    public void setPathList(ArrayList<Node> pathList) {
        this.pathList = pathList;
    }

    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public Node getGoakNode() {
        return goalNode;
    }

    public void setGoalNode(Node goalNode) {
        this.goalNode = goalNode;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    public boolean isGoalReached() {
        return goalReached;
    }

    public void setGoalReached(boolean goalReached) {
        this.goalReached = goalReached;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public Node[][] getNode() {
        return node;
    }

    public void setNode(Node[][] node) {
        this.node = node;
    }

}
