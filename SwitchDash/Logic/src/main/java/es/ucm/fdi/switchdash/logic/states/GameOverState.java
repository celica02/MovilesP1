package es.ucm.fdi.switchdash.logic.states;

import java.util.List;

import es.ucm.fdi.switchdash.engine.Entity;
import es.ucm.fdi.switchdash.engine.Game;
import es.ucm.fdi.switchdash.engine.GameState;
import es.ucm.fdi.switchdash.engine.Sprite;
import es.ucm.fdi.switchdash.logic.Assets;
import es.ucm.fdi.switchdash.logic.entities.Background;
import es.ucm.fdi.switchdash.logic.entities.BlinkingEntity;
import es.ucm.fdi.switchdash.logic.entities.Points;

public class GameOverState extends GameState
{
    private Background background;
    private Sprite playAgain;
    private int _points;

    private boolean alphaUp;



    public GameOverState(Game game, int points)
    {
        super(game);
        _points = points;
    }

    public GameOverState(Game game, List<Entity> entities, int points)
    {
        super(game, entities);
        _points = points;
        background = (Background)entities.get(0);
        init();
    }


    @Override
    protected void init()
    {
        BlinkingEntity playAgain  = new BlinkingEntity(0, 1396, Assets.playAgain, game.getGraphics());
        addEntity(playAgain);

        Sprite gameOver  = new Sprite(0, 364, Assets.gameOver, game.getGraphics());
        addEntity(gameOver);

        Points points = new Points(0, 1000, game.getGraphics());
        points.setCentered();
        addEntity(points);

        for(Entity e : entities)
            e.setCenteredX();
    }

    @Override
    public void update(float deltaTime)
    {
        super.update(deltaTime);
    }

    @Override
    public void render(float deltaTime)
    {
        game.getGraphics().clear(0x0000FF00);
        super.render(deltaTime);
    }
}
