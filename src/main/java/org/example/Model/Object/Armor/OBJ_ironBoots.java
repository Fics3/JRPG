package org.example.Model.Object.Armor;

import org.example.Model.Main.GameCFG;
import org.example.Model.Object.Object;

public class OBJ_ironBoots extends Object {
    private int gainHP;
    public OBJ_ironBoots(GameCFG gameCFG) {
        super(gameCFG);
        setName("ironBoots");
        setGainHP(10);
        setEquipable(true);
        setBoots(true);
        setDescription("Just an iron boots [gain "+getGainHP()+" HP]");
    }

    public int getGainHP() {
        return gainHP;
    }

    public void setGainHP(int gainHP) {
        this.gainHP = gainHP;
    }
}
