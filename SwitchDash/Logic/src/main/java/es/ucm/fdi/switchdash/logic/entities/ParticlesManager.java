package es.ucm.fdi.switchdash.logic.entities;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

import es.ucm.fdi.switchdash.engine.Entity;
import es.ucm.fdi.switchdash.engine.GameState;
import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.logic.Assets;

public class ParticlesManager extends Entity {
    private float speed;
    private float ballDistance;

    private float spawnPosY;

    private int lastColour = 0;

    private int minPart = 4, maxPart = 9;

    private GameState state;

    private Deque<Particle> pool = new ArrayDeque<>();

    private Deque<Particle> particles = new ArrayDeque<>();

    public ParticlesManager(float ballSpeed, float distanceDiff, float _posY, Graphics graphics, GameState gameState)
    {
        super(graphics);

        speed = ballSpeed;
        ballDistance = distanceDiff;

        spawnPosY = _posY;

        state = gameState;

//        spawnParticle();
    }

    public ParticlesManager(float spawnPos, Graphics graphics)
    {
        super(graphics);

        spawnPosY = spawnPos;

//        spawnParticle();
    }

    public void createParticles(float _posY, int color){
        System.out.println(_posY);

        int nParticles = new Random().nextInt(maxPart - minPart +1) + minPart;
        for(int i =0; i< nParticles; i++)
            spawnParticle(_posY, color);
    }

    private void spawnParticle(float _posY, int color)
    {
        Particle particle;
        if(!pool.isEmpty())
        {
            particle = pool.getFirst(); pool.remove();
            particle.reset(0, _posY);
            particle.setVelocity(randomDirX(), randomDirY());
        }
        else
            particle = new Particle(posX, _posY, randomDirX(),randomDirY(), Assets.balls, g, 2, 10);

        newBall(particle, color, _posY);
    }
    private float randomDirX() {
        float dirX = new Random().nextInt(150);
        float a = (float)Math.random();
        int sign = (a >= 0.5) ? -1 : 1;
        return dirX * sign;
    }

    private float randomDirY() {
        float dirY = new Random().nextInt(150);
        return -dirY;
    }
    private void newBall(Particle particle, int color, float posY)
    {
        particles.addLast(particle);

        particle.setActiveSprite(color, particle.getActiveCol());
        particle.setCenteredX();
        particle.setPosY(posY);
        particle.setAlpha(0.5f);
        float a = (float)Math.random() + 1;
        particle.setHeight(particle.getHeight()/a);
        particle.setWidth(particle.getWidth()/a);
    }

    public void particleDestroyed(Particle particle)
    {
        particles.remove();
        particle.setActive(false);
        particle.setVisible(false);
        pool.addLast(particle);
    }

    public Particle getNextBall() { return particles.getFirst(); }

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


    @Override
    public void updateEntity(float deltaTime)
    {
        for (Particle p: particles) {
            p.updateEntity(deltaTime);
            if(!p.isActive())
                particleDestroyed(p);
        }
    }

    @Override
    public void render(float deltaTime)
    {
        for (Particle p: particles)
            p.render(deltaTime);
    }
}

