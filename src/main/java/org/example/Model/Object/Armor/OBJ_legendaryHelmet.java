package org.example.Model.Object.Armor;

import org.example.Model.Main.GameCFG;
import org.example.Model.Object.ObjectModel;

public class OBJ_legendaryHelmet extends ObjectModel {
    private int gainHP;
    public OBJ_legendaryHelmet(GameCFG gameCFG) {
        super(gameCFG);
        setName("legendaryHelmet");
        setGainHP(15);
        setEquipable(true);
        setHelmet(true);
        setDescription("helmet from myths [gain "+getGainHP()+" HP]");
    }

    public int getGainHP() {
        return gainHP;
    }

    public void setGainHP(int gainHP) {
        this.gainHP = gainHP;
    }
}
