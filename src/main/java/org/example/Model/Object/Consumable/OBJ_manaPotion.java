package org.example.Model.Object.Consumable;

import org.example.Model.Main.GameCFG;
import org.example.Model.Object.ObjectModel;

public class OBJ_manaPotion extends ObjectModel {
    private int heal;
    public OBJ_manaPotion(GameCFG gameCFG) {
        super(gameCFG);
        setHeal(10);
        setName("manaPotion");
        setConsumable(true);
        setDescription("Potion: Restores "+getHeal()+" mana");
    }
    public void consume(){
        getGameCFG().getPlayer().setMana(getGameCFG().getPlayer().getMana()+heal);
        if(getGameCFG().getPlayer().getMaxMana()<getGameCFG().getPlayer().getMana()){
            getGameCFG().getPlayer().setMana(getGameCFG().getPlayer().getMaxMana());
        }
    }
    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }
}
