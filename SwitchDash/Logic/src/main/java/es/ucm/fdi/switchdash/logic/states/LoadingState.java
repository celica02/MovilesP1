package es.ucm.fdi.switchdash.logic.states;

import es.ucm.fdi.switchdash.engine.Game;
import es.ucm.fdi.switchdash.engine.GameState;
import es.ucm.fdi.switchdash.engine.Graphics;

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
        Assets.logo = g.newImage("switchdashlogo.png");
        Assets.instructions = g.newImage("instructions.png");
        Assets.howToPlay = g.newImage("howtoplay.png");
        Assets.tapToPlay = g.newImage("taptoplay.png");
        Assets.gameOver = g.newImage("gameover.png");
        Assets.playAgain = g.newImage("playagain.png");
        Assets.arrowsBackground = g.newImage("arrowsbackground.png");
        Assets.white = g.newImage("white.png");

        Assets.scoreFont = g.newImage("scorefont.png");
        Assets.backgrounds = g.newImage("backgrounds.png");
        Assets.balls = g.newImage("balls.png");
        Assets.players = g.newImage("players.png");
        Assets.buttons = g.newImage("buttons.png");
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
