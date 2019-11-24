package es.ucm.fdi.switchdash.logic.entities;

import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Image;
import es.ucm.fdi.switchdash.engine.Sprite;

public class BlinkingEntity extends Sprite {
    float alphaChange = 1f;

    public BlinkingEntity(Graphics graphics) {
        super(graphics);
    }

    public BlinkingEntity(float posX, float posY, Image image, Graphics graphics) {
        super(posX, posY, image, graphics);
    }

    public void updateEntity(float deltaTime)
    {
        if(((getAlpha() <= 0) && (alphaChange > 0)) || ((getAlpha() >= 1) && (alphaChange < 0)))
            alphaChange = -alphaChange;

        alpha = alpha - alphaChange * deltaTime;
        setAlpha(alpha);
    }
}
