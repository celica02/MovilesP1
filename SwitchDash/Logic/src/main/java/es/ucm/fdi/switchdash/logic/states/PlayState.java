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

    BallManager ballManager;
    Player player;
    Points points;

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

        player = new Player(0, 1200, Assets.players, game.getGraphics(), 2, 1);
        addEntity(player);

        ballManager = new BallManager(1,-10, player, game.getGraphics());
        addEntity(ballManager);

        points = new Points(game.getGraphics().getWidth() -10, 100, game.getGraphics());
        addEntity(points);

        for (Entity e: entities)
            e.setCenteredX();
    }

    @Override
    public void update(float deltaTime)
    {
        for (Ball b: ballManager.getBalls())
        {
            b.update(deltaTime);
            checkCollision(b);
        }
        super.update(deltaTime);
    }


    public void checkCollision(Ball b)
    {
        if (b.isActive() && b.collides(player))
        {
            if(b.getCurrentColor() != player.getCurrentColor())
            {
                System.out.println("Has perdido!");
            }

            ballManager.ballDestroyed(b);
            System.out.println("destroyed");
            points.increasePoints(1);
        }
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
