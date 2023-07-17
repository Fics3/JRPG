package org.example.Model.Object.Armor;

import org.example.Model.Main.GameCFG;
import org.example.Model.Object.ObjectModel;

public class OBJ_leatherChestplate extends ObjectModel {
    private int gainHP;
    public OBJ_leatherChestplate(GameCFG gameCFG) {
        super(gameCFG);
        setName("leatherChestplate");
        setGainHP(9);
        setEquipable(true);
        setChestplate(true);
        setDescription("Old leather jacket [gain "+getGainHP()+" HP]");
    }

    public int getGainHP() {
        return gainHP;
    }

    public void setGainHP(int gainHP) {
        this.gainHP = gainHP;
    }
}
