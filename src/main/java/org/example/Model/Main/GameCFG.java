package org.example.Model.Main;

import org.example.Model.Entity.Entity;
import org.example.Model.Entity.Player;
import org.example.Model.Object.Object;

import java.util.ArrayList;

public class GameCFG {
    private final int originalTileSize = 16;
    private final int scale = 3;
    private int tileSize= originalTileSize*scale;
    private int gameState;
    private final int titleState = 0;
    private final int adventureState = 1;
    private final int pauseState = 2;
    private final int dialogueState = 3;
    private final int fightState = 4;
    private final int statState = 5;
    private final int optionsState = 6;
    private final int gameOverState = 7;
    private final int loadEntity = 8;
    private final int loadObjects = 9;
    private final int loadInventory = 10;
    private final int loadGame = 11;
    private final int levelEditorState = 12;

    private final int maxWorldCol=50;
    private final int maxWorldRow=50;
    private final int maxScreenCol=16;
    private final int maxScreenRow=12;
    private final int maxWorldWight=maxWorldCol*tileSize;
    private final int maxWorldHeight=maxWorldRow*tileSize;
    private int screenWight=getTileSize()*maxScreenCol;//768
    private int screenHeight=getTileSize()*maxScreenRow;//576

    private ArrayList<Object> objects = new ArrayList<>();
    private ArrayList<Entity> npcs = new ArrayList<>();

    private CollisionChecker collisionChecker ;
    private AssetsSetter assetsSetter = new AssetsSetter(this);
    private final KeyboardController keyboardController = new KeyboardController(this);
    private LevelEditor levelEditor = new LevelEditor(this);
    ;
    private Player player;


    private String tmp="RAP";

    public GameCFG (){
        setupGame();
        setGameState(getTitleState());
    }

    public void setupGame() {
        objects.clear();
        npcs.clear();
        assetsSetter.setObject();
//        assetsSetter.setNpc();
//        assetsSetter.setEnemy();
        player = new Player(this, keyboardController);
        collisionChecker = new CollisionChecker(this, player);
    }
    public void loadGame(){
        player = new Player(this, keyboardController);
        collisionChecker = new CollisionChecker(this, player);
    }
        public void update(){
        if(getGameState()==getTitleState()){

        }
        else if(getGameState() == getAdventureState()) {
            player.update();
                for (Entity npc : npcs) {
                    npc.update();
                }
        }
        else if (getGameState() == getPauseState()){
        }
        else if (getGameState() == getFightState()){
        }
        else if(getGameState() == getDialogueState()){
        }
        else if(getGameState() == getStatState()){
                if(player.getInventory().get(player.getInventorySlot()).getName()!=null) {
                    Object curObject = player.getInventory().get(player.getInventorySlot());
                    if(keyboardController.isUse()){
                        if(curObject.isConsumable()){
                            consume(curObject);
                            setGameState(loadInventory);
                        }
                        if(curObject.isWeapon()){
                            equipWeapon(curObject);
                        }
                        if(curObject.isHelmet()){
                            equipHelmet(curObject);
                        }
                        if(curObject.isChestplate()){
                            equipChest(curObject);
                        }
                        if(curObject.isBoots()){
                            equipBoots(curObject);
                        }

                    }
                }
                keyboardController.setUse(false);
            }
        else if(getGameState() == getGameOverState()) {
                if (keyboardController.isRespawn()) {
                    for (Entity entity : npcs) {
                        if (entity.getLvl() < 10 && entity.isEnemy()) {
                            entity.lvlUp();
                            entity.defaultPosition();

                        }
                    }
                    int tmpLvl = player.getLvl();
                    player.setDafautl();
                    player.setLvl(tmpLvl);
                    setGameState(getAdventureState());
                    keyboardController.setRespawn(false);
                }
            }
        else if(getGameState() == getLevelEditorState()){
            if(keyboardController.isEditor()){
            }
        }
        }
    public Object getObjectWithId(int id ){
        for (Object object : objects) {
            if(object.getId()==id){
                return object;
            }
        }
        return null;
    }
    public void equipHelmet(Object curObject){
        if (player.getCurrentHelmet() != null) {
            player.setMaxHP(player.getMaxHP()-player.getCurrentHelmet().getGainHP());
            player.addItemOnPlace(player.getInventorySlot(), player.getCurrentHelmet());
            player.getInventory().remove(curObject);
            player.setCurrentHelmet(curObject);
            setGameState(loadInventory);
            player.setMaxHP(getPlayer().getMaxHP() + player.getCurrentHelmet().getGainHP());
        }
        else{
            player.getInventory().remove(curObject);
            player.setCurrentHelmet(curObject);
            player.getInventory().add(new Object(this));
            player.setInventoryCapacity(player.getInventoryCapacity()-1);
            setGameState(loadInventory);
            player.setMaxHP(getPlayer().getMaxHP() + player.getCurrentHelmet().getGainHP());
        }
    }
    public void consume(Object object){
        object.consume();
        player.getInventory().remove(object);
        player.setInventoryCapacity(player.getInventoryCapacity()-1);
        player.getInventory().add(new Object(this));
    }
    public void equipChest(Object curObject){
        if (player.getCurrentChest() != null) {
            player.setMaxHP(player.getMaxHP()-player.getCurrentChest().getGainHP());
            player.addItemOnPlace(player.getInventorySlot(), player.getCurrentChest());
            player.getInventory().remove(curObject);
            player.setCurrentChest(curObject);
            setGameState(loadInventory);
            player.setMaxHP(getPlayer().getMaxHP() + player.getCurrentChest().getGainHP());
        }
        else{
            player.getInventory().remove(curObject);
            player.setCurrentChest(curObject);
            player.getInventory().add(new Object(this));
            player.setInventoryCapacity(player.getInventoryCapacity()-1);
            setGameState(loadInventory);
            player.setMaxHP( getPlayer().getMaxHP() + player.getCurrentChest().getGainHP());
        }
    }
    public void equipBoots(Object curObject){
        if (player.getCurrentBoots() != null) {
            player.setMaxHP(player.getMaxHP()-player.getCurrentBoots().getGainHP());
            player.addItemOnPlace(player.getInventorySlot(), player.getCurrentBoots());
            player.getInventory().remove(curObject);
            player.setCurrentBoots(curObject);
            setGameState(loadInventory);
            player.setMaxHP( getPlayer().getMaxHP() + player.getCurrentBoots().getGainHP());
        }
        else{
            player.getInventory().remove(curObject);
            player.setCurrentBoots(curObject);
            player.getInventory().add( new Object(this));
            player.setInventoryCapacity(player.getInventoryCapacity()-1);
            setGameState(loadInventory);
            player.setMaxHP(getPlayer().getMaxHP() + player.getCurrentBoots().getGainHP());
        }
    }
    public void equipWeapon(Object curObject){
        if(player.getCurrentWeapon()!=null) {
            player.addItemOnPlace(player.getInventorySlot(), player.getCurrentWeapon());
            player.getInventory().remove(curObject);
            player.setCurrentWeapon(curObject);
            setGameState(loadInventory);
            player.setDamage(getPlayer().getLvl() + player.getCurrentWeapon().getDamage());
        }
        else {
            player.getInventory().remove(curObject);
            player.setCurrentWeapon(curObject);
            player.getInventory().add(new Object(this));
            player.setInventoryCapacity(player.getInventoryCapacity()-1);
            setGameState(loadInventory);
            player.setDamage(getPlayer().getLvl() + player.getCurrentWeapon().getDamage());
        }
    }
    public int getGameState() {
        return gameState;
    }

