package es.ucm.fdi.switchdash.logic.entities;

import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Image;
import es.ucm.fdi.switchdash.engine.Input.KeyboardEvent;
import es.ucm.fdi.switchdash.engine.SpriteSheet;

public class Player extends SpriteSheet
{

    int currentColor;

    public Player(float posX, float posY, Image image, Graphics graphics, int nRows, int nCols)
    {
        super(posX, posY, image, graphics, nRows, nCols);

        currentColor = activeRow;
    }


    @Override
    protected void handleKeyEvent(KeyboardEvent e) {
        super.handleKeyEvent(e);
    }

    public void changeColor(){
        currentColor = (currentColor == 0 ? 1: 0);

        setActiveSprite(currentColor, activeCol);

    }

    public int getCurrentColor() { return currentColor; }

}
