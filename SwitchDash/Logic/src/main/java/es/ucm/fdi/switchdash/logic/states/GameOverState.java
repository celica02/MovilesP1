package es.ucm.fdi.switchdash.logic.states;

import java.util.List;

import es.ucm.fdi.switchdash.engine.Entity;
import es.ucm.fdi.switchdash.engine.Game;
import es.ucm.fdi.switchdash.engine.GameState;
import es.ucm.fdi.switchdash.engine.Sprite;
import es.ucm.fdi.switchdash.logic.Assets;

public class GameOverState extends GameState
{
    private Sprite playAgain;
    private int points;

    private boolean alphaUp;

    public GameOverState(Game game, int points)
    {
        super(game);
        this.points = points;
    }

    public GameOverState(Game game, List<Entity> entities, int points)
    {
        super(game, entities);
        this.points = points;
    }


    @Override
    protected void init()
    {
        playAgain  = new Sprite(0, 1396, Assets.playAgain, game.getGraphics());
        addEntity(playAgain);

        Sprite gameOver  = new Sprite(0, 364, Assets.gameOver, game.getGraphics());
        addEntity(gameOver);

        for(Entity e : entities)
            e.setCenteredX();
    }

    @Override
    public void update(float deltaTime)
    {
        oscillateAlpha(playAgain, deltaTime);
        super.update(deltaTime);
    }


    private void oscillateAlpha(Sprite sprite, float deltaTime)
    {
        float alphaIncrement = 0.8f;
        float alpha = sprite.getAlpha();

        if(alphaUp)
            alpha += alphaIncrement * deltaTime;
        else
            alpha -= alphaIncrement * deltaTime;

        if(alpha < 0.0f || alpha > 1.0f)
            alphaUp = !alphaUp;

        sprite.setAlpha(alpha);
    }

    @Override
    public void render(float deltaTime)
    {
        game.getGraphics().clear(0x0000FF00);
        super.render(deltaTime);
    }
}
