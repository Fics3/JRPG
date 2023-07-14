package org.example.Model.Object;

import org.example.Model.Main.GameCFG;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Object {
    private int id;
    private int X,Y;
    private boolean collision;
    private String name;
    private GameCFG gameCFG;
    private Rectangle solidArea=new Rectangle(0,0,24,16);
    private  int solidAreaDefaultX, solidAreaDefaultY;
    private ArrayList<Integer> possibleObjects = new ArrayList<>();
    private String description;
    private boolean equipable = false;
    private boolean consumable = false;
    private boolean weapon = false;
    private boolean helmet = false;
    private boolean chestplate = false;
    private boolean boots = false;

    public Object(GameCFG gameCFG) {
        this.gameCFG = gameCFG;
    }
    public void consume(){
        return;
    }
    public int getGainHP(){
        return 0;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEquipable() {
        return equipable;
    }

    public void setEquipable(boolean equipable) {
        this.equipable = equipable;
    }

    public boolean isConsumable() {
        return consumable;
    }

    public void setConsumable(boolean consumable) {
        this.consumable = consumable;
    }

    public boolean isWeapon() {
        return weapon;
    }

    public void setWeapon(boolean weapon) {
        this.weapon = weapon;
    }

    public boolean isHelmet() {
        return helmet;
    }

    public void setHelmet(boolean helmet) {
        this.helmet = helmet;
    }

    public boolean isChestplate() {
        return chestplate;
    }

    public void setChestplate(boolean chestplate) {
        this.chestplate = chestplate;
    }

    public boolean isBoots() {
        return boots;
    }

    public void setBoots(boolean boots) {
        this.boots = boots;
    }

    public int getDamage() {
        return 0;
    }

    public GameCFG getGameCFG() {
        return gameCFG;
    }
    public void use(){

    }
}
