package org.example.View;

import org.example.Model.Entity.Entity;
import org.example.View.EntityView.EntityView;
import org.example.View.EntityView.PlayerView;
import org.example.Model.Main.GameCFG;
import org.example.Model.Object.ObjectModel;
import org.example.View.Tiles.TileManager;
import org.example.View.UI.UI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class GamePanel extends JPanel implements Runnable {

    private final int maxScreenCol=16;
    private final int maxScreenRow=12;
    private double maxFPS = 60;
    private TileManager tileManager;
    private GameCFG gameCFG = new GameCFG();
    private UI ui;
    private PlayerView playerView = new PlayerView(this);
    private EntityView entityView;
    private ObjectView objectView;
    private LevelEditorView levelEditorView;

    private ArrayList<EntityView> entityViews = new ArrayList<>();
    private ArrayList<ObjectView> objectViews = new ArrayList<>();
    Thread gameThread;
    public GamePanel(){
        tileManager = new TileManager(this);
        ui =new UI(this);
        this.addKeyListener(gameCFG.getKeyboardController());
        this.setPreferredSize(new Dimension(gameCFG.getScreenWight(),gameCFG.getScreenHeight()));
        this.setBackground(new Color(99,155,255));
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void setupGame(){
        levelEditorView = new LevelEditorView(gameCFG.getLevelEditor(),this);
        entityViews.clear();
        for (Entity npc : gameCFG.getNpcs()) {
            entityView = new EntityView(this,npc);
            entityViews.add(entityView);
        }
        objectViews.clear();
        for (ObjectModel objectModel : gameCFG.getObjects()) {
            objectView = new ObjectView(this, objectModel);
            objectViews.add(objectView);
        }
    }
    @Override
    public void run() {
        double drawInterval = 1000000000 /maxFPS;
        double nextDrawTime = System.nanoTime()+drawInterval;
        while (gameThread!=null) {
                update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime/=1000000;
                if(remainingTime < 0) remainingTime=0;
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void update() {
        gameCFG.update();
        if(gameCFG.getGameState()==gameCFG.getLoadGame()){
            setupGame();
            gameCFG.setGameState(gameCFG.getAdventureState());
        }
        if(gameCFG.getGameState()==gameCFG.getLoadEntity()) {
            entityViews.clear();
            for (Entity npc : gameCFG.getNpcs()) {
                entityView = new EntityView(this, npc);
                entityViews.add(entityView);
            }
            getGameCFG().setGameState(getGameCFG().getAdventureState());
        }
        if( gameCFG.getGameState() == gameCFG.getLoadObjects()) {
            objectViews.clear();
            for (ObjectModel obj : gameCFG.getObjects()) {
                objectView = new ObjectView(this, obj);
                objectViews.add(objectView);
            }
            getGameCFG().setGameState(getGameCFG().getAdventureState());
        }
        if( gameCFG.getGameState() == gameCFG.getLoadInventory()) {
            playerView.getInventory().clear();
            for (ObjectModel objectModel : gameCFG.getPlayer().getInventory()) {
                objectView = new ObjectView(this, objectModel);
                playerView.getInventory().add(objectView);
            }
            getGameCFG().setGameState(getGameCFG().getStatState());
        }
        if( gameCFG.getKeyboardController().isLoadObj()){
            levelEditorView.getObjects();
            gameCFG.getKeyboardController().setLoadObj(false);
        }
    }


    public void paintComponent(Graphics graphics){

        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        if(gameCFG.getGameState()!=gameCFG.getLevelEditorState())tileManager.draw(graphics2D);
        if(gameCFG.getGameState()!=gameCFG.getFightState() && gameCFG.getGameState() != gameCFG.getLevelEditorState()) {
            for (ObjectView obj : objectViews) {
                obj.draw(graphics2D);
            }
            for (EntityView npc : entityViews) {
                npc.draw(graphics2D);
            }
            playerView.draw(graphics2D);
        }
        if(gameCFG.getGameState()==gameCFG.getStatState()){

        }
        ui.draw(graphics2D);
        graphics2D.dispose();
    }


    public int getMaxScreenCol() {
        return maxScreenCol;
    }

    public int getMaxScreenRow() {
        return maxScreenRow;
    }

//    public PlayerView getPlayerView() {
//        return playerView;
//    }

    public GameCFG getGameCFG() {
        return gameCFG;
    }

    public PlayerView getPlayerView() {
        return playerView;
    }

    public void setPlayerView(PlayerView playerView) {
        this.playerView = playerView;
    }

    public ArrayList<EntityView> getEntityViews() {
        return entityViews;
    }
    public EntityView getEntityView (String name){
        for (EntityView npc : entityViews) {
            if(Objects.equals(npc.getName(), name)){
                return npc;
            }
        }
        return null;
    }

    public LevelEditorView getLevelEditorView() {
        return levelEditorView;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public void setLevelEditorView(LevelEditorView levelEditorView) {
        this.levelEditorView = levelEditorView;
    }
    //    public FightView getFightView() {
//        return fightView;
//    }
    //    public void setNpcs(EntityView npcs) {
//        this.npcsView.add(npcs);
//    }
//
//    public ArrayList<EntityView> getNpcs() {
//        return npcsView;
//    }
//    public EntityView getNpc(int id){
//        for (EntityView npc : npcsView) {
////            if(npc.getId()==id){
//                return npc;
//            }
// //       }
//        return null;
//    }

//    public ArrayList<ObjectView> getObjectsView() {
//        return objectsView;
//    }
////    public ObjectView getObjectView(int id){
////        for (ObjectView obj : objectsView) {
////            if(obj.getId()==id){
////                return obj;
////            }
////        }
////dd
//
//    public void setObjectsView(ObjectView obj) {
//        this.objectsView.add(obj);
//    }
}
