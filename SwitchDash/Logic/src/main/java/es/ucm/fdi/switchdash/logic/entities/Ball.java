package es.ucm.fdi.switchdash.logic.entities;

import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Image;
import es.ucm.fdi.switchdash.engine.Input;
import es.ucm.fdi.switchdash.engine.SpriteSheet;

public class Ball extends SpriteSheet
{

    public Ball(float posX, float posY, Image image, Graphics graphics, int nRows, int nCols) {
        super(posX, posY, image, graphics, nRows, nCols);
    }
    public Ball(float posX, float posY, Image image, Graphics graphics, int nRows, int nCols, int row, int col) {
        super(posX, posY, image, graphics, nRows, nCols, row, col);
    }

    @Override
    public void updateEntity(float deltaTime)
    {
        posY  = posY+430 * deltaTime;
    }


    public int getCurrentColor(){return activeRow; }
}
