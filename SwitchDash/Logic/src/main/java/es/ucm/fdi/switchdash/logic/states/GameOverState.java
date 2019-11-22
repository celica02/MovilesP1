package es.ucm.fdi.switchdash.logic.states;

import es.ucm.fdi.switchdash.engine.Entity;
import es.ucm.fdi.switchdash.engine.Game;
import es.ucm.fdi.switchdash.engine.GameState;

public class GameOverState extends GameState
{

    int points;

    public GameOverState(Game game, int points)
    {
        super(game);
        this.points = points;
    }


    @Override
    protected void init()
    {

    }
    
}
