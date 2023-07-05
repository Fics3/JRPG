package org.example.Tiles;

import org.example.Main.GameCFG;

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
    GameCFG gameCFG;

    public TileManager(GameCFG gameCFG) throws IOException {
        this.gameCFG = gameCFG;
        tiles=new Tile[53];
        mapDataNum = new int[gameCFG.getMaxWorldCol()][gameCFG.getMaxWorldRow()];
        loadMap("/Maps/map_03.txt");
        getTileImage();

    }
    public void getTileImage() throws IOException {

        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = new Tile();
            if(i>=10){
            tiles[i].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Outdoors_"+i+".png"))));}
            else tiles[i].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Outdoors_"+"0"+i+".png"))));
        BufferedImage scaledImage = new BufferedImage(gameCFG.getTileSize(),gameCFG.getTileSize(),tiles[i].getImage().getType());
        Graphics2D graphics2D = scaledImage.createGraphics();
        graphics2D.drawImage(tiles[i].getImage(),0,0,gameCFG.getTileSize(),gameCFG.getTileSize(),null);
        tiles[i].setImage(scaledImage);
    }
    }
    public void loadMap(String filepath){
        try {
            InputStream inputStream = getClass().getResourceAsStream(filepath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            for (int i = 0; i < gameCFG.getMaxWorldRow(); i++) {
                String line = bufferedReader.readLine();
                for (int j = 0; j < gameCFG.getMaxWorldCol(); j++) {
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

        for (int i = 0; i < gameCFG.getMaxWorldCol(); i++) {
            for (int j = 0; j < gameCFG.getMaxWorldRow(); j++) {
                int worldX=i*gameCFG.getTileSize();
                int worldY=j*gameCFG.getTileSize();
                if (worldY+gameCFG.getTileSize() > gameCFG.getPlayer().getY() - gameCFG.getPlayer().getPlayerView().getScreenY() &&
                        worldY-gameCFG.getTileSize()< gameCFG.getPlayer().getY() + gameCFG.getPlayer().getPlayerView().getScreenY() &&
                        worldX+gameCFG.getTileSize() > gameCFG.getPlayer().getX() - gameCFG.getPlayer().getPlayerView().getScreenX() &&
                        worldX -gameCFG.getTileSize()< gameCFG.getPlayer().getX() + gameCFG.getPlayer().getPlayerView().getScreenX()) {
                    int screenX = worldX - gameCFG.getPlayer().getX() + gameCFG.getPlayer().getPlayerView().getScreenX();
                    int screenY = worldY - gameCFG.getPlayer().getY() + gameCFG.getPlayer().getPlayerView().getScreenY();
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
