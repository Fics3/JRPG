import org.example.IO.InOut;
import org.example.IO.SaveLoad;
import org.example.Model.Main.GameCFG;
import org.example.Model.Object.ObjectModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class SaveLoadTest {

    private GameCFG gameCFG = new GameCFG();
    private SaveLoad saveLoad = new SaveLoad(gameCFG);


    @Test
    public void saveLoadTest(){
        gameCFG.setupGame();
        int size=gameCFG.getObjects().size();;
        saveLoad.save();
        gameCFG.getObjects().clear();

        saveLoad.load("src/main/resources/Saves/save");
        gameCFG.loadGame();

        assertEquals(size,gameCFG.getObjects().size());
    }
    @Test
    public void saveLoadUncorrectTest(){
        gameCFG.setupGame();
        int size = gameCFG.getObjects().size();
        saveLoad.save();
        gameCFG.getObjects().clear();


        saveLoad.load("src/main/resources/Saves/sae");
        gameCFG.loadGame();

        assertNotEquals(size,gameCFG.getObjects().size());
    }

}
