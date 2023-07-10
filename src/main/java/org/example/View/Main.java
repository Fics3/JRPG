package org.example.View;

import org.example.View.GamePanel;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        JFrame window  = new JFrame();
         window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         window.setResizable(false);
         window.setTitle("JRPG_game");

         GamePanel gamePanel= new GamePanel();
         window.add(gamePanel);
//         window.add(gamePanel.getFightView());
         window.pack();

         window.setLocationRelativeTo(null);
         window.setVisible(true);
         gamePanel.setupGame();
         gamePanel.startGameThread();
    }

}