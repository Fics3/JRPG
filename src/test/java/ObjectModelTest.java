import org.example.IO.SaveLoadWorld;
import org.example.Model.Entity.Entity;
import org.example.Model.Entity.Player;
import org.example.Model.Main.GameCFG;
import org.example.Model.Object.OBJ_chest;
import org.example.Model.Object.OBJ_dungeon;
import org.example.Model.Object.ObjectModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ObjectModelTest {
    private GameCFG gameCFG;
    private Player player;
    private Object object;

    @BeforeEach
    public void setup(){
        gameCFG = new GameCFG();
        gameCFG.setupGame();
        player = new Player(gameCFG,gameCFG.getKeyboardController());
    }

    @Test
    public void testChest(){
        OBJ_chest objChest = new OBJ_chest(gameCFG);
        int x = 0;
        int y = 0;
        objChest.setX(0);
        objChest.setY(0);

        objChest.use();
        ObjectModel objectModel = null;

        for (ObjectModel objectModel1 : gameCFG.getObjects()) {
            if(objectModel1.getX()==x && objectModel1.getY()==y && !Objects.equals(objectModel1.getName(), "chest")){
                objectModel=objectModel1;
            }
        }

        assertNotNull(objectModel);
    }
    @Test
    public void testDungeon(){
        OBJ_dungeon objDungeon = new OBJ_dungeon(gameCFG);
        int x = 0;
        int y = 0;
        objDungeon.setX(0);
        objDungeon.setY(0);

        objDungeon.use();
        Entity entity = null;

        for (Entity entity1 : gameCFG.getNpcs()) {
            if(entity1.getX()==x && entity1.getY()==y ){
                entity = entity1;
            }
        }

        assertNotNull(entity);
    }
}
