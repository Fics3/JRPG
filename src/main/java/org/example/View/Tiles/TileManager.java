package org.example.View.Tiles;

import org.example.IO.InOut;
import org.example.View.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;


public class TileManager {

    private Tile[] tiles;
    GamePanel gamePanel;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        InOut inOut = new InOut();
        tiles=new Tile[104];

        getTileImage();

    }
    public void getTileImage(){
        InOut inOut = new InOut();
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = new Tile();
            if(i>=10){
            tiles[i].setImage(inOut.setup("/Tiles/Outdoors_"+i));}
            else tiles[i].setImage(inOut.setup("/Tiles/Outdoors_"+"0"+i));
        BufferedImage scaledImage = new BufferedImage(gamePanel.getGameCFG().getTileSize(),gamePanel.getGameCFG().getTileSize(),tiles[i].getImage().getType());
        Graphics2D graphics2D = scaledImage.createGraphics();
        graphics2D.drawImage(tiles[i].getImage(),0,0,gamePanel.getGameCFG().getTileSize(),gamePanel.getGameCFG().getTileSize(),null);
        tiles[i].setImage(scaledImage);
    }
    }
    public void draw(Graphics2D graphics2D){
        for (int i = 0; i < gamePanel.getGameCFG().getMaxWorldCol(); i++) {
            for (int j = 0; j < gamePanel.getGameCFG().getMaxWorldRow(); j++) {
                int worldX=i*gamePanel.getGameCFG().getTileSize();
                int worldY=j*gamePanel.getGameCFG().getTileSize();
                if (worldY+gamePanel.getGameCFG().getTileSize() > gamePanel.getGameCFG().getPlayer().getY() - gamePanel.getPlayerView().getScreenY() &&
                        worldY-gamePanel.getGameCFG().getTileSize()< gamePanel.getGameCFG().getPlayer().getY() + gamePanel.getPlayerView().getScreenY() &&
                        worldX+gamePanel.getGameCFG().getTileSize() > gamePanel.getGameCFG().getPlayer().getX() - gamePanel.getPlayerView().getScreenX() &&
                        worldX -gamePanel.getGameCFG().getTileSize()< gamePanel.getGameCFG().getPlayer().getX() + gamePanel.getPlayerView().getScreenX()) {
                    int screenX = worldX - gamePanel.getGameCFG().getPlayer().getX() + gamePanel.getPlayerView().getScreenX();
                    int screenY = worldY - gamePanel.getGameCFG().getPlayer().getY() + gamePanel.getPlayerView().getScreenY();
                    if(gamePanel.getGameCFG().getMapDataNum()!=null) {
                        graphics2D.drawImage(tiles[gamePanel.getGameCFG().getMapDataNum()[i][j]].getImage(), screenX, screenY, null);
                    }
                }
            }
            
        }
    }

    public Tile[] getTiles() {
        return tiles;
    }
    public Tile getTile(int value) {
        return tiles[value];
    }
}
