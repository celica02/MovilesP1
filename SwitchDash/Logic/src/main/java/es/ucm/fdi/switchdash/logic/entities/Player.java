package es.ucm.fdi.switchdash.logic.entities;

import java.util.List;

import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Image;
import es.ucm.fdi.switchdash.engine.Input.TouchEvent;
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
    protected void handleTouchEvent(TouchEvent e)
    {
       if(e.type == TouchEvent.DOWN){
           currentColor = currentColor == 0 ? 1: 0;

            setActiveSprite(currentColor, activeCol);
       }
    }


    @Override
    protected void handleKeyEvent(KeyboardEvent e) {
        super.handleKeyEvent(e);
    }

    public void changeColor(){
        currentColor = (currentColor == 0 ? 0: 1);

        setActiveSprite(currentColor, activeCol);

    }
    public void setCurrentColor(int color){
        currentColor = color;
//        System.out.println(currentColor);


        setActiveSprite(currentColor, activeCol);
    }

    public int getCurrentColor() { return currentColor; }

}
