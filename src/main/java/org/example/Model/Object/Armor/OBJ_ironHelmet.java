package org.example.Model.Object.Armor;

import org.example.Model.Main.GameCFG;
import org.example.Model.Object.Object;

public class OBJ_ironHelmet extends Object {
    private int gainHP;
    public OBJ_ironHelmet(GameCFG gameCFG) {
        super(gameCFG);
        setName("ironHelmet");
        setGainHP(8);
        setEquipable(true);
        setHelmet(true);
        setDescription("Just an iron helmet [gain "+getGainHP()+" HP]");
    }

    public int getGainHP() {
        return gainHP;
    }

    public void setGainHP(int gainHP) {
        this.gainHP = gainHP;
    }
}
