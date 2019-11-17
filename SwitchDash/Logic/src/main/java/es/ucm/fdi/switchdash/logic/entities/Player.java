package es.ucm.fdi.switchdash.logic.entities;

import java.util.List;

import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Image;
import es.ucm.fdi.switchdash.engine.Input;
import es.ucm.fdi.switchdash.engine.Input.TouchEvent;
import es.ucm.fdi.switchdash.engine.Input.KeyboardEvent;
import es.ucm.fdi.switchdash.engine.SpriteSheet;

public class Player extends SpriteSheet
{
    public Player(float posX, float posY, Image image, Graphics graphics, int nRows, int nCols) {
        super(posX, posY, image, graphics, nRows, nCols);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void handleInput(List<TouchEvent> touchEvents, List<KeyboardEvent> keyEvents, float deltaTime) {
        super.handleInput(touchEvents, keyEvents, deltaTime);
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
    }
}
