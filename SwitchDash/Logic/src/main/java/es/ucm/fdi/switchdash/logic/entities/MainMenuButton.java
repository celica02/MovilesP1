package es.ucm.fdi.switchdash.logic.entities;

import java.util.List;

import es.ucm.fdi.switchdash.engine.Entity;
import es.ucm.fdi.switchdash.engine.Game;
import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Image;
import es.ucm.fdi.switchdash.engine.Input;
import es.ucm.fdi.switchdash.engine.SpriteSheet;
import es.ucm.fdi.switchdash.logic.Assets;
import es.ucm.fdi.switchdash.logic.states.MainMenuState;
import es.ucm.fdi.switchdash.logic.states.PlayState;

public class MainMenuButton extends SpriteSheet {

    private Game _game;
    List<Entity> _entities;


    public MainMenuButton(float posX, float posY, Graphics graphics, Game game, List<Entity> entities) {
        super(posX, posY, Assets.buttons, graphics, 1, 10, 0, 1);
        _game = game;
        _entities = entities;
    }

    protected void handleTouchEvent(Input.TouchEvent e)
    {
        if(e.type == Input.TouchEvent.DOWN)
//            if(inBounds(e.x, e.y))
                _game.setState(new MainMenuState(_game, _entities));
    }

}
