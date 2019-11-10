package es.ucm.fdi.switchdash.logic.states;

import es.ucm.fdi.switchdash.engine.Entity;
import es.ucm.fdi.switchdash.engine.Game;
import es.ucm.fdi.switchdash.engine.GameState;
import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Input;
import es.ucm.fdi.switchdash.engine.Input.InputEvent;
import es.ucm.fdi.switchdash.engine.utils.MyRect;
import es.ucm.fdi.switchdash.logic.Assets;
import es.ucm.fdi.switchdash.logic.entities.MainMenu;

public class MainMenuState extends GameState
{

    public MainMenuState(Game game)
    {
        super(game);
    }

    @Override
    protected void init()
    {
        MyRect dest = new MyRect(0, 0, Assets.logo.getWidth()/2,Assets.logo.getWidth()/2);
        MainMenu mainMenu = new MainMenu(Assets.logo, Assets.logo.getFullRect(), dest, game.getGraphics());

        addEntity(mainMenu);
    }



    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
