package es.ucm.fdi.switchdash.logic.entities;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import es.ucm.fdi.switchdash.engine.Entity;
import es.ucm.fdi.switchdash.engine.GameState;
import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.logic.Assets;

/**
 * Mánager de las pelotas.
 * Actualiza y spawnea las pelotas, y ofrece funcionalidad para su control y manejo.
 */
public class BallManager extends Entity
{
    private float speed;
    private float ballDistance;

    private float spawnPosY;

    private int lastColour = 0;

    private GameState state;

    private Deque<Ball> pool = new ArrayDeque<>();

    private Deque<Ball> balls = new ArrayDeque<>();

    // ---------- CONSTRUCTORAS ---------- //

    public BallManager(float ballSpeed, float distanceDiff, float spawnPos, Entity p, Graphics graphics, GameState gameState)
    {
        super(graphics);

        speed = ballSpeed;
        ballDistance = distanceDiff;

        spawnPosY = spawnPos;

        state = gameState;

        spawnBall();
    }

    // ---------- FUNCIONES ---------- //

    /**
     * Spawnea una nueva pelota
     */
    private void spawnBall()
    {
        Ball ball;
        // 1) Miramos si la pool de pelotas inactivas no está vacía para reutilizar una de ellas
        if(!pool.isEmpty())
        {
            ball = pool.getFirst(); pool.remove();
            ball.reset(0, spawnPosY);
            ball.setSpeed(speed);
        }
        // Si no hubiera, entonces simplemente creamos una nueva
        else
            ball = new Ball(0, spawnPosY, speed, Assets.balls, g, 2, 10);

        // 2) Creamos la nueva pelota con el color que le toque
        newBall(ball);
    }

    /**
     * Crea y resetea la pelota con el color oportuno
     *
     * @param ball pelota a spawnear
     */
    private void newBall(Ball ball)
    {
        balls.addLast(ball);

        ball.setActiveSprite(randomColour(), ball.getActiveCol());
        ball.setCenteredX();
        ball.setPosY(spawnPosY);
    }

    /**
     * "Destruye" una pelota y la añade a la pool de pelotas inactivas
     * @param b pelota destruida
     */
    public void ballDestroyed(Ball b)
    {
        balls.remove();
        b.setActive(false);
        b.setVisible(false);
        pool.addLast(b);
    }

    /**
     * @return obtiene la primera pelota (la más baja de todas)
     */
    public Ball getNextBall() { return balls.getFirst(); }

    /**
     * Devuelve el nuevo color de la pelota en base al color de la pelota anterior.
     * 70% de posibilidades de ser el mismo color,
     *
     * @return el nuevo color
     */
    private int randomColour()
    {
        int newC = 0;
        if (lastColour == 0) newC = 1;

        float a = (float)Math.random();

        return lastColour = a < 0.7 ? lastColour : newC;
    }

    /**
     * Comprueba cuándo hay que spawnear la siguiente pelota.
     * En este caso, en base a la diferencia de distancias entre las pelotas.
     */
    private void spawnTimer()
    {
        float pos =  balls.getLast().getPosY();

        if(pos >= ballDistance + spawnPosY)
            spawnBall();
    }


    /**
     * Cambia la velocidad de las pelotas.
     *
     * @param speed nueva velocidad de las pelotas
     */
    public void setSpeed(float speed) { this.speed = speed;   for (Ball b: balls)  b.setSpeed(speed); }
    /**
     * @return la velocidad actual de las pelotas
     */
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
