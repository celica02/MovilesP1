package es.ucm.fdi.switchdash.logic.entities;

import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Image;
import es.ucm.fdi.switchdash.engine.Input;
import es.ucm.fdi.switchdash.engine.SpriteSheet;

public class Particle extends SpriteSheet
{
    private float _velX, _velY;
    private float gravity = 50f;
    private float decreaseAlpha = 0.2f;

    public Particle(float posX, float posY, float velX, float velY, Image image, Graphics graphics, int nRows, int nCols) {
        super(posX, posY - 10, image, graphics, nRows, nCols);

        _velX = velX;
        _velY = velY;
    }
    public Particle(float posX, float posY, float velX, float velY, Image image, Graphics graphics, int nRows, int nCols, int row, int col) {
        super(posX, posY -10, image, graphics, nRows, nCols, row, col);

        _velX = velX;
        _velY = velY;    }

    @Override
    public void updateEntity(float deltaTime)
    {

        posX += _velX * deltaTime;
        posY += _velY * deltaTime;
        _velY += gravity * deltaTime;

        setAlpha(getAlpha() - decreaseAlpha *deltaTime);
        if(getAlpha() == 0)
            setActive(false);
    }

    public void setVelocity(float velX, float velY) {
        _velX = velX;
        _velY = velY;
    }

    public int getCurrentColor(){return activeRow; }
    public void setCurrentColor(int c){activeRow = c; }
}
