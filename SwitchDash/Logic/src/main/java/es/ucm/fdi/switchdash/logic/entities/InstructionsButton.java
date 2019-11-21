package es.ucm.fdi.switchdash.logic.entities;

import es.ucm.fdi.switchdash.engine.Game;
import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Image;
import es.ucm.fdi.switchdash.engine.Input;
import es.ucm.fdi.switchdash.engine.SpriteSheet;
import es.ucm.fdi.switchdash.logic.Assets;
import es.ucm.fdi.switchdash.logic.states.PlayState;

public class InstructionsButton extends SpriteSheet {
    private Game _game;

    public InstructionsButton(float posX, Graphics graphics, Game game) {
        super(posX, 30, Assets.buttons, graphics, 1, 10, 0, 1);
        _game = game;
    }

    protected void handleTouchEvent(Input.TouchEvent e)
    {
        if(e.type == Input.TouchEvent.DOWN)
            if(inBounds(e.x, e.y))
                _game.setState(new PlayState(_game));
    }

    public void setCenteredX() { }
    public void setCenteredY() { }
    public void setCentered() { }
}
