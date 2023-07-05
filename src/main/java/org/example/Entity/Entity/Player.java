package org.example.Entity.Entity;

import org.example.Entity.EntityView.PlayerView;
import org.example.Main.GameCFG;
import org.example.KeyboardController;

import java.awt.Rectangle;
import java.io.IOException;

public class Player extends Entity {
    private static KeyboardController keyboardController;
    private int spriteNum=2;
    private int spriteCycle = 0;
    PlayerView playerView;

    public Player(GameCFG gameCFG, KeyboardController keyboardController)  {
        super(gameCFG);

        Player.keyboardController = keyboardController;


        setSolidDefaultX(getSolidArea().x);
        setSolidDefaultY(getSolidArea().y);

        setSolidArea(new Rectangle(8, 16, (int) (getGameCFG().getTileSize() / 1.5), (int) (getGameCFG().getTileSize() / 1.5)));
        try {
            playerView = new PlayerView(gameCFG);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setDafautl();

    }

    public void setDafautl() {
        setX(getGameCFG().getMaxWorldWight() / 2);
        setY(getGameCFG().getMaxWorldHeight() / 2);
        setSpeed(4);
        setMaxHP(50);
        setHP(getMaxHP());
        setLvl(1);
        setDirection("down");
    }



    public void update() {
        if (getKeyboardController().isDownPressed() || getKeyboardController().isLeftPressed() ||getKeyboardController().isUpPressed() || getKeyboardController().isRightPressed()) {
            if (getGameCFG().getKeyboardController().isUpPressed()) {
                setDirection("up");
                this.setY(this.getY() - this.getSpeed());
            } else if (getGameCFG().getKeyboardController().isDownPressed()) {
                setDirection("down");
                this.setY(this.getY() + this.getSpeed());
            } else if (getGameCFG().getKeyboardController().isRightPressed()) {
                setDirection("right");
                this.setX(this.getX() + this.getSpeed());
            } else if (getGameCFG().getKeyboardController().isLeftPressed()) {
                setDirection("left");
                this.setX(this.getX() - this.getSpeed());
            }
            setCollisionOn(false);
            getGameCFG().getCollisionChecker().checkTile(this);
            getGameCFG().getCollisionChecker().checkObject(this, true);
            int id = getGameCFG().getCollisionChecker().checkEntity(this, getGameCFG().getNpcs());
            if (id != 0 && getKeyboardController().isDialogue()) {
                interactNPC();
                speak(id);
            }
            if (isCollisionOn()) {
                switch (getDirection()) {
                    case "up":
                        setY(getY() + getSpeed());
                        break;
                    case "down":
                        setY(getY() - getSpeed());
                        break;
                    case "right":
                        setX(getX() - getSpeed());
                        break;
                    case "left":
                        setX(getX() + getSpeed());
                        break;
                }

                }
            spriteCycle++;
            if (spriteCycle > 20) {
                if (getSpriteNum() == 1) setSpriteNum(2);
                else if (getSpriteNum() == 2) setSpriteNum(3);
                else if (getSpriteNum() == 3) setSpriteNum(1);
                spriteCycle = 0;
            }
        }
    }
    public void speak(int id){
        getGameCFG().setTmp(getGameCFG().getNpc(id).getCurrentDialogue());
        switch (this.getDirection()){
            case "up":
                getGameCFG().getNpc(id).setDirection("down");
                break;
            case "down":
                getGameCFG().getNpc(id).setDirection("up");
                break;
            case "right":
                getGameCFG().getNpc(id).setDirection("left");
                break;
            case "left":
                getGameCFG().getNpc(id).setDirection("right");
                break;
        }
    }

    public void interactNPC() {
        getGameCFG().setGameState(getGameCFG().getDialogueState());
        keyboardController.setDialogue(false);
    }

    public static KeyboardController getKeyboardController() {
        return keyboardController;
    }

    public int getSpriteNum() {
        return spriteNum;
    }

    public void setSpriteNum(int spriteNum) {
        this.spriteNum = spriteNum;
    }

    public PlayerView getPlayerView() {
        return playerView;
    }
}

