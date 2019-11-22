package es.ucm.fdi.switchdash.logic.states;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.switchdash.engine.Entity;
import es.ucm.fdi.switchdash.engine.Game;
import es.ucm.fdi.switchdash.engine.GameState;
import es.ucm.fdi.switchdash.logic.Assets;
import es.ucm.fdi.switchdash.logic.entities.Background;
import es.ucm.fdi.switchdash.logic.entities.Ball;
import es.ucm.fdi.switchdash.logic.entities.BallManager;
import es.ucm.fdi.switchdash.logic.entities.Player;
import es.ucm.fdi.switchdash.logic.entities.Points;

public class PlayState extends GameState
{
    private Background arrowsBackground;

    private BallManager ballMgr;
    private Player player;
    private Points points;

    private boolean gameOver = false;

    private float speed;
    private float maxSpeed;
    private float speedIncrement;
    private float timeToReach;

    private float gameOverTime;

    public PlayState(Game game)
    {
        super(game);
    }

    @Override
    protected void init()
    {
         speed = 400;
         maxSpeed = 1800;
         timeToReach = 20;

         speedIncrement = (maxSpeed - speed) / timeToReach;

         gameOverTime = 2;

        arrowsBackground = new Background(speed, game.getGraphics());
        arrowsBackground.init();
        arrowsBackground.setAlpha(0.7f);
        addEntity(arrowsBackground);

        player = new Player(0, 1200, Assets.players, game.getGraphics(), 2, 1);
        addEntity(player);

        ballMgr = new BallManager(1f, 0.2f, timeToReach, speed,0, player, game.getGraphics(), this);
        addEntity(ballMgr);

        points = new Points(game.getGraphics().getWidth() - 5, 100, game.getGraphics());

        for(Entity e : entities)
            e.setCenteredX();

        addEntity(points);

        speed = 450;
    }

    @Override
    public void update(float deltaTime)
    {
        super.update(deltaTime);

        if(speed < maxSpeed)
        {
            speed += (speedIncrement * deltaTime);
            arrowsBackground.setSpeed(speed);
            ballMgr.setSpeed(speed);
        }

        if (!gameOver)
            checkCollision(ballMgr.getNextBall());
        else
            gameOver(deltaTime);
    }



    private void gameOver(float deltaTime)
    {
        gameOverTime -= deltaTime;

        if(gameOverTime <= 0) {
            List<Entity> ents = new ArrayList<>();
            ents.add(arrowsBackground);
            game.setState(new GameOverState(game, ents, points.getPoints()));
        }
    }


    private void checkCollision(Ball b)
    {
        if (b.isActive() && player.isActive() && b.collides(player))
        {
            if(b.getCurrentColor() != player.getCurrentColor())
            {
                gameOver = true;
                player.setActive(false);
                player.setVisible(false);
            }
            else
            {
                ballMgr.ballDestroyed(b);
                points.increasePoints(1);
            }
        }
    }

    @Override
    public void render(float deltaTime)
    {
        game.getGraphics().clear(0x0000FF00);
        super.render(deltaTime);
    }
}
