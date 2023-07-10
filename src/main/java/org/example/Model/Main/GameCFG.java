package org.example.Model.Main;

import org.example.Model.Entity.Entity;
import org.example.Model.Entity.Player;
import org.example.Model.FightModel;
import org.example.Model.Object;

import java.io.IOException;
import java.util.ArrayList;

public class GameCFG {
    private final int originalTileSize = 16;
    private final int scale = 3;
    private int tileSize= originalTileSize*scale;
    private int gameState;
    private final int adventureState = 1;
    private final int pauseState = 2;
    private final int dialogueState = 3;
    private final int fightState = 4;
    private final int statState = 5;
    private final int load = 6;
    private AssetsSetter assetsSetter = new AssetsSetter(this);
    private final int maxWorldCol=50;
    private final int maxWorldRow=50;
    private final int maxScreenCol=16;
    private final int maxScreenRow=12;
    private final int maxWorldWight=maxWorldCol*tileSize;
    private final int maxWorldHeight=maxWorldRow*tileSize;
    private int screenWight=getTileSize()*maxScreenCol;//768
    private int screenHeight=getTileSize()*maxScreenRow;//576

    private CollisionChecker collisionChecker ;

    private ArrayList<Object> objects = new ArrayList<>();
    private ArrayList<Entity> npcs = new ArrayList<>();
    private ArrayList<Entity> enemies = new ArrayList<>();
    KeyboardController keyboardController = new KeyboardController(this);
    Player player = new Player(this, keyboardController);
    FightModel fightModel;


    private String tmp="RAP";

    public GameCFG () throws IOException {
        collisionChecker = new CollisionChecker(this, player);
        assetsSetter.setObject();
        assetsSetter.setNpc();
        assetsSetter.setEnemy();
        setGameState(getAdventureState());
    }

    public void setupGame() throws IOException {
        setGameState(getAdventureState());
    }
        public void update(){
            if(getGameState() == getAdventureState()) {
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
            else if(getGameState() == getLoad()){
            }
            else if(getGameState() == getStatState()){

            }
        }

    public void setEnemy(Entity entity){
        this.enemies.add(entity);
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

    public ArrayList<Entity> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Entity> enemies) {
        this.enemies = enemies;
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

    public int getLoad() {
        return load;
    }

    public int getStatState() {
        return statState;
    }
}
