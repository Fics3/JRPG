package org.example.Objects;

import org.example.GamePanel;
import org.example.Tiles.TileManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Object {
    private BufferedImage image;
    private int id;
    private int X,Y;
    private boolean collision;
    private boolean isVisible=false;
    private Rectangle solidArea=new Rectangle(0,0,24,16);
    private  int solidAreaDefaultX, solidAreaDefaultY;

    GamePanel gamePanel;

    public Object(GamePanel gamePanel) {
        this.gamePanel=gamePanel;
    }


    public void draw(Graphics2D graphics2D, GamePanel gamePanel) {
        if(this.isVisible) {
            if (Y + gamePanel.getTileSize() > gamePanel.getPlayer().getY() - gamePanel.getPlayer().getScreenY() &&
                    Y - gamePanel.getTileSize() < gamePanel.getPlayer().getY() + gamePanel.getPlayer().getScreenY() &&
                     X+ gamePanel.getTileSize() > gamePanel.getPlayer().getX() - gamePanel.getPlayer().getScreenX() &&
                    X - gamePanel.getTileSize() < gamePanel.getPlayer().getX() + gamePanel.getPlayer().getScreenX()) {
                int screenX = X - gamePanel.getPlayer().getX() + gamePanel.getPlayer().getScreenX();
                int screenY = Y - gamePanel.getPlayer().getY() + gamePanel.getPlayer().getScreenY();
                graphics2D.drawImage(image, screenX, screenY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
            }
        }
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getY() {
        return Y;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setSolidArea(int i, int i1) {
        solidArea.x=i;
        solidArea.y=i1;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    public void setSolidAreaDefaultX(int solidAreaDefaultX) {
        this.solidAreaDefaultX = solidAreaDefaultX;
    }

    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

    public void setSolidAreaDefaultY(int solidAreaDefaultY) {
        this.solidAreaDefaultY = solidAreaDefaultY;
    }

    public void setSolidAreaDefault(int i,int i1) {
        solidAreaDefaultX=i;
        solidAreaDefaultY=i1;
    }
}
