package org.example.View.EntityView;


import org.example.View.GamePanel;
import org.example.View.UtilityTools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class PlayerView extends EntityView {

    private int screenX;
    private int screenY;

    private String direction ="up";
    private int spriteNum=2;
    private int spriteCycle = 0;
    private int fightComNum = 0;

    private BufferedImage upStay, up1, up2,downStay, down1,down2,leftStay,left1,left2,rightStay,right1,right2;
    public PlayerView(GamePanel gamePanel) throws IOException {
        super(gamePanel);
        screenX=gamePanel.getGameCFG().getScreenWight()/2-(gamePanel.getGameCFG().getTileSize()/2);
        screenY=gamePanel.getGameCFG().getScreenHeight()/2-(gamePanel.getGameCFG().getTileSize()/2);
        getPlayerImage();
    }

    public void getPlayerImage() throws IOException {
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
    public BufferedImage setupPlayer(String imageName) throws IOException {
        UtilityTools utilityTools = new UtilityTools();
        BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/"+imageName+".png")));
        return utilityTools.scaleImage(image, getGamePanel().getGameCFG().getTileSize(),getGamePanel().getGameCFG().getTileSize());
    }
    public void draw(Graphics2D graphics2D){

        BufferedImage image = null;
        switch (getGamePanel().getGameCFG().getPlayer().getDirection()) {
            case "up":
                if(getGamePanel().getGameCFG().getPlayer().getSpriteNum()==1) image=getUp1();
                else if(getGamePanel().getGameCFG().getPlayer().getSpriteNum()==2)image=getUpStay();
                else if(getGamePanel().getGameCFG().getPlayer().getSpriteNum()==3)image=getUp2();
                break;
            case "down":
                if(getGamePanel().getGameCFG().getPlayer().getSpriteNum()==1) image=getDown1();
                else if(getGamePanel().getGameCFG().getPlayer().getSpriteNum()==2)image=getDownStay();
                else if(getGamePanel().getGameCFG().getPlayer().getSpriteNum()==3)image=getDown2();
                break;
            case "left":
                if(getGamePanel().getGameCFG().getPlayer().getSpriteNum()==1) image=getLeft1();
                else if(getGamePanel().getGameCFG().getPlayer().getSpriteNum()==2)image=getLeftStay();
                else if(getGamePanel().getGameCFG().getPlayer().getSpriteNum()==3)image=getLeft2();
                break;
            case "right" :
                if(getGamePanel().getGameCFG().getPlayer().getSpriteNum()==1) image=getRight1();
                else if(getGamePanel().getGameCFG().getPlayer().getSpriteNum()==2)image=getRightStay();
                else if(getGamePanel().getGameCFG().getPlayer().getSpriteNum()==3)image=getRight2();
                break;
        }
        graphics2D.drawImage(image, screenX, screenY,null);
    }
    public void setScreenX(int screenX) {
        this.screenX = screenX;
    }
    public int getScreenX() {
        return screenX;
    }

    public void setScreenY(int screenY) {
        this.screenY = screenY;
    }

    public int getScreenY() {
        return screenY;
    }

    public int getSpriteNum() {
        return spriteNum;
    }

    public void setSpriteNum(int spriteNum) {
        this.spriteNum = spriteNum;
    }
    public BufferedImage getUp1() {
        return up1;
    }

    public void setUp1(BufferedImage up1) {
        this.up1 = up1;
    }

    public BufferedImage getUp2() {
        return up2;
    }

    public void setUp2(BufferedImage up2) {
        this.up2 = up2;
    }

    public BufferedImage getDown1() {
        return down1;
    }

    public void setDown1(BufferedImage down1) {
        this.down1 = down1;
    }

    public BufferedImage getDown2() {
        return down2;
    }

    public void setDown2(BufferedImage down2) {
        this.down2 = down2;
    }

    public BufferedImage getLeft1() {
        return left1;
    }

    public void setLeft1(BufferedImage left1) {
        this.left1 = left1;
    }

    public BufferedImage getLeft2() {
        return left2;
    }

    public void setLeft2(BufferedImage left2) {
        this.left2 = left2;
    }

    public BufferedImage getRight1() {
        return right1;
    }

    public void setRight1(BufferedImage right1) {
        this.right1 = right1;
    }

    public BufferedImage getRight2() {
        return right2;
    }

    public void setRight2(BufferedImage right2) {
        this.right2 = right2;
    }

    public BufferedImage getUpStay() {
        return upStay;
    }

    public void setUpStay(BufferedImage upStay) {
        this.upStay = upStay;
    }

    public BufferedImage getDownStay() {
        return downStay;
    }

    public void setDownStay(BufferedImage downStay) {
        this.downStay = downStay;
    }

    public BufferedImage getLeftStay() {
        return leftStay;
    }

    public void setLeftStay(BufferedImage leftStay) {
        this.leftStay = leftStay;
    }

    public BufferedImage getRightStay() {
        return rightStay;
    }

    public void setRightStay(BufferedImage rightStay) {
        this.rightStay = rightStay;
    }

//    @Override
//    public String getDirection() {
//        return direction;
//    }
//
//    @Override
//    public void setDirection(String direction) {
//        this.direction = direction;
//    }
}
