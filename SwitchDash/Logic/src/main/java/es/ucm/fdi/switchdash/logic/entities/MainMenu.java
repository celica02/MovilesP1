package es.ucm.fdi.switchdash.logic.entities;

import java.util.List;

import es.ucm.fdi.switchdash.engine.Entity;
import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Image;
import es.ucm.fdi.switchdash.engine.Input;
import es.ucm.fdi.switchdash.engine.utils.MyRect;

public class MainMenu extends Entity

{
    public MainMenu(Image image, MyRect source, Graphics graphics) {
        super(image, source, graphics);
    }

    public MainMenu(Image image, MyRect source, MyRect dest, Graphics graphics) {
        super(image, source, dest, graphics);
    }

    public MainMenu(Image image, MyRect source, int destX, int destY, Graphics graphics) {
        super(image, source, destX, destY, graphics);
    }

    @Override
    public void update(float deltaTime)
    {

        posX = (posX+=5 * deltaTime);
    }

    @Override
    public void render(float deltaTime)
    {
        super.render(deltaTime);
    }

    @Override
    public void handleInput(List<Input.InputEvent> events, float deltaTime) {

    }

    @Override
    public void drawCentered() {

    }
}
