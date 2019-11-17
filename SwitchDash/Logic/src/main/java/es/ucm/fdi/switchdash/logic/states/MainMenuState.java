package es.ucm.fdi.switchdash.logic.states;

import es.ucm.fdi.switchdash.engine.Entity;
import es.ucm.fdi.switchdash.engine.Game;
import es.ucm.fdi.switchdash.engine.GameState;
import es.ucm.fdi.switchdash.logic.Assets;
import es.ucm.fdi.switchdash.logic.entities.Sprite;
import es.ucm.fdi.switchdash.logic.entities.SpriteSheet;

public class MainMenuState extends GameState
{

    public MainMenuState(Game game)
    {
        super(game);
    }

    @Override
    protected void init()
    {
        Sprite logo = new Sprite(0, 356, Assets.logo, game.getGraphics());

        SpriteSheet player = new SpriteSheet(0, 700, Assets.players, game.getGraphics(), 2, 1);
        player.setActiveSprite(1, 0);

        addEntity(player);
        addEntity(logo);

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
