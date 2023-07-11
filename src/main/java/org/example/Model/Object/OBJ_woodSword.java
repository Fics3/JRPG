package org.example.Model.Object;

import org.example.Model.Main.GameCFG;

public class OBJ_woodSword extends Object{
    private int damage;
    public OBJ_woodSword(GameCFG gameCFG) {
        super(gameCFG);
        setName("woodSword");
        setDamage(5);
        setEquipable(true);
        setWeapon(true);
        setDescription("Old wooden sword [gain "+getDamage()+" damage]");
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

}
