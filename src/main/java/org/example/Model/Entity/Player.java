package org.example.Model.Entity;

import org.example.Model.FightModel;
import org.example.Model.Main.GameCFG;
import org.example.Model.Main.KeyboardController;
import org.example.Model.Object.OBJ_woodSword;
import org.example.Model.Object.Object;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Player extends Entity {
    private final KeyboardController keyboardController;

    FightModel fightModel = new FightModel();
    private int exp = 0;
    private int maxExp = 10;
    private ArrayList<Object> inventory = new ArrayList<>();
    private int inventorySize = 20;
    private int inventoryCapacity = 0;
    private int inventorySlot = 0;
    private Object currentWeapon;
    private Object currentHelmet;
    private Object currentChest;
    private Object currentBoots;

    public Player(GameCFG gameCFG, KeyboardController keyboardController)  {
        super(gameCFG);
        for ( int i = 0; i<inventorySize ; i++) {
            inventory.add(0,new Object(gameCFG));
        }
        this.keyboardController = keyboardController;
        setSolidDefaultX(getSolidArea().x);
        setSolidDefaultY(getSolidArea().y);

        setName("Player");
        setCurrentWeapon(new OBJ_woodSword(gameCFG));
        setSolidArea(new Rectangle(8, 16, (int) (getGameCFG().getTileSize() / 1.5), (int) (getGameCFG().getTileSize() / 1.5)));
        setDafautl();
    }

    public void setDafautl() {
        setX(getGameCFG().getMaxWorldWight() / 2);
        setY(getGameCFG().getMaxWorldHeight() / 2);
        setLvl(1);
        setSpeed(4);
        setMaxHP(50);
        setHP(getMaxHP());
        setDamage(getLvl() + currentWeapon.getDamage());
        setMaxMana(20);
        setMana(getMaxMana());
        setDirection("down");
    }
    public void update() {
        if (getKeyboardController().isDownPressed() || getKeyboardController().isLeftPressed() ||getKeyboardController().isUpPressed() || getKeyboardController().isRightPressed()) {
            if (getGameCFG().getKeyboardController().isUpPressed()) {
                setDirection("up");
                this.setY(this.getY() - this.getSpeed());
            } else if (getGameCFG().getKeyboardController().isDownPressed()) {
                setDirection("down");
                this.setY(this.getY() + this.getSpeed());
            } else if (getGameCFG().getKeyboardController().isRightPressed()) {
                setDirection("right");
                this.setX(this.getX() + this.getSpeed());
            } else if (getGameCFG().getKeyboardController().isLeftPressed()) {
                setDirection("left");
                this.setX(this.getX() - this.getSpeed());
            }
            setCollisionOn(false);
            getGameCFG().getCollisionChecker().checkTile(this);
            Integer objId = getGameCFG().getCollisionChecker().checkObject(this, true);
//            System.out.println(getGameCFG().getObjects().size());
            if(objId != null && inventoryCapacity<inventorySize) {
                addItem(getGameCFG().getObjectWithId(objId));
                getGameCFG().getObjects().remove(getGameCFG().getObjectWithId(objId));
                getGameCFG().setGameState(getGameCFG().getLoadObjects());

            }
            int idNpc = getGameCFG().getCollisionChecker().checkEntity(this, getGameCFG().getNpcs());
            if (idNpc != 0 && getKeyboardController().isDialogue() && !getGameCFG().getNpc(idNpc).isEnemy()) {
                interactNPC();
                speak(idNpc);
            }
            if(idNpc!=0 && getGameCFG().getNpc(idNpc).isEnemy()){
                try {
                    interactEnemy(idNpc);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if (isCollisionOn()) {
                switch (getDirection()) {
                    case "up":
                        setY(getY() + getSpeed());
                        break;
                    case "down":
                        setY(getY() - getSpeed());
                        break;
                    case "right":
                        setX(getX() - getSpeed());
                        break;
                    case "left":
                        setX(getX() + getSpeed());
                        break;
                }

                }
            setSpriteCycle(getSpriteCycle()+1);
            if (getSpriteCycle() > 15) {
                if (getSpriteNum() == 1) setSpriteNum(2);
                else if (getSpriteNum() == 2) setSpriteNum(3);
                else if (getSpriteNum() == 3) setSpriteNum(1);
                setSpriteCycle(0);
            }
        }
    }
    public void lvlUp(int exp){
        setExp(getExp()+exp);
        if(getExp()>=maxExp*getLvl() && getLvl()<10){
            setExp(getMaxExp()-getExp());
            setLvl(getLvl()+1);
            setMaxHP(getMaxHP()+10*getLvl());
            setDamage(getDamage()+5*getLvl());
            setMaxMana(getMaxMana()+5*getLvl());
        }
    }
    public void speak(int id){
        getGameCFG().setTmp(getGameCFG().getNpc(id).getCurrentDialogue());
        switch (this.getDirection()){
            case "up":
                getGameCFG().getNpc(id).setDirection("down");
                break;
            case "down":
                getGameCFG().getNpc(id).setDirection("up");
                break;
            case "right":
                getGameCFG().getNpc(id).setDirection("left");
                break;
            case "left":
                getGameCFG().getNpc(id).setDirection("right");
                break;
        }
    }
    public void interactEnemy(int idNpc) throws InterruptedException {
        getGameCFG().setGameState(getGameCFG().getFightState());
        Thread thread = new Thread(() -> {
            try {
                fightModel.fight(Player.this,getGameCFG().getNpc(idNpc));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
    }
    public void interactNPC() {
        getGameCFG().setGameState(getGameCFG().getDialogueState());
        keyboardController.setDialogue(false);
    }
    public ArrayList<String> getStats(){
        ArrayList<String> fightMenu = new ArrayList<>();
        fightMenu.add(getName());
        fightMenu.add("lvl: "+ getLvl());
        fightMenu.add("exp: "+getExp()+"/"+getMaxExp()*getLvl());
        fightMenu.add("HP: "+ getHP()+"/"+getMaxHP());
        fightMenu.add("Mana: "+getMana()+"/"+getMaxMana());
        fightMenu.add("DMG: "+getDamage());


        return fightMenu;
    }

    public KeyboardController getKeyboardController() {
        return keyboardController;
    }


    public FightModel getFightModel() {
        return fightModel;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getMaxExp() {
        return maxExp;
    }

    public void setMaxExp(int maxExp) {
        this.maxExp = maxExp;
    }

    public ArrayList<Object> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Object> inventory) {
        this.inventory = inventory;
    }
    public void addItem(Object object){
        this.inventory.set(inventoryCapacity,object);
        inventoryCapacity++;
    }

    public int getInventorySlot() {
        return inventorySlot;
    }

    public void setInventorySlot(int inventorySlot) {
        this.inventorySlot = inventorySlot;
    }

    public int getInventorySize() {
        return inventorySize;
    }

    public void setInventorySize(int inventorySize) {
        this.inventorySize = inventorySize;
    }

    public Object getCurrentWeapon() {
        return currentWeapon;
    }

    public void setCurrentWeapon(Object currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public Object getCurrentHelmet() {
        return currentHelmet;
    }

    public void setCurrentHelmet(Object currentHelmet) {
        this.currentHelmet = currentHelmet;
    }

    public Object getCurrentChest() {
        return currentChest;
    }

    public void setCurrentChest(Object currentChest) {
        this.currentChest = currentChest;
    }

    public Object getCurrentBoots() {
        return currentBoots;
    }

    public void setCurrentBoots(Object currentBoots) {
        this.currentBoots = currentBoots;
    }
    public void addItemOnPlace(int id, Object object){
        inventory.add(id , object);
    }

    public void setInventoryCapacity(int inventoryCapacity) {
        this.inventoryCapacity = inventoryCapacity;
    }

    public int getInventoryCapacity() {
        return inventoryCapacity;
    }
}

