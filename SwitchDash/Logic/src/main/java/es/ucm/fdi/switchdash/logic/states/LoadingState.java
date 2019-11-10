package es.ucm.fdi.switchdash.logic.states;

import es.ucm.fdi.switchdash.engine.Game;
import es.ucm.fdi.switchdash.engine.GameState;
import es.ucm.fdi.switchdash.engine.Graphics;
import es.ucm.fdi.switchdash.engine.Graphics.ImageFormat;
import es.ucm.fdi.switchdash.logic.Assets;

public class LoadingState extends GameState
{
    public LoadingState(Game game)
    {
        super(game);
    }


    @Override
    protected void init()
    {
        Graphics g = game.getGraphics();
        Assets.logo = g.newImage("switchdashlogo.png", ImageFormat.ARGB4444);
        Assets.instructions = g.newImage("instructions.png", ImageFormat.ARGB4444);
        Assets.howToPlay = g.newImage("howtoplay.png", ImageFormat.ARGB4444);
        Assets.tapToPlay = g.newImage("taptoplay.png", ImageFormat.ARGB4444);
        Assets.gameOver = g.newImage("gameover.png", ImageFormat.ARGB4444);
        Assets.playAgain = g.newImage("playagain.png", ImageFormat.ARGB4444);
        Assets.arrowsBackground = g.newImage("arrowsbackground.png", ImageFormat.ARGB4444);
        Assets.white = g.newImage("white.png", ImageFormat.ARGB4444);

        Assets.scoreFont = g.newImage("scorefont.png", ImageFormat.ARGB4444);
        Assets.backgrounds = g.newImage("backgrounds.png", ImageFormat.ARGB4444);
        Assets.balls = g.newImage("balls.png", ImageFormat.ARGB4444);
        Assets.players = g.newImage("players.png", ImageFormat.ARGB4444);
        Assets.buttons = g.newImage("buttons.png", ImageFormat.ARGB4444);
    }

    @Override
    public void update(float deltaTime)
    {
        // Loading Screen
        game.setState(new MainMenuState(game));
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
