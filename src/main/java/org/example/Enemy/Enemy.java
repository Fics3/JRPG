//package org.example.Enemy;
//
//
//import org.example.Entity.Entity.Entity;
//import org.example.Entity.EntityView.EntityView;
//import org.example.Model.Main.GameCFG;
//
//import java.io.IOException;
//import java.util.Objects;
//import java.util.Random;
//public class Enemy extends Entity {
//    EntityView entityView;
//    public Enemy(GameCFG gameCFG, String name, int x, int y) throws IOException {
//        super(gameCFG);
//        setName(name);
//        setX(x);
//        setY(y);
//    }
//
//    @Override
//    public void setAction() {
//        setActionCounterLock(getActionCounterLock() + 1);
//        if (getActionCounterLock() > 120) {
//            Random random = new Random();
//            int i = random.nextInt(100) + 1;
//
//            if (i <= 25) {
//                setDirection("up");
//            }
//            if (i > 25 && i <= 50) {
//                setDirection("down");
//            }
//            if (i > 50 && i <= 75) {
//                setDirection("left");
//            }
//            if (i > 75) {
//                setDirection("right");
//            }
//        }
//        setActionCounterLock(0);
//    }
//
//
//}
