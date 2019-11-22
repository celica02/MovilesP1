package es.ucm.fdi.switchdash.logic.entities;

import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Input;
import es.ucm.fdi.switchdash.engine.SpriteSheet;
import es.ucm.fdi.switchdash.logic.Assets;

public class SoundButton extends SpriteSheet {
    public SoundButton(float posX, float posY, Graphics graphics) {
        super(posX, posY, Assets.buttons, graphics, 1, 10, 0, 2);
    }

    protected void handleTouchEvent(Input.TouchEvent e)
    {
        if(e.type == Input.TouchEvent.DOWN)
            if(inBounds(e.x, e.y))
            {
                if (getActiveCol() == 2)
                    setActiveSprite(0, 3);
                else
                    setActiveSprite(0, 2);

            }
    }
}
