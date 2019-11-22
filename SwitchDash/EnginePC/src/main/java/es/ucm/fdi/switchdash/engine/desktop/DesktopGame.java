package es.ucm.fdi.switchdash.engine.desktop;


import es.ucm.fdi.switchdash.engine.FileIO;
import es.ucm.fdi.switchdash.engine.Game;
import es.ucm.fdi.switchdash.engine.GameState;
import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Input;

public class DesktopGame implements Game
{
    DesktopGraphics graphics;
    DesktopInput input;
    GameState gameState;

    java.awt.image.BufferStrategy renderBuffer;

    public DesktopGame(String title, int windowWidth, int windowHeight, int resWidth, int resHeight)
    {
        Window window = new Window(title);
        window.init(windowWidth, windowHeight);


        try {
            window.createBufferStrategy(2);
        }
        catch(Exception e) {
            System.err.println(e);
        }

        renderBuffer = window.getBufferStrategy();

        graphics = new DesktopGraphics(renderBuffer.getDrawGraphics(), window, resWidth, resHeight);
        input = new DesktopInput(window, graphics);

        gameState = getStartState();
    }


    public void run()
    {
        long startTime = System.nanoTime();

        while (true)
        {
            float deltaTime = (System.nanoTime() - startTime) / 1000000000.0f;
            startTime = System.nanoTime();

            update(deltaTime);
            render(deltaTime);
            handleInput(deltaTime);
        }
    }


    private void update(float deltaTime)
    {
        getCurrentState().update(deltaTime);
    }

   private void render(float deltaTime)
    {
        do {
            do {
                graphics.setGraphics(renderBuffer.getDrawGraphics());
                try {
                    getCurrentState().render(deltaTime);
                }
                finally {
                    graphics.dispose();
                }
            } while(renderBuffer.contentsRestored());
            renderBuffer.show();
        } while(renderBuffer.contentsLost());
    }

    private void handleInput(float deltaTime)
    {
        getCurrentState().handleInput(deltaTime);
    }

    @Override
    public Graphics getGraphics() { return graphics; }

    @Override
    public Input getInput() { return input; }

    @Override
    public FileIO getFileIO() { return null; }

    @Override
    public GameState getStartState() { return null; }

    @Override
    public GameState getCurrentState() { return gameState; }

    @Override
    public void setState(GameState state)
    {
        if (gameState == null)
            throw new IllegalArgumentException("GameState must not be null");

        this.gameState.dispose();

        this.gameState = state;

        this.gameState.update(0);
    }
}
