package org.example.Model.Object.Armor;

import org.example.Model.Main.GameCFG;
import org.example.Model.Object.Object;

public class OBJ_leatherHelmet extends Object {
    private int gainHP;
    public OBJ_leatherHelmet(GameCFG gameCFG) {
        super(gameCFG);
        setName("leatherHelmet");
        setGainHP(5);
        setEquipable(true);
        setHelmet(true);
        setDescription("Old leather hood [gain "+getGainHP()+" HP]");
    }

    public int getGainHP() {
        return gainHP;
    }

    public void setGainHP(int gainHP) {
        this.gainHP = gainHP;
    }
}
