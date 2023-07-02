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

    private static KeyboardController keyboardController;
    private int spriteCycle=0;

    private int screenX;
    private int screenY;

    public Player(GamePanel gamePanel, KeyboardController keyboardController) throws IOException {
        super(gamePanel);

        Player.keyboardController =keyboardController;

        screenX=gamePanel.getScreenWight()/2-(gamePanel.getTileSize()/2);
        screenY=gamePanel.getScreenHeight()/2-(gamePanel.getTileSize()/2);

        setSolidDefaultX(getSolidArea().x);
        setSolidDefaultY(getSolidArea().y);

        setSolidArea(new Rectangle(8,16, (int) (gamePanel.getTileSize()/1.5), (int) (gamePanel.getTileSize()/1.5)));

        setDafautl();
        getPlayerImage();
    }

    public void setDafautl(){
        setX(getGamePanel().getMaxWorldWight()/2);
        setY(getGamePanel().getMaxWorldHeight()/2);
        setSpeed(4);
        setDirection("down");
    }
    public void getPlayerImage() throws IOException {
        try {
            this.setDown1(setupPlayer("FWalk1"));
            this.setDownStay(setupPlayer("FStay"));
            this.setDown2(setupPlayer("FWalk2"));
            this.setUp1(setupPlayer("BWalk1"));
            this.setUpStay(setupPlayer("BStay"));
            this.setUp2(setupPlayer("BWalk2"));
            this.setLeft1(setupPlayer("LWalk1"));
            this.setLeftStay(setupPlayer("LStay"));
            this.setLeft2(setupPlayer("LWalk2"));
            this.setRight1(setupPlayer("RWalk1"));
            this.setRightStay(setupPlayer("RStay"));
            this.setRight2(setupPlayer("RWalk2"));

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
//    public BufferedImage setup(String imageName) throws IOException {
//        UtilityTools utilityTools = new UtilityTools();
//        BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/"+imageName+".png")));
//        BufferedImage scaledImage = null;
//
//        scaledImage = utilityTools.scaleImage(image,getGamePanel().getTileSize(),getGamePanel().getTileSize());
//        return scaledImage;
//    }
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
            setCollisionOn(false);
            getGamePanel().getCollisionChecker().checkTile(this);

            getGamePanel().getCollisionChecker().checkObject(this,true);

            getGamePanel().getCollisionChecker().checkEntity(this, getGamePanel().getNpcs());

            if(isCollisionOn()){
                switch (getDirection()){
                    case "up":setY(getY()+getSpeed()); break;
                    case "down":setY(getY()-getSpeed()); break;
                    case "right":setX(getX()-getSpeed()); break;
                    case "left":setX(getX()+getSpeed()); break;
                }
            }

            spriteCycle++;
            if (spriteCycle > 20) {
                if (getSpriteNum() == 1) setSpriteNum(2);
                else if (getSpriteNum() == 2) setSpriteNum(3);
                else if (getSpriteNum() == 3) setSpriteNum(1);
                spriteCycle = 0;
            }
        }

    }
    public void draw(Graphics2D graphics2D){

        BufferedImage image = null;
        switch (getDirection()) {
            case "up":
                if(getSpriteNum()==1) image=getUp1();
                else if(getSpriteNum()==2)image=getUpStay();
                else if(getSpriteNum()==3)image=getUp2();
                break;
            case "down":
                if(getSpriteNum()==1) image=getDown1();
                else if(getSpriteNum()==2)image=getDownStay();
                else if(getSpriteNum()==3)image=getDown2();
                break;
            case "left":
                if(getSpriteNum()==1) image=getLeft1();
                else if(getSpriteNum()==2)image=getLeftStay();
                else if(getSpriteNum()==3)image=getLeft2();
                break;
            case "right" :
                if(getSpriteNum()==1) image=getRight1();
                else if(getSpriteNum()==2)image=getRightStay();
                else if(getSpriteNum()==3)image=getRight2();
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
