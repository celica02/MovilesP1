package es.ucm.fdi.switchdash.logic.entities;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import es.ucm.fdi.switchdash.engine.Entity;
import es.ucm.fdi.switchdash.engine.GameState;
import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.logic.Assets;

public class BallManager extends Entity
{
    private float speed;
    private float ballDistance;

    private float spawnPosY;

    private int lastColour = 0;

    private GameState state;

    private Deque<Ball> pool = new ArrayDeque<>();

    private Deque<Ball> balls = new ArrayDeque<>();

    public BallManager(float ballSpeed, float distanceDiff, float spawnPos, Entity p, Graphics graphics, GameState gameState)
    {
        super(graphics);

        speed = ballSpeed;
        ballDistance = distanceDiff;

        spawnPosY = spawnPos;

        state = gameState;

        spawnBall();
    }

    private void spawnBall()
    {
        Ball ball;
        if(!pool.isEmpty())
        {
            ball = pool.getFirst(); pool.remove();
            ball.reset(0, spawnPosY);
            ball.setSpeed(speed);
        }
        else
            ball = new Ball(0, spawnPosY, speed, Assets.balls, g, 2, 10);

        newBall(ball);
    }

    private void newBall(Ball ball)
    {
        balls.addLast(ball);

        ball.setActiveSprite(randomColour(), ball.getActiveCol());
        ball.setCenteredX();
        ball.setPosY(spawnPosY);
    }

    public void ballDestroyed(Ball b)
    {
        balls.remove();
        b.setActive(false);
        b.setVisible(false);
        pool.addLast(b);
    }

    public Ball getNextBall() { return balls.getFirst(); }

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

    private void spawnTimer()
    {
        float pos =  balls.getLast().getPosY();

        if(pos >= ballDistance + spawnPosY)
            spawnBall();
    }


    public void setSpeed(float speed) { this.speed = speed;   for (Ball b: balls)  b.setSpeed(speed); }
    public float getSpeed() { return speed; }

    @Override
    public void updateEntity(float deltaTime)
    {
        for (Ball b: balls)
            b.updateEntity(deltaTime);

        spawnTimer();
    }

    @Override
    public void render(float deltaTime)
    {
        for (Ball b: balls)
            b.render(deltaTime);
    }
}
