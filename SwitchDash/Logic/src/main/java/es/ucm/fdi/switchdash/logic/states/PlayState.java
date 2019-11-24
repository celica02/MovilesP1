package es.ucm.fdi.switchdash.logic.states;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.switchdash.engine.Entity;
import es.ucm.fdi.switchdash.engine.Game;
import es.ucm.fdi.switchdash.engine.GameState;
import es.ucm.fdi.switchdash.engine.Input;
import es.ucm.fdi.switchdash.engine.Sprite;
import es.ucm.fdi.switchdash.logic.Assets;
import es.ucm.fdi.switchdash.logic.entities.Background;
import es.ucm.fdi.switchdash.logic.entities.Ball;
import es.ucm.fdi.switchdash.logic.entities.BallManager;
import es.ucm.fdi.switchdash.logic.entities.Flash;
import es.ucm.fdi.switchdash.logic.entities.ParticlesManager;
import es.ucm.fdi.switchdash.logic.entities.Player;
import es.ucm.fdi.switchdash.logic.entities.Points;
import es.ucm.fdi.switchdash.logic.entities.Text;


/**
 * Estado de la partida.
 */
public class PlayState extends GameState
{
    private Background background;

    private BallManager ballMgr;
    private Player player;
    private Points pointsTxt;
    private ParticlesManager particlesManager;

    private int ballCounter = 0;

    private boolean gameOver = false;

    private float speedIncrement;

    private float gameOverTime;

    // ---------- CONSTRUTORAS ---------- //

    public PlayState(Game game)
    {
        super(game);
    }

    public PlayState(Game game, List<Entity> entities) {
        super(game, entities);
    }

    @Override
    protected void init()
    {
         speedIncrement = 90;
         gameOverTime = 2;

        Flash flash = new Flash(game.getGraphics());
        addEntity(flash);

        background = new Background(384, game.getGraphics());
        addEntity(background);

        player = new Player(0, 1200, Assets.players, game.getGraphics(), 2, 1);
        addEntity(player);

        ballMgr = new BallManager(430, 395, 0, player, game.getGraphics(), this);
        addEntity(ballMgr);

        pointsTxt = new Points(game.getGraphics().getWidth() - 100, 100, game.getGraphics());

        particlesManager = new ParticlesManager(game.getGraphics());
        addEntity( particlesManager);

        for(Entity e : entities)
            e.setCenteredX();

        addEntity(pointsTxt);
    }


    // ---------- FUNCIONES ---------- //

    @Override
    public void update(float deltaTime)
    {
        super.update(deltaTime);

        if (!gameOver)
            checkCollision(ballMgr.getNextBall());
        else
            gameOver(deltaTime);
    }


    /**
     * Comprueba que haya colisión entre la primera pelota (la más baja) y el jugador.
     *
     * @param b última pelota
     */
    private void checkCollision(Ball b)
    {
        if (b.isActive() && player.isActive() && b.collides(player))
        {
            // 1) Miramos que sean del mismo color

            // a) Si no lo son, acaba la partida
            if(b.getCurrentColor() != player.getCurrentColor())
            {
                gameOver = true;
                player.setActive(false);
                player.setVisible(false);
            }
            // b) Si lo son, entonces destruimos la pelota e incrementamos los puntos
            else
            {
                ballMgr.ballDestroyed(b);
                pointsTxt.increasePoints(1);
                particlesManager.createParticles( b.getPosY() + (b.getHeight())/2, b.getCurrentColor());

                // c) Por último, hacemos que cada 10 pelotas destruidas aumente la velocidad de las mismas
                if(pointsTxt.getPoints() % 10 == 0)
                {
                    background.setSpeed(background.getSpeed() + speedIncrement);
                    ballMgr.setSpeed(ballMgr.getSpeed() + speedIncrement);
                }
            }
        }
    }

    /**
     * Espera unos segundos y luego termina la partida.
     *
     * @param deltaTime delta time del juego
     */
    private void gameOver(float deltaTime)
    {
        gameOverTime -= deltaTime;

        if(gameOverTime <= 0) {
            List<Entity> ents = new ArrayList<>();
            ents.add(background);
            game.setState(new GameOverState(game, ents, pointsTxt.getPoints()));
        }
    }




    @Override
    public void render(float deltaTime)
    {
        game.getGraphics().clear(background.getColor());
        super.render(deltaTime);
    }
}
