package org.example.Model.Object.Armor;

import org.example.Model.Main.GameCFG;
import org.example.Model.Object.Object;

public class OBJ_legendaryBoots extends Object {
    private int gainHP;
    public OBJ_legendaryBoots(GameCFG gameCFG) {
        super(gameCFG);
        setName("legendaryBoots");
        setGainHP(19);
        setEquipable(true);
        setBoots(true);
        setDescription("Boots from myths [gain "+getGainHP()+" HP]");
    }

    public int getGainHP() {
        return gainHP;
    }

    public void setGainHP(int gainHP) {
        this.gainHP = gainHP;
    }
}
