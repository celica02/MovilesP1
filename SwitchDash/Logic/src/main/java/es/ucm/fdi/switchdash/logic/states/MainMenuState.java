package es.ucm.fdi.switchdash.logic.states;

import es.ucm.fdi.switchdash.engine.Game;
import es.ucm.fdi.switchdash.engine.GameState;
import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.utils.MyRect;
import es.ucm.fdi.switchdash.logic.Assets;

public class MainMenuState extends GameState
{
    public MainMenuState(Game game)
    {
        super(game);
    }

    @Override
    public void update(float deltaTime)
    {

    }

    @Override
    public void render(float deltaTime)
    {
        Graphics g = game.getGraphics();

        //g.drawImage(Assets.logo, new MyRect(0, Assets.logo.getWidth(), 0, Assets.logo.getHeight()),
              //  new MyRect(0, Assets.logo.getWidth()/2,0, Assets.logo.getHeight()/2), 255);


        g.drawImage(Assets.logo, 50, 0, Assets.logo.getWidth()* 1/2, Assets.logo.getHeight()* 1/2);
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
