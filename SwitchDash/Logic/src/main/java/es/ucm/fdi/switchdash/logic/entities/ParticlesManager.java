package es.ucm.fdi.switchdash.logic.entities;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

import es.ucm.fdi.switchdash.engine.Entity;
import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.logic.Assets;

/**
 * Mánager de las pelotas.
 * Actualiza y spawnea las partículas que se crean al destruirse las pelotas cuando colisionan con la pala.
 */
public class ParticlesManager extends Entity {

    private int minPart = 4, maxPart = 9;

    private Deque<Particle> pool = new ArrayDeque<>();
    private Deque<Particle> particles = new ArrayDeque<>();

    // ---------- CONSTRUCTORA ---------- //

    public ParticlesManager(Graphics graphics)
    {
        super(graphics);
    }

    // ---------- FUNCIONES ---------- //

    /**
     * Genera un número aleatorio de partículas en el rango mínimo y máximo
     *
     * @param _posY posción en la Y donde van a crearse
     * @param color color que tendrán las partículas. Mismo color que la bola que se destruye
     */
    public void createParticles(float _posY, int color){

        int nParticles = new Random().nextInt(maxPart - minPart +1) + minPart;
        for(int i =0; i< nParticles; i++)
            spawnParticle(_posY, color);
    }

    /**
     * Spawnea una nueva partícula
     *
     * @param _posY posición donde se creará
     * @param color color de la partícula
     */
    private void spawnParticle(float _posY, int color)
    {
        Particle particle;
        // 1) Comprueba si la pool de partículas inactivas no está vacía, para reutilizar una de ellas.
        if(!pool.isEmpty())
        {
            particle = pool.getFirst(); pool.remove();
            particle.reset(0, _posY);
            particle.setVelocity(randomDirX(), randomDirY());
        }
        // Si no hubiera, entonces simplemente creamos una nueva.
        else
            particle = new Particle(posX, _posY, randomDirX(),randomDirY(), Assets.balls, g, 2, 10);

        // 2) Creamos la nueva partícula.
        newParticle(particle, color, _posY);
    }

    /**
     * @return Dirección en X en la que se moverá la partícula
     */
    private float randomDirX()
    {
        float dirX = new Random().nextInt(150);
        float a = (float)Math.random();
        int sign = (a >= 0.5) ? -1 : 1;
        return dirX * sign;
    }

    /**
     * @return Dirección en Y en la que se moverá la partícula
     */
    private float randomDirY()
    {
        float dirY = new Random().nextInt(150);
        return -dirY;
    }

    /**
     * Crea y resetea la patícula con el color y la posición oportunos. Y la escala más pequeña,
     * a un tamaño aleatorio entre el tamaño actual y la mitad
     *
     * @param particle partícula a resetear
     * @param color color de la partícula
     * @param posY posición donde se colocará la partícula
     */
    private void newParticle(Particle particle, int color, float posY)
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

    /**
     * "Destruye" una partícula y la añade a la pool de partículas inactivas
     * @param particle partícula destruida
     */
    public void particleDestroyed(Particle particle)
    {
        particles.remove();
        particle.setActive(false);
        particle.setVisible(false);
        pool.addLast(particle);
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

