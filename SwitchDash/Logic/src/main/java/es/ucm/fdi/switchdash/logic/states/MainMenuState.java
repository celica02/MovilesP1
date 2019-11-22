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
    Sprite tapToPlay;
    float tapToPlayA = 1, ttpChange = 0.5f;
    public MainMenuState(Game game)
    {
        super(game);
    }

    @Override
    protected void init()
    {
        Background arrowsBackground = new Background(game.getGraphics());
        arrowsBackground.init();
//        ArrowsBG arrowsBackground = new ArrowsBG(0, -100, Assets.arrowsBackground, game.getGraphics());
        arrowsBackground.setAlpha(0.7f);
        addEntity(arrowsBackground);

        Sprite logo = new Sprite(0, 356, Assets.logo, game.getGraphics());
        addEntity(logo);

        tapToPlay = new Sprite(0,950,Assets.tapToPlay, game.getGraphics());
        addEntity(tapToPlay);

        SoundButton sound = new SoundButton(100, game.getGraphics());
        addEntity(sound);
        InstructionsButton instructions= new InstructionsButton (900, game.getGraphics(), game);
        addEntity(instructions);


        for (Entity e: entities)
            e.setCenteredX();
    }

    @Override
    public void update(float deltaTime){
        if(((tapToPlay.getAlpha() <= 0.15) && (ttpChange > 0)) || ((tapToPlay.getAlpha() >= 0.85) && (ttpChange < 0)))
            ttpChange = -ttpChange;
        System.out.println(tapToPlayA);
        tapToPlayA = tapToPlayA - ttpChange *deltaTime;
        tapToPlay.setAlpha(tapToPlayA);
        super.update(deltaTime);
    }
    @Override
    public void render(float deltaTime)
    {
        game.getGraphics().clear(0x0000FF00);
        super.render(deltaTime);
    }
}
