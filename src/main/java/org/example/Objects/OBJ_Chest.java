package org.example.Objects;

import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Chest extends Object {
    public OBJ_Chest(GamePanel gamePanel) throws IOException {
        super(gamePanel);
        setId(2);
        setCollision(true);
        setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Objects/chest.png"))));



    }
}
