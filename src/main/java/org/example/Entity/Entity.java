package org.example.Entity;

import org.example.GamePanel;
import org.example.UtilityTools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity {
    private int x;
    private int y;
    private int speed;
    private BufferedImage upStay, up1, up2,downStay, down1,down2,leftStay,left1,left2,rightStay,right1,right2;
    private String direction;
    private int spriteNum=2;
    private Rectangle solidArea= new Rectangle(0, 0 ,24,16);
    private int solidDefaultX, solidDefaultY;
    private boolean collisionOn=false;
    private GamePanel gamePanel;


    public Entity(GamePanel gamePanel){
        this.gamePanel = gamePanel;

    }
    public void setAction(){

    }
    public void update(){

        setCollisionOn(false);
        getGamePanel().getCollisionChecker().checkTile(this);
        getGamePanel().getCollisionChecker().checkObject(this,false);
        gamePanel.getCollisionChecker().checkPlayer(gamePanel.getPlayer());

        if(isCollisionOn()){
            switch (getDirection()){
                case "up":setY(getY()+getSpeed()); break;
                case "down":setY(getY()-getSpeed()); break;
                case "right":setX(getX()-getSpeed()); break;
                case "left":setX(getX()+getSpeed()); break;
            }
        }

    }


    public BufferedImage setup(String imageName) throws IOException {
        UtilityTools utilityTools = new UtilityTools();
        BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/NPC/"+imageName+".png")));
        return utilityTools.scaleImage(image,getGamePanel().getTileSize(),getGamePanel().getTileSize());
    }
    public BufferedImage setupPlayer(String imageName) throws IOException {
        UtilityTools utilityTools = new UtilityTools();
        BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/"+imageName+".png")));
        return utilityTools.scaleImage(image,getGamePanel().getTileSize(),getGamePanel().getTileSize());
    }
    public void draw(Graphics2D graphics2D)  {
        BufferedImage image = null;
        if (x + gamePanel.getTileSize() > gamePanel.getPlayer().getY() - gamePanel.getPlayer().getScreenY() &&
                y - gamePanel.getTileSize() < gamePanel.getPlayer().getY() + gamePanel.getPlayer().getScreenY() &&
                x + gamePanel.getTileSize() > gamePanel.getPlayer().getX() - gamePanel.getPlayer().getScreenX() &&
                y - gamePanel.getTileSize() < gamePanel.getPlayer().getX() + gamePanel.getPlayer().getScreenX()) {

            int screenX = x - gamePanel.getPlayer().getX() + gamePanel.getPlayer().getScreenX();
            int screenY = y - gamePanel.getPlayer().getY() + gamePanel.getPlayer().getScreenY();
            switch (getDirection()) {
                case "up":
                    if (spriteNum == 1) image = getUp1();
                    else if (spriteNum == 2) image = getUpStay();
                    else if (spriteNum == 3) image = getUp2();
                    break;
                case "down":
                    if (spriteNum == 1) image = getDown1();
                    else if (spriteNum == 2) image = getDownStay();
                    else if (spriteNum == 3) image = getDown2();
                    break;
                case "left":
                    if (spriteNum == 1) image = getLeft1();
                    else if (spriteNum == 2) image = getLeftStay();
                    else if (spriteNum == 3) image = getLeft2();
                    break;
                case "right":
                    if (spriteNum == 1) image = getRight1();
                    else if (spriteNum == 2) image = getRightStay();
                    else if (spriteNum == 3) image = getRight2();
                    break;
            }
            graphics2D.drawImage(image, screenX, screenY, null);
  }

    }
    public void setX(int x) {
        this.x = x;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }

    public boolean isCollisionOn() {
        return collisionOn;
    }

    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    public void setSpriteNum(int spriteNum) {
        this.spriteNum = spriteNum;
    }

    public int getSpriteNum() {
        return spriteNum;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setSolidArea(int i, int i1) {
        solidArea.x=i;
        solidArea.y=i1;
    }
    public void setSolidX(int i){
        solidArea.x=i;
    }
    public void setSolidY(int i){
        solidArea.y=i;
    }

    public int getSolidDefaultX() {
        return solidDefaultX;
    }

    public void setSolidDefaultX(int solidDefaultX) {
        this.solidDefaultX = solidDefaultX;
    }

    public int getSolidDefaultY() {
        return solidDefaultY;
    }

    public void setSolidDefaultY(int solidDefaultY) {
        this.solidDefaultY = solidDefaultY;
    }

    public boolean isCollision() {
        return collisionOn;
    }
}
