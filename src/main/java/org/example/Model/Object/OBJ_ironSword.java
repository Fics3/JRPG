package org.example.Model.Object;

import org.example.Model.Main.GameCFG;

public class OBJ_ironSword extends Object{
    private int damage;
    public OBJ_ironSword(GameCFG gameCFG) {
        super(gameCFG);
        setName("ironSword");
        setDamage(8);
        setEquipable(true);
        setWeapon(true);
        setDescription("Just an iron sword [gain "+getDamage()+" damage]");
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

}
