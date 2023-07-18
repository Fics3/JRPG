import org.example.IO.SaveLoadWorld;
import org.example.Model.Entity.Entity;
import org.example.Model.Entity.Player;
import org.example.Model.FightModel;
import org.example.Model.Main.GameCFG;
import org.example.Model.Object.Consumable.OBJ_healthPotion;
import org.example.Model.Object.Consumable.OBJ_manaPotion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class FightModelTest {
    private GameCFG gameCFG;
    private Entity entity;
    Player player;

    @BeforeEach
    public void setup() {
        gameCFG = new GameCFG();
        entity = new Entity(gameCFG);
        player = new Player(gameCFG, gameCFG.getKeyboardController());
    }

    @Test
    public void entityDiesTest() {
        entity.setHP(0);
        gameCFG.setNpcs(entity);
        FightModel fightModel = new FightModel();
        try {
            fightModel.fight(player, entity);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(0, gameCFG.getObjects().size());
        assertEquals(2,player.getLvl());
    }

    @Test
    public void playerDiesTest() {
        entity.setHP(5);
        player.setHP(0);
        gameCFG.setNpcs(entity);
        FightModel fightModel = new FightModel();
        try {
            fightModel.fight(player, entity);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(gameCFG.getGameOverState(), gameCFG.getGameState());
    }

    @Test
    public void playerSlashTest() {
        entity.setHP(1);
        gameCFG.setNpcs(entity);
        player.setDamage(5);
        player.getKeyboardController().setFight(true);
        player.getKeyboardController().setSlash(true);
        FightModel fightModel = new FightModel();
        try {
            fightModel.fight(player, entity);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(0, gameCFG.getObjects().size());
    }

    @Test
    public void playerVampireSlashTest() {
        entity.setHP(1);
        gameCFG.setNpcs(entity);
        player.setDamage(5);
        player.getKeyboardController().setFight(true);
        player.getKeyboardController().setVampireSlash(true);
        FightModel fightModel = new FightModel();
        try {
            fightModel.fight(player, entity);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(0, gameCFG.getObjects().size());
    }

    @Test
    public void entityVampireSlashTest() {
        entity.setMaxHP(5);
        entity.setHP(entity.getMaxHP());
        entity.setDamage(5);
        player.setHP(1);
        gameCFG.setNpcs(entity);
        FightModel fightModel = new FightModel();
        fightModel.setTurn(false);
        entity.vampireSlash(player);
        try {
            fightModel.fight(player, entity);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(gameCFG.getGameOverState(), gameCFG.getGameState());
    }

    @Test
    public void entitySlashTest() {
        entity.setMaxHP(5);
        entity.setHP(entity.getMaxHP());
        entity.setDamage(5);
        player.setHP(1);
        gameCFG.setNpcs(entity);
        FightModel fightModel = new FightModel();
        fightModel.setTurn(false);
        entity.slash(player);
        try {
            fightModel.fight(player, entity);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(gameCFG.getGameOverState(), gameCFG.getGameState());
    }
    @Test
    public void playerRestoreHealthTest() {
        player.setMaxHP(20);
        player.setHP(10);
        player.setMana(5);

        player.restoreHealth();

        assertEquals(13, player.getHP());
        assertEquals(0,player.getMana());
    }
    @Test
    public void playerHealthPotionTest() {
        gameCFG.getPlayer().setMaxHP(20);
        gameCFG.getPlayer().setHP(10);
        OBJ_healthPotion objHealthPotion = new OBJ_healthPotion(gameCFG);

        objHealthPotion.consume();

        System.out.println(player.getHP());
        assertEquals(20,  gameCFG.getPlayer().getHP());
    }
    @Test
    public void playerManaPotionTest() {
        gameCFG.getPlayer().setMaxMana(20);
        gameCFG.getPlayer().setMana(10);
        OBJ_manaPotion objManaPotion = new OBJ_manaPotion(gameCFG);

        objManaPotion.consume();

        System.out.println(player.getHP());
        assertEquals(20,  gameCFG.getPlayer().getMana());
    }

}

