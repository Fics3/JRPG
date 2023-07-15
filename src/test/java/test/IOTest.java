package test;

import org.example.IO.InOut;
import org.example.Model.Main.GameCFG;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IOTest {
    private InOut ioUtils = new InOut();
    private GameCFG gamePanelSettings = new GameCFG();

    @BeforeEach
    public void setUp() {
        ioUtils = new InOut();
        gamePanelSettings = new GameCFG();
    }
    @Test
    public void testReadImage() {
        BufferedImage image = ioUtils.setup("GreenBoy_FWalk1");
        assertNotNull(image);
    }
    @Test
    public void testLoadMap() {
        ioUtils.getDataMap(gamePanelSettings);
    }
}