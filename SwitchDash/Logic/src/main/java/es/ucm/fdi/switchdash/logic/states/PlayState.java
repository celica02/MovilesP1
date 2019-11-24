package es.ucm.fdi.switchdash.logic.states;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.switchdash.engine.Entity;
import es.ucm.fdi.switchdash.engine.Game;
import es.ucm.fdi.switchdash.engine.GameState;
import es.ucm.fdi.switchdash.engine.Input;
import es.ucm.fdi.switchdash.logic.Assets;
import es.ucm.fdi.switchdash.logic.entities.Background;
import es.ucm.fdi.switchdash.logic.entities.Ball;
import es.ucm.fdi.switchdash.logic.entities.BallManager;
import es.ucm.fdi.switchdash.logic.entities.Player;
import es.ucm.fdi.switchdash.logic.entities.Points;
import es.ucm.fdi.switchdash.logic.entities.Text;

public class PlayState extends GameState
{
    private Background background;

    private BallManager ballMgr;
    private Player player;
    private Points pointsTxt;
//    private Text pointsTxt;
    private int points = 0;

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
         speed = 384;
         maxSpeed = 1800;
         timeToReach = 20;

         speedIncrement = (maxSpeed - speed) / timeToReach;

         gameOverTime = 2;

        background = new Background(speed, game.getGraphics());
        addEntity(background);

        player = new Player(0, 1200, Assets.players, game.getGraphics(), 2, 1);
        addEntity(player);

        ballMgr = new BallManager(1f, 0.2f, timeToReach, speed,0, player, game.getGraphics(), this);
        addEntity(ballMgr);

        pointsTxt = new Points(game.getGraphics().getWidth() - 100, 100, game.getGraphics());

        for(Entity e : entities)
            e.setCenteredX();

        addEntity(pointsTxt);

        speed = 450;
    }

    @Override
    public void update(float deltaTime)
    {
        super.update(deltaTime);

        if(speed < maxSpeed)
        {
            speed += (speedIncrement * deltaTime);
            background.setSpeed(speed);
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
            ents.add(background);
            game.setState(new GameOverState(game, ents, pointsTxt.getPoints()));
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
                pointsTxt.increasePoints(1);
            }
        }
    }

    @Override
    public void render(float deltaTime)
    {
        game.getGraphics().clear(background.getColor());
        super.render(deltaTime);
    }

    @Override
    public void handleInput(float deltaTime)
    {
        super.handleInput(deltaTime);

        if(touchEvents.size() > 0){ //Mira si se ha tocado la pantalla
            boolean touched = false;
            int i = 0;
            while(i < touchEvents.size() && !touched){
                if(touchEvents.get(i).type == Input.TouchEvent.DOWN) {
                    touched = true;
                    player.changeColor();
                }
                i++;
            }
        }//if
    }//handleInput
}
