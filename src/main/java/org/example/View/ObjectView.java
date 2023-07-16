package org.example.View;

import org.example.IO.InOut;
import org.example.Model.Object.Object;

import java.awt.*;
import java.awt.image.BufferedImage;


public class ObjectView {
    private BufferedImage image;
    private GamePanel gamePanel;
    private Object object;

    public ObjectView(GamePanel gamePanel, Object object)  {
        this.gamePanel=gamePanel;
        this.object=object;
        getImages();
    }
    public void draw(Graphics2D graphics2D) {
        if (object.getX()+gamePanel.getGameCFG().getTileSize() > gamePanel.getGameCFG().getPlayer().getX() - gamePanel.getPlayerView().getScreenX() &&
                object.getY()-gamePanel.getGameCFG().getTileSize()< gamePanel.getGameCFG().getPlayer().getY() + gamePanel.getPlayerView().getScreenY() &&
                object.getX()+gamePanel.getGameCFG().getTileSize() > gamePanel.getGameCFG().getPlayer().getX() - gamePanel.getPlayerView().getScreenX() &&
                object.getY() -gamePanel.getGameCFG().getTileSize()< gamePanel.getGameCFG().getPlayer().getY() + gamePanel.getPlayerView().getScreenY()) {
                int screenX = object.getX() - gamePanel.getGameCFG().getPlayer().getX() + gamePanel.getPlayerView().getScreenX();
                int screenY = object.getY() - gamePanel.getGameCFG().getPlayer().getY() + gamePanel.getPlayerView().getScreenY();
                graphics2D.drawImage(image, screenX, screenY, gamePanel.getGameCFG().getTileSize(), gamePanel.getGameCFG().getTileSize(), null);
        }
    }
    public void getImages(){
        InOut inOut = new InOut();
        UtilityTools utilityTools = new UtilityTools();
        if(object.getName()!=null){
        setImage(utilityTools.scaleImage(inOut.setup("/Objects/"+object.getName()),gamePanel.getGameCFG().getTileSize(),gamePanel.getGameCFG().getTileSize()));}
    }
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

}
