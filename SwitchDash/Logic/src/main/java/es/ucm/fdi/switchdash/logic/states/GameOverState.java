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
import es.ucm.fdi.switchdash.logic.entities.Flash;
import es.ucm.fdi.switchdash.logic.entities.InstructionsButton;
import es.ucm.fdi.switchdash.logic.entities.SoundButton;
import es.ucm.fdi.switchdash.logic.entities.Text;

public class GameOverState extends GameState
{
   private Sprite flash;

    private Background background;
    private InstructionsButton instructionsButton;
    private SoundButton soundButton;
    private int _points;

    public GameOverState(Game game, int points)
    {
        super(game);
        _points = points;
    }

    public GameOverState(Game game, List<Entity> entities, int points)
    {
        super(game, entities);
        _points = points;
        background = (Background)entities.get(0);
        init();
    }


    @Override
    protected void init()
    {
        flash = new Sprite(0,0, game.getGraphics().getWidth(), game.getGraphics().getHeight(), Assets.white, Assets.white.getFullRect(), game.getGraphics());
        flash.setVisible(false);

        BlinkingEntity playAgain  = new BlinkingEntity(0, 1396, Assets.playAgain, game.getGraphics());
        addEntity(playAgain);

        Sprite gameOver  = new Sprite(0, 364, Assets.gameOver, game.getGraphics());
        addEntity(gameOver);

        Text points = new Text(0, 1000, Integer.toString(_points), game.getGraphics());
        Text pointsTxt = new Text(0, points.getPosY() + points.getHeight(), "points", game.getGraphics());

        addEntity(points);
        addEntity(pointsTxt);

        soundButton = new SoundButton(30, 30, game.getGraphics());


        List<Entity> ents = new ArrayList<>();
        ents.add(background);

        instructionsButton = new InstructionsButton (900, 30, game.getGraphics(), game, ents);


        for (Entity e: entities)
            e.setCenteredX();

        addEntity(soundButton);
        addEntity(instructionsButton);
    }

    @Override
    public void update(float deltaTime)
    {
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

        if(touchEvents.size() > 0){ //Mira si se ha tocado la pantalla
            boolean entityTouched = false;
            boolean touched = false;
            int i = 0;
            while(i < touchEvents.size() && !entityTouched){
                if(touchEvents.get(i).type == Input.TouchEvent.DOWN){
                    touched = true;
                    if(instructionsButton.inBounds(touchEvents.get(i).x, touchEvents.get(i).y) || soundButton.inBounds(touchEvents.get(i).x, touchEvents.get(i).y))
                        entityTouched = true;
                }//If comprobación tipo de evento
                i++;
            }//While de eventos
            if(!entityTouched &&touched)
                game.setState(new PlayState(game));
        }//If si hay eventos de haber tocado
    }//handleInput
}
