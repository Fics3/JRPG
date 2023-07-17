package org.example.Model.Object;

import org.example.Model.Main.AssetsSetter;
import org.example.Model.Main.GameCFG;

import java.util.Random;

public class OBJ_chest extends ObjectModel {
    public OBJ_chest(GameCFG gameCFG) {
        super(gameCFG);
        setCollision(true);
        setName("chest");
    }
    public void use(){
        AssetsSetter assetsSetter = new AssetsSetter(getGameCFG());
        Random random = new Random();
        int i = random.nextInt(4,17);
        ObjectModel tmp = (assetsSetter.chooseObj(i));
        tmp.setX(this.getX());
        tmp.setY(this.getY());
        tmp.setId(this.getId());
        getGameCFG().setObject(tmp);
    }
}
