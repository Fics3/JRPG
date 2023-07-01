package org.example.Objects;

import org.example.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Object {
    private BufferedImage image;
    private int id;
    private int X,Y;
    private boolean isVisible=false;

    public void draw(Graphics2D graphics2D, GamePanel gamePanel) {
        if(this.isVisible) {
            int worldX = X * gamePanel.getTileSize();
            int worldY = Y * gamePanel.getTileSize();
            if (worldY + gamePanel.getTileSize() > gamePanel.getPlayer().getY() - gamePanel.getPlayer().getScreenY() &&
                    worldY - gamePanel.getTileSize() < gamePanel.getPlayer().getY() + gamePanel.getPlayer().getScreenY() &&
                    worldX + gamePanel.getTileSize() > gamePanel.getPlayer().getX() - gamePanel.getPlayer().getScreenX() &&
                    worldX - gamePanel.getTileSize() < gamePanel.getPlayer().getX() + gamePanel.getPlayer().getScreenX()) {
                int screenX = worldX - gamePanel.getPlayer().getX() + gamePanel.getPlayer().getScreenX();
                int screenY = worldY - gamePanel.getPlayer().getY() + gamePanel.getPlayer().getScreenY();

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
}
