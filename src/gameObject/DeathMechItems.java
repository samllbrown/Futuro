package gameObject;

import javafx.scene.image.Image;

public class DeathMechItems extends Item{

    protected DeathMechItems(int x, int y, int damage) {
        super(x, y, damage);
    }

    @Override
    public Image getImage() {
        return null;
    }
}
