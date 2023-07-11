package org.example.Model.Entity.Enemies;

import org.example.Model.Entity.Entity;
import org.example.Model.Main.GameCFG;

public class Enemy_GreenBoy extends Entity {
    public Enemy_GreenBoy(GameCFG gameCFG, int x, int y, int id) {
        super(gameCFG,x,y,id);
        setEnemy(true);
        setMaxHP(50);
        setLvl(1);
        setHP(getMaxHP()*getLvl());
        setDirection("down");
        setSpeed(2);
        setMaxMana(20);
        setMana(getMaxMana()*getLvl());
        setId(id);
        setName("GreenBoy");
        setDamage(4*getLvl());
        setDialogue("Sample Text");
    }

}
