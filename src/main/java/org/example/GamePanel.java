package org.example;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize= originalTileSize*scale;
    final int maxScreenCol=16;
    final int maxScreenRow=12;
    final int screenWight=tileSize*maxScreenCol;//768
    final int screenHeight=tileSize*maxScreenRow;//576

    int playerX=100;


    int playerY=100;
    int playerSpeed=4;

    double maxFPS = 60;

    KeyboardController keyboardController = new KeyboardController();

   Thread gameThread;
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


        if(keyboardController.upPressed){
            playerY-=playerSpeed;
        }
        else if(keyboardController.downPressed){
            playerY+=playerSpeed;
        }
        else if(keyboardController.rightPressed){
            playerX+=playerSpeed;
        }
        else if(keyboardController.leftPressed){
            playerX-=playerSpeed;
        }
    }
    public void paintComponent(Graphics graphics){

        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.cyan);
        graphics2D.fillRect(playerX,playerY,tileSize,tileSize);
        graphics2D.dispose();
    }
}