    public void setGameState(int State) {
        this.gameState = State;
    }

    public int getAdventureState() {
        return adventureState;
    }

    public int getFightState() {
        return fightState;
    }

    public int getPauseState() {
        return pauseState;
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public int getDialogueState() {
        return dialogueState;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }
    public int getMaxWorldHeight() {
        return maxWorldHeight;
    }

    public int getMaxWorldWight() {
        return maxWorldWight;
    }

    public int getMaxWorldCol() {
        return maxWorldCol;
    }

    public int getMaxWorldRow() {
        return maxWorldRow;
    }

    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }

    public void setCollisionChecker(CollisionChecker collisionChecker) {
        this.collisionChecker = collisionChecker;
    }

    public ArrayList<Object> getObjects() {
        return objects;
    }
    public Object getObject(int obj){
        return objects.get(obj);
    }

    public void setObjects(ArrayList<Object> objects) {
        this.objects = objects;
    }

    public ArrayList<Entity> getNpcs() {
        return npcs;
    }
    public void setObject(Object object){
        objects.add(object);
    }

    public void setNpcs(Entity npc) {
        this.npcs.add(npc);
    }
    public Entity getNpc(int id){
        for (Entity npc : npcs) {
            if(npc.getId() == id){
                return npc;
            }
        }
        return null;
    }

    public AssetsSetter getAssetsSetter() {
        return assetsSetter;
    }

    public void setAssetsSetter(AssetsSetter assetsSetter) {
        this.assetsSetter = assetsSetter;
    }

    public Player getPlayer() {
        return player;
    }
    public KeyboardController getKeyboardController() {
        return keyboardController;
    }

    public int getScreenWight() {
        return screenWight;
    }

    public void setScreenWight(int screenWight) {
        this.screenWight = screenWight;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }
    public void deleteNpc(Entity entity){
        npcs.remove(entity);
    }
    public int getStatState() {
        return statState;
    }
    public void setObject(int id){
        objects.set(id,null);
    }

    public int getLoadObjects() {
        return loadObjects;
    }

    public int getLoadInventory() {
        return loadInventory;
    }

    public int getLoadEntity() {
        return loadEntity;
    }

    public int getGameOverState() {
        return gameOverState;
    }

    public int getTitleState() {
        return titleState;
    }

    public int getOptionsState() {
        return optionsState;
    }

    public int getLoadGame() {
        return loadGame;
    }

    public int getLevelEditorState() {
        return levelEditorState;
    }

    public LevelEditor getLevelEditor() {
        return levelEditor;
    }

    public void setLevelEditor(LevelEditor levelEditor) {
        this.levelEditor = levelEditor;
    }
}
