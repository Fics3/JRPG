package org.example.View.Tiles;

import org.example.View.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    private Tile[] tiles;
    private int mapDataNum[][];
    GamePanel gamePanel;

    public TileManager(GamePanel gamePanel) throws IOException {
        this.gamePanel = gamePanel;
        tiles=new Tile[53];
        mapDataNum = new int[gamePanel.getGameCFG().getMaxWorldCol()][gamePanel.getGameCFG().getMaxWorldRow()];
        loadMap("/Maps/map_03.txt");
        getTileImage();

    }
    public void getTileImage() throws IOException {

        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = new Tile();
            if(i>=10){
            tiles[i].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Outdoors_"+i+".png"))));}
            else tiles[i].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Outdoors_"+"0"+i+".png"))));
        BufferedImage scaledImage = new BufferedImage(gamePanel.getGameCFG().getTileSize(),gamePanel.getGameCFG().getTileSize(),tiles[i].getImage().getType());
        Graphics2D graphics2D = scaledImage.createGraphics();
        graphics2D.drawImage(tiles[i].getImage(),0,0,gamePanel.getGameCFG().getTileSize(),gamePanel.getGameCFG().getTileSize(),null);
        tiles[i].setImage(scaledImage);
    }
    }
    public void loadMap(String filepath){
        try {
            InputStream inputStream = getClass().getResourceAsStream(filepath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            for (int i = 0; i < gamePanel.getGameCFG().getMaxWorldRow(); i++) {
                String line = bufferedReader.readLine();
                for (int j = 0; j < gamePanel.getGameCFG().getMaxWorldCol(); j++) {
                    String[] data = line.split(" ");
                    mapDataNum[j][i]=Integer.parseInt(data[j]);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
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
//        graphics2D.drawImage(tiles[51].getImage(),100, 100,
//                gamePanel.getTileSize(),gamePanel.getTileSize(),null);
//        graphics2D.drawImage(tiles[0].getImage(),0,0,
//                gamePanel.getTileSize(),gamePanel.getTileSize(),null);
    }

    public Tile getTiles(int value) {
        return tiles[value];
    }
}
