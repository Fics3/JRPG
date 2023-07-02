package org.example.Objects;

import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Dungeon extends Object{

    public OBJ_Dungeon(GamePanel gamePanel) throws IOException {
        super(gamePanel);
        setId(3);
        setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Objects/dungeon.png"))));

    }
}
