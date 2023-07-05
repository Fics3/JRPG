package org.example;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTools {




    public BufferedImage scaleImage(BufferedImage image, int width, int height){
        BufferedImage scaledImage = new BufferedImage(width,height,image.getType());
        Graphics2D graphics2D = scaledImage.createGraphics();
        graphics2D.drawImage(image,0,0,width,height,null);
        return scaledImage;
    }
}
