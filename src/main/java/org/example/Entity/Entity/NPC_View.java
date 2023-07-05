//package org.example.Entity;
//
//import org.example.main.GameCFG;
//import org.example.Main.GamePanel;
//
//import java.io.IOException;
//
//public class NPC_View extends EntityView{
//    public NPC_View(GamePanel gamePanel, GameCFG gameCFG, Entity entity) throws IOException {
//        super(gamePanel,gameCFG);
//        getImage(entity.getName());
//    }
//    public void getImage(String name) throws IOException {
//        try {
//            this.setDown1(setup(name+"_FWalk1"));
//            this.setDownStay(setup(name+"_FWalkStay"));
//            this.setDown2(setup(name+"_FWalk2"));
//            this.setUp1(setup(name+"_BWalk1"));
//            this.setUpStay(setup(name+"_BWalkStay"));
//            this.setUp2(setup(name+"_BWalk2"));
//            this.setLeft1(setup(name+"_LWalk1"));
//            this.setLeftStay(setup(name+"_LWalkStay"));
//            this.setLeft2(setup(name+"_LWalk2"));
//            this.setRight1(setup(name+"_RWalk1"));
//            this.setRightStay(setup(name+"_RWalkStay"));
//            this.setRight2(setup(name+"_RWalk2"));
//
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//    }
//}
