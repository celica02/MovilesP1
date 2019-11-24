package es.ucm.fdi.switchdash.engine;

/**
     * Interfaz que aglomera todas las demás interfaces
 */
public interface Game
{
    /**
     * @return la instancia del motor gráfico
     */
    Graphics getGraphics();

    /**
     * @return la instancia del Input manager
     */
    Input getInput();


    FileIO getFileIO();

    /**
     * @return el estado de comienzo de la app.
     */
    GameState getStartState();
    /**
     * @return el estado actual corriendo en la app.
     */
    GameState getCurrentState();
    /**
     * Selecciona el nuevo estado que va a correr la app.
     *
     * @param state el estado nuevo que se va a ejecutar
     */
    void setState(GameState state);
}
