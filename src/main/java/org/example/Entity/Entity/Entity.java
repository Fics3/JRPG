package org.example.Entity.Entity;

import org.example.Entity.EntityView.EntityView;
import org.example.Main.GameCFG;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

public class Entity {
    private String name;
    private int x;
    private int y;
    private int speed;
    private int maxHP;
    private int HP;
    private int lvl;
    private String direction;
    private Rectangle solidArea= new Rectangle(0, 0 ,24,16);
    private int solidDefaultX, solidDefaultY;
    private boolean collisionOn=false;
    private GameCFG gameCFG;
    private int id;
    private ArrayList<String> dialogues = new ArrayList<>();
    private int actionCounterLock;

    private String currentDialogue;
    EntityView entityView;



    public Entity(GameCFG gameCFG){
        this.gameCFG = gameCFG;

    }
    public  Entity(GameCFG gameCFG, String name,int x,int y,int id) throws IOException {
        this.gameCFG = gameCFG;
        setName(name);
        if(name == "GreenBoy"){

            setDirection("down");
            setSpeed(4);
            setX(x);
            setY(y);
            setId(id);
            setName(name);
            setDialogue("Sample Text");
            entityView = new EntityView(gameCFG, this);
        }
    }
    public void setAction(){

    }

    public void setDialogue(String txt){
        dialogues.add(txt);
        currentDialogue = dialogues.get(0);
    }
//    public void update(){
//
//        setCollisionOn(false);
//        gameCFG.getCollisionChecker().checkTile(this);
//        gameCFG.getCollisionChecker().checkObject(this,false);
////        gameCFG.getCollisionChecker().checkPlayer(this);
//        if(isCollisionOn()){
//            switch (getDirection()){
//                case "up":setY(getY()+getSpeed()); break;
//                case "down":setY(getY()-getSpeed()); break;
//                case "right":setX(getX()-getSpeed()); break;
//                case "left":setX(getX()+getSpeed()); break;
//            }
//        }
//
//    }


    public void setX(int x) {
        this.x = x;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }

    public boolean isCollisionOn() {
        return collisionOn;
    }

    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

//    public void setSpriteNum(int spriteNum) {
//        this.spriteNum = spriteNum;
//    }
//
//    public int getSpriteNum() {
//        return spriteNum;
//    }


    public void setSolidArea(int i, int i1) {
        solidArea.x=i;
        solidArea.y=i1;
    }
    public void setSolidX(int i){
        solidArea.x=i;
    }
    public void setSolidY(int i){
        solidArea.y=i;
    }

    public int getSolidDefaultX() {
        return solidDefaultX;
    }

    public void setSolidDefaultX(int solidDefaultX) {
        this.solidDefaultX = solidDefaultX;
    }

    public int getSolidDefaultY() {
        return solidDefaultY;
    }

    public void setSolidDefaultY(int solidDefaultY) {
        this.solidDefaultY = solidDefaultY;
    }

    public boolean isCollision() {
        return collisionOn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrentDialogue() {
        return currentDialogue;
    }

    public void setCurrentDialogue(String currentDialogue) {
        this.currentDialogue = currentDialogue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public GameCFG getGameCFG() {
        return gameCFG;
    }

    public int getActionCounterLock() {
        return actionCounterLock;
    }

    public void setActionCounterLock(int actionCounterLock) {
        this.actionCounterLock = actionCounterLock;
    }

    public EntityView getEntityView() {
        return entityView;
    }
}

