package org.example.Model.Object.Armor;

import org.example.Model.Main.GameCFG;
import org.example.Model.Object.Object;

public class OBJ_leatherBoots extends Object {
    private int gainHP;
    public OBJ_leatherBoots(GameCFG gameCFG) {
        super(gameCFG);
        setName("leatherBoots");
        setGainHP(5);
        setEquipable(true);
        setBoots(true);
        setDescription("Old leather boots [gain "+getGainHP()+" damage]");
    }

    public int getGainHP() {
        return gainHP;
    }

    public void setGainHP(int gainHP) {
        this.gainHP = gainHP;
    }
}
