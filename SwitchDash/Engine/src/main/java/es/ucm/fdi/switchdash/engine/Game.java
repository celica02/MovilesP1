package es.ucm.fdi.switchdash.engine;

/**
 * Interface that brings together all other interfaces.
 */
public interface Game
{
    /**
     *
     * @return the graphics engine instance
     */
    public Graphics getGraphics();

    /**
     *
     * @return the input manager instance
     */
    public Input getInput();


    public FileIO getFileIO();

    public GameState getStartState();
    public  GameState getCurrentState();
    public void setState(GameState state);
}
