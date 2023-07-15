package org.example.Model;


public class Node {
    protected Node parent;
    protected int col;
    protected int row;
    protected int gCost;
    protected int hCost;
    protected int fCost;
    protected boolean solid;
    protected boolean open;
    protected boolean checked;

    public Node(int col, int row){
        this.col=col;
        this.row=row;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
