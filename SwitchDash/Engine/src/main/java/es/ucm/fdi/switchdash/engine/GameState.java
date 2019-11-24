package es.ucm.fdi.switchdash.engine;

import es.ucm.fdi.switchdash.engine.Input.TouchEvent;

import java.util.ArrayList;
import java.util.List;


/**
 * Estado de juego. Contiene una lista de entidades, las cuales actualiza mientras el estado esté activo
 */
public abstract class GameState
{
    protected final Game game;

    protected List<Entity> entities;
    protected List<TouchEvent> touchEvents;
    protected List<Input.KeyboardEvent> keyEvents;


    // ---------- CONSTRUCTORAS ---------- //

    public GameState(Game game)
    {
        this.game = game;
        entities = new ArrayList<>();
        init();
    }

    // Crea el nuevo estado con una lista de entidades inicial
    public GameState(Game game, List<Entity> entities) {
        this.game = game;
        this.entities = new ArrayList<>();
        this.entities.addAll(entities);
    }

    /**
     * Inicializa el estado
     */
    protected abstract void init();

    /**
     * Actualiza las entidades del estado
     *
     * @param deltaTime delta time del juego
     */
    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); i++)
            entities.get(i).update(deltaTime);
    }

    /**
     * Renderiza las entidades del estado
     *
     * @param deltaTime delta time del juego
     */
    public void render(float deltaTime) {
        for (int i = 0; i < entities.size(); i++)
            entities.get(i).render(deltaTime);
    }

    /**
     * Obtiene los nuevos eventos de pulsación y teclado y los traspasa a las entidades del estado
     *
     * @param deltaTime delta time del juego
     */
    public void handleInput(float deltaTime) {
        touchEvents = game.getInput().getTouchEvents();
        keyEvents = game.getInput().getKeyEvents();

        for (int i = 0; i < entities.size(); i++)
            entities.get(i).handleInput(touchEvents, keyEvents, deltaTime);
    }

    /**
     * Añade una nueva entidad al estado
     *
     * @param e la entidad a añadir
     */
    public void addEntity(Entity e) {
        e.setID(entities.size());
        entities.add(e);
    }

    public void resume() {}
    public void pause() {}
    public void dispose(){ game.getGraphics().dispose(); }
}
