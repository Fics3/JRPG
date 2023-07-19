package org.example.Model.Main;

import org.example.IO.InOut;
import org.example.IO.SaveLoadWorld;
import org.example.Model.Entity.Entity;
import org.example.Model.Entity.Player;
import org.example.Model.Object.ObjectModel;
import org.example.Model.PathFinder;

import java.util.ArrayList;
import java.util.Arrays;

public class GameCFG {
    private String name ="world";
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

    private int maxWorldCol=20;
    private int maxWorldRow=20;
    private final int maxScreenCol=16;
    private final int maxScreenRow=12;
    private final int maxWorldWight=maxWorldCol*tileSize;
    private final int maxWorldHeight=maxWorldRow*tileSize;
    private int screenWight=getTileSize()*maxScreenCol;//768
    private int screenHeight=getTileSize()*maxScreenRow;//576

    private ArrayList<ObjectModel> objectModels = new ArrayList<>();
    private ArrayList<Entity> npcs = new ArrayList<>();

    private CollisionChecker collisionChecker ;
    private AssetsSetter assetsSetter = new AssetsSetter(this);
    private final KeyboardController keyboardController = new KeyboardController(this);
    private LevelEditor levelEditor = new LevelEditor(this);
    PathFinder pathFinder ;
    private Player player;

    SaveLoadWorld saveLoadWorld = new SaveLoadWorld(this);
    private int[][] mapDataNum;
    private int[][] dataMap;

    public GameCFG (){
        player = new Player(this,keyboardController);
        saveLoadWorld.load("src/main/resources/Saves/world");
        setGameState(getTitleState());
    }

