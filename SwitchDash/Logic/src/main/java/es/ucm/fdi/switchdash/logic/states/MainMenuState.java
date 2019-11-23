package es.ucm.fdi.switchdash.logic.states;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.switchdash.engine.Entity;
import es.ucm.fdi.switchdash.engine.Game;
import es.ucm.fdi.switchdash.engine.GameState;
import es.ucm.fdi.switchdash.logic.Assets;
import es.ucm.fdi.switchdash.engine.Sprite;
import es.ucm.fdi.switchdash.logic.entities.Background;
import es.ucm.fdi.switchdash.logic.entities.BlinkingEntity;
import es.ucm.fdi.switchdash.logic.entities.InstructionsButton;
import es.ucm.fdi.switchdash.logic.entities.SoundButton;

public class MainMenuState extends GameState
{


    Background background;

    public MainMenuState(Game game)
    {
        super(game);
    }
    public MainMenuState(Game game, List<Entity> entities)
    {
        super(game, entities);
        background = (Background)entities.get(0);

        init();
    }

    @Override
    protected void init()
    {
        if(background == null){
            background = new Background(game.getGraphics());
            addEntity(background);
        }

        Sprite logo = new Sprite(0, 356, Assets.logo, game.getGraphics());
        addEntity(logo);

        BlinkingEntity tapToPlay = new BlinkingEntity(0,950,Assets.tapToPlay, game.getGraphics());
        addEntity(tapToPlay);

        SoundButton sound = new SoundButton(30, 30, game.getGraphics());

        List<Entity> ents = new ArrayList<>();
        ents.add(background);

        InstructionsButton instructions = new InstructionsButton (900, 30, game.getGraphics(), game, ents);

        for (Entity e: entities)
            e.setCenteredX();

        addEntity(sound);
        addEntity(instructions);
    }

    @Override
    public void update(float deltaTime){

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
        game.getGraphics().clear(background.getColor());
        super.render(deltaTime);
    }
}
