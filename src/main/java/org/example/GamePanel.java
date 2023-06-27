package org.example;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize= originalTileSize*scale;
    final int maxScreenCol=16;
    final int maxScreenRow=12;
    final int screenWight=tileSize*maxScreenCol;
    final int screenHeight=tileSize*maxScreenRow;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWight,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

}
