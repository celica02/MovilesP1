package es.ucm.fdi.switchdash.logic.states;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.switchdash.engine.Entity;
import es.ucm.fdi.switchdash.engine.Game;
import es.ucm.fdi.switchdash.engine.GameState;
import es.ucm.fdi.switchdash.engine.Input;
import es.ucm.fdi.switchdash.logic.Assets;
import es.ucm.fdi.switchdash.engine.Sprite;
import es.ucm.fdi.switchdash.logic.entities.Background;
import es.ucm.fdi.switchdash.logic.entities.BlinkingEntity;
import es.ucm.fdi.switchdash.logic.entities.Flash;
import es.ucm.fdi.switchdash.logic.entities.InstructionsButton;
import es.ucm.fdi.switchdash.logic.entities.SoundButton;

public class MainMenuState extends GameState
{
    private Flash flash;
    private Background background;
    private SoundButton soundButton;
    private InstructionsButton instructionsButton;
    private List<Entity> ents;

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
        flash = new Flash(game.getGraphics());
        addEntity(flash);

        if(background == null){
            background = new Background(384, game.getGraphics());
            addEntity(background);
        }

        Sprite logo = new Sprite(0, 356, Assets.logo, game.getGraphics());
        addEntity(logo);

        BlinkingEntity tapToPlay = new BlinkingEntity(0,950,Assets.tapToPlay, game.getGraphics());
        addEntity(tapToPlay);

        soundButton = new SoundButton(30, 30, game.getGraphics());

        ents = new ArrayList<>();
        ents.add(background);
        ents.add(new Flash(game.getGraphics()));

        instructionsButton = new InstructionsButton (900, 30, game.getGraphics(), game, ents);


        for (Entity e: entities)
            e.setCenteredX();

        addEntity(soundButton);
        addEntity(instructionsButton);
    }

    @Override
    public void update(float deltaTime){

        super.update(deltaTime);
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

        if(touchEvents.size() > 0){
            boolean entityTouched = false;
            boolean touched = false;
            int i = 0;
            while(i < touchEvents.size() && !entityTouched){
                if(touchEvents.get(i).type == Input.TouchEvent.DOWN){
                    touched = true;
                    if(soundButton.inBounds(touchEvents.get(i).x, touchEvents.get(i).y) || instructionsButton.inBounds(touchEvents.get(i).x, touchEvents.get(i).y))
                        entityTouched = true;
                }//If comprobación tipo de evento
                i++;
            }//While de eventos


            if(!entityTouched && touched)
                game.setState(new InstructionsState(game, ents));
        }
    }
}
