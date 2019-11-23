package es.ucm.fdi.switchdash.logic.entities;

import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Image;
import es.ucm.fdi.switchdash.engine.Sprite;

public class BlinkingEntity extends Sprite {
    float alphaChange = 1f;

    public BlinkingEntity(float posX, float posY, Image image, Graphics graphics) {
        super(posX, posY, image, graphics);
    }

    public void update(float deltaTime)
    {
        if(((getAlpha() <= 0) && (alphaChange > 0)) || ((getAlpha() >= 1) && (alphaChange < 0)))
            alphaChange = -alphaChange;

        alpha = alpha - alphaChange *deltaTime;
        setAlpha(alpha);
    }
}
