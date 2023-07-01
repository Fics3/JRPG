package org.example.Objects;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Dungeon extends Object{

    public OBJ_Dungeon() throws IOException {
        setId(3);
        setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Objects/dungeon.png"))));

    }
}
