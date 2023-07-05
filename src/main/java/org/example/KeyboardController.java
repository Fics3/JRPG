package org.example;

import org.example.Main.GameCFG;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardController implements KeyListener {
    

    private boolean upPressed,downPressed,leftPressed, rightPressed;
    private boolean dialogue = false;
    private GameCFG gameCFG;

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
                System.out.println(1);
                dialogue=true;
//                gameCFG.setGameState(gameCFG.getDialogueState());
            }
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
}
