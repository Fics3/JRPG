package org.example.Objects;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Chest extends Object {
    public OBJ_Chest() throws IOException {
        setId(2);
        setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Objects/chest.png"))));



    }
}
