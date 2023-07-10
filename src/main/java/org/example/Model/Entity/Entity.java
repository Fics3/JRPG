package org.example.Model.Entity;

import org.example.Model.Main.GameCFG;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
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
    private boolean collisionOn=false;
    private GameCFG gameCFG;
    private int id;
    private ArrayList<String> dialogues = new ArrayList<>();
    private boolean isEnemy;
    private String currentDialogue;
    private int damage;
    private int mana;
    private int maxMana;
    private int spriteNum=2;
    private int spriteCycle = 0;
    private int actionLockCounter = 0;



    public Entity(GameCFG gameCFG){
        this.gameCFG = gameCFG;
    }
    public  Entity(GameCFG gameCFG,boolean isEnemy,String name,int x,int y,int id) throws IOException {
        this.gameCFG = gameCFG;
        setName(name);
        setEnemy(isEnemy);
        if(Objects.equals(name, "GreenBoy")){
            setMaxHP(50);
            setLvl(1);
            setHP(maxHP*lvl);
            setDirection("down");
            setSpeed(1);
            setX(x);
            setY(y);
            setMaxMana(20);
            setMana(maxMana*lvl);
            setId(id);
            setName(name);
            setDamage(4*lvl);
            setDialogue("Sample Text");
        }
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

    public void setDialogue(String txt){
        dialogues.add(txt);
        currentDialogue = dialogues.get(0);
    }
    public void update(){
//        setDamage(damage*lvl);
//        setMaxHP(maxHP+5*lvl);
//        setAction();
        setCollisionOn(false);
        gameCFG.getCollisionChecker().checkTile(this);
        gameCFG.getCollisionChecker().checkObject(this,false);
        if(isEnemy) runToPlayer();
        if(isCollisionOn()){
            switch (getDirection()){
                case "up":setY(getY()+getSpeed()); break;
                case "down":setY(getY()-getSpeed()); break;
                case "right":setX(getX()-getSpeed()); break;
                case "left":setX(getX()+getSpeed()); break;
            }
        }
//        if(!isCollisionOn()){
//            switch (getDirection()){
//                case "up":setY(getY()-getSpeed()); break;
//                case "down":setY(getY()+getSpeed()); break;
//                case "right":setX(getX()+getSpeed()); break;
//                case "left":setX(getX()-getSpeed()); break;
//            }
//        }
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
            int pX=getGameCFG().getPlayer().getX()-this.getX();
            int pY = getGameCFG().getPlayer().getY()-this.getY();
            if(pX>0 && pY>0){
                setDirection("down");
                this.setX(getX()+getSpeed());
                this.setY(getY()+getSpeed());
            }
            if(pX>0 && pY<0){
                setDirection("right");
                this.setX(getX()+getSpeed());
                this.setY(getY()-getSpeed());
            }
            if(pX<0 && pY<0){
                setDirection("left");
                this.setX(getX()-getSpeed());
                this.setY(getY()-getSpeed());
            }
            if(pX<0 && pY>0){
                setDirection("left");
                this.setX(getX()-getSpeed());
                this.setY(getY()+getSpeed());
            }
            if(pX < 0 && pY == 0){
                setDirection("left");
                this.setX(getX()-getSpeed());
            }
            if(pX>0 && pY == 0){
                setDirection("right");
                this.setX(getX()+getSpeed());
            }
            if(pX==0 && pY<0){
                setDirection("up");
                this.setY(getY()-getSpeed());
            }
            if(pX==0 && pY>0){
                setDirection("down");
                this.setY(getY()+getSpeed());
            }
//            this.setY(getY() + getSpeed());
        }

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
}

