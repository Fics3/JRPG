package org.example.Tiles;

import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    private GamePanel gamePanel;
    private Tile[] tiles;
    int mapDataNum[][];

    public TileManager(GamePanel gamePanel) throws IOException {

        this.gamePanel=gamePanel;
        tiles=new Tile[53];
        mapDataNum = new int[gamePanel.getMaxScreenCol()][gamePanel.getMaxScreenRow()];
        getTileImage();
        loadMap("/Maps/map_01.txt");
    }
    public void getTileImage() throws IOException {

        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = new Tile();
            if(i>=10){
            tiles[i].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Outdoors_"+i+".png"))));}
            else tiles[i].setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Outdoors_"+"0"+i+".png"))));}
    }
    public void loadMap(String filepath){
        try {
            InputStream inputStream = getClass().getResourceAsStream(filepath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            for (int i = 0; i < gamePanel.getMaxScreenRow(); i++) {
                String line = bufferedReader.readLine();
                for (int j = 0; j < gamePanel.getMaxScreenCol(); j++) {

                    String[] data = line.split(" ");

                    int value= Integer.parseInt(data[j]);

                    mapDataNum[j][i]=value;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D graphics2D){

        for (int i = 0; i < gamePanel.getMaxScreenCol(); i++) {
            for (int j = 0; j < gamePanel.getMaxScreenRow(); j++) {
                graphics2D.drawImage(tiles[mapDataNum[i][j]].getImage(),i* gamePanel.getTileSize(), j* gamePanel.getTileSize(),
                        gamePanel.getTileSize(),gamePanel.getTileSize(),null);
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
