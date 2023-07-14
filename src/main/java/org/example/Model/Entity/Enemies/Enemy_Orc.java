package org.example.Model.Entity.Enemies;

import org.example.Model.Entity.Entity;
import org.example.Model.Main.GameCFG;
import org.example.Model.PathFinder;

public class Enemy_Orc extends Entity {
    public Enemy_Orc(GameCFG gameCFG) {
        super(gameCFG);
        setPathFinder(new PathFinder(gameCFG));
        setEnemy(true);
        setMaxHP(30);
        setBaseHP(getMaxHP());
        setLvl(1);
        setHP(getMaxHP()*getLvl());
        setDirection("down");
        setSpeed(2);
        setMaxMana(20);
        setMana(getMaxMana()*getLvl());
        setName("Orc");
        setDamage(4*getLvl());
    }

}
