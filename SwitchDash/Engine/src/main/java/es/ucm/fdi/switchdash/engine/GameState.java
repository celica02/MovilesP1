package es.ucm.fdi.switchdash.engine;

import es.ucm.fdi.switchdash.engine.Input.InputEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class GameState
{
    protected final Game game;

    protected List<Entity> entities;

    public GameState(Game game)
    {
        this.game = game;
        entities = new ArrayList<>();
        init();
    }

    protected abstract void init();

    public void update(float deltaTime)
    {
        for(Entity e: entities)
            e.update(deltaTime);
    }
    public void render(float deltaTime)
    {
        for(Entity e: entities)
            e.render(deltaTime);
    }
    public void handleInput(float deltaTime)
    {
        List<InputEvent> events = game.getInput().getInputEvents();

        for(Entity e: entities)
            e.handleInput(events, deltaTime);
    }

    public void addEntity(Entity e) { entities.add(e); }

    public abstract void pause();
    public abstract void resume();

    public abstract void dispose();
}
