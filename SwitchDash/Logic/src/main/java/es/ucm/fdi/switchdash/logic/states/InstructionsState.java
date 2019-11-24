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
import es.ucm.fdi.switchdash.logic.entities.BlinkingEntity;
import es.ucm.fdi.switchdash.logic.entities.MainMenuButton;

public class InstructionsState extends GameState {

    private Background background;
    private MainMenuButton mainMenuButton;

    public InstructionsState(Game game, List<Entity> entities) {
        super(game, entities);
        background = (Background)entities.get(0);

        init();
    }

    @Override
    protected void init() {

        Sprite title = new Sprite(0, 290, Assets.howToPlay, game.getGraphics());
        addEntity(title);

        Sprite instructions = new Sprite(0, 768, Assets.instructions, game.getGraphics());
        addEntity(instructions);

        List<Entity> ents = new ArrayList<>();
        ents.add(background);

        mainMenuButton = new MainMenuButton(900,30, game.getGraphics(), game, ents);

        BlinkingEntity tapToPlay= new BlinkingEntity(0, 1464, Assets.tapToPlay, game.getGraphics());
        addEntity(tapToPlay);

        for (Entity e: entities)
            e.setCenteredX();

        addEntity(mainMenuButton);
    }//init

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
                    if(mainMenuButton.inBounds(touchEvents.get(i).x, touchEvents.get(i).y))
                        entityTouched = true;
                }//If comprobación tipo de evento
                i++;
            }//While de eventos


            if(!entityTouched &&touched)
                game.setState(new PlayState(game));
        }//If si hay eventos de haber tocado
    }//handleInput
}//clase
