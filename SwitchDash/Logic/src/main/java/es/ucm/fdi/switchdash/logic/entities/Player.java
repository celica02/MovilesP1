package es.ucm.fdi.switchdash.logic.entities;

import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Image;
import es.ucm.fdi.switchdash.engine.Input;
import es.ucm.fdi.switchdash.engine.SpriteSheet;


/**
 * Jugador, tabla que cambia de color con cada pulsación de pantalla
 */
public class Player extends SpriteSheet
{

    int currentColor;

    public Player(float posX, float posY, Image image, Graphics graphics, int nRows, int nCols)
    {
        super(posX, posY, image, graphics, nRows, nCols);

        currentColor = activeRow;
    }

    @Override
    protected void handleTouchEvent(Input.TouchEvent e) {

        if(e.type == Input.TouchEvent.DOWN)
            changeColor();
    }

    public void changeColor(){
        currentColor = (currentColor == 0 ? 1: 0);

        setActiveSprite(currentColor, activeCol);

    }

    public int getCurrentColor() { return currentColor; }

}
