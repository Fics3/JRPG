package org.example;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class UtilityTools {

    public void getDataMap(){
        InputStream inputStreamData = getClass().getResourceAsStream("/DataMaps/dataMap_03.txt");
        BufferedReader bufferedReaderData = new BufferedReader(new InputStreamReader(inputStreamData));

    }

    public BufferedImage scaleImage(BufferedImage image, int width, int height){
        BufferedImage scaledImage = new BufferedImage(width,height,image.getType());
        Graphics2D graphics2D = scaledImage.createGraphics();
        graphics2D.drawImage(image,0,0,width,height,null);
        return scaledImage;
    }
}
