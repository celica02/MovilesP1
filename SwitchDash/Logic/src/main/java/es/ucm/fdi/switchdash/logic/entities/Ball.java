package es.ucm.fdi.switchdash.logic.entities;

import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Image;
import es.ucm.fdi.switchdash.engine.Input;
import es.ucm.fdi.switchdash.engine.SpriteSheet;

/**
 * Pelota que cae verticalmente a una velocidad específica
 */
public class Ball extends SpriteSheet
{
    private float speed;


    // ---------- CONSTRUCTORAS ---------- //

    public Ball(float posX, float posY, float ballSpeed, Image image, Graphics graphics, int nRows, int nCols) {
        super(posX, posY, image, graphics, nRows, nCols);

        speed = ballSpeed;
    }
    public Ball(float posX, float posY, float ballSpeed, Image image, Graphics graphics, int nRows, int nCols, int row, int col) {
        super(posX, posY, image, graphics, nRows, nCols, row, col);

        speed = ballSpeed;
    }

    // ---------- FUNCIONES ---------- //

    @Override
    public void updateEntity(float deltaTime)
    {
        posY  = posY + speed * deltaTime;
    }

    public void setSpeed(float s) { speed = s; }

    public int getCurrentColor(){return activeRow; }
}
