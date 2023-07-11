package org.example.View.EntityView;

import org.example.IO.InOut;
import org.example.Model.Entity.Entity;

import org.example.View.GamePanel;
import org.example.View.UtilityTools;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class EntityView {
    private int spriteNum=2;
    String name;
    private GamePanel gamePanel;
    private BufferedImage upStay, up1, up2,downStay, down1,down2,leftStay,left1,left2,rightStay,right1,right2;
    private Entity entity;
//    String name = "GreenBoy";

    public EntityView(GamePanel gamePanel) {
        this.gamePanel=gamePanel;
//        getImages(name);
//        setup(name);
    }
    public EntityView(GamePanel gamePanel, Entity entity) {
        this.gamePanel=gamePanel;
        this.entity=entity;
        getImages(entity.getName());
        setName(entity.getName());
    }

    public void draw(Graphics2D graphics2D)  {
        BufferedImage image = null;
        if (entity.getX() + gamePanel.getGameCFG().getTileSize() > gamePanel.getGameCFG().getPlayer().getX() -gamePanel.getPlayerView().getScreenX() &&
                entity.getY() - gamePanel.getGameCFG().getTileSize() < gamePanel.getGameCFG().getPlayer().getY() +gamePanel.getPlayerView().getScreenY() &&
                entity.getX() + gamePanel.getGameCFG().getTileSize() > gamePanel.getGameCFG().getPlayer().getX() -gamePanel.getPlayerView().getScreenX() &&
                entity.getY() - gamePanel.getGameCFG().getTileSize() < gamePanel.getGameCFG().getPlayer().getY() +gamePanel.getPlayerView().getScreenY()) {

            int screenX = entity.getX() - gamePanel.getGameCFG().getPlayer().getX() +gamePanel.getPlayerView().getScreenX();
            int screenY = entity.getY() - gamePanel.getGameCFG().getPlayer().getY() +gamePanel.getPlayerView().getScreenY();
            switch (entity.getDirection()) {
                case "up":
                    if (entity.getSpriteNum() == 1) image = getUp1();
                    else if (entity.getSpriteNum() == 2) image = getUpStay();
                    else if (entity.getSpriteNum() == 3) image = getUp2();
                    break;
                case "down":
                    if (entity.getSpriteNum() == 1) image = getDown1();
                    else if (entity.getSpriteNum() == 2) image = getDownStay();
                    else if (entity.getSpriteNum() == 3) image = getDown2();
                    break;
                case "left":
                    if (entity.getSpriteNum() == 1) image = getLeft1();
                    else if (entity.getSpriteNum() == 2) image = getLeftStay();
                    else if (entity.getSpriteNum() == 3) image = getLeft2();
                    break;
                case "right":
                    if (entity.getSpriteNum() == 1) image = getRight1();
                    else if (entity.getSpriteNum() == 2) image = getRightStay();
                    else if (entity.getSpriteNum() == 3) image = getRight2();
                    break;
            }
            graphics2D.drawImage(image, screenX, screenY, null);
        }


    }
    public BufferedImage setup(String imageName){
        UtilityTools utilityTools = new UtilityTools();
        InOut inOut = new InOut();
        return utilityTools.scaleImage(inOut.setup(imageName),gamePanel.getGameCFG().getTileSize(),gamePanel.getGameCFG().getTileSize());
    }
    public void getImages(String name) {
            this.setDown1(setup(name+"_FWalk1"));
            this.setDownStay(setup(name+"_FWalkStay"));
            this.setDown2(setup(name+"_FWalk2"));
            this.setUp1(setup(name+"_BWalk1"));
            this.setUpStay(setup(name+"_BWalkStay"));
            this.setUp2(setup(name+"_BWalk2"));
            this.setLeft1(setup(name+"_LWalk1"));
            this.setLeftStay(setup(name+"_LWalkStay"));
            this.setLeft2(setup(name+"_LWalk2"));
            this.setRight1(setup(name+"_RWalk1"));
            this.setRightStay(setup(name+"_RWalkStay"));
            this.setRight2(setup(name+"_RWalk2"));

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

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
