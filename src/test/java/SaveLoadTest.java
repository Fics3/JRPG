import org.example.IO.InOut;
import org.example.IO.SaveLoad;
import org.example.Model.Main.GameCFG;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaveLoadTest {

    private GameCFG gameCFG = new GameCFG();
    private SaveLoad saveLoad = new SaveLoad(gameCFG);

    @BeforeEach
    public void setUp() {
        gameCFG = new GameCFG();
    }

    @Test
    public void saveLoadTest(){
        gameCFG.setupGame();
        saveLoad.save();

        int[][] map = gameCFG.getMapDataNum();

        saveLoad.load("save");
        gameCFG.loadGame();

        assertEquals(map,gameCFG.getMapDataNum());
    }
}
