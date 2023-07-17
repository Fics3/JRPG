package org.example.Model.Main;


import org.example.IO.SaveLoad;
import org.example.IO.SaveLoadWorld;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyboardController implements KeyListener {
    private final GameCFG gameCFG;
    private boolean upPressed,downPressed,leftPressed, rightPressed;
    private boolean useObject = false;

    private boolean fight=false;
    private boolean slash=false;
    private boolean vampireSlash=false;
    private boolean magic=false;
    private boolean items=false;
    private boolean restoreHealth=false;
    private boolean firstPotion = false;
    private boolean secondPotion = false;

    private boolean editor = false;
    private boolean tiles = false;
    private boolean objects = false;
    private boolean row = false;
    boolean placeObj = false;
    boolean loadObj = false;

    private boolean use = false;

    private boolean respawn  = false;

    private boolean chooseLvl = false;

    private int commandNum = 0;
    private final SaveLoad saveLoad;
    private final SaveLoadWorld saveLoadWorld;


    public KeyboardController(GameCFG gameCFG){
        this.gameCFG=gameCFG;
        saveLoad = new SaveLoad(gameCFG);
        saveLoadWorld = new SaveLoadWorld(gameCFG);
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(gameCFG.getGameState() == gameCFG.getTitleState()){
            if(code==KeyEvent.VK_S){
                if(commandNum==6){
                    commandNum=0;
                }
                else commandNum++;
            }
            if(code==KeyEvent.VK_W){
                if(commandNum==0){
                    commandNum=6;
                }
                else commandNum--;
            }
            if(code == KeyEvent.VK_ENTER){
                if(commandNum == 0){
                    gameCFG.setupGame();
                    gameCFG.setGameState(gameCFG.getLoadGame());
                }
                if(commandNum == 1){
                    gameCFG.setName("world");
                    saveLoad.load("save");
                    gameCFG.loadGame();
                    gameCFG.setGameState(gameCFG.getLoadGame());
                }
                if(commandNum == 3){
                    loadObj=true;
                    gameCFG.setGameState(gameCFG.getLevelEditorState());
                }
                if(commandNum == 4){
                    gameCFG.setName("custom");
                    saveLoadWorld.load("customSave");
                    gameCFG.loadCustomGame();
                }
                if(commandNum == 5){
                    saveLoad.load("custom");
                    gameCFG.loadGame();
                }
                if(commandNum == 6){
                    System.exit(0);
                }
            }
        }
        else if( gameCFG.getGameState() == gameCFG.getLevelEditorState()){
            if(!isEditor()){
                if(code==KeyEvent.VK_A){
                    row= !row;
                }
                if(code==KeyEvent.VK_D){
                    row = !row;
                }
                if(code == KeyEvent.VK_S){
                    if(row && gameCFG.getLevelEditor().getRow()>0){
                        gameCFG.getLevelEditor().setRow(gameCFG.getLevelEditor().getRow()-1);}
                    else if(!row && gameCFG.getLevelEditor().getCol()>0 ) gameCFG.getLevelEditor().setCol(gameCFG.getLevelEditor().getCol()-1);
                }
                if(code == KeyEvent.VK_W) {
                    if (row && gameCFG.getLevelEditor().getRow()<100) {
                        gameCFG.getLevelEditor().setRow(gameCFG.getLevelEditor().getRow() + 1);}
                    else if(!row && gameCFG.getLevelEditor().getCol()<100) gameCFG.getLevelEditor().setCol(gameCFG.getLevelEditor().getCol() + 1);
                }
                if(code == KeyEvent.VK_ENTER && gameCFG.getLevelEditor().getRow()>1 && gameCFG.getLevelEditor().getCol()>1){
                    editor=true;
                    gameCFG.getLevelEditor().setLevel("customSave");
                }
            }
            else{
                if(!tiles && !objects) {
                    if (code == KeyEvent.VK_SPACE) {
                        gameCFG.getLevelEditor().placeTile();
                    }
                    if (code == KeyEvent.VK_C){
                        gameCFG.getLevelEditor().placeCollision();
                    }
                    if (code == KeyEvent.VK_D) {
                        if (gameCFG.getLevelEditor().getCurCol() < gameCFG.getLevelEditor().getCol() - 1)
                            gameCFG.getLevelEditor().setCurCol(gameCFG.getLevelEditor().getCurCol() + 1);
                    }
                    if (code == KeyEvent.VK_A) {
                        if (gameCFG.getLevelEditor().getCurCol() > 0)
                            gameCFG.getLevelEditor().setCurCol(gameCFG.getLevelEditor().getCurCol() - 1);
                    }
                    if (code == KeyEvent.VK_S) {
                        if (gameCFG.getLevelEditor().getCurRow() < gameCFG.getLevelEditor().getRow() - 1)
                            gameCFG.getLevelEditor().setCurRow(gameCFG.getLevelEditor().getCurRow() + 1);
                    }
                    if (code == KeyEvent.VK_W) {
                        if (gameCFG.getLevelEditor().getCurRow() > 0)
                            gameCFG.getLevelEditor().setCurRow(gameCFG.getLevelEditor().getCurRow() - 1);
                    }
                    if  (code == KeyEvent.VK_P){
                        gameCFG.getPlayer().setX(gameCFG.getLevelEditor().getCurCol());
                        gameCFG.getPlayer().setY(gameCFG.getLevelEditor().getCurRow());
                    }
                }
                else if(tiles && !objects){
                    if (code == KeyEvent.VK_D) {
                        if (gameCFG.getLevelEditor().getCurTile() < 103)
                            gameCFG.getLevelEditor().setCurTile(gameCFG.getLevelEditor().getCurTile() + 1);
                    }
                    if (code == KeyEvent.VK_A) {
                        if (gameCFG.getLevelEditor().getCurTile() > 0)
                            gameCFG.getLevelEditor().setCurTile(gameCFG.getLevelEditor().getCurTile() - 1);
                    }
                    if (code == KeyEvent.VK_S) {
                        if (gameCFG.getLevelEditor().getCurTile() < 100)
                            gameCFG.getLevelEditor().setCurTile(gameCFG.getLevelEditor().getCurTile() + 4);
                    }
                    if (code == KeyEvent.VK_W) {
                        if (gameCFG.getLevelEditor().getCurTile() > 3)
                            gameCFG.getLevelEditor().setCurTile(gameCFG.getLevelEditor().getCurTile() - 4);
                    }
                }
                else if(tiles && objects){
                    if (code == KeyEvent.VK_D) {
                        if (gameCFG.getLevelEditor().getCurObj() < 21)
                            gameCFG.getLevelEditor().setCurObj(gameCFG.getLevelEditor().getCurObj() + 1);
                    }
                    if (code == KeyEvent.VK_A) {
                        if (gameCFG.getLevelEditor().getCurObj() > 2)
                            gameCFG.getLevelEditor().setCurObj(gameCFG.getLevelEditor().getCurObj() - 1);
                    }
                    if (code == KeyEvent.VK_S) {
                        if (gameCFG.getLevelEditor().getCurObj() < 18)
                            gameCFG.getLevelEditor().setCurObj(gameCFG.getLevelEditor().getCurObj() + 4);
                    }
                    if (code == KeyEvent.VK_W) {
                        if (gameCFG.getLevelEditor().getCurObj() > 5)
                            gameCFG.getLevelEditor().setCurObj(gameCFG.getLevelEditor().getCurObj() - 4);
                    }
                }
                else if(!tiles && objects){
                    if(code == KeyEvent.VK_SPACE){
                        gameCFG.getLevelEditor().placeObj();
                        placeObj = true;
                    }
                    if (code == KeyEvent.VK_D) {
                        if (gameCFG.getLevelEditor().getCurCol() < gameCFG.getLevelEditor().getCol() - 1)
                            gameCFG.getLevelEditor().setCurCol(gameCFG.getLevelEditor().getCurCol() + 1);
                    }
                    if (code == KeyEvent.VK_A) {
                        if (gameCFG.getLevelEditor().getCurCol() > 0)
                            gameCFG.getLevelEditor().setCurCol(gameCFG.getLevelEditor().getCurCol() - 1);
                    }
                    if (code == KeyEvent.VK_S) {
                        if (gameCFG.getLevelEditor().getCurRow() < gameCFG.getLevelEditor().getRow() - 1)
                            gameCFG.getLevelEditor().setCurRow(gameCFG.getLevelEditor().getCurRow() + 1);
                    }
                    if (code == KeyEvent.VK_W) {
                        if (gameCFG.getLevelEditor().getCurRow() > 0)
                            gameCFG.getLevelEditor().setCurRow(gameCFG.getLevelEditor().getCurRow() - 1);
                    }
                }
                if(code==KeyEvent.VK_SHIFT){
                    tiles=!tiles;
                }
                if(code==KeyEvent.VK_V){
                    objects =! objects;
                }
                if(code == KeyEvent.VK_F5){
                    saveLoadWorld.save();
                }
                if(code == KeyEvent.VK_ESCAPE){
                    gameCFG.setGameState(gameCFG.getTitleState());
                }
            }
        }
        else if (gameCFG.getGameState() == gameCFG.getAdventureState()) {
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_F5){
                saveLoad.save();
            }
            if(code == KeyEvent.VK_ESCAPE){
                gameCFG.setGameState(gameCFG.getTitleState());
            }
            if(code == KeyEvent.VK_SPACE && gameCFG.getPlayer().isCollisionOn()){
                useObject =true;
//                gameCFG.setGameState(gameCFG.getDialogueState());
            }
            if(code == KeyEvent.VK_I){
                gameCFG.setGameState(gameCFG.getLoadInventory());
            }
        }
            else if( gameCFG.getGameState() == gameCFG.getStatState()){
                if(code == KeyEvent.VK_I) {
                    gameCFG.setGameState(gameCFG.getAdventureState());
                }
                if(code == KeyEvent.VK_D){
                    if(gameCFG.getPlayer().getInventorySlot()<gameCFG.getPlayer().getInventorySize()-1) {
                        gameCFG.getPlayer().setInventorySlot(gameCFG.getPlayer().getInventorySlot() + 1);
                    }
                    else{
                        gameCFG.getPlayer().setInventorySlot(0);
                    }
                }
                if(code == KeyEvent.VK_A){
                    if(gameCFG.getPlayer().getInventorySlot()<=0) {
                        gameCFG.getPlayer().setInventorySlot(gameCFG.getPlayer().getInventorySize() - 2);
                    }
                    else {
                        gameCFG.getPlayer().setInventorySlot(gameCFG.getPlayer().getInventorySlot() - 1);
                    }
                }
                if(code == KeyEvent.VK_SPACE){
                    use = true;
                }
            }
            else if(gameCFG.getGameState() == gameCFG.getPauseState()) {
                if (code == KeyEvent.VK_ESCAPE) {
                    gameCFG.setGameState(gameCFG.getAdventureState());
                }
            }
            else if(code == KeyEvent.VK_SPACE&& gameCFG.getGameState()==gameCFG.getDialogueState()){
                    gameCFG.setGameState(gameCFG.getAdventureState());
                    setUseObject(false);
            }
            else if(gameCFG.getGameState()==gameCFG.getFightState()){
                if(code == KeyEvent.VK_1 && !fight && !magic && !items){
                    setFight(true);
                }
                else if( code== KeyEvent.VK_2 && !fight && !magic && !items){
                    setMagic(true);
                }
                else if ( code == KeyEvent.VK_3 && !fight && !magic && !items){
                    setItems(true);
                }
                if(code == KeyEvent.VK_Q && items){
                    firstPotion =true;
                }
                if(code == KeyEvent.VK_W && items){
                    secondPotion=true;
                }
                if(code == KeyEvent.VK_Q && magic){
                    setRestoreHealth(true);
                }
                if(code == KeyEvent.VK_Q && fight){
                    setSlash(true);
                }
                else if(code== KeyEvent.VK_W && fight){
                    setVampireSlash(true);
                }
                if(code == KeyEvent.VK_ESCAPE){
                    fight=false;
                    magic=false;
                    items=false;
                }
            }
            else if( gameCFG.getGameState()== gameCFG.getGameOverState()){
                if(code == KeyEvent.VK_1){
                    respawn = true;
                }
                else if(code == KeyEvent.VK_2){
                    gameCFG.setGameState(gameCFG.getTitleState());
                }
            }
            else if(gameCFG.getGameState()==gameCFG.getOptionsState()){

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upPressed=false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed=false;
        }
        if(code == KeyEvent.VK_S){
            downPressed=false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed=false;
        }
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public void setUpPressed(boolean upPressed) {
        this.upPressed = upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public void setDownPressed(boolean downPressed) {
        this.downPressed = downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public void setRightPressed(boolean rightPressed) {
        this.rightPressed = rightPressed;
    }

    public boolean isUseObject() {
        return useObject;
    }

    public void setUseObject(boolean useObject) {
        this.useObject = useObject;
    }

    public boolean isFight() {
        return fight;
    }

    public void setFight(boolean fight) {
        this.fight = fight;
    }

    public boolean isMagic() {
        return magic;
    }

    public void setMagic(boolean magic) {
        this.magic = magic;
    }

    public boolean isItems() {
        return items;
    }

    public void setItems(boolean items) {
        this.items = items;
    }

    public boolean isSlash() {
        return slash;
    }

    public void setSlash(boolean slash) {
        this.slash = slash;
    }

    public boolean isVampireSlash() {
        return vampireSlash;
    }

    public void setVampireSlash(boolean vampireSlash) {
        this.vampireSlash = vampireSlash;
    }

    public boolean isRestoreHealth() {
        return restoreHealth;
    }

    public void setRestoreHealth(boolean restoreHealth) {
        this.restoreHealth = restoreHealth;
    }

    public boolean isUse() {
        return use;
    }

    public void setUse(boolean use) {
        this.use = use;
    }

    public boolean isFirstPotion() {
        return firstPotion;
    }

    public void setFirstPotion(boolean firstPotion) {
        this.firstPotion = firstPotion;
    }

    public boolean isSecondPotion() {
        return secondPotion;
    }

    public void setSecondPotion(boolean secondPotion) {
        this.secondPotion = secondPotion;
    }

    public boolean isRespawn() {
        return respawn;
    }

    public void setRespawn(boolean respawn) {
        this.respawn = respawn;
    }

    public int getCommandNum() {
        return commandNum;
    }

    public void setCommandNum(int commandNum) {
        this.commandNum = commandNum;
    }

    public boolean isEditor() {
        return editor;
    }

    public void setEditor(boolean editor) {
        this.editor = editor;
    }

    public void setRow(boolean row) {
        this.row = row;
    }
    public boolean isRow(){
        return row;
    }

    public SaveLoadWorld getSaveLoadWorld() {
        return saveLoadWorld;
    }

    public boolean isObjects() {
        return objects;
    }

    public void setObjects(boolean objects) {
        this.objects = objects;
    }

    public boolean isTiles() {
        return tiles;
    }

    public boolean isPlaceObj() {
        return placeObj;
    }

    public void setPlaceObj(boolean placeObj) {
        this.placeObj = placeObj;
    }

    public boolean isChooseLvl() {
        return chooseLvl;
    }

    public void setChooseLvl(boolean chooseLvl) {
        this.chooseLvl = chooseLvl;
    }

    public void setLoadObj(boolean loadObj) {
        this.loadObj = loadObj;
    }

    public boolean isLoadObj() {
        return loadObj;
    }
}
