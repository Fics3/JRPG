package org.example.Model.Object.Armor;

import org.example.Model.Main.GameCFG;
import org.example.Model.Object.Object;

public class OBJ_ironChestplate extends Object {
    private int gainHP;
    public OBJ_ironChestplate(GameCFG gameCFG) {
        super(gameCFG);
        setName("ironChestplate");
        setGainHP(15);
        setEquipable(true);
        setChestplate(true);
        setDescription("Just an iron plate [gain "+getGainHP()+" HP]");
    }

    public int getGainHP() {
        return gainHP;
    }

    public void setGainHP(int gainHP) {
        this.gainHP = gainHP;
    }
}
