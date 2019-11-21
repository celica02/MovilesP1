package es.ucm.fdi.switchdash.logic.states;

import es.ucm.fdi.switchdash.engine.Game;
import es.ucm.fdi.switchdash.engine.GameState;
import es.ucm.fdi.switchdash.engine.Graphics;

import es.ucm.fdi.switchdash.logic.Assets;

public class LoadingState extends GameState
{

    private final String spritesPath = "Sprites/";

    public LoadingState(Game game)
    {
        super(game);
    }


    @Override
    protected void init()
    {
        Graphics g = game.getGraphics();
        Assets.logo = g.newImage(spritesPath + "switchdashlogo.png");
        Assets.instructions = g.newImage(spritesPath + "instructions.png");
        Assets.howToPlay = g.newImage(spritesPath + "howtoplay.png");
        Assets.tapToPlay = g.newImage(spritesPath + "taptoplay.png");
        Assets.gameOver = g.newImage(spritesPath + "gameover.png");
        Assets.playAgain = g.newImage(spritesPath + "playagain.png");
        Assets.arrowsBackground = g.newImage(spritesPath + "arrowsbackground.png");
        Assets.white = g.newImage(spritesPath +"white.png");

        Assets.scoreFont = g.newImage(spritesPath + "scorefont.png");
        Assets.backgrounds = g.newImage(spritesPath + "backgrounds.png");
        Assets.balls = g.newImage(spritesPath + "balls.png");
        Assets.players = g.newImage(spritesPath + "players.png");
        Assets.buttons = g.newImage(spritesPath + "buttons.png");
    }

    @Override
    public void update(float deltaTime)
    {
        // Loading Screen
        game.setState(new PlayState(game));
    }

    @Override
    public void render(float deltaTime) { }

    @Override
    public void handleInput(float deltaTime) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void dispose() { }
}
