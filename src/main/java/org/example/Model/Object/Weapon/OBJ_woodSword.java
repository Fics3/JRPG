package org.example.Model.Object.Weapon;

import org.example.Model.Main.GameCFG;
import org.example.Model.Object.ObjectModel;

public class OBJ_woodSword extends ObjectModel {
    private int damage;
    public OBJ_woodSword(GameCFG gameCFG) {
        super(gameCFG);
        setName("woodSword");
        setDamage(5);
        setEquipable(true);
        setWeapon(true);
        setDescription("Old wooden sword [gain "+getDamage()+" DMG]");
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

}
