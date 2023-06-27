package org.example.Entity;

import org.example.GamePanel;
import org.example.KeyboardController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    private GamePanel gamePanel;
    private static KeyboardController keyboardController;
    private int spriteCycle=0;
    private int spriteNum=1;

    public Player(GamePanel gamePanel, KeyboardController keyboardController) throws IOException {
        this.gamePanel=gamePanel;
        Player.keyboardController =keyboardController;
        setDafautl();
        getPlayerImage();
    }

    public void setDafautl(){
        setX(100);
        setY(100);
        setSpeed(4);
        setDirection("down");
    }
    public void getPlayerImage() throws IOException {
        try {
            this.setDown1(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/FWalk1.png"))));
            this.setDownStay(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/FStay.png"))));
            this.setDown2(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/FWalk2.png"))));
            this.setUp1(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/BWalk1.png"))));
            this.setUpStay(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/BStay.png"))));
            this.setUp2(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/BWalk2.png"))));
            this.setLeft1(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/LWalk1.png"))));
            this.setLeftStay(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/LStay.png"))));
            this.setLeft2(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/LWalk2.png"))));
            this.setRight1(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/RWalk1.png"))));
            this.setRightStay(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/RStay.png"))));
            this.setRight2(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/RWalk2.png"))));

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if(keyboardController.isDownPressed() || keyboardController.isLeftPressed() || keyboardController.isUpPressed() || keyboardController.isRightPressed()) {

            if (keyboardController.isUpPressed()) {
                setDirection("up");
                this.setY(this.getY() - this.getSpeed());
            } else if (keyboardController.isDownPressed()) {
                setDirection("down");
                this.setY(this.getY() + this.getSpeed());
            } else if (keyboardController.isRightPressed()) {
                setDirection("right");
                this.setX(this.getX() + this.getSpeed());
            } else if (keyboardController.isLeftPressed()) {
                setDirection("left");
                this.setX(this.getX() - this.getSpeed());
            }

            spriteCycle++;
            if (spriteCycle > 20) {
                if (spriteNum == 1) spriteNum = 2;
                else if (spriteNum == 2) spriteNum = 3;
                else if (spriteNum == 3) spriteNum = 1;
                spriteCycle = 0;
            }
        }

    }
    public void draw(Graphics2D graphics2D){
//        graphics2D.setColor(Color.cyan);
//        graphics2D.fillRect(getX(),getY(),gamePanel.getTileSize(),gamePanel.getTileSize());

        BufferedImage image = null;
        switch (getDirection()) {
            case "up":
                if(spriteNum==1) image=getUp1();
                else if(spriteNum==2)image=getUpStay();
                else if(spriteNum==3)image=getUp2();
                break;
            case "down":
                if(spriteNum==1) image=getDown1();
                else if(spriteNum==2)image=getDownStay();
                else if(spriteNum==3)image=getDown2();
                break;
            case "left":
                if(spriteNum==1) image=getLeft1();
                else if(spriteNum==2)image=getLeftStay();
                else if(spriteNum==3)image=getLeft2();
                break;
            case "right" :
                if(spriteNum==1) image=getRight1();
                else if(spriteNum==2)image=getRightStay();
                else if(spriteNum==3)image=getRight2();
                break;
        };

        graphics2D.drawImage(image,getX(),getY(),gamePanel.getTileSize(),gamePanel.getTileSize(),null);
    }
}
