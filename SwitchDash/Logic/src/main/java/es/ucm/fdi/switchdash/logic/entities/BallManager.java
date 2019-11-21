package es.ucm.fdi.switchdash.logic.entities;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import es.ucm.fdi.switchdash.engine.Entity;
import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.logic.Assets;

public class BallManager extends Entity
{
    float spawnTime;
    float timeCounter;

    float spawnPosY;

    int lastColour = 0;

    Entity player;

    private Deque<Ball> pool = new ArrayDeque<>();

    private List<Ball> balls = new ArrayList<>();

    public BallManager(float time,float spawnPos, Entity p, Graphics graphics)
    {
        super(graphics);
        spawnTime = time;
        timeCounter = time;

        spawnPosY = spawnPos;

        player = p;
    }

    private void spawnBall()
    {
        Ball ball;
        if(!pool.isEmpty())
        {
            ball = pool.getFirst();
            pool.remove();

            ball.reset(0, spawnPosY);
        }
        else
        {
            ball = new Ball(0, spawnPosY, Assets.balls, g, 2, 10);
            balls.add(ball);
        }
        ball.setActiveSprite(randomColour(), ball.getActiveCol());
        ball.setCenteredX();
    }

    public void ballDestroyed(Ball b)
    {
        b.setActive(false);
        b.setVisible(false);
        pool.addLast(b);
    }

    public List<Ball> getBalls() { return balls; }

    /**
     * Method that returns randomly witch color will be the new ball.
     * 70% of possibilities to be the same colour as the previous ball
     *
     * @return the new colour
     */
    private int randomColour()
    {
        int newC = 0;
        if (lastColour == 0) newC = 1;

        float a = (float)Math.random();

        return lastColour = a < 0.7 ? lastColour : newC;
    }

    private void spawnTimer(float deltaTime)
    {
        timeCounter -= deltaTime;

        if(timeCounter <= 0)
        {
            timeCounter = spawnTime;
            spawnBall();
        }
    }

    @Override
    public void updateEntity(float deltaTime)
    {
        spawnTimer(deltaTime);

        for (Ball b: balls)
        {
            b.updateEntity(deltaTime);

            if (b.collides(player))
                ballDestroyed(b);
        }
    }

    @Override
    public void render(float deltaTime)
    {
        for (Ball b: balls)
            b.renderEntity(deltaTime);
    }
}
