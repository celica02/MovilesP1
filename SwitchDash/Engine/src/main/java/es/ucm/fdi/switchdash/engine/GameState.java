package es.ucm.fdi.switchdash.engine;

import es.ucm.fdi.switchdash.engine.Input.TouchEvent;

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

   /* public GameState(Game game, List<Entity> ents)
    {
        this.game = game;

        entities
    }*/

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
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        List<Input.KeyboardEvent> keyEvents = game.getInput().getKeyEvents();

        for(Entity e: entities)
            e.handleInput(touchEvents, keyEvents, deltaTime);
    }

    public void addEntity(Entity e) {
        e.setID(entities.size());
        entities.add(e);
    }

    public abstract void pause();
    public abstract void resume();

    public void dispose(){ game.getGraphics().dispose(); }
}
