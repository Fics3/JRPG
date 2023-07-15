package org.example.View.Tiles;

import org.example.IO.InOut;
import org.example.View.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;


public class TileManager {

    private Tile[] tiles;
    private int[][] mapDataNum;
    GamePanel gamePanel;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        InOut inOut = new InOut();
        tiles=new Tile[102];
        mapDataNum = new int[gamePanel.getGameCFG().getMaxWorldCol()][gamePanel.getGameCFG().getMaxWorldRow()];
        mapDataNum=inOut.loadMap("/Maps/map_03.txt",gamePanel);
        getTileImage();

    }
    public void getTileImage(){
        InOut inOut = new InOut();
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = new Tile();
            if(i>=10){
            tiles[i].setImage(inOut.setupTile("/Tiles/Outdoors_"+i+".png"));}
            else tiles[i].setImage(inOut.setupTile("/Tiles/Outdoors_"+"0"+i+".png"));
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
                    graphics2D.drawImage(tiles[mapDataNum[i][j]].getImage(), screenX, screenY, null);
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
