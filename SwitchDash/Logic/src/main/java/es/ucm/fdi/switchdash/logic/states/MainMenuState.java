package es.ucm.fdi.switchdash.logic.states;

import es.ucm.fdi.switchdash.engine.Entity;
import es.ucm.fdi.switchdash.engine.Game;
import es.ucm.fdi.switchdash.engine.GameState;
import es.ucm.fdi.switchdash.engine.utils.Pool;
import es.ucm.fdi.switchdash.logic.Assets;
import es.ucm.fdi.switchdash.engine.Sprite;
import es.ucm.fdi.switchdash.engine.SpriteSheet;
import es.ucm.fdi.switchdash.logic.entities.ArrowsBG;
import es.ucm.fdi.switchdash.logic.entities.Background;
import es.ucm.fdi.switchdash.logic.entities.Ball;
import es.ucm.fdi.switchdash.logic.entities.BallsLogic;
import es.ucm.fdi.switchdash.logic.entities.Player;
import es.ucm.fdi.switchdash.logic.entities.Points;

public class MainMenuState extends GameState
{
    float posIniBalls = 10;
    private Ball ball;

    public MainMenuState(Game game)
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


        BallsLogic balls = new BallsLogic(game.getGraphics());
        addEntity(balls);

        Points points = new Points(950, 100, game.getGraphics());
        addEntity(points);


        for (Entity e: entities)
            e.setCentered();
    }

    @Override
    public void update(float deltaTime){

        super.update(deltaTime);
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
