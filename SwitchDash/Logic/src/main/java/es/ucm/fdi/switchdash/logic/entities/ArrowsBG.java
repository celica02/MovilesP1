package es.ucm.fdi.switchdash.logic.entities;

import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Image;
import es.ucm.fdi.switchdash.engine.Sprite;
import es.ucm.fdi.switchdash.engine.utils.MyRect;

public class ArrowsBG extends Sprite
{
    public ArrowsBG(float posX, float posY, Image image, Graphics graphics) {
        super(posX, posY, image, graphics);
    }

    public ArrowsBG(float posX, float posY, Image image, MyRect source, Graphics graphics) {
        super(posX, posY, image, source, graphics);
    }

    public ArrowsBG(float posX, float posY, float width, float height, Image image, MyRect source, Graphics graphics) {
        super(posX, posY, width, height, image, source, graphics);
    }

    @Override
    public void update(float deltaTime)
    {
        super.update(deltaTime);

        posY  = posY+430 * deltaTime;
    }
}
