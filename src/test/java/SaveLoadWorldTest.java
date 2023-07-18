import org.example.IO.SaveLoad;
import org.example.IO.SaveLoadWorld;
import org.example.Model.Main.GameCFG;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaveLoadWorldTest {
    private GameCFG gameCFG = new GameCFG();
    private SaveLoadWorld saveLoadWorld = new SaveLoadWorld(gameCFG);


    @Test
    public void saveLoadWorldTest(){
        gameCFG.getLevelEditor().setCol(4);
        gameCFG.getLevelEditor().setRow(4);
        gameCFG.getLevelEditor().setLevel("testCustomWorld");
        saveLoadWorld.save("src/test/java/test/testCustomWorld");
        System.out.println(System.getProperty("user.dir"));

        int[][] map = gameCFG.getLevelEditor().getDataMap();

        saveLoadWorld.load("src/test/java/test/testCustomWorld");

        gameCFG.loadCustomGame();
        assertEquals(Arrays.deepToString(map),Arrays.deepToString(gameCFG.getDataMap()));
    }
}
