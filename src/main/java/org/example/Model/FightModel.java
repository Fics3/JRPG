package org.example.Model;

import org.example.Model.Entity.Entity;
import org.example.Model.Entity.Player;

import java.util.Random;

public class FightModel{
    private final int fight = 1;
    private final int magic = 2;
    private final int items = 3;
    private boolean turn = true;
    Entity entity;

    public void fight(Player player,Entity entity) throws InterruptedException {
        this.entity=entity;
        while (player.getHP() > 0 && entity.getHP()> 0){
            if(turn && player.getHP()>0 && entity.getHP()>0 ){
                playerMove(player, entity);
            }
            else if(!turn &&  entity.getHP()> 0 && player.getHP() > 0){
                entityMove(player,entity);
            }
        }
        if(entity.getHP()<=0) {
            player.getGameCFG().deleteNpc(entity);
            player.lvlUp(10);
            player.getGameCFG().setGameState(player.getGameCFG().getLoadEntity());
        }
        else if (player.getHP()<=0){
            player.getGameCFG().setGameState(player.getGameCFG().getGameOverState());
        }
    }

    public void playerMove(Player player,Entity entity) {
        while (turn) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            System.out.println("PlayerHp: "+player.getHP());
//            System.out.println("EnemyHP: "+entity.getHP());
            if (player.getKeyboardController().isFight()) {
                if (player.getKeyboardController().isSlash()) {
                    player.slash(entity);
                    setTurn(false);
                    player.getKeyboardController().setFight(false);
                    break;
                } else if (player.getKeyboardController().isVampireSlash()) {
                    player.vampireSlash(entity);
                    setTurn(false);
                    player.getKeyboardController().setFight(false);
                    break;
                }
            } else if (player.getKeyboardController().isMagic()) {
                if (player.getKeyboardController().isRestoreHealth() && player.getMana() >= 5) {
                    player.restoreHealth();
                    setTurn(false);
                    player.getKeyboardController().setMagic(false);
                }
            } else if (player.getKeyboardController().isItems()) {
                if (player.getManaPotions() > 0 && player.getHeathPotions() > 0) {
                    if (player.getKeyboardController().isFirstPotion()) {
                        player.removePotion("healthPotion");
                        player.setHeathPotions(player.getHeathPotions() - 1);
                        player.getKeyboardController().setFirstPotion(false);
                    }
                    if (player.getKeyboardController().isSecondPotion()) {
                        player.removePotion("manaPotion");
                        player.setManaPotions(player.getManaPotions() - 1);
                        player.getKeyboardController().setSecondPotion(false);
                    }
                }
                if (player.getManaPotions() > 0 && player.getHeathPotions() == 0) {
                    if (player.getKeyboardController().isFirstPotion()) {
                        player.removePotion("manaPotion");
                        player.setManaPotions(player.getManaPotions() - 1);
                        player.getKeyboardController().setFirstPotion(false);
                    }
                }
                if (player.getManaPotions() == 0 && player.getHeathPotions() > 0) {
                    if (player.getKeyboardController().isFirstPotion()) {
                        player.removePotion("healthPotion");
                        player.setHeathPotions(player.getHeathPotions() - 1);
                        player.getKeyboardController().setFirstPotion(false);
                    }
                }
            }
        }
    }
    public void entityMove(Player player,Entity entity){
        Random random=new Random();
        int i = random.nextInt(0,50)+1;
            if(i<=25) {
                entity.slash(player);
            }
            else if( i>25){
                entity.vampireSlash(player);
            }
            setTurn(true);
    }
    public int getItems() {
        return items;
    }

    public int getMagic() {
        return magic;
    }

    public int getFight() {
        return fight;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public Entity getEntity() {
        return entity;
    }
    //    public Thread getThread() {
//        return thread;
//    }
}
