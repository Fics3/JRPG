package org.example.Entity;

import org.example.GamePanel;
import org.example.KeyboardController;
import org.example.UtilityTools;

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

    private int screenX;
    private int screenY;

    public Player(GamePanel gamePanel, KeyboardController keyboardController) throws IOException {
        this.gamePanel=gamePanel;
        Player.keyboardController =keyboardController;

        screenX=gamePanel.getScreenWight()/2-(gamePanel.getTileSize()/2);
        screenY=gamePanel.getScreenHeight()/2-(gamePanel.getTileSize()/2);

        solidArea = new Rectangle(8,16, (int) (gamePanel.getTileSize()/1.5), (int) (gamePanel.getTileSize()/1.5));

        setDafautl();
        getPlayerImage();
    }

    public void setDafautl(){
        setX(gamePanel.getMaxWorldWight()/2);
        setY(gamePanel.getMaxWorldHeight()/2);
        setSpeed(4);
        setDirection("down");
    }
    public void getPlayerImage() throws IOException {
        try {
            this.setDown1(setup("FWalk1"));
            this.setDownStay(setup("FStay"));
            this.setDown2(setup("FWalk2"));
            this.setUp1(setup("BWalk1"));
            this.setUpStay(setup("BStay"));
            this.setUp2(setup("BWalk2"));
            this.setLeft1(setup("LWalk1"));
            this.setLeftStay(setup("LStay"));
            this.setLeft2(setup("LWalk2"));
            this.setRight1(setup("RWalk1"));
            this.setRightStay(setup("RStay"));
            this.setRight2(setup("RWalk2"));

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public BufferedImage setup(String imageName) throws IOException {
        UtilityTools utilityTools = new UtilityTools();
        BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/"+imageName+".png")));
        BufferedImage scaledImage = null;

        scaledImage = utilityTools.scaleImage(image,gamePanel.getTileSize(),gamePanel.getTileSize());
        return scaledImage;
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
            collisionOn=false;
            gamePanel.getCollisionChecker().checkTile(this);

            if(collisionOn){
                switch (getDirection()){
                    case "up":setY(getY()+getSpeed()); break;
                    case "down":setY(getY()-getSpeed()); break;
                    case "right":setX(getX()-getSpeed()); break;
                    case "left":setX(getX()+getSpeed()); break;
                }
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

        graphics2D.drawImage(image,screenX,screenY,null);
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }
}
