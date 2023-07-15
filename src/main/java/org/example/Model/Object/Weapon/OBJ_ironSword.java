package org.example.Model.Object.Weapon;

import org.example.Model.Main.GameCFG;
import org.example.Model.Object.Object;

public class OBJ_ironSword extends Object {
    private int damage;
    public OBJ_ironSword(GameCFG gameCFG) {
        super(gameCFG);
        setName("ironSword");
        setDamage(8);
        setEquipable(true);
        setWeapon(true);
        setDescription("Just an iron sword [gain "+getDamage()+" DMG]");
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

}
