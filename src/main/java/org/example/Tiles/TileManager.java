package org.example.Tiles;

import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    private GamePanel gamePanel;
    private Tile[] tiles;
    private int mapDataNum[][];

    private int mapColData[][];

    public TileManager(GamePanel gamePanel) throws IOException {

        this.gamePanel=gamePanel;
        tiles=new Tile[53];
        mapDataNum = new int[gamePanel.getMaxWorldCol()][gamePanel.getMaxWorldRow()];
        mapColData = new int[gamePanel.getMaxWorldCol()][gamePanel.getMaxWorldRow()];
        loadMap("/Maps/map_03.txt");
        getTileImage();

    }
    public void getTileImage() throws IOException {

        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = new Tile();
            if(i>=10){
            tiles[i].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Outdoors_"+i+".png"))));}
            else tiles[i].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Outdoors_"+"0"+i+".png"))));
        BufferedImage scaledImage = new BufferedImage(gamePanel.getTileSize(),gamePanel.getTileSize(),tiles[i].getImage().getType());
        Graphics2D graphics2D = scaledImage.createGraphics();
        graphics2D.drawImage(tiles[i].getImage(),0,0,gamePanel.getTileSize(),gamePanel.getTileSize(),null);
        tiles[i].setImage(scaledImage);
    }
    }
    public void loadMap(String filepath){
        try {
            InputStream inputStream = getClass().getResourceAsStream(filepath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            InputStream inputStreamData = getClass().getResourceAsStream("/DataMaps/dataMap_"+filepath.substring(10,12)+".txt");
            BufferedReader bufferedReaderData = new BufferedReader(new InputStreamReader(inputStreamData));
            for (int i = 0; i < gamePanel.getMaxWorldRow(); i++) {
                String lineData= bufferedReaderData.readLine();
                String line = bufferedReader.readLine();
                for (int j = 0; j < gamePanel.getMaxWorldCol(); j++) {

                    String[] collision=lineData.split(" ");
                    String[] data = line.split(" ");

                    mapColData[j][i]=Integer.parseInt(collision[j]);
                    mapDataNum[j][i]=Integer.parseInt(data[j]);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D graphics2D){


        for (int i = 0; i < gamePanel.getMaxWorldCol(); i++) {
            for (int j = 0; j < gamePanel.getMaxWorldRow(); j++) {
                int worldX=i*gamePanel.getTileSize();
                int worldY=j*gamePanel.getTileSize();
                if (worldY+gamePanel.getTileSize() > gamePanel.getPlayer().getY() - gamePanel.getPlayer().getScreenY() &&
                        worldY-gamePanel.getTileSize()< gamePanel.getPlayer().getY() + gamePanel.getPlayer().getScreenY() &&
                        worldX+gamePanel.getTileSize() > gamePanel.getPlayer().getX() - gamePanel.getPlayer().getScreenX() &&
                        worldX -gamePanel.getTileSize()< gamePanel.getPlayer().getX() + gamePanel.getPlayer().getScreenX()) {
                    int screenX = worldX - gamePanel.getPlayer().getX() + gamePanel.getPlayer().getScreenX();
                    int screenY = worldY - gamePanel.getPlayer().getY() + gamePanel.getPlayer().getScreenY();

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

    public int getMapDataNum(int col, int row) {
        return mapDataNum[col][row];
    }

    public int getMapColData(int col,int row) {
        return mapColData[col][row];
    }
}
