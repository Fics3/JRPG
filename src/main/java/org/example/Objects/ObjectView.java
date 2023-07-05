package org.example.Objects;

import org.example.Main.GameCFG;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class ObjectView {
    private BufferedImage image;
    private GameCFG gameCFG;

    public ObjectView(GameCFG gameCFG, String name) throws IOException {
        this.gameCFG =gameCFG;
        if(name == "chest") {
            setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Objects/chest.png"))));
        }
    }
    public void draw(Graphics2D graphics2D, Object object) {
        if (object.getX()+gameCFG.getTileSize() > gameCFG.getPlayer().getY() - gameCFG.getPlayer().getPlayerView().getScreenY() &&
                object.getY()-gameCFG.getTileSize()< gameCFG.getPlayer().getY() + gameCFG.getPlayer().getPlayerView().getScreenY() &&
                object.getX()+gameCFG.getTileSize() > gameCFG.getPlayer().getX() - gameCFG.getPlayer().getPlayerView().getScreenX() &&
                object.getY() -gameCFG.getTileSize()< gameCFG.getPlayer().getX() + gameCFG.getPlayer().getPlayerView().getScreenX()) {
                int screenX = object.getX() - gameCFG.getPlayer().getX() + gameCFG.getPlayer().getPlayerView().getScreenX();
                int screenY = object.getY() - gameCFG.getPlayer().getY() + gameCFG.getPlayer().getPlayerView().getScreenY();
                graphics2D.drawImage(image, screenX, screenY, gameCFG.getTileSize(), gameCFG.getTileSize(), null);
        }
    }
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

}
