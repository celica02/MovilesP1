package es.ucm.fdi.switchdash.logic.entities;

import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Image;
import es.ucm.fdi.switchdash.engine.Sprite;
import es.ucm.fdi.switchdash.engine.utils.MyRect;
import es.ucm.fdi.switchdash.logic.Assets;

public class ArrowsBG extends Sprite
{
    public ArrowsBG(float posX, float posY, Image image, Graphics graphics) {
        super(posX, posY, image, graphics);
    }

    @Override
    public void update(float deltaTime)
    {
        super.update(deltaTime);

        posY  = posY+430 * deltaTime;
        if(posY >= -10)
            posY = -Assets.arrowsBackground.getHeight()/5;
    }
}
