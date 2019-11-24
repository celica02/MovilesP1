package es.ucm.fdi.switchdash.logic.entities;

import java.util.List;

import es.ucm.fdi.switchdash.engine.Entity;
import es.ucm.fdi.switchdash.engine.Game;
import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Input;
import es.ucm.fdi.switchdash.engine.SpriteSheet;
import es.ucm.fdi.switchdash.logic.Assets;
import es.ucm.fdi.switchdash.logic.states.InstructionsState;

/**
 * Clase que implementa el botón que al pulsarlo cambia al estado de instrucciones
 */
public class InstructionsButton extends SpriteSheet {
    private Game _game;
    List<Entity> _entities;

    // ---------- CONSTRUCTORA ---------- //
    public InstructionsButton(float posX, float posY, Graphics graphics, Game game, List<Entity> entities) {
        super(posX, posY, Assets.buttons, graphics, 1, 10, 0, 0);
        _game = game;
        _entities = entities;
    }

    // ---------- FUNCIONES ---------- //
    protected void handleTouchEvent(Input.TouchEvent e)
    {
        if(e.type == Input.TouchEvent.DOWN)
            if(inBounds(e.x, e.y))
                _game.setState(new InstructionsState(_game, _entities));
    }
}
