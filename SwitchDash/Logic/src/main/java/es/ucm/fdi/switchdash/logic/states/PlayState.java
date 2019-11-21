package es.ucm.fdi.switchdash.logic.states;

import es.ucm.fdi.switchdash.engine.Entity;
import es.ucm.fdi.switchdash.engine.Game;
import es.ucm.fdi.switchdash.engine.GameState;
import es.ucm.fdi.switchdash.logic.Assets;
import es.ucm.fdi.switchdash.engine.Sprite;
import es.ucm.fdi.switchdash.logic.entities.Background;
import es.ucm.fdi.switchdash.logic.entities.Ball;
import es.ucm.fdi.switchdash.logic.entities.BallManager;
import es.ucm.fdi.switchdash.logic.entities.Player;
import es.ucm.fdi.switchdash.logic.entities.Points;

public class PlayState extends GameState
{

    public PlayState(Game game)
    {
        super(game);
    }

    @Override
    protected void init()
    {
        Background arrowsBackground = new Background(game.getGraphics());
        arrowsBackground.init();
        arrowsBackground.setAlpha(0.7f);
        addEntity(arrowsBackground);

        Sprite logo = new Sprite(0, 356, Assets.logo, game.getGraphics());
        addEntity(logo);

        Player player = new Player(0, 1200, Assets.players, game.getGraphics(), 2, 1);
        player.setActiveSprite(1, 0);
        addEntity(player);


        BallManager ballManager = new BallManager(1,-10, player, game.getGraphics());
        addEntity(ballManager);

        Points points = new Points(950, 100, game.getGraphics());
        addEntity(points);


        for (Entity e: entities)
            e.setCenteredX();
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
