package org.example.Model.Object;

import org.example.Model.Main.GameCFG;

public class OBJ_dungeon extends Object{
    public OBJ_dungeon(GameCFG gameCFG) {
        super(gameCFG);
        setCollision(true);
        setName("dungeon");
    }
}
