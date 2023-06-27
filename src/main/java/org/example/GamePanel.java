package org.example;

import org.example.Entity.Player;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {

    private final int originalTileSize = 16;
    private final int scale = 3;
    private int tileSize= originalTileSize*scale;
    private final int maxScreenCol=16;
    private final int maxScreenRow=12;
    private final int screenWight=tileSize*maxScreenCol;//768
    private final int screenHeight=tileSize*maxScreenRow;//576

    private int playerX=100;

    private int playerY=100;
    private int playerSpeed=3;

    private double maxFPS = 60;
    KeyboardController keyboardController = new KeyboardController();

   Thread gameThread;
   private Player player;
    {
        try {
            player = new Player(this,keyboardController);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWight,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyboardController);
        this.setFocusable(true);


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
        player.draw(graphics2D);
        graphics2D.dispose();
    }
    public void setTileSize(int value){
        tileSize=value;
    }
    public int getTileSize(){
        return tileSize;
    }
}
