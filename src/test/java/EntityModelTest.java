import org.example.IO.SaveLoadWorld;
import org.example.Model.Entity.Entity;
import org.example.Model.Main.GameCFG;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EntityModelTest {
    private GameCFG gameCFG;
    private Entity entity;
    private SaveLoadWorld saveLoadWorld;

    @BeforeEach
    public void setup(){
        gameCFG = new GameCFG();
        gameCFG.setupGame();
        entity = new Entity(gameCFG);
    }

    @Test
    public void testWorldBounds(){
        entity.setX(-3);
        entity.setY(-3);
        entity.setDirection("up");

        entity.update();

        assertEquals(0, entity.getX());
        assertEquals(0, entity.getY());

        int worldMaxX=(gameCFG.getMaxWorldCol()-1)*gameCFG.getTileSize()-16;
        int worldMaxY=(gameCFG.getMaxWorldRow()-1)*gameCFG.getTileSize()-16;

        entity.setX(worldMaxX);
        entity.setY(worldMaxY);

        assertEquals(worldMaxX, entity.getX());
        assertEquals(worldMaxY, entity.getY());
    }
    @Test
    public void testTilesBounds(){
        SaveLoadWorld saveLoadWorld = new SaveLoadWorld(gameCFG);

        saveLoadWorld.load("src/test/java/tileCollisionTest");
        gameCFG.getCollisionChecker().setMapColData(gameCFG.getDataMap());
        entity.setDirection("left");
        entity.setSpeed(4);

        entity.update();

        assertTrue(entity.isCollisionOn());
    }
    @Test
    public void testObjectBounds(){
        SaveLoadWorld saveLoadWorld = new SaveLoadWorld(gameCFG);

        saveLoadWorld.load("src/test/java/objCollisionTest");
        gameCFG.loadCustomGame();
        entity.setDirection("left");
        entity.setSpeed(4);

        entity.update();

        assertTrue(entity.isCollisionOn());
    }
}
