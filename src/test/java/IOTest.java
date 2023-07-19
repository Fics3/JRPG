import org.example.IO.InOut;
import org.example.Model.Main.GameCFG;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class IOTest {
    private InOut inOut = new InOut();
    private GameCFG gameCFG = new GameCFG();

    @BeforeEach
    public void setUp() {
        inOut = new InOut();
        gameCFG = new GameCFG();
    }
    @Test
    public void readImageTest() {
        BufferedImage image = inOut.setup("/NPC/GreenBoy_FWalk1");
        assertNotNull(image);
    }

//    @Test
//    public void testLoadMap() {
//        inOut.getDataMap(gameCFG);
//    }
}