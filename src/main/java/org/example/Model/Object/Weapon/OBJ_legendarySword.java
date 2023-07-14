package org.example.Model.Object.Weapon;

import org.example.Model.Main.GameCFG;
import org.example.Model.Object.Object;

public class OBJ_legendarySword extends Object {
    private int damage;
    public OBJ_legendarySword(GameCFG gameCFG) {
        super(gameCFG);
        setName("legendarySword");
        setDamage(20);
        setEquipable(true);
        setWeapon(true);
        setDescription("Sword from myths [gain "+getDamage()+" DMG]");
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
