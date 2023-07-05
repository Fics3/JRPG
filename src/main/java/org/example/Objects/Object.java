package org.example.Objects;

import org.example.Main.GameCFG;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

public class Object {
    private int id;
    private int X,Y;
    private boolean collision;
    private String name;
    GameCFG gameCFG;
    private Rectangle solidArea=new Rectangle(0,0,24,16);
    private  int solidAreaDefaultX, solidAreaDefaultY;
    private ArrayList<Integer> possibleObjects = new ArrayList<>();
    ObjectView objectView;



    public Object(GameCFG gameCFG, int id) throws IOException {
        this.gameCFG = gameCFG;
        setCollision(true);
        if(id == 2) {
            setName("chest");
            objectView = new ObjectView(gameCFG,"chest");
        }
    }
    public int getId() {
        return id;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getY() {
        return Y;
    }




    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setSolidArea(int i, int i1) {
        solidArea.x=i;
        solidArea.y=i1;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    public void setSolidAreaDefaultX(int solidAreaDefaultX) {
        this.solidAreaDefaultX = solidAreaDefaultX;
    }

    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

    public void setSolidAreaDefaultY(int solidAreaDefaultY) {
        this.solidAreaDefaultY = solidAreaDefaultY;
    }

    public void setSolidAreaDefault(int i,int i1) {
        solidAreaDefaultX=i;
        solidAreaDefaultY=i1;
    }

    public ArrayList<Integer> getPossibleObjects() {
        return possibleObjects;
    }

    public void setPossibleObjects(ArrayList<Integer> possibleObjects) {
        this.possibleObjects = possibleObjects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObjectView getObjectView() {
        return objectView;
    }
}
