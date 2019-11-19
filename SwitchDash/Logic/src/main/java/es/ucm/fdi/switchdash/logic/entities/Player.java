package es.ucm.fdi.switchdash.logic.entities;

import java.util.List;

import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Image;
import es.ucm.fdi.switchdash.engine.Input.TouchEvent;
import es.ucm.fdi.switchdash.engine.Input.KeyboardEvent;
import es.ucm.fdi.switchdash.engine.SpriteSheet;

public class Player extends SpriteSheet
{
    public Player(float posX, float posY, Image image, Graphics graphics, int nRows, int nCols) {
        super(posX, posY, image, graphics, nRows, nCols);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    protected void handleTouchEvent(TouchEvent e)
    {
       if(e.type == TouchEvent.DOWN)
           if(inBounds(e.x, e.y))
           {
               System.out.println("Me has tocado!");
           }
    }

    @Override
    protected void handleKeyEvent(KeyboardEvent e) {
        super.handleKeyEvent(e);
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
    }
}
