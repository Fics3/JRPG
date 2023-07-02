package org.example.Entity;

import org.example.GamePanel;

import java.io.IOException;
import java.util.Random;

public class NPC_GreenBoy extends Entity {

    public NPC_GreenBoy(GamePanel gamePanel,int x, int y) throws IOException {
        super(gamePanel);
        setDirection("down");
        setSpeed(4);
        setX(x);
        setY(y);

        getImage();
    }
    public void setAction(){
        Random random = new Random();
        int i = random.nextInt(100)+1;

    }
    public void getImage() throws IOException {
        try {
            this.setDown1(setup("GreenBoy_FWalk1"));
            this.setDownStay(setup("GreenBoy_FWalkStay"));
            this.setDown2(setup("GreenBoy_FWalk2"));
            this.setUp1(setup("GreenBoy_BWalk1"));
            this.setUpStay(setup("GreenBoy_BWalkStay"));
            this.setUp2(setup("GreenBoy_BWalk2"));
            this.setLeft1(setup("GreenBoy_LWalk1"));
            this.setLeftStay(setup("GreenBoy_LWalkStay"));
            this.setLeft2(setup("GreenBoy_LWalk2"));
            this.setRight1(setup("GreenBoy_RWalk1"));
            this.setRightStay(setup("GreenBoy_RWalkStay"));
            this.setRight2(setup("GreenBoy_RWalk2"));

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


}
