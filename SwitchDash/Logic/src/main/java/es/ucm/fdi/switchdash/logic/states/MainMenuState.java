package es.ucm.fdi.switchdash.logic.states;

import es.ucm.fdi.switchdash.engine.Entity;
import es.ucm.fdi.switchdash.engine.Game;
import es.ucm.fdi.switchdash.engine.GameState;
import es.ucm.fdi.switchdash.logic.Assets;
import es.ucm.fdi.switchdash.engine.Sprite;
import es.ucm.fdi.switchdash.engine.SpriteSheet;
import es.ucm.fdi.switchdash.logic.entities.ArrowsBG;

public class MainMenuState extends GameState
{

    public MainMenuState(Game game)
    {
        super(game);
    }

    @Override
    protected void init()
    {
        ArrowsBG arrowsBackground = new ArrowsBG(0, -500, Assets.arrowsBackground, game.getGraphics());
        arrowsBackground.setAlpha(0.7f);
        addEntity(arrowsBackground);

        Sprite logo = new Sprite(0, 356, Assets.logo, game.getGraphics());
        addEntity(logo);

        SpriteSheet player = new SpriteSheet(0, 1200, Assets.players, game.getGraphics(), 2, 1);
        player.setActiveSprite(1, 0);
        addEntity(player);

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
