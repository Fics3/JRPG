package org.example.Entity.Entity;

import org.example.Main.GameCFG;

import java.util.Random;

public class NPC_GreenBoy extends Entity {

    public NPC_GreenBoy(GameCFG gameCFG,String name,int x, int y,int id) {
        super(gameCFG);
        setDirection("down");
        setSpeed(4);
        setX(x);
        setY(y);
        setId(id);
        setName(name);

        setDialogue("Sample Text");
    }
    public void setAction(){
        Random random = new Random();
        int i = random.nextInt(100)+1;

    }

}
