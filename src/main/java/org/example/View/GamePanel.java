package org.example.View;

import org.example.Model.Entity.Entity;
import org.example.View.EntityView.EntityView;
import org.example.View.EntityView.PlayerView;
import org.example.Model.Main.GameCFG;
import org.example.Model.Object.Object;
import org.example.View.Tiles.TileManager;
import org.example.View.UI.UI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

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
    private ArrayList<EntityView> entityViews = new ArrayList<>();
    private ArrayList<ObjectView> objectViews = new ArrayList<>();
    Thread gameThread;
    public GamePanel(){
        tileManager = new TileManager(this);
        ui =new UI(this);
        this.addKeyListener(gameCFG.getKeyboardController());
        this.setPreferredSize(new Dimension(gameCFG.getScreenWight(),gameCFG.getScreenHeight()));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void setupGame() throws IOException {

        for (Entity npc : gameCFG.getNpcs()) {
            entityView = new EntityView(this,npc);
            entityViews.add(entityView);
        }
        for (Object object: gameCFG.getObjects()) {
            objectView = new ObjectView(this,object);
            objectViews.add(objectView);
        }
        gameCFG.setGameState(gameCFG.getAdventureState());
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
        if (gameCFG.getGameState()==gameCFG.getLoadEntity()) {
            entityViews.clear();
            for (Entity npc : gameCFG.getNpcs()) {
                entityView = new EntityView(this, npc);
                entityViews.add(entityView);
            }
            getGameCFG().setGameState(getGameCFG().getAdventureState());
        }
        if( gameCFG.getGameState() == gameCFG.getLoadObjects()) {
            objectViews.clear();
            for (Object obj : gameCFG.getObjects()) {
                objectView = new ObjectView(this, obj);
                objectViews.add(objectView);
            }
            getGameCFG().setGameState(getGameCFG().getAdventureState());
        }
        if( gameCFG.getGameState() == gameCFG.getLoadInventory()) {
            playerView.getInventory().clear();
            for (Object object : gameCFG.getPlayer().getInventory()) {
                objectView = new ObjectView(this, object);
                playerView.getInventory().add(objectView);
            }
            getGameCFG().setGameState(getGameCFG().getStatState());
        }
    }


    public void paintComponent(Graphics graphics){

        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        tileManager.draw(graphics2D);
        if(gameCFG.getGameState()!=gameCFG.getFightState()) {
            for (ObjectView obj : objectViews) {
                obj.draw(graphics2D);
            }
//            System.out.println(entityViews.get(1));
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
            if(npc.getName()==name){
                return npc;
            }
        }
        return null;
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
