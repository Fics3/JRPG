package org.example.Model.Object;

import org.example.Model.Main.GameCFG;
import org.example.Model.Object.Object;

public class OBJ_chest extends Object {
    public OBJ_chest(GameCFG gameCFG) {
        super(gameCFG);
        setCollision(true);
        setName("chest");
    }
}
