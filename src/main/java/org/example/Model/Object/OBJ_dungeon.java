package org.example.Model.Object;

import org.example.Model.Entity.Entity;
import org.example.Model.Main.AssetsSetter;
import org.example.Model.Main.GameCFG;

import java.util.Random;

public class OBJ_dungeon extends Object{
    public OBJ_dungeon(GameCFG gameCFG) {
        super(gameCFG);
        setCollision(true);
        setName("dungeon");
    }
    public void use(){
        AssetsSetter assetsSetter = new AssetsSetter(getGameCFG());
        Random random = new Random();
        int i = random.nextInt(18,21);
        Entity enemy = assetsSetter.chooseEntity(i);
        enemy.setX(getX());
        enemy.setY(getY());
        getGameCFG().setNpcs(enemy);
//        setId(getGameCFG().getNpcs().size());
//        getGameCFG().setNpcs(enemy);
//        getGameCFG().setGameState(getGameCFG().getLoadEntity());
//        System.out.println(enemy);
//        try {
//            getGameCFG().getPlayer().interactEnemy(enemy);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }
}
