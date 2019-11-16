package es.ucm.fdi.switchdash.logic.states;

import es.ucm.fdi.switchdash.engine.Entity;
import es.ucm.fdi.switchdash.engine.Game;
import es.ucm.fdi.switchdash.engine.GameState;
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
        MainMenu mainMenu = new MainMenu(Assets.logo, Assets.logo.getFullRect(), game.getGraphics());
        mainMenu.setSize(mainMenu.getWidth()/2,mainMenu.getHeight()/2);

        mainMenu.setPosY(200);

        MainMenu player = new MainMenu(Assets.players, Assets.players.getRect(0, 0,Assets.players.getWidth(),Assets.players.getHeight()/2), game.getGraphics());

       // player.setPosY(100);
        //addEntity(player);
        addEntity(mainMenu);

        for (Entity e: entities)
            e.setCentered();
    }


    @Override
    public void render(float deltaTime)
    {
        game.getGraphics().clear(0x0000FF00);
        super.render(deltaTime);
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
