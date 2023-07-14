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
        tileManager.getTileImage();
    }

    public void draw(Graphics2D graphics2D){
        for (int i = 0; i < getLevelEditor().getCol(); i++) {
            for (int j = 0; j < getLevelEditor().getRow(); j++) {
                int worldX=i*getLevelEditor().getGameCFG().getTileSize();
                int worldY=j*getLevelEditor().getGameCFG().getTileSize();
//                if (worldY+getLevelEditor().getGameCFG().getTileSize() > getLevelEditor().getGameCFG().getPlayer().getY() - screenY &&
//                        worldY-getLevelEditor().getGameCFG().getTileSize()< getLevelEditor().getGameCFG().getPlayer().getY() + screenY &&
//                        worldX+getLevelEditor().getGameCFG().getTileSize() > getLevelEditor().getGameCFG().getPlayer().getX() - screenX &&
//                        worldX -getLevelEditor().getGameCFG().getTileSize()< getLevelEditor().getGameCFG().getPlayer().getX() + screenX) {
                    int screenXX = worldX - getLevelEditor().getGameCFG().getPlayer().getX() + screenX;
                    int screenYY = worldY - getLevelEditor().getGameCFG().getPlayer().getY() + screenY;
                    graphics2D.drawImage(tileManager.getTiles()[levelEditor.getMap()[i][j]].getImage(), worldX, worldY, null);
               // }
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
