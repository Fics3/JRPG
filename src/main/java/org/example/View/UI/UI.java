package org.example.View.UI;


import org.example.Model.Entity.Entity;
import org.example.Model.Object.Object;
import org.example.View.EntityView.PlayerView;
import org.example.View.GamePanel;
import org.example.View.ObjectView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class UI {
    private Graphics2D graphics2D;
    private Font arial_40;
    private GamePanel gamePanel;
    private int slotCol;
    private int slotRow;
    public UI(GamePanel gamePanel){
        slotCol = gamePanel.getGameCFG().getPlayer().getInventorySlot();
        slotRow = gamePanel.getGameCFG().getPlayer().getInventorySlot()/5;
        this.gamePanel=gamePanel;
        int x= gamePanel.getGameCFG().getTileSize()*2;
        int y= gamePanel.getGameCFG().getTileSize()*7+ gamePanel.getGameCFG().getTileSize()/2;
///        frame.setSize(x,y);*/
        arial_40 = new Font("Comic Sans MS",Font.PLAIN,40);

    }

    public void draw(Graphics2D graphics2D){
        this.graphics2D=graphics2D;
        graphics2D.setFont(arial_40);
        graphics2D.setColor(Color.white);
//        drawPath();
        if(gamePanel.getGameCFG().getGameState() == gamePanel.getGameCFG().getAdventureState()){

        }
        else if(gamePanel.getGameCFG().getGameState()==gamePanel.getGameCFG().getPauseState()){
            drawPauseScreen();
        }
        else if(gamePanel.getGameCFG().getGameState()==gamePanel.getGameCFG().getDialogueState()){
            drawDialogueScreen();
        }
        else if(gamePanel.getGameCFG().getGameState()==gamePanel.getGameCFG().getFightState()){
            drawFightScreen();
        }
        else if(gamePanel.getGameCFG().getGameState() == gamePanel.getGameCFG().getStatState()){
            drawStats();
            drawInventory();
        }
    }
    public void drawFightScreen(){

        int x= gamePanel.getGameCFG().getTileSize()*2;
        int y=gamePanel.getGameCFG().getTileSize()*7+ gamePanel.getGameCFG().getTileSize()/2;
        int width=gamePanel.getGameCFG().getScreenWight()-(gamePanel.getGameCFG().getTileSize()*4);
        int height = gamePanel.getGameCFG().getTileSize()*4;

        drawSubWindow(x,y,width,height);
        drawFightStat(x,y);
//        drawFightMenu();

    }
    public void drawFightStat(int x,int y){
        int enemySpriteX= gamePanel.getGameCFG().getScreenWight()/2+-gamePanel.getGameCFG().getTileSize()+gamePanel.getGameCFG().getTileSize();
        int enemySpriteY=gamePanel.getGameCFG().getScreenHeight()/2-gamePanel.getGameCFG().getTileSize()*3;
        x+=gamePanel.getGameCFG().getTileSize()/2;
        y+=gamePanel.getGameCFG().getTileSize();

        drawFightMenu(x,y);

        drawSubWindow(enemySpriteX+gamePanel.getGameCFG().getTileSize()-gamePanel.getGameCFG().getTileSize(),enemySpriteY-gamePanel.getGameCFG().getTileSize(),3*gamePanel.getGameCFG().getTileSize(),gamePanel.getGameCFG().getTileSize()*(gamePanel.getGameCFG().getPlayer().getFightModel().getEntity().getStats().size()-1));

        for (String tmp : gamePanel.getGameCFG().getPlayer().getStats()) {
            graphics2D.drawString(tmp, x, y);
            y+=gamePanel.getGameCFG().getTileSize()/2;
        }

        for(String tmp : gamePanel.getGameCFG().getPlayer().getFightModel().getEntity().getStats()){
            graphics2D.drawString(tmp, enemySpriteX+gamePanel.getGameCFG().getTileSize()/2, enemySpriteY);
            enemySpriteY+=gamePanel.getGameCFG().getTileSize()/2;
        }
        enemySpriteX= gamePanel.getGameCFG().getScreenWight()/2+-gamePanel.getGameCFG().getTileSize();
        enemySpriteY=gamePanel.getGameCFG().getScreenHeight()/2-gamePanel.getGameCFG().getTileSize()*3;
        graphics2D.drawImage(gamePanel.getEntityView(gamePanel.getGameCFG().getPlayer().getFightModel().getEntity().getName()).getDown1(),enemySpriteX,enemySpriteY,null);
        graphics2D.drawImage(gamePanel.getPlayerView().getUpStay(),enemySpriteX,enemySpriteY*2,null);
    }
    public void drawFightMenu(int x, int y){
        if(gamePanel.getGameCFG().getPlayer().getKeyboardController().isFight()){
            graphics2D.drawString("Q: Slash - "+gamePanel.getGameCFG().getPlayer().getDamage()+" DMG", x+gamePanel.getGameCFG().getTileSize()*3,y);
            graphics2D.drawString("W: VampiricSlash - "+ gamePanel.getGameCFG().getPlayer().getDamage()/2, x+gamePanel.getGameCFG().getTileSize()*3,y+gamePanel.getGameCFG().getTileSize()/2);
        }
        else if(gamePanel.getGameCFG().getPlayer().getKeyboardController().isMagic()){
            graphics2D.drawString("Q: RestoreHealth - "+5*gamePanel.getGameCFG().getPlayer().getLvl()+"  (cost 5 mana)", x+gamePanel.getGameCFG().getTileSize()*3,y);
        }
        else if(gamePanel.getGameCFG().getPlayer().getKeyboardController().isItems()){

        }
        else{
            graphics2D.drawString("1: Fight", x+gamePanel.getGameCFG().getTileSize()*3,y);
            graphics2D.drawString("2: Magic", x+gamePanel.getGameCFG().getTileSize()*3,y+gamePanel.getGameCFG().getTileSize()/2);
            graphics2D.drawString("3: Items", x+gamePanel.getGameCFG().getTileSize()*3,y+gamePanel.getGameCFG().getTileSize());
        }
    }
    public void drawPauseScreen(){
        String txt = "pause";

        int length = (int)graphics2D.getFontMetrics().getStringBounds(txt,graphics2D).getWidth();
        int x = gamePanel.getGameCFG().getScreenWight()/2 - length/2;
        int y = gamePanel.getGameCFG().getScreenHeight()/2;

        graphics2D.drawString(txt, x,y);
    }
    public void drawDialogueScreen() {
        int x= gamePanel.getGameCFG().getTileSize()*2;
        int y=gamePanel.getGameCFG().getTileSize()*7+ gamePanel.getGameCFG().getTileSize()/2;
        int width=gamePanel.getGameCFG().getScreenWight()-(gamePanel.getGameCFG().getTileSize()*4);
        int height = gamePanel.getGameCFG().getTileSize()*4;
        drawSubWindow(x,y,width,height);

//        BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/Player_Face.png")));
//        UtilityTools utilityTools = new UtilityTools();
//        image = utilityTools.scaleImage(image, gameCFG.getTileSize(), gameCFG.getTileSize());
//        graphics2D.drawImage(image,x,y,null);

        x+=gamePanel.getGameCFG().getTileSize();
        y+=gamePanel.getGameCFG().getTileSize();
        String tmp = new String("Бро это ключевое событие");
        graphics2D.drawString(tmp,x,y);
    }
    public void drawStats(){
//        int x= gamePanel.getGameCFG().getTileSize()*2;
//        int y=gamePanel.getGameCFG().getTileSize()*7+ gamePanel.getGameCFG().getTileSize()/2;

        int x=gamePanel.getPlayerView().getScreenX()-gamePanel.getGameCFG().getTileSize()*4;
        int y=gamePanel.getPlayerView().getScreenY()-2*gamePanel.getGameCFG().getTileSize();

        drawSubWindow(x,y, 3*gamePanel.getGameCFG().getTileSize(), 4*gamePanel.getGameCFG().getTileSize());
        x +=  gamePanel.getGameCFG().getTileSize()/2;
        y += gamePanel.getGameCFG().getTileSize()-gamePanel.getGameCFG().getTileSize()/2+gamePanel.getGameCFG().getTileSize()/4;
        for (String tmp : gamePanel.getGameCFG().getPlayer().getStats()) {
            graphics2D.drawString(tmp, x, y);
            y+=gamePanel.getGameCFG().getTileSize()/2;
        }
    }
    public void drawInventory(){
        int frameX =gamePanel.getGameCFG().getTileSize()*9;
        int frameY =gamePanel.getPlayerView().getScreenY()-2*gamePanel.getGameCFG().getTileSize();;
        int frameWidth = gamePanel.getGameCFG().getTileSize()*6;
        int frameHeight = gamePanel.getGameCFG().getTileSize()*5;

        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        slotCol = gamePanel.getGameCFG().getPlayer().getInventorySlot()%5;
        slotRow = gamePanel.getGameCFG().getPlayer().getInventorySlot()/5;

        final int slotStartX = frameX+20;
        final int slotStartY = frameY+20;
        int slotX = slotStartX;
        int slotY = slotStartY;
        for (ObjectView objectView: gamePanel.getPlayerView().getInventory()) {
            graphics2D.drawImage(objectView.getImage(),slotX,slotY,null);

            slotX+=gamePanel.getGameCFG().getTileSize();
            if(slotX-gamePanel.getGameCFG().getTileSize()*5 == slotStartX ){
                slotX = slotStartX;
                slotY+=gamePanel.getGameCFG().getTileSize();
            }
//            slotY+=gamePanel.getGameCFG().getTileSize()/5;
        }

        int cursorX = slotStartX + (gamePanel.getGameCFG().getTileSize()*slotCol);
        int cursorY = slotStartY + (gamePanel.getGameCFG().getTileSize()*slotRow);
        int cursorWidth = gamePanel.getGameCFG().getTileSize();
        int cursorHeight =  gamePanel.getGameCFG().getTileSize();

        graphics2D.setColor(Color.white);
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.drawRoundRect(cursorX,cursorY,cursorWidth,cursorHeight,10,10);

        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gamePanel.getGameCFG().getTileSize()*3;
        drawSubWindow(dFrameX,dFrameY,dFrameWidth,dFrameHeight);

        int textX  =dFrameX + 20;
        int textY  =dFrameY + gamePanel.getGameCFG().getTileSize();
//        graphics2D.setFont(arial_40.deriveFont(28F));
        if(gamePanel.getGameCFG().getPlayer().getInventory().get(gamePanel.getGameCFG().getPlayer().getInventorySlot()).getDescription()!=null)
        {
            String tmp = gamePanel.getGameCFG().getPlayer().getInventory().get(gamePanel.getGameCFG().getPlayer().getInventorySlot()).getDescription();
            graphics2D.drawString(tmp, textX, textY+gamePanel.getGameCFG().getTileSize()/2);
            graphics2D.drawString(gamePanel.getGameCFG().getPlayer().getInventory().get(gamePanel.getGameCFG().getPlayer().getInventorySlot()).getName(),textX,textY);
        }
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
    public int getForCenterText(String txt){
        int length =(int)graphics2D.getFontMetrics().getStringBounds(txt,graphics2D).getWidth();
        return gamePanel.getGameCFG().getScreenWight()/2-length/2;
    }
    public void drawPath(){
        graphics2D.setColor(new Color(255,0,0,79));
        for (int i = 0; i < gamePanel.getGameCFG().getNpc(3).getPathFinder().getPathList().size(); i++) {

            int wX = gamePanel.getGameCFG().getNpc(3).getPathFinder().getPathList().get(i).getCol()*gamePanel.getGameCFG().getTileSize();
            int wY = gamePanel.getGameCFG().getNpc(3).getPathFinder().getPathList().get(i).getRow()*gamePanel.getGameCFG().getTileSize();
            int sX = wX -gamePanel.getGameCFG().getPlayer().getX() + gamePanel.getPlayerView().getScreenX();
            int sY = wY -gamePanel.getGameCFG().getPlayer().getY() + gamePanel.getPlayerView().getScreenY();

            graphics2D.fillRect(sX,sY,gamePanel.getGameCFG().getTileSize(),gamePanel.getGameCFG().getTileSize());
        }
    }
}
