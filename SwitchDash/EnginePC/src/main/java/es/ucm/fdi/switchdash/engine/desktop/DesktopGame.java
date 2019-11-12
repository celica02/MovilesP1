package es.ucm.fdi.switchdash.engine.desktop;

import es.ucm.fdi.switchdash.engine.FileIO;
import es.ucm.fdi.switchdash.engine.Game;
import es.ucm.fdi.switchdash.engine.GameState;
import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Input;

public class DesktopGame implements Game {
    DesktopGraphics graphics;
    DesktopInput input;
    GameState gameState;

    public void DesktopGame(){
        //graphics.newImage();
    }

    @Override
    public Graphics getGraphics() {
        return graphics;
    }

    @Override
    public Input getInput() {
        return input;
    }

    @Override
    public FileIO getFileIO() {
        return null;
    }

    @Override
    public GameState getStartState() {
        return null;
    }

    @Override
    public GameState getCurrentState() {
        return gameState;
    }

    @Override
    public void setState(GameState state) {
        if (gameState == null)
            throw new IllegalArgumentException("GameState must not be null");

        this.gameState.pause();
        this.gameState.dispose();

        state.resume();
        state.update(0);

        this.gameState = state;    }
}
