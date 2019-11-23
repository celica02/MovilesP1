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

    public GameState(Game game, List<Entity> entities)
    {
        this.game = game;
        this.entities = new ArrayList<>();
        init();

        this.entities.addAll(entities);
    }

    protected abstract void init();

    public void update(float deltaTime)
    {
        for(int i = 0; i < entities.size(); i++)
            entities.get(i).update(deltaTime);
    }
    public void render(float deltaTime)
    {
        for(int i = 0; i < entities.size(); i++)
            entities.get(i).render(deltaTime);
    }
    public void handleInput(float deltaTime)
    {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        List<Input.KeyboardEvent> keyEvents = game.getInput().getKeyEvents();

        for(int i = 0; i < entities.size(); i++)
            entities.get(i).handleInput(touchEvents, keyEvents, deltaTime);
    }

    public void addEntity(Entity e) {
        e.setID(entities.size());
        entities.add(e);
    }


    public void resume() {}
    public void pause() {}
    public void dispose(){ game.getGraphics().dispose(); }
}
