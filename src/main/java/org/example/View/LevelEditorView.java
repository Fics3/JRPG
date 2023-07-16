package org.example.View;

import org.example.Model.Main.LevelEditor;
import org.example.View.Tiles.TileManager;

import javax.swing.*;
import java.awt.*;

public class LevelEditorView extends JPanel {

    private final LevelEditor levelEditor;
    private final TileManager tileManager;

    private int screenX;
    private int screenY;

    public LevelEditorView(LevelEditor levelEditor, TileManager tileManager){
        this.levelEditor = levelEditor;
        this.tileManager = tileManager;
        screenX=levelEditor.getGameCFG().getScreenWight()/2-(levelEditor.getGameCFG().getTileSize()/2);
        screenY=levelEditor.getGameCFG().getScreenHeight()/2-(levelEditor.getGameCFG().getTileSize()/2);
        tileManager.getTileImage();
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
                    graphics2D.drawImage(tileManager.getTiles()[levelEditor.getTile(i, j)].getImage(), screenXX, screenYY, null);
                }
            }
        }

    }
        public LevelEditor getLevelEditor() {
            return levelEditor;
        }

        public TileManager getTileManager() {
            return tileManager;
        }
    }
