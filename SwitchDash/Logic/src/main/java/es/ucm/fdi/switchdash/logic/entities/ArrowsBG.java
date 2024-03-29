package es.ucm.fdi.switchdash.logic.entities;

import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Image;
import es.ucm.fdi.switchdash.engine.Sprite;
import es.ucm.fdi.switchdash.logic.Assets;

/**
 * Lógica de las flechas del fondo
 */
public class ArrowsBG extends Sprite
{
    float _speed;

    // ---------- CONSTRUCTORAS ---------- //
    public ArrowsBG(float posX, float posY, Image image, Graphics graphics, float speed) {
        super(posX, posY, image, graphics);
        _speed = speed;
    }

    // ---------- FUNCIONES ---------- //
    @Override
    public void update(float deltaTime)
    {
        super.update(deltaTime);

        posY  = posY+_speed * deltaTime;

        if(posY >= 0)
            posY = -Assets.arrowsBackground.getHeight()/5;
    }

    public void setSpeed(float speed){ _speed = speed; }
    public float getSpeed(){ return _speed; }
}
