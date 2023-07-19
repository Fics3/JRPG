package org.example.IO;

import org.example.Model.Main.GameCFG;
import org.example.View.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class InOut {
    public BufferedImage setup(String imageName) {
        BufferedImage image;
        try {
            image = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream(imageName + ".png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }

    public int[][] getDataMap(GameCFG gameCFG) {
        int [][] mapColData = new int[gameCFG.getMaxWorldCol()][gameCFG.getMaxWorldRow()];
        InputStream inputStreamData = getClass().getResourceAsStream("/DataMaps/dataMap_03.txt");
        BufferedReader bufferedReaderData = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStreamData)));
        for (int i = 0; i < gameCFG.getMaxWorldRow(); i++) {
            String lineData = null;
            try {
                lineData = bufferedReaderData.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (int j = 0; j < gameCFG.getMaxWorldCol(); j++) {
                String[] collision = lineData.split(" ");
                mapColData[j][i] = Integer.parseInt(collision[j]);
            }
        }
        return mapColData;
    }
    public int[][] loadMap(String filepath, GameCFG gameCFG){
        int [][] mapDataNum = new int[gameCFG.getMaxWorldCol()][gameCFG.getMaxWorldRow()];
        InputStream inputStream = getClass().getResourceAsStream(filepath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
            for (int i = 0; i < gameCFG.getMaxWorldRow(); i++) {
                String line = null;
                try {
                    line = bufferedReader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                for (int j = 0; j < gameCFG.getMaxWorldCol(); j++) {
                    String[] data = line.split(" ");
                    mapDataNum[j][i]=Integer.parseInt(data[j]);
                }
            }
            return mapDataNum;
        }
}

