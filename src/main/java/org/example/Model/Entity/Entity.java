package org.example.Model.Entity;

import org.example.Model.Main.GameCFG;
import org.example.Model.PathFinder;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

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
    private boolean collisionOn = false;
    private final GameCFG gameCFG;
    private int id;
    private ArrayList<String> dialogues = new ArrayList<>();
    private boolean isEnemy = false;
    private String currentDialogue;
    private int damage;
    private int mana;
    private int maxMana;
    private int spriteNum=2;
    private int spriteCycle = 0;
    private int actionLockCounter = 0;
    private PathFinder pathFinder;
    private int baseHP = 10;
    private int defaultX;
    private int defaultY;



    //    public Entity(GameCFG gameCFG){
//        this.gameCFG = gameCFG;
//    }
    public  Entity(GameCFG gameCFG) {
        this.gameCFG = gameCFG;
    }
    public void defaultPosition(){
        setX(getDefaultX());
        setY(getDefaultY());
    }

    public void setAction(){
        actionLockCounter++;
        if(actionLockCounter==120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75) {
                direction = "right";
            }
            actionLockCounter=0;
        }
    }

    public void checkCollision(){
    }

    public void setDialogue(String txt){
        dialogues.add(txt);
        currentDialogue = dialogues.get(0);
    }
    public void update(){
        setAction();
        if(isEnemy) {runToPlayer();}
//        if(isCollisionOn()){
//            switch (getDirection()){
//                case "up":setY(getY()+getSpeed()); break;
//                case "down":setY(getY()-getSpeed()); break;
//                case "right":setX(getX()-getSpeed()); break;
//                case "left":setX(getX()+getSpeed()); break;
//            }
//        }
        if(!isCollisionOn()){
            switch (getDirection()){
                case "up":setY(getY()-getSpeed()); break;
                case "down":setY(getY()+getSpeed()); break;
                case "right":setX(getX()+getSpeed()); break;
                case "left":setX(getX()-getSpeed()); break;
            }
        }
        setCollisionOn(false);

        gameCFG.getCollisionChecker().checkTile(this);
        gameCFG.getCollisionChecker().checkObject(this,false);
        boolean tmp =  gameCFG.getCollisionChecker().checkPlayer(this);
        if(tmp){
            try {
                if(this.isEnemy)gameCFG.getPlayer().interactEnemy(this.getId());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        spriteCycle++;
        if (spriteCycle > 15) {
            if (getSpriteNum() == 1) setSpriteNum(2);
            else if (getSpriteNum() == 2) setSpriteNum(3);
            else if (getSpriteNum() == 3) setSpriteNum(1);
            spriteCycle = 0;
        }
    }

    public void runToPlayer() {
        if (Math.abs(this.getX() - getGameCFG().getPlayer().getX())-gameCFG.getTileSize() < getGameCFG().getScreenWight() / 2 &&
                Math.abs(this.getY() - getGameCFG().getPlayer().getY())-gameCFG.getTileSize() < getGameCFG().getScreenHeight() / 2) {
//            this.setX(getX()-getSpeed());
            int pX= (gameCFG.getPlayer().getX() + gameCFG.getPlayer().getSolidArea().x)/gameCFG.getTileSize();
            int pY =(gameCFG.getPlayer().getY() + gameCFG.getPlayer().getSolidArea().y)/gameCFG.getTileSize();

            searchPath(pX,pY);

//            int tileDistance = (pX + pY)/gameCFG.getTileSize();

//            if(pX>0 && pY>0){
//                setDirection("down");
//                this.setX(getX()+getSpeed());
//                this.setY(getY()+getSpeed());
//            }
//            if(pX>0 && pY<0){
//                setDirection("right");
//                this.setX(getX()+getSpeed());
//                this.setY(getY()-getSpeed());
//            }
//            if(pX<0 && pY<0){
//                setDirection("left");
//                this.setX(getX()-getSpeed());
//                this.setY(getY()-getSpeed());
//            }
//            if(pX<0 && pY>0){
//                setDirection("left");
//                this.setX(getX()-getSpeed());
//                this.setY(getY()+getSpeed());
//            }
//            if(pX < 0 && pY == 0){
//                setDirection("left");
//                this.setX(getX()-getSpeed());
//            }
//            if(pX>0 && pY == 0){
//                setDirection("right");
//                this.setX(getX()+getSpeed());
//            }
//            if(pX==0 && pY<0){
//                setDirection("up");
//                this.setY(getY()-getSpeed());
//            }
//            if(pX==0 && pY>0){
//                setDirection("down");
//                this.setY(getY()+getSpeed());
//            }
//            this.setY(getY() + getSpeed());
        }

        }
    public void searchPath(int goalCol, int goalRow){
        int startCol = (x + solidArea.x)/gameCFG.getTileSize();
        int startRow = (y + solidArea.y)/gameCFG.getTileSize();

        pathFinder.setNode(startCol,startRow,goalCol,goalRow);

        if(pathFinder.search()){
            int nextX = pathFinder.getPathList().get(0).getCol()*gameCFG.getTileSize();
            int nextY = pathFinder.getPathList().get(0).getRow()*gameCFG.getTileSize();

            int enLeftX = x + solidArea.x;
            int enRightX = x+solidArea.x +solidArea.width;
            int enTopY = y+solidArea.y;
            int enBotY = y+solidArea.y+solidArea.height;

            if(enTopY>nextY && enLeftX >= nextX && enRightX < nextX +gameCFG.getTileSize()){
                direction = "up";
            }
            else if(enTopY<nextY && enLeftX >= nextX && enRightX < nextX +gameCFG.getTileSize()){
                direction = "down";
            }
            else if(enTopY >= nextY && enBotY < nextY + gameCFG.getTileSize()){
                if(enLeftX>nextX) {
                    direction = "left";
                }
                if(enLeftX < nextX){
                    direction="right";
                }
            }
            else if(enTopY>nextY && enLeftX>nextX){
                direction = "up";
                if(collisionOn){
                    direction="left";
                }
            }
            else if( enTopY>nextY && enLeftX<nextX){
                direction="up";
                if(collisionOn){
                    direction = "right";
                }
            }
            else if( enTopY<nextY && enLeftX<nextX){
                direction="down";
                if(collisionOn){
                    direction = "right";
                }
            }
            else if( enTopY<nextY && enLeftX>nextX){
                direction="down";
                if(collisionOn){
                    direction = "left";
                }
            }
            int nextCol = pathFinder.getPathList().get(0).getCol();
            int nextRow = pathFinder.getPathList().get(0).getRow();

        }

    }
    public void lvlUp(){
        setLvl(lvl+1);
        setMaxHP(baseHP+10*lvl);
        setDamage(getDamage()+lvl);
        setHP(getMaxHP());
    }
    public void slash(Entity entity){
        entity.setHP(entity.getHP()-damage);
        gameCFG.getKeyboardController().setSlash(false);
    }
    public void vampireSlash(Entity entity){
        entity.setHP(entity.getHP()-damage/2);
        this.setHP(this.getHP()+damage/2);
        if(this.getHP()>this.maxHP)this.setHP(maxHP);
        gameCFG.getKeyboardController().setVampireSlash(false);
    }
    public void restoreHealth(){
        this.setHP(getHP()+3*lvl);
        this.setMana(getMana()-5);
        if(this.getHP()>this.getMaxHP()){
            this.setHP(getMaxHP());
        }
        gameCFG.getKeyboardController().setRestoreHealth(false);
    }
    public ArrayList<String> getStats(){
        ArrayList<String> fightMenu = new ArrayList<>();
        fightMenu.add(getName());
        fightMenu.add("lvl: "+ getLvl());
        fightMenu.add("HP: "+ getHP()+"/"+getMaxHP());
        fightMenu.add("DMG: "+getDamage());
        fightMenu.add("Mana: "+getMana()+"/"+getMaxMana());
        return fightMenu;
    }


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


    public boolean isEnemy() {
        return isEnemy;
    }

    public void setEnemy(boolean enemy) {
        isEnemy = enemy;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public int getSpriteNum() {
        return spriteNum;
    }

    public void setSpriteNum(int spriteNum) {
        this.spriteNum = spriteNum;
    }

    public void setSpriteCycle(int spriteCycle) {
        this.spriteCycle = spriteCycle;
    }

    public int getSpriteCycle() {
        return spriteCycle;
    }

    public PathFinder getPathFinder() {
        return pathFinder;
    }

    public void setPathFinder(PathFinder pathFinder) {
        this.pathFinder = pathFinder;
    }


    public int getBaseHP() {
        return baseHP;
    }

    public void setBaseHP(int baseHP) {
        this.baseHP = baseHP;
    }

    public int getDefaultY() {
        return defaultY;
    }

    public void setDefaultY(int defaultY) {
        this.defaultY = defaultY;
    }

    public int getDefaultX() {
        return defaultX;
    }

    public void setDefaultX(int defaultX) {
        this.defaultX = defaultX;
    }
}

