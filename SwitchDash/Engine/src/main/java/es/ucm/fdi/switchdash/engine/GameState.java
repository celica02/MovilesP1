package es.ucm.fdi.switchdash.engine;

public abstract class GameState
{
    protected final Game game;

    public GameState(Game game)
    {
        this.game = game;
    }

    public abstract void update(float deltaTime);
    public abstract void render(float deltaTime);

    public abstract void pause();
    public abstract void resume();

    public abstract void dispose();
}
