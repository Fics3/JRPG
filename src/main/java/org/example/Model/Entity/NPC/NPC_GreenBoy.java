package org.example.Model.Entity.NPC;

import org.example.Model.Entity.Entity;
import org.example.Model.Main.GameCFG;

import java.util.Random;

public class NPC_GreenBoy extends Entity {

    public NPC_GreenBoy(GameCFG gameCFG,int x, int y,int id) {
        super(gameCFG);
        setDirection("down");
        setSpeed(2);
        setX(x);
        setY(y);
        setId(id);
        setName("GreenBoy");
        setDialogue("Sample Text");
    }
//    public void setAction(){
//        Random random = new Random();
//        int i = random.nextInt(100)+1;
//
//    }

}
