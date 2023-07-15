package org.example.Model.Object.Armor;

import org.example.Model.Main.GameCFG;
import org.example.Model.Object.Object;

public class OBJ_legendaryChestplate extends Object {
    private int gainHP;
    public OBJ_legendaryChestplate(GameCFG gameCFG) {
        super(gameCFG);
        setName("legendaryChestplate");
        setGainHP(25);
        setEquipable(true);
        setChestplate(true);
        setDescription("plate from myths [gain "+getGainHP()+" HP]");
    }

    public int getGainHP() {
        return gainHP;
    }

    public void setGainHP(int gainHP) {
        this.gainHP = gainHP;
    }
}
