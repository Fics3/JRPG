package org.example.Main;

import org.example.Entity.Entity.Entity;
import org.example.Objects.Object;
import org.example.Tiles.TileManager;
import org.example.UI.UI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {

    private final int maxScreenCol=16;
    private final int maxScreenRow=12;
    private int screenWight;
    private int screenHeight;

//    private ArrayList<ObjectView> objectsView = new ArrayList<>();
//    private ArrayList<EntityView> npcsView = new ArrayList<>();


    private double maxFPS = 60;
//    private AssetsSetterView assetsSetterView ;
    private TileManager tileManager;
    private GameCFG gameCFG = new GameCFG();
    private UI ui =new UI(gameCFG);
    Thread gameThread;
    public GamePanel() throws IOException {
//        assetsSetterView= new AssetsSetterView(this,gameCFG);
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
//        assetsSetterView.setObjectView();
//        assetsSetterView.setNpcView();
        gameCFG.setGameState(gameCFG.getAdventureState());
    }
    @Override
    public void run() {
        double drawInterval = 1000000000 /maxFPS;
        double nextDrawTime = System.nanoTime()+drawInterval;
        while (gameThread!=null) {
            try {
                update();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime/=1000000;
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
                if (remainingTime<0) remainingTime=0;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void update() throws IOException {
//            getGameCFG().update();
//            System.out.println(playerView.getX());
            gameCFG.getPlayer().update();
//            for (EntityView npc : npcsView) {
//                npc.update();
//            }
    }
    public void paintComponent(Graphics graphics){

        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        gameCFG.getTileManager().draw(graphics2D);
        for (Object obj : gameCFG.getObjects()) {
                obj.getObjectView().draw(graphics2D,obj);
        }
        for (Entity npc : gameCFG.getNpcs()) {
            npc.getEntityView().draw(graphics2D);
        }
        gameCFG.getPlayer().getPlayerView().draw(graphics2D);
        try {
            ui.draw(graphics2D);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        graphics2D.dispose();
    }
    public int getScreenWight() {
        return screenWight;
    }

    public int getScreenHeight() {
        return screenHeight;
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
