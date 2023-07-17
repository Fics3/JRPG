package org.example.View.UI;


import org.example.Model.Entity.Entity;
import org.example.Model.Object.Consumable.OBJ_healthPotion;
import org.example.Model.Object.Consumable.OBJ_manaPotion;
import org.example.Model.Object.ObjectModel;
import org.example.View.EntityView.EntityView;
import org.example.View.GamePanel;
import org.example.View.LevelEditorView;
import org.example.View.ObjectView;

import java.awt.*;
import java.util.ArrayList;


public class UI {
    private Graphics2D graphics2D;
    private final Font arial_40;
    private final GamePanel gamePanel;
    private int slotCol;
    private int slotRow;

    private int visible = 0;
    private final ArrayList<EntityView> entityViews = new ArrayList<>();
    private final ArrayList<ObjectView> objectViews = new ArrayList<>();


    public UI(GamePanel gamePanel){
        this.gamePanel=gamePanel;
        arial_40 = new Font("Comic Sans MS",Font.PLAIN,40);

    }

    public void draw(Graphics2D graphics2D){
        this.graphics2D=graphics2D;
        graphics2D.setFont(arial_40);
        graphics2D.setColor(Color.white);
        if(gamePanel.getGameCFG().getGameState()==gamePanel.getGameCFG().getTitleState()){
            drawTitleScreen();
        }
        else if(gamePanel.getGameCFG().getGameState() == gamePanel.getGameCFG().getAdventureState()){

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
        else if(gamePanel.getGameCFG().getGameState() == gamePanel.getGameCFG().getGameOverState()){
            drawGameOver();
        }
        else if( gamePanel.getGameCFG().getGameState() == gamePanel.getGameCFG().getLevelEditorState()){
            drawLevelEditor();
            for (ObjectView objectView : objectViews) {
                objectView.drawObjEditor(graphics2D);
            }
            for (EntityView entityView: entityViews) {
                entityView.drawEntityEditor(graphics2D);
            }
        }
    }
    public void drawTitleScreen(){
        graphics2D.setColor(new Color(0,0,0,255));
        graphics2D.fillRect(0,0,gamePanel.getGameCFG().getScreenWight(),gamePanel.getGameCFG().getScreenHeight());

        String txt = "JRPG";
        graphics2D.setColor(Color.WHITE);
        int textX = getForCenterText(txt);
        int textY = gamePanel.getGameCFG().getTileSize()*4;
        graphics2D.drawString(txt,textX,textY);

        txt = "New Game";
        textX = getForCenterText(txt)+gamePanel.getGameCFG().getTileSize();
        textY +=gamePanel.getGameCFG().getTileSize()*2;
        graphics2D.setFont(arial_40.deriveFont(20f));
        graphics2D.drawString(txt,textX,textY);
        if(gamePanel.getGameCFG().getKeyboardController().getCommandNum() == 0){
            drawCursor(textX,textY);
        }
        txt = "Load Game";
        textX = getForCenterText(txt);
        textY +=gamePanel.getGameCFG().getTileSize()/2;
        graphics2D.drawString(txt,textX,textY);
        if(gamePanel.getGameCFG().getKeyboardController().getCommandNum() == 1){
            drawCursor(textX,textY);
        }
        txt = "Options";
        textX = getForCenterText(txt);
        textY +=gamePanel.getGameCFG().getTileSize()/2;
        graphics2D.drawString(txt,textX,textY);
        if(gamePanel.getGameCFG().getKeyboardController().getCommandNum() == 2){
            drawCursor(textX,textY);
        }
        txt = "Level Editor";
        textX = getForCenterText(txt);
        textY +=gamePanel.getGameCFG().getTileSize()/2;
        graphics2D.drawString(txt,textX,textY);
        if(gamePanel.getGameCFG().getKeyboardController().getCommandNum() == 3){
            drawCursor(textX,textY);
        }
        txt = "New Game at Custom Level";
        textX = getForCenterText(txt);
        textY +=gamePanel.getGameCFG().getTileSize()/2;
        graphics2D.drawString(txt,textX,textY);
        if(gamePanel.getGameCFG().getKeyboardController().getCommandNum() == 4){
            drawCursor(textX,textY);
        }
        txt = "Load Custom Level ";
        textX = getForCenterText(txt);
        textY +=gamePanel.getGameCFG().getTileSize()/2;
        graphics2D.drawString(txt,textX,textY);
        if(gamePanel.getGameCFG().getKeyboardController().getCommandNum() == 5){
            drawCursor(textX,textY);
        }
        txt = "Exit";
        textX = getForCenterText(txt);
        textY +=gamePanel.getGameCFG().getTileSize()/2;
        graphics2D.drawString(txt,textX,textY);
        if(gamePanel.getGameCFG().getKeyboardController().getCommandNum() == 6){
            drawCursor(textX,textY);
        }
    }
    public void drawLevelEditor(){
        if(gamePanel.getGameCFG().getKeyboardController().isEditor()){
            gamePanel.getLevelEditorView().draw(graphics2D);
//            final int slotStartX = gamePanel.getLevelEditorView().getScreenX();
//            final int slotStartY = gamePanel.getLevelEditorView().getScreenY();
//
//            int cursorX = slotStartX;
//            int cursorY = slotStartY;
            int cursorWidth = gamePanel.getGameCFG().getTileSize();
            int cursorHeight =  gamePanel.getGameCFG().getTileSize();
            if(gamePanel.getLevelEditorView().getLevelEditor().getDataMap()[gamePanel.getLevelEditorView().getLevelEditor().getCurCol()][gamePanel.getLevelEditorView().getLevelEditor().getCurRow()]>0){
                graphics2D.setColor(Color.red);
            }
            else {
                graphics2D.setColor(Color.white);
            }
            graphics2D.setStroke(new BasicStroke(3));
            graphics2D.drawRoundRect(gamePanel.getLevelEditorView().getScreenX(),gamePanel.getLevelEditorView().getScreenY(),cursorWidth,cursorHeight,10,10);

            int frameX = gamePanel.getGameCFG().getScreenWight()-gamePanel.getLevelEditorView().getScreenX();
            int frameY = 0;

            drawSubWindow(frameX,frameY,gamePanel.getGameCFG().getScreenWight()-gamePanel.getLevelEditorView().getScreenX(),gamePanel.getGameCFG().getScreenHeight());
            final int slotStartX = frameX+25;
            final int slotStartY = frameY+15;
            int slotX = slotStartX;
            int slotY = slotStartY;
            if(gamePanel.getGameCFG().getKeyboardController().isObjects()) {
                for (int i = 2; i <= 21; i++) {
                    if (i <= 17) {
                        graphics2D.drawImage(gamePanel.getLevelEditorView().getObjectViews().get(i-2).getImage(), slotX, slotY, null);
                    }
                    else graphics2D.drawImage(gamePanel.getLevelEditorView().getEntityViews().get(i-18).getDown1(), slotX, slotY, null);
                    slotX += gamePanel.getGameCFG().getTileSize();
                    if (slotX - gamePanel.getGameCFG().getTileSize() * 4 == slotStartX) {
                        slotX = slotStartX;
                        slotY += gamePanel.getGameCFG().getTileSize();
                    }
                }
            }
            else {
                for (int i = 0; i < 44; i++) {
                    graphics2D.drawImage(gamePanel.getTileManager().getTile(i + visible).getImage(), slotX, slotY, null);
                    slotX += gamePanel.getGameCFG().getTileSize();
                    if (slotX - gamePanel.getGameCFG().getTileSize() * 4 == slotStartX) {
                        slotX = slotStartX;
                        slotY += gamePanel.getGameCFG().getTileSize();
                    }
                }
            }
            if(gamePanel.getGameCFG().getKeyboardController().isPlaceObj()) loadViews();
            int cursorX=slotStartX;
            int cursorY=slotStartY;
            if(!gamePanel.getGameCFG().getKeyboardController().isObjects()) {
                cursorX = slotStartX + (((gamePanel.getLevelEditorView().getLevelEditor().getCurTile()) - visible) % 4 * gamePanel.getGameCFG().getTileSize());
                cursorY = slotStartY + ((gamePanel.getLevelEditorView().getLevelEditor().getCurTile() - visible) / 4 * gamePanel.getGameCFG().getTileSize());
            }
            else {
                cursorX = slotStartX + ((gamePanel.getLevelEditorView().getLevelEditor().getCurObj()-2) % 4 * gamePanel.getGameCFG().getTileSize());
                cursorY = slotStartY + ((gamePanel.getLevelEditorView().getLevelEditor().getCurObj()-2) / 4 * gamePanel.getGameCFG().getTileSize());
            }
            if(gamePanel.getLevelEditorView().getLevelEditor().getCurTile()-visible>=40 && visible<60){
                visible+=4;
            }
            if(gamePanel.getLevelEditorView().getLevelEditor().getCurTile()-visible<40 && visible>0){
                visible-=4;
            }
            graphics2D.setColor(Color.white);
            graphics2D.setStroke(new BasicStroke(3));
            graphics2D.drawRoundRect(cursorX,cursorY,cursorWidth,cursorHeight,10,10);

//            if(gamePanel.getLevelEditorView().getLevelEditor().getCurTile()-visible>=44 && visible<60){
//                visible+=4;
//                cursorX-=gamePanel.getLevelEditorView().getLevelEditor().getCurTile()%4*gamePanel.getGameCFG().getTileSize();
//            }
        }
        else {
            graphics2D.setColor(new Color(0,0,0,255));
            graphics2D.fillRect(0,0,gamePanel.getGameCFG().getScreenWight(),gamePanel.getGameCFG().getScreenHeight());
            graphics2D.setColor(Color.WHITE);
            String txt = "set world size";
            int txtX = getForCenterText(txt);
            int txtY = gamePanel.getGameCFG().getTileSize()*4;
            graphics2D.drawString(txt,txtX,txtY);
            if(!gamePanel.getGameCFG().getKeyboardController().isRow()) {
                txt = "X: " + gamePanel.getGameCFG().getLevelEditor().getCol() + "     y: " + gamePanel.getGameCFG().getLevelEditor().getRow();

            }
            else{
                txt = "x: " + gamePanel.getGameCFG().getLevelEditor().getCol() + "     Y: " + gamePanel.getGameCFG().getLevelEditor().getRow();
            }
            txtX = getForCenterText(txt);
            txtY += gamePanel.getGameCFG().getTileSize()*2;
            graphics2D.drawString(txt,txtX,txtY);
            txt = "Press Enter to continue";
            txtX = getForCenterText(txt);
            txtY += gamePanel.getGameCFG().getTileSize()*2;
            if(gamePanel.getGameCFG().getLevelEditor().getRow()>1 && gamePanel.getGameCFG().getLevelEditor().getCol()>1) {
                graphics2D.drawString(txt, txtX, txtY);
            }
        }
    }
    public void drawFightScreen(){

        int x= gamePanel.getGameCFG().getTileSize()*2;
        int y=gamePanel.getGameCFG().getTileSize()*7+ gamePanel.getGameCFG().getTileSize()/2;
        int width=gamePanel.getGameCFG().getScreenWight()-(gamePanel.getGameCFG().getTileSize()*4);
        int height = gamePanel.getGameCFG().getTileSize()*4;

        drawSubWindow(x,y,width,height);
        drawFightStat(x,y);
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
            if(gamePanel.getGameCFG().getPlayer().getHeathPotions()>0 && gamePanel.getGameCFG().getPlayer().getManaPotions()>0){
                graphics2D.drawString("Q: Health Potion - restores "+ new OBJ_healthPotion(gamePanel.getGameCFG()).getHeal() + " HP. Remains "+gamePanel.getGameCFG().getPlayer().getHeathPotions(), x+gamePanel.getGameCFG().getTileSize()*3,y);
                graphics2D.drawString("W: Mana Potion - restores "+ new OBJ_manaPotion(gamePanel.getGameCFG()).getHeal() + " mana. Remains "+gamePanel.getGameCFG().getPlayer().getManaPotions(), x+gamePanel.getGameCFG().getTileSize()*3,y+gamePanel.getGameCFG().getTileSize()/2);
            }
            if(gamePanel.getGameCFG().getPlayer().getHeathPotions()>0 && gamePanel.getGameCFG().getPlayer().getManaPotions()==0) {
                graphics2D.drawString("Q: Health Potion - restores "+ new OBJ_healthPotion(gamePanel.getGameCFG()).getHeal() + " HP. Remains "+gamePanel.getGameCFG().getPlayer().getHeathPotions(), x+gamePanel.getGameCFG().getTileSize()*3,y);
            }
            if(gamePanel.getGameCFG().getPlayer().getHeathPotions()==0 && gamePanel.getGameCFG().getPlayer().getManaPotions()>0) {
                graphics2D.drawString("Q: Mana Potion - restores "+ new OBJ_manaPotion(gamePanel.getGameCFG()).getHeal() + " mana. Remains "+gamePanel.getGameCFG().getPlayer().getManaPotions(), x+gamePanel.getGameCFG().getTileSize()*3,y);
            }
            if(gamePanel.getGameCFG().getPlayer().getHeathPotions()==0 && gamePanel.getGameCFG().getPlayer().getManaPotions()==0){
                graphics2D.drawString("No potions",x+gamePanel.getGameCFG().getTileSize()*3,y);
            }
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


        x+=gamePanel.getGameCFG().getTileSize();
        y+=gamePanel.getGameCFG().getTileSize();
        String tmp = "Бро это ключевое событие";
        graphics2D.drawString(tmp,x,y);
    }
    public void drawStats(){

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

        slotCol = gamePanel.getGameCFG().getPlayer().getInventorySlot();
        slotRow = gamePanel.getGameCFG().getPlayer().getInventorySlot()/5;
        int frameX =gamePanel.getGameCFG().getTileSize()*9;
        int frameY =gamePanel.getPlayerView().getScreenY()-2*gamePanel.getGameCFG().getTileSize();
        int frameWidth = gamePanel.getGameCFG().getTileSize()*6;
        int frameHeight = gamePanel.getGameCFG().getTileSize()*5;
        
        int equipY =frameY-gamePanel.getGameCFG().getTileSize()*2-10;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);
        drawSubWindow(frameX,equipY,frameWidth,gamePanel.getGameCFG().getTileSize()*2+10);
        graphics2D.drawString("your equipment",frameX+20,equipY+30);
        equipY+=30;
        int txtY =equipY+gamePanel.getGameCFG().getTileSize()+gamePanel.getGameCFG().getTileSize()/4;
        final int helmetX = frameX+25;
        final int chestX = helmetX+gamePanel.getGameCFG().getTileSize()+gamePanel.getGameCFG().getTileSize()/3;
        final int bootsX = chestX+gamePanel.getGameCFG().getTileSize()+gamePanel.getGameCFG().getTileSize()/3;
        final int weaponX = bootsX+gamePanel.getGameCFG().getTileSize()+gamePanel.getGameCFG().getTileSize()/3;
        if(gamePanel.getGameCFG().getPlayer().getCurrentHelmet()!=null) {
            graphics2D.drawImage(new ObjectView(gamePanel, gamePanel.getGameCFG().getPlayer().getCurrentHelmet()).getImage(), helmetX, equipY, null);
        }
        graphics2D.drawString("helmet",helmetX,txtY);
        if(gamePanel.getGameCFG().getPlayer().getCurrentChest()!=null) {
            graphics2D.drawImage(new ObjectView(gamePanel, gamePanel.getGameCFG().getPlayer().getCurrentChest()).getImage(), chestX, equipY, null);
        }
        graphics2D.drawString("chest",chestX,txtY);
        if(gamePanel.getGameCFG().getPlayer().getCurrentBoots()!=null) {
            graphics2D.drawImage(new ObjectView(gamePanel, gamePanel.getGameCFG().getPlayer().getCurrentBoots()).getImage(), bootsX, equipY, null);
        }
        graphics2D.drawString("boots",bootsX,txtY);
        if(gamePanel.getGameCFG().getPlayer().getCurrentWeapon()!=null) {
            graphics2D.drawImage(new ObjectView(gamePanel, gamePanel.getGameCFG().getPlayer().getCurrentWeapon()).getImage(), weaponX, equipY, null);
        }
        graphics2D.drawString("weapon",weaponX,txtY);

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

        int dFrameY = frameY + frameHeight;
        int dFrameHeight = gamePanel.getGameCFG().getTileSize()*2;
        drawSubWindow(frameX,dFrameY, frameWidth,dFrameHeight);

        int textX  = frameX + 20;
        int textY  =dFrameY + gamePanel.getGameCFG().getTileSize();
//        graphics2D.setFont(arial_40.deriveFont(28F));
        if(gamePanel.getGameCFG().getPlayer().getInventory().get(gamePanel.getGameCFG().getPlayer().getInventorySlot()).getDescription()!=null)
        {
            String tmp = gamePanel.getGameCFG().getPlayer().getInventory().get(gamePanel.getGameCFG().getPlayer().getInventorySlot()).getDescription();
            graphics2D.drawString(tmp, textX, textY+gamePanel.getGameCFG().getTileSize()/2);
            graphics2D.drawString(gamePanel.getGameCFG().getPlayer().getInventory().get(gamePanel.getGameCFG().getPlayer().getInventorySlot()).getName(),textX,textY    );
        }

    }
    public void loadViews(){
        gamePanel.getGameCFG().getKeyboardController().setPlaceObj(false);
        int id =gamePanel.getGameCFG().getLevelEditor().getDataMap()[gamePanel.getLevelEditorView().getLevelEditor().getCurCol()][gamePanel.getLevelEditorView().getLevelEditor().getCurRow()];
        ObjectModel objectModel = gamePanel.getGameCFG().getAssetsSetter().chooseObj(id);
        Entity entity = gamePanel.getGameCFG().getAssetsSetter().chooseEntity(id);
        entityViews.removeIf(entityView ->
                entityView.getEntity().getX() ==
                        gamePanel.getLevelEditorView().getLevelEditor().getCurCol()*gamePanel.getGameCFG().getTileSize()
                && entityView.getEntity().getY() ==
                        gamePanel.getLevelEditorView().getLevelEditor().getCurRow()*gamePanel.getGameCFG().getTileSize());
        objectViews.removeIf(objectView ->
                objectView.getObjectModel().getX() ==
                        gamePanel.getLevelEditorView().getLevelEditor().getCurCol() *gamePanel.getGameCFG().getTileSize()
                && objectView.getObjectModel().getY() ==
                        gamePanel.getLevelEditorView().getLevelEditor().getCurRow()*gamePanel.getGameCFG().getTileSize());
        if(id<=17 && id > 1) {
            objectModel.setX(gamePanel.getLevelEditorView().getLevelEditor().getCurCol()*gamePanel.getGameCFG().getTileSize());
            objectModel.setY(gamePanel.getLevelEditorView().getLevelEditor().getCurRow()*gamePanel.getGameCFG().getTileSize());
            objectViews.add(new ObjectView(gamePanel,objectModel));
        }
        else if(id>17){
            entity.setX(gamePanel.getLevelEditorView().getLevelEditor().getCurCol()*gamePanel.getGameCFG().getTileSize());
            entity.setY(gamePanel.getLevelEditorView().getLevelEditor().getCurRow()*gamePanel.getGameCFG().getTileSize());
            entityViews.add(new EntityView(gamePanel,entity));
        }

    }
    public void drawGameOver(){
        graphics2D.setColor(new Color(0,0,0,150));
        graphics2D.fillRect(0,0,gamePanel.getGameCFG().getScreenWight(),gamePanel.getGameCFG().getScreenHeight());


        String txt = "YOU DIED";
        graphics2D.setColor(Color.WHITE);
        int textX = getForCenterText(txt);
        int textY = gamePanel.getGameCFG().getTileSize()*4;
        graphics2D.drawString(txt,textX,textY);

        graphics2D.setFont(arial_40.deriveFont(20f));
        txt = "Press 1 to get respawn";
        textX = getForCenterText(txt);
        textY+=gamePanel.getGameCFG().getTileSize()*2;
        graphics2D.drawString(txt,textX,textY);
        txt = "Press 2 to go to title screen";
        textX = getForCenterText(txt);
        textY+=gamePanel.getGameCFG().getTileSize();
        graphics2D.drawString(txt,textX,textY);

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
    public void drawCursor(int x,int y){
        graphics2D.drawString("->",x-gamePanel.getGameCFG().getTileSize()/2,y);

    }
}
