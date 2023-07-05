package org.example.UI;


import org.example.Main.GameCFG;

import java.awt.*;
import java.io.IOException;

public class UI {

    Graphics2D graphics2D;
    private Font arial_40;
    private String message;
    private boolean messageOn=false;
    GameCFG gameCFG;
//    private String dialogue;


    public UI(GameCFG gameCFG){
        this.gameCFG = gameCFG;

        arial_40 = new Font("Comic Sans MS",Font.PLAIN,40);
    }

    public void draw(Graphics2D graphics2D) throws IOException {
        this.graphics2D=graphics2D;
        graphics2D.setFont(arial_40);
        graphics2D.setColor(Color.white);
        if(gameCFG.getGameState() == gameCFG.getAdventureState()){

        }
        if(gameCFG.getGameState()==gameCFG.getPauseState()){
            drawPauseScreen();
        }
        if(gameCFG.getGameState()==gameCFG.getDialogueState()){
            drawDialogueScreen();
        }
    }
    public void drawPauseScreen(){
        String txt = "pause";

        int length = (int)graphics2D.getFontMetrics().getStringBounds(txt,graphics2D).getWidth();
        int x = gameCFG.getScreenWight()/2 - length/2;

        int y = gameCFG.getScreenHeight()/2;
        graphics2D.drawString(txt, x,y);
    }

    public void drawDialogueScreen() throws IOException {
        int x= gameCFG.getTileSize()*2;
        int y=gameCFG.getTileSize()*7+ gameCFG.getTileSize()/2;
        int width=gameCFG.getScreenWight()-(gameCFG.getTileSize()*4);
        int height = gameCFG.getTileSize()*4;
        drawSubWindow(x,y,width,height);

//        BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/Player_Face.png")));
//        UtilityTools utilityTools = new UtilityTools();
//        image = utilityTools.scaleImage(image, gameCFG.getTileSize(), gameCFG.getTileSize());
//        graphics2D.drawImage(image,x,y,null);

        x+=gameCFG.getTileSize();
        y+=gameCFG.getTileSize();
        String tmp = new String("Бро это ключевое событие");
        graphics2D.drawString(tmp,x,y);
    }
    public void drawSubWindow(int x ,int y, int width, int height){
        Color color = new Color(0,0,0,200);
        graphics2D.setColor(color);
        graphics2D.fillRoundRect(x,y,width,height,35,35);

        color = new Color(255,255,255);
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN,16));
        graphics2D.setColor(color);
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.drawRoundRect(x+10,y+10,width-20,height-20,25,25);

    }
}
