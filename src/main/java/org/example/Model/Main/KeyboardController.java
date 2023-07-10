package org.example.Model.Main;

import org.example.Model.Main.GameCFG;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class KeyboardController implements KeyListener {
    

    private boolean upPressed,downPressed,leftPressed, rightPressed;
    private boolean dialogue = false;
    private GameCFG gameCFG;
    private boolean fight=false;
    private boolean slash=false;
    private boolean vampireSlash=false;
    private boolean magic=false;
    private boolean items=false;
    private boolean restoreHealth=false;

    public KeyboardController(GameCFG gameCFG){
        this.gameCFG=gameCFG;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (gameCFG.getGameState() == gameCFG.getAdventureState()) {
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

            if(code == KeyEvent.VK_ESCAPE){
                gameCFG.setGameState(gameCFG.getPauseState());
            }
            if(code == KeyEvent.VK_SPACE && gameCFG.getPlayer().isCollisionOn()){
                dialogue=true;
//                gameCFG.setGameState(gameCFG.getDialogueState());
            }
            if(code == KeyEvent.VK_I){
                gameCFG.setGameState(gameCFG.getStatState());
            }
        }
            else if( gameCFG.getGameState() == gameCFG.getStatState() && code == KeyEvent.VK_I){
                gameCFG.setGameState(gameCFG.getAdventureState());
            }
            else if(gameCFG.getGameState() == gameCFG.getPauseState()) {
                if (code == KeyEvent.VK_ESCAPE) {
                    gameCFG.setGameState(gameCFG.getAdventureState());
                }
            }
            else if(code == KeyEvent.VK_SPACE&& gameCFG.getGameState()==gameCFG.getDialogueState()){
                    gameCFG.setGameState(gameCFG.getAdventureState());
                    setDialogue(false);
            }
            else if(gameCFG.getGameState()==gameCFG.getFightState()){
                if(code == KeyEvent.VK_1 && !fight){
                    setFight(true);
                }
                else if( code== KeyEvent.VK_2 && !magic){
                    setMagic(true);
                }
                if(code == KeyEvent.VK_Q && magic){
                    setRestoreHealth(true);
                }
                if(code == KeyEvent.VK_Q && fight){
                    setSlash(true);
//                setFight(false);
                }
                else if(code== KeyEvent.VK_W && fight){
                    setVampireSlash(true);
//                setFight(false);
                }
                if(code == KeyEvent.VK_ESCAPE){
                    fight=false;
                    magic=false;
                    items=false;
                }
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

    public boolean isDialogue() {
        return dialogue;
    }

    public void setDialogue(boolean dialogue) {
        this.dialogue = dialogue;
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
}
