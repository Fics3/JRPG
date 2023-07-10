package org.example.View.UI;


import org.example.Model.Entity.Entity;
import org.example.View.EntityView.PlayerView;
import org.example.View.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class UI {
    Graphics2D graphics2D;
    private Font arial_40;
    GamePanel gamePanel;
    ArrayList<String> fight = new ArrayList<>();
    public UI(GamePanel gamePanel){
        this.gamePanel=gamePanel;
        int x= gamePanel.getGameCFG().getTileSize()*2;
        int y= gamePanel.getGameCFG().getTileSize()*7+ gamePanel.getGameCFG().getTileSize()/2;
///        frame.setSize(x,y);*/
        arial_40 = new Font("Comic Sans MS",Font.PLAIN,40);
//        fightMenu.add(gamePanel.getGameCFG().getPlayer().getName());
//        fightMenu.add("lvl: "+ gamePanel.getGameCFG().getPlayer().getLvl());
//        fightMenu.add("DMG: "+gamePanel.getGameCFG().getPlayer().getDamage());
//        fightMenu.add(1,"HP: "+ gamePanel.getGameCFG().getPlayer().getHP()+"/"+gamePanel.getGameCFG().getPlayer().getMaxHP());
    }

    public void draw(Graphics2D graphics2D) throws IOException {
        this.graphics2D=graphics2D;
        graphics2D.setFont(arial_40);
        graphics2D.setColor(Color.white);
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
    public void drawDialogueScreen() throws IOException {
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

        int x=gamePanel.getPlayerView().getScreenX()-gamePanel.getGameCFG().getTileSize()*5;
        int y=gamePanel.getPlayerView().getScreenY()-2*gamePanel.getGameCFG().getTileSize();

        drawSubWindow(x,y, 3*gamePanel.getGameCFG().getTileSize(), 4*gamePanel.getGameCFG().getTileSize());
        x +=  gamePanel.getGameCFG().getTileSize()/2;
        y += gamePanel.getGameCFG().getTileSize()-gamePanel.getGameCFG().getTileSize()/2+gamePanel.getGameCFG().getTileSize()/4;
        for (String tmp : gamePanel.getGameCFG().getPlayer().getStats()) {
            graphics2D.drawString(tmp, x, y);
            y+=gamePanel.getGameCFG().getTileSize()/2;
        }
        drawInventory();
    }
    public void drawInventory(){

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
}
