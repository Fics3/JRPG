package org.example.View;

import org.example.IO.InOut;
import org.example.Model.Object.ObjectModel;

import java.awt.*;
import java.awt.image.BufferedImage;


public class ObjectView {
    private BufferedImage image;
    private GamePanel gamePanel;
    private ObjectModel objectModel;

    public ObjectView(GamePanel gamePanel, ObjectModel objectModel)  {
        this.gamePanel=gamePanel;
        this.objectModel = objectModel;
        getImages();
    }
    public void draw(Graphics2D graphics2D) {
        if (objectModel.getX()+gamePanel.getGameCFG().getTileSize() > gamePanel.getGameCFG().getPlayer().getX() - gamePanel.getPlayerView().getScreenX() &&
                objectModel.getY()-gamePanel.getGameCFG().getTileSize()< gamePanel.getGameCFG().getPlayer().getY() + gamePanel.getPlayerView().getScreenY() &&
                objectModel.getX()+gamePanel.getGameCFG().getTileSize() > gamePanel.getGameCFG().getPlayer().getX() - gamePanel.getPlayerView().getScreenX() &&
                objectModel.getY() -gamePanel.getGameCFG().getTileSize()< gamePanel.getGameCFG().getPlayer().getY() + gamePanel.getPlayerView().getScreenY()) {
                int screenX = objectModel.getX() - gamePanel.getGameCFG().getPlayer().getX() + gamePanel.getPlayerView().getScreenX();
                int screenY = objectModel.getY() - gamePanel.getGameCFG().getPlayer().getY() + gamePanel.getPlayerView().getScreenY();
                graphics2D.drawImage(image, screenX, screenY, gamePanel.getGameCFG().getTileSize(), gamePanel.getGameCFG().getTileSize(), null);
        }
    }
    public void drawObjEditor(Graphics2D graphics2D){
        if (getObjectModel().getY() + gamePanel.getGameCFG().getTileSize() > gamePanel.getGameCFG().getLevelEditor().getCurRow()*gamePanel.getGameCFG().getTileSize() - gamePanel.getLevelEditorView().getScreenY() &&
                getObjectModel().getY() - gamePanel.getGameCFG().getLevelEditor().getGameCFG().getTileSize() < gamePanel.getGameCFG().getLevelEditor().getCurRow()*gamePanel.getGameCFG().getTileSize() + gamePanel.getLevelEditorView().getScreenY() &&
                getObjectModel().getX() + gamePanel.getGameCFG().getLevelEditor().getGameCFG().getTileSize() > gamePanel.getGameCFG().getLevelEditor().getCurCol()*gamePanel.getGameCFG().getTileSize() - gamePanel.getLevelEditorView().getScreenX()&&
                getObjectModel().getX() - gamePanel.getGameCFG().getLevelEditor().getGameCFG().getTileSize() < gamePanel.getGameCFG().getLevelEditor().getCurCol()*gamePanel.getGameCFG().getTileSize() + gamePanel.getLevelEditorView().getScreenX()) {
            int screenXX = getObjectModel().getX()-gamePanel.getGameCFG().getLevelEditor().getCurCol()*gamePanel.getGameCFG().getTileSize()+gamePanel.getLevelEditorView().getScreenX();
            int screenYY = getObjectModel().getY()-gamePanel.getGameCFG().getLevelEditor().getCurRow()*gamePanel.getGameCFG().getTileSize()+gamePanel.getLevelEditorView().getScreenY();
            graphics2D.drawImage(image,screenXX,screenYY,null);
        }
    }
    public void getImages(){
        InOut inOut = new InOut();
        UtilityTools utilityTools = new UtilityTools();
        if(objectModel.getName()!=null){
        setImage(utilityTools.scaleImage(inOut.setup("/Objects/"+ objectModel.getName()),gamePanel.getGameCFG().getTileSize(),gamePanel.getGameCFG().getTileSize()));}
    }
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public ObjectModel getObjectModel() {
        return objectModel;
    }
}
