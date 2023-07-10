package org.example.View;

import org.example.Model.Object;
import org.example.View.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class ObjectView {
    private BufferedImage image;
    private GamePanel gamePanel;
    Object object;

    public ObjectView(GamePanel gamePanel, Object object) throws IOException {
        this.gamePanel=gamePanel;
        this.object=object;
        if(Objects.equals(object.getName(), "chest")) {
            setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Objects/chest.png"))));
        }
    }
    public void draw(Graphics2D graphics2D) {
        if (object.getX()+gamePanel.getGameCFG().getTileSize() > gamePanel.getGameCFG().getPlayer().getY() - gamePanel.getPlayerView().getScreenY() &&
                object.getY()-gamePanel.getGameCFG().getTileSize()< gamePanel.getGameCFG().getPlayer().getY() + gamePanel.getPlayerView().getScreenY() &&
                object.getX()+gamePanel.getGameCFG().getTileSize() > gamePanel.getGameCFG().getPlayer().getX() - gamePanel.getPlayerView().getScreenX() &&
                object.getY() -gamePanel.getGameCFG().getTileSize()< gamePanel.getGameCFG().getPlayer().getX() + gamePanel.getPlayerView().getScreenX()) {
                int screenX = object.getX() - gamePanel.getGameCFG().getPlayer().getX() + gamePanel.getPlayerView().getScreenX();
                int screenY = object.getY() - gamePanel.getGameCFG().getPlayer().getY() + gamePanel.getPlayerView().getScreenY();
                graphics2D.drawImage(image, screenX, screenY, gamePanel.getGameCFG().getTileSize(), gamePanel.getGameCFG().getTileSize(), null);
        }
    }
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

}