    public void setupGame() {
        setName("world");
        saveLoadWorld.load("src/main/resources/Saves/"+name);
        pathFinder = new PathFinder(this);
        objectModels.clear();
        npcs.clear();
        assetsSetter.setObject();
        player = new Player(this, keyboardController);
        assetsSetter = new AssetsSetter(this);
        collisionChecker = new CollisionChecker(this, player);
        player.setX(maxWorldWight/2-getTileSize());
        player.setY(maxWorldHeight/2-getTileSize());
        gameState = loadGame;
    }
    public void loadCustomGame(){
        setName("custom");
        player = new Player(this, keyboardController);
        collisionChecker = new CollisionChecker(this,player);
        assetsSetter.setMapColData(dataMap);
        collisionChecker.setMapColData(dataMap);
        objectModels.clear();
        npcs.clear();
        assetsSetter.setObject();
        player.setX(maxWorldWight/2-getTileSize());
        player.setY(maxWorldHeight/2-getTileSize());
        collisionChecker.setMapColData(dataMap);
        pathFinder = new PathFinder(this);
        pathFinder.setMapColData(dataMap);
        gameState=loadGame;
    }
    public void loadGame(){
        collisionChecker = new CollisionChecker(this, player);
        pathFinder = new PathFinder(this);
//        objectModels.clear();
//        npcs.clear();
//        assetsSetter.setObject();
        player.setX(player.getDefaultX());
        player.setY(player.getDefaultY());
        collisionChecker.setMapColData(dataMap);
        pathFinder.setMapColData(dataMap);
        gameState = loadGame;
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
                    ObjectModel curObjectModel = player.getInventory().get(player.getInventorySlot());
                    if(keyboardController.isUse()){
                        if(curObjectModel.isConsumable()){
                            consume(curObjectModel);
                            setGameState(loadInventory);
                        }
                        if(curObjectModel.isWeapon()){
                            equipWeapon(curObjectModel);
                        }
                        if(curObjectModel.isHelmet()){
                            equipHelmet(curObjectModel);
                        }
                        if(curObjectModel.isChestplate()){
                            equipChest(curObjectModel);
                        }
                        if(curObjectModel.isBoots()){
                            equipBoots(curObjectModel);
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
    public ObjectModel getObjectWithId(int id ){
        for (ObjectModel objectModel : objectModels) {
            if(objectModel.getId()==id){
                return objectModel;
            }
        }
        return null;
    }
    public void equipHelmet(ObjectModel curObjectModel){
        if (player.getCurrentHelmet() != null) {
            player.setMaxHP(player.getMaxHP()-player.getCurrentHelmet().getGainHP());
            player.addItemOnPlace(player.getInventorySlot(), player.getCurrentHelmet());
            player.getInventory().remove(curObjectModel);
            player.setCurrentHelmet(curObjectModel);
            setGameState(loadInventory);
            player.setMaxHP(getPlayer().getMaxHP() + player.getCurrentHelmet().getGainHP());
        }
        else{
            player.getInventory().remove(curObjectModel);
            player.setCurrentHelmet(curObjectModel);
            player.getInventory().add(new ObjectModel(this));
            player.setInventoryCapacity(player.getInventoryCapacity()-1);
            setGameState(loadInventory);
            player.setMaxHP(getPlayer().getMaxHP() + player.getCurrentHelmet().getGainHP());
        }
    }
    public void consume(ObjectModel objectModel){
        objectModel.consume();
        player.getInventory().remove(objectModel);
        player.setInventoryCapacity(player.getInventoryCapacity()-1);
        player.getInventory().add(new ObjectModel(this));
    }
    public void equipChest(ObjectModel curObjectModel){
        if (player.getCurrentChest() != null) {
            player.setMaxHP(player.getMaxHP()-player.getCurrentChest().getGainHP());
            player.addItemOnPlace(player.getInventorySlot(), player.getCurrentChest());
            player.getInventory().remove(curObjectModel);
            player.setCurrentChest(curObjectModel);
            setGameState(loadInventory);
            player.setMaxHP(getPlayer().getMaxHP() + player.getCurrentChest().getGainHP());
        }
        else{
            player.getInventory().remove(curObjectModel);
            player.setCurrentChest(curObjectModel);
            player.getInventory().add(new ObjectModel(this));
            player.setInventoryCapacity(player.getInventoryCapacity()-1);
            setGameState(loadInventory);
            player.setMaxHP( getPlayer().getMaxHP() + player.getCurrentChest().getGainHP());
        }
    }
    public void equipBoots(ObjectModel curObjectModel){
        if (player.getCurrentBoots() != null) {
            player.setMaxHP(player.getMaxHP()-player.getCurrentBoots().getGainHP());
            player.addItemOnPlace(player.getInventorySlot(), player.getCurrentBoots());
            player.getInventory().remove(curObjectModel);
            player.setCurrentBoots(curObjectModel);
            setGameState(loadInventory);
            player.setMaxHP( getPlayer().getMaxHP() + player.getCurrentBoots().getGainHP());
        }
        else{
            player.getInventory().remove(curObjectModel);
            player.setCurrentBoots(curObjectModel);
            player.getInventory().add( new ObjectModel(this));
            player.setInventoryCapacity(player.getInventoryCapacity()-1);
            setGameState(loadInventory);
            player.setMaxHP(getPlayer().getMaxHP() + player.getCurrentBoots().getGainHP());
        }
    }
    public void equipWeapon(ObjectModel curObjectModel){
        if(player.getCurrentWeapon()!=null) {
            player.addItemOnPlace(player.getInventorySlot(), player.getCurrentWeapon());
            player.getInventory().remove(curObjectModel);
            player.setCurrentWeapon(curObjectModel);
            setGameState(loadInventory);
            player.setDamage(getPlayer().getLvl() + player.getCurrentWeapon().getDamage());
        }
        else {
            player.getInventory().remove(curObjectModel);
            player.setCurrentWeapon(curObjectModel);
            player.getInventory().add(new ObjectModel(this));
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

    public ArrayList<ObjectModel> getObjects() {
        return objectModels;
    }
    public ObjectModel getObject(int obj){
        return objectModels.get(obj);
    }

    public void setObjects(ArrayList<ObjectModel> objectModels) {
        this.objectModels = objectModels;
    }

    public ArrayList<Entity> getNpcs() {
        return npcs;
    }
    public void setObject(ObjectModel objectModel){
        objectModels.add(objectModel);
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
        objectModels.set(id,null);
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

    public void setMaxWorldCol(int maxWorldCol) {
        this.maxWorldCol = maxWorldCol;
    }

    public void setMaxWorldRow(int maxWorldRow) {
        this.maxWorldRow = maxWorldRow;
    }

    public int[][] getMapDataNum() {
        return mapDataNum;
    }

    public void setMapDataNum(int[][] mapDataNum) {
        this.mapDataNum = mapDataNum;
    }

    public String getName() {
        return name;
    }

    public int[][] getDataMap() {
        return dataMap;
    }

    public void setDataMap(int[][] dataMap) {
        this.dataMap = dataMap;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PathFinder getPathFinder() {
        return pathFinder;
    }

    public void setPathFinder(PathFinder pathFinder) {
        this.pathFinder = pathFinder;
    }
}
