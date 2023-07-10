//package org.example.Main;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class FightView extends JPanel {
//    GamePanel gamePanel;
//    Graphics2D graphics2D;
//    int x;
//    int y;
//    public FightView(GamePanel gamePanel, FightModel fightModel){
//        this.gamePanel=gamePanel;
//
//        x= gamePanel.getGameCFG().getTileSize()*2;
//        y=gamePanel.getGameCFG().getTileSize()*7+ gamePanel.getGameCFG().getTileSize()/2;
//        setSize(x,y);
//        this.setPreferredSize(new Dimension(gamePanel.getGameCFG().getScreenWight()/2,gamePanel.getGameCFG().getScreenHeight()/2));
//        this.setBackground(Color.black);
//        this.setDoubleBuffered(true);
////        this.setFocusable(true);
////        Color color = new Color(255,255,255);
////        Font arial_40 = new Font("Comic Sans MS",Font.PLAIN,40);
////        JButton fight = new JButton("fight");
////        fight.setFont(arial_40);
////        this.add(fight);
//    }
//    public void draw(Graphics2D graphics2D){
//        int x= gamePanel.getGameCFG().getTileSize()*2;
//        int y=gamePanel.getGameCFG().getTileSize()*7+ gamePanel.getGameCFG().getTileSize()/2;
//        drawFightMeny(graphics2D);
//        setVisible(true);
//    }
//    public void drawFightMeny(Graphics2D graphics2D){
//        this.graphics2D = graphics2D;
//        int txtX;
//        int txtY;
//        String text = gamePanel.getGameCFG().getPlayer().getName();
//        int width=gamePanel.getGameCFG().getScreenWight()-(gamePanel.getGameCFG().getTileSize()*4);
//        int height = gamePanel.getGameCFG().getTileSize()*4;
//        drawSubWindow(x,y,width,height);
//
//
////        JButton fight = new JButton();
////
////        fight.setText("fight");
////        fight.setSize(x,y);
////        frame.add(fight);
////        frame.add(fight);
////        gamePanel.add(frame);
////        frame.setVisible(true);
//        //        frame.setVisible(true);
//
//        txtX=getForCenterText(graphics2D, text)/3;
//        txtY=gamePanel.getGameCFG().getTileSize()+y;
//        graphics2D.drawString(text,txtX,txtY);
//    }
//    public void drawSubWindow(int x ,int y, int width, int height){
//        Color color = new Color(0,0,0,200);
//        graphics2D.setColor(color);
//        graphics2D.fillRoundRect(x,y,width,height,35,35);
//
//        color = new Color(255,255,255);
//        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN,16));
//        graphics2D.setColor(color);
//        graphics2D.setStroke(new BasicStroke(5));
//        graphics2D.drawRoundRect(x+10,y+10,width-20,height-20,25,25);
//
//    }
//    public int getForCenterText(Graphics2D graphics2D,String txt){
//        int length =(int)graphics2D.getFontMetrics().getStringBounds(txt,graphics2D).getWidth();
//        return gamePanel.getGameCFG().getScreenWight()/2-length/2;
//    }
//}
