package org.example;


import java.awt.*;

public class UI {

    private GamePanel gamePanel;
    Graphics2D graphics2D;
    private Font arial_40;
    private String message;
    private boolean messageOn=false;


    public UI(GamePanel gamePanel){

        this.gamePanel=gamePanel;
        arial_40 = new Font("Comic Sans MS",Font.PLAIN,40);
    }

    public void showMessage(String text){
        message = text;
        setMessageOn(true);
    }
    public void draw(Graphics2D graphics2D){
        this.graphics2D=graphics2D;
        graphics2D.setFont(arial_40);
        graphics2D.setColor(Color.white);
        if(gamePanel.getGameState() == gamePanel.getAdventureState()){

        }
        if(gamePanel.getGameState()==gamePanel.getPauseState()){
            drawPauseScreen();
        }
    }
    public void drawPauseScreen(){
        String txt = "pause";

        int length = (int)graphics2D.getFontMetrics().getStringBounds(txt,graphics2D).getWidth();
        int x = gamePanel.getScreenWight()/2 - length/2;

        int y = gamePanel.getScreenHeight()/2;
        graphics2D.drawString(txt, x,y);
    }

    public boolean isMessageOn() {
        return messageOn;
    }

    public void setMessageOn(boolean messageOn) {
        this.messageOn = messageOn;
    }
}
