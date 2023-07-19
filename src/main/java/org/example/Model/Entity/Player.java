package org.example.Model.Entity;

import org.example.Model.FightModel;
import org.example.Model.Main.GameCFG;
import org.example.Model.Main.KeyboardController;
import org.example.Model.Object.Weapon.OBJ_woodSword;
import org.example.Model.Object.ObjectModel;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Objects;

public class Player extends Entity {
    private final KeyboardController keyboardController;

    FightModel fightModel = new FightModel();
    private int exp = 0;
    private int maxExp = 10;
    private ArrayList<ObjectModel> inventory = new ArrayList<>();
    private int inventorySize = 20;
    private int inventoryCapacity = 0;
    private int inventorySlot = 0;
    private ObjectModel currentWeapon;
    private ObjectModel currentHelmet;
    private ObjectModel currentChest;
    private ObjectModel currentBoots;
    private int heathPotions=0;
    private int manaPotions=0;

    public Player(GameCFG gameCFG, KeyboardController keyboardController)  {
        super(gameCFG);
        for ( int i = 0; i<inventorySize ; i++) {
            inventory.add(0,new ObjectModel(gameCFG));
        }
        this.keyboardController = keyboardController;
        setSolidDefaultX(getSolidArea().x);
        setSolidDefaultY(getSolidArea().y);

        setName("Player");
        setCurrentWeapon(new OBJ_woodSword(gameCFG));
        setSolidArea(new Rectangle(16,16 , 24, 16));
        setDafautl();
        gameCFG.setGameState(gameCFG.getLoadInventory());
    }

    public void setDafautl() {
        setDefaultX(getGameCFG().getMaxWorldWight() / 2);
        setDefaultY(getGameCFG().getMaxWorldHeight() / 2);
        setX(getDefaultX());
        setY(getDefaultY());
        setLvl(1);
        setSpeed(4);
        setMaxHP(10);
        setBaseHP(getMaxHP());
        if(currentHelmet!=null) setMaxHP(getMaxHP()+currentHelmet.getGainHP());
        if(currentBoots!=null) setMaxHP(getMaxHP()+currentBoots.getGainHP());
        if(currentChest!=null) setMaxHP(getMaxHP()+currentChest.getGainHP());
        setHP(getMaxHP());
        setDamage(getLvl() + currentWeapon.getDamage());
        setMaxMana(20);
        setMana(getMaxMana());
        setDirection("down");
    }
    public void update() {
        int playerX = getX();
        int playerY = getY();

        if (playerX < 0) {
            playerX = 0;
        } else if (playerX > (getGameCFG().getMaxWorldCol()-1) * getGameCFG().getTileSize() - 16) {
            playerX = (getGameCFG().getMaxWorldCol() - 1) * getGameCFG().getTileSize() - 16;
        }

        if (playerY < 0) {
            playerY = 0;
        } else if (playerY > (getGameCFG().getMaxWorldRow() - 1) * getGameCFG().getTileSize() - 16) {
            playerY = (getGameCFG().getMaxWorldRow() - 1) * getGameCFG().getTileSize() - 16;
        }

        setX(playerX);
        setY(playerY);
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
            if(!this.isCollision()) {
                if (objId != null && inventoryCapacity < inventorySize && !getGameCFG().getObjectWithId(objId).isCollision() ) {
                    addItem(getGameCFG().getObjectWithId(objId));
                    if (Objects.equals(getGameCFG().getObjectWithId(objId).getName(), "healthPotion")) heathPotions++;
                    else if (Objects.equals(getGameCFG().getObjectWithId(objId).getName(), "manaPotion")) manaPotions++;
                    getGameCFG().getObjects().remove(getGameCFG().getObjectWithId(objId));
                    getGameCFG().setGameState(getGameCFG().getLoadObjects());
                }
            }
            else {
                if (objId != null && keyboardController.isUseObject()) {
                    getGameCFG().getObjectWithId(objId).use();
                    getGameCFG().getObjects().remove(getGameCFG().getObjectWithId(objId));
                    getGameCFG().setGameState(getGameCFG().getLoadGame());
                }
            }

//            if(objId != null && getGameCFG().getObjectWithId(objId).isCollision() && keyboardController.isUseObject()) {
//                getGameCFG().getObjectWithId(objId);
//            }
            int idNpc = getGameCFG().getCollisionChecker().checkEntity(this, getGameCFG().getNpcs());
            if (idNpc != 0 && getKeyboardController().isUseObject() && !getGameCFG().getNpc(idNpc).isEnemy()) {
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
            setExp(0);
            setLvl(getLvl()+1);
            setMaxHP(getMaxHP()+10*getLvl());
            setHP(getMaxHP());
            setDamage(getDamage()+5*getLvl());
            setMaxMana(getMaxMana()+5*getLvl());
        }
    }
    public void speak(int id){
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
    public void interactEnemy(Entity entity) throws InterruptedException {
        getGameCFG().setGameState(getGameCFG().getFightState());
        Thread thread = new Thread(() -> {
            try {
                fightModel.fight(Player.this,entity);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
    }
    public void interactNPC() {
        getGameCFG().setGameState(getGameCFG().getDialogueState());
        keyboardController.setUseObject(false);
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

    public void removePotion(String potionName){
        for (ObjectModel objectModel : inventory) {
            if(Objects.equals(objectModel.getName(), potionName)){
                getGameCFG().consume(objectModel);
                break;
            }
        }
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

    public ArrayList<ObjectModel> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<ObjectModel> inventory) {
        this.inventory = inventory;
    }
    public void addItem(ObjectModel objectModel){
        this.inventory.set(inventoryCapacity, objectModel);
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

    public ObjectModel getCurrentWeapon() {
        return currentWeapon;
    }

    public void setCurrentWeapon(ObjectModel currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public ObjectModel getCurrentHelmet() {
        return currentHelmet;
    }

    public void setCurrentHelmet(ObjectModel currentHelmet) {
        this.currentHelmet = currentHelmet;
    }

    public ObjectModel getCurrentChest() {
        return currentChest;
    }

    public void setCurrentChest(ObjectModel currentChest) {
        this.currentChest = currentChest;
    }

    public ObjectModel getCurrentBoots() {
        return currentBoots;
    }

    public void setCurrentBoots(ObjectModel currentBoots) {
        this.currentBoots = currentBoots;
    }
    public void addItemOnPlace(int id, ObjectModel objectModel){
        inventory.add(id , objectModel);
    }

    public void setInventoryCapacity(int inventoryCapacity) {
        this.inventoryCapacity = inventoryCapacity;
    }

    public int getInventoryCapacity() {
        return inventoryCapacity;
    }

    public int getHeathPotions() {
        return heathPotions;
    }

    public void setHeathPotions(int heathPotions) {
        this.heathPotions = heathPotions;
    }

    public int getManaPotions() {
        return manaPotions;
    }

    public void setManaPotions(int manaPotions) {
        this.manaPotions = manaPotions;
    }

    @Override
    public void setDefaultY(int defaultY) {
        super.setDefaultY(defaultY);
    }

    @Override
    public void setDefaultX(int defaultX) {
        super.setDefaultX(defaultX);
    }
}

