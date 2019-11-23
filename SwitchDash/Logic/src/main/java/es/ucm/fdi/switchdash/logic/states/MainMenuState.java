package es.ucm.fdi.switchdash.logic.states;

import es.ucm.fdi.switchdash.engine.Entity;
import es.ucm.fdi.switchdash.engine.Game;
import es.ucm.fdi.switchdash.engine.GameState;
import es.ucm.fdi.switchdash.logic.Assets;
import es.ucm.fdi.switchdash.engine.Sprite;
import es.ucm.fdi.switchdash.logic.entities.ArrowsBG;
import es.ucm.fdi.switchdash.logic.entities.Background;
import es.ucm.fdi.switchdash.logic.entities.InstructionsButton;
import es.ucm.fdi.switchdash.logic.entities.SoundButton;

public class MainMenuState extends GameState
{
    private Sprite tapToPlay;

    private boolean alphaUp;

    public MainMenuState(Game game)
    {
        super(game);
    }

    @Override
    protected void init()
    {
        Background arrowsBackground = new Background(400, game.getGraphics());
        arrowsBackground.init();
//        ArrowsBG arrowsBackground = new ArrowsBG(0, -100, Assets.arrowsBackground, game.getGraphics());
        arrowsBackground.setAlpha(0.7f);
        addEntity(arrowsBackground);

        Sprite logo = new Sprite(0, 356, Assets.logo, game.getGraphics());
        addEntity(logo);

        tapToPlay = new Sprite(0,950,Assets.tapToPlay, game.getGraphics());
        addEntity(tapToPlay);

        SoundButton sound = new SoundButton(30, 30, game.getGraphics());
        InstructionsButton instructions = new InstructionsButton (900, 30, game.getGraphics(), game);

        for (Entity e: entities)
            e.setCenteredX();

        addEntity(sound);
        addEntity(instructions);
    }

    @Override
    public void update(float deltaTime){
       oscillateAlpha(tapToPlay, deltaTime);
        super.update(deltaTime);
    }

    private void oscillateAlpha(Sprite sprite, float deltaTime)
    {
        float alphaIncrement = 0.8f;
        float alpha = sprite.getAlpha();

        if(alphaUp)
            alpha += alphaIncrement * deltaTime;
        else
            alpha -= alphaIncrement * deltaTime;

        if(alpha < 0.0f || alpha > 1.0f)
            alphaUp = !alphaUp;

        sprite.setAlpha(alpha);
    }

    @Override
    public void render(float deltaTime)
    {
        game.getGraphics().clear(0x0000FF00);
        super.render(deltaTime);
    }
}
