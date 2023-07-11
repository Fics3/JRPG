package org.example.Model.Object;

import org.example.Model.Main.GameCFG;

public class OBJ_healthPotion extends Object{
    private int heal;
    public OBJ_healthPotion(GameCFG gameCFG) {
        super(gameCFG);
        setHeal(10);
        setName("healthPotion");
        setConsumable(true);
        setDescription("Potion: Heals "+getHeal()+" HP");
    }
    public void consume(){
        getGameCFG().getPlayer().setHP(getGameCFG().getPlayer().getHP()+heal);
        if(getGameCFG().getPlayer().getMaxHP()<getGameCFG().getPlayer().getHP()){
            getGameCFG().getPlayer().setHP(getGameCFG().getPlayer().getMaxHP());
        }
    }
    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }
}
