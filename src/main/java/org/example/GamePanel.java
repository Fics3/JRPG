package org.example;

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

    TileManager tileManager=new TileManager(this);

    private double maxFPS = 60;
    private KeyboardController keyboardController = new KeyboardController();
    private CollisionChecker collisionChecker=new CollisionChecker(this);
    private AssetsSetter assetsSetter = new AssetsSetter(this);
    private LinkedList<Object> objects = new LinkedList<>();
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
        player.update();
    }
    public void paintComponent(Graphics graphics){

        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        tileManager.draw(graphics2D);

        for (Object tmp : objects) {
                tmp.draw(graphics2D,this);
        }

        player.draw(graphics2D);
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

    public Object getObjects(int id) {
        for (Object tmp : objects) {
            if(tmp.getId()==id){
                return tmp;
            }
        }
        return null;
    }
}
