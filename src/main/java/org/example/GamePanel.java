package org.example;

import org.example.Entity.Entity;
import org.example.Entity.Player;
import org.example.Objects.Object;
import org.example.Tiles.TileManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;

public class GamePanel extends JPanel implements Runnable {

    private final int originalTileSize = 16;
    private final int scale = 3;
    private int tileSize= originalTileSize*scale;
    private final int maxScreenCol=16;
    private final int maxScreenRow=12;
    private final int screenWight=tileSize*maxScreenCol;//768
    private final int screenHeight=tileSize*maxScreenRow;//576

    private final int maxWorldCol=50;
    private final int maxWorldRow=50;
    private final int maxWorldWight=maxWorldCol*tileSize;
    private final int maxWorldHeight=maxWorldRow*tileSize;

    private int gameState;
    private final int adventureState = 1;
    private final int pauseState = 2;
    private final int fightState = 3;

    TileManager tileManager=new TileManager(this);

    private double maxFPS = 60;
    private KeyboardController keyboardController = new KeyboardController(this);
    private CollisionChecker collisionChecker=new CollisionChecker(this);
    private AssetsSetter assetsSetter = new AssetsSetter(this);
    private final UI ui = new UI(this);
    private LinkedList<Object> objects = new LinkedList<>();
    private LinkedList<Entity> npcs = new LinkedList<>();
   Thread gameThread;
   private Player player;
    {
        try {
            player = new Player(this,keyboardController);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public GamePanel() throws IOException {
        this.setPreferredSize(new Dimension(screenWight,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyboardController);
        this.setFocusable(true);

    }
    public void setupGame() throws IOException {
        assetsSetter.setObject();
        assetsSetter.setNpc();
        gameState = adventureState;
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
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
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
                if (remainingTime<0) remainingTime=0;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update(){
        if(gameState == adventureState) {
            player.update();
            for (Entity npc : npcs) {
                npc.update();
            }

        }
        if (gameState == pauseState){

        }

    }
    public void paintComponent(Graphics graphics){

        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        tileManager.draw(graphics2D);
        for (Object tmp : objects) {
                tmp.draw(graphics2D,this);
        }
        for (Entity npc : npcs) {
            npc.draw(graphics2D);
        }
        player.draw(graphics2D);
        ui.draw(graphics2D);
        graphics2D.dispose();
    }
    public void setTileSize(int value){
        tileSize=value;
    }
    public int getTileSize(){
        return tileSize;
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

    public Player getPlayer() {
        return player;
    }

    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }

    public void setCollisionChecker(CollisionChecker collisionChecker) {
        this.collisionChecker = collisionChecker;
    }

    public void setObjects(Object obj) {
        this.objects.add(obj);
    }

    public Object getObject(int id) {
        for (Object tmp : objects) {
            if(tmp.getId()==id){
                return tmp;
            }
        }
        return null;
    }

    public LinkedList<Object> getObjects() {
        return objects;
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

    public void setNpcs(Entity npcs) {
        this.npcs.add(npcs);
    }

    public LinkedList<Entity> getNpcs() {
        return npcs;
    }

    public TileManager getTileManager() {
        return tileManager;
    }
}
