package org.example.View;

import org.example.Model.Entity.Entity;
import org.example.Model.Main.LevelEditor;
import org.example.View.EntityView.EntityView;
import org.example.View.Tiles.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LevelEditorView extends JPanel {

    private final LevelEditor levelEditor;
    private final GamePanel gamePanel;

    private int screenX;
    private int screenY;
    private ArrayList<ObjectView> objectViews = new ArrayList<>();
    private ArrayList<EntityView> entityViews = new ArrayList<>();

    public LevelEditorView(LevelEditor levelEditor, GamePanel gamePanel){
        this.levelEditor = levelEditor;
        this.gamePanel = gamePanel;
        screenX=levelEditor.getGameCFG().getScreenWight()/2-(levelEditor.getGameCFG().getTileSize()*3);
        screenY=levelEditor.getGameCFG().getScreenHeight()/2-(levelEditor.getGameCFG().getTileSize());
        gamePanel.getTileManager().getTileImage();
    }

    public void draw(Graphics2D graphics2D) {
        for (int i = 0; i < levelEditor.getCol(); i++) {
            for (int j = 0; j < levelEditor.getRow(); j++) {
                int worldX = i * levelEditor.getGameCFG().getTileSize();
                int worldY = j * levelEditor.getGameCFG().getTileSize();
                if (worldY + levelEditor.getGameCFG().getTileSize() > levelEditor.getCurRow()*levelEditor.getGameCFG().getTileSize() - screenY &&
                        worldY - levelEditor.getGameCFG().getTileSize() < levelEditor.getCurRow()*levelEditor.getGameCFG().getTileSize() + screenY &&
                        worldX + levelEditor.getGameCFG().getTileSize() > levelEditor.getCurCol()*levelEditor.getGameCFG().getTileSize() - screenX &&
                        worldX - levelEditor.getGameCFG().getTileSize() < levelEditor.getCurCol()*levelEditor.getGameCFG().getTileSize() + screenX) {
                    int screenXX = worldX-levelEditor.getCurCol()*levelEditor.getGameCFG().getTileSize()+screenX;
                    int screenYY = worldY-levelEditor.getCurRow()*levelEditor.getGameCFG().getTileSize()+screenY;
                    graphics2D.drawImage(gamePanel.getTileManager().getTiles()[levelEditor.getTile(i, j)].getImage(), screenXX, screenYY, null);
                }
            }
        }
    }

        public LevelEditor getLevelEditor() {
            return levelEditor;
        }

    public void getObjects(){
        for (int i = 2; i <=  21 ; i++) {
            if(i<=17) {
                objectViews.add(new ObjectView(gamePanel, gamePanel.getGameCFG().getAssetsSetter().chooseObj(i)));
            }
            else entityViews.add(new EntityView(gamePanel,gamePanel.getGameCFG().getAssetsSetter().chooseEntity(i)));
        }
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public ArrayList<ObjectView> getObjectViews() {
        return objectViews;
    }

    public void setObjectViews(ArrayList<ObjectView> objectViews) {
        this.objectViews = objectViews;
    }

    public ArrayList<EntityView> getEntityViews() {
        return entityViews;
    }

    public void setEntityViews(ArrayList<EntityView> entityViews) {
        this.entityViews = entityViews;
    }
}
